<template>
    <div class="wrapper">
       <Head>商家信息</Head>
        <div class="business-info-box">
            <!--商家logo部分-->
            <div class="business-logo">
                <img :src="business.businessImg">
            </div>
            <!-- 商家信息部分 -->
            <div class="business-info">
                <h1>{{ business.businessName }}</h1>
                <p>起送费&yen;{{ business.starPrice }} | 配送费&yen;{{ business.deliveryPrice }}</p>
                <p>{{ business.businessExplain }}</p>
            </div>
            
            <!--食品列表部分-->
            <ul class="food">
                <li v-for="food in foodList" :key="food.foodId">
                    <div class="food-left">
                        <img :src="food.foodImg">
                        <div class="food-left-info">
                            <h3>{{ food.foodName }}</h3>
                            <p>{{ food.foodExplain }}</p>
                            <p>&yen;{{ food.foodPrice }}</p>
                        </div>
                    </div>
                    <div class="food-right">
                        <div>
                            <i 
                                class="fa fa-minus-circle" 
                                @click="minus(food)" 
                                :style="{'color': food.quantity>0?'#0097EF':'#999'}"></i>
                        </div>
                        <p><span class="count">{{ food.quantity }}</span></p>
                        <div>
                            <i class="fa fa-plus-circle" @click="add(food)"></i>
                        </div>
                    </div>
                </li>
                
            </ul>
        </div>
        
        <div style="height: 14vw;"></div>
        <!-- 结算部分 -->
        <div class="cart">
            <div class="cart-left">
                <div class="cart-left-icon">
                    <i class="fa fa-shopping-cart"></i>
                    <div class="cart-left-icon-quantity">{{ totalCount }}</div>
                </div>
                <div class="cart-left-info">
                    <p>
                        &#165;<span class="total-price">{{ totalPrice }}</span>
                    </p>
                    <p>另需配送费3元</p>
                </div>
            </div>
            <div class="cart-right" @click="goOrder">
                    去结算
            </div>
        </div>
    </div>
</template>

<script setup>
    import Head from '@/components/head.vue'
    import { ref, inject, computed } from 'vue'
    import { useRoute, useRouter } from 'vue-router';
    import { getSessionStorage } from '@/common.js';
    import qs from 'qs';

    const business = ref({});
    const foodList = ref([]);
    const axios = inject('axios')
    const route = useRoute()
    const router = useRouter()
    const user = ref(getSessionStorage("user"));
    //准备购物车中的数据
    let cart = {
        foodId: 0,
        businessId: 0,
        userId: '',
        quantity: 0
    }
    
 
    async function init() {
        //根据id查询商家信息
        let response = await axios.get("/BusinessController/findBusinessById?businessId=" + route.query.businessId)
        business.value = response.data.data
        //根据businessId 查询food信息
        response = await axios.get("/FoodController/findFoodByBusinessId?businessId=" + route.query.businessId)
        foodList.value = response.data.data
        //为每个商品添加quantity属性
        foodList.value.forEach(food => {
            food.quantity = 0;
        });
        //根据用户id和商家id查询购物车信息
        //判断用户是否登录
        if(user.value) {
            response = await axios.post("/CartController/findCartByUserIdAndBusinessId",
            qs.stringify({
                userId: user.value.userId,
                businessId: business.value.businessId
            }));
            console.log('response.data :>> ', response.data);
            //回显购物车商品数量
            // response.data.data.forEach((cart) => {
            //     foodList.value.forEach(food => {
            //         if(cart.foodId === food.foodId) {
            //             food.quantity = cart.quantity;
            //         }
            //     })
            // })
            response.data.data.forEach((cart) => {
                foodList.value.find(food => cart.foodId === food.foodId).quantity = cart.quantity;
            })
        }
        
    }

    init();
    //计算属性求总数量
    let totalCount = computed(() => {
        let count = 0;
        foodList.value.forEach(food => count += food.quantity);
        return count;
    })
    //计算属性求总价格
    let totalPrice = computed(() => {
        let price = 0;
        foodList.value.forEach(food => price += food.quantity * food.foodPrice);
        return price;
    })
   
    //添加购物车
    function add(food) {
        //用户是否登录
        if(!user.value) {
            //跳转到登录页面
            router.push("/login");
            return;
        }else {
           food.quantity ++ ; 
        }
        cart.foodId = food.foodId;
        cart.businessId = business.value.businessId
        cart.quantity = food.quantity;
        cart.userId = user.value.userId
        //判断购物车中是否包含当前商品
        if(food.quantity == 1) {
            addCart();
        }else {
            updateCart();
        }
    }
    //购物车减少商品
    function minus(food) {
        if(food.quantity > 0) {
            food.quantity -- ; 
        }
        cart.foodId = food.foodId;
        cart.businessId = business.value.businessId
        cart.quantity = food.quantity;
        cart.userId = user.value.userId
        if(food.quantity == 0) {
            //删除购物车中商品
            deleteCart()
        }else {
            //修改购物车中商品
            updateCart()
        }
        
    }
    //添加购物车
    function addCart() {
        //向cart表中添加数据
            //发送异步请求
            axios.post('/CartController/saveCart', qs.stringify(cart))
            .then((response) => {
                if(response.data.code != 200) {
                    alert("添加失败")
                }
            })
    }
    //更新购物车
    function updateCart() {
        //更新cart表中的数据
        axios.post('/CartController/updateCart', qs.stringify(cart))
            .then((response) => {
                if(response.data.code != 200) {
                    alert("更新失败")
                }
            })
    }
    //删除购物车
    function deleteCart() {
        axios.post('/CartController/deleteCart', qs.stringify(cart))
            .then((response) => {
                if(response.data.code != 200) {
                    alert("删除失败")
                }
            })
    }
    

    //去确认订单页面
    function goOrder() {
        if(totalCount.value <= 0) {
            alert("购物车中没有商品，不能结算！");
            return;
        }
        router.push({
            path: "/order",
            query: {
                businessId: business.value.businessId,
                totalPrice: totalPrice.value 
            }
        }, )
    }
   
</script>

<style scoped src="../assets/css/businessInfo.css">
   
</style>