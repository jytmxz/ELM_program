<template>
    <div class="wrapper">
			<header>确认订单</header>
			
			<!-- 订单信息部分 -->
            <div class="order-info">
                <h5>订单配送至：</h5>
                <div class="order-info-address">
                    <p>沈阳市浑南区智慧四街1-121号</p>
                    <i class="fa fa-angle-right"></i>
                </div>
                <p>张先生  13656785432</p>
            </div>
            <!--商家名称-->
            <h3>{{ business.businessName }}</h3>
            
             <!-- 订单明细部分 -->
            <ul class="order-detailed">
                <li v-for="cart in cartList" :key="cart.cartId">
                    <div class="order-detailed-left">
                        <img :src="cart.food.foodImg">
                        <p>{{ cart.food.foodName }} x <span class="count">{{ cart.quantity }}</span></p>
                    </div>
                    <p>&#165; <span class="price">{{ cart.food.foodPrice }}</span></p>
                </li>
            </ul>
            <div class="order-deliveryfee">
                <p>配送费</p>
                <p>&#165;<span class="fee">{{ business.deliveryPrice }}</span></p>
            </div>
			
			<!-- 合计部分 -->
            <div class="total">
                <div class="total-left">
                    &#165;<span class="total-price">{{ totalPrice }}</span>
                </div>
                <div class="total-right" @click="goPayment()">
                    	去支付
                </div>
            </div>
		</div>
</template>

<script setup>
import { getSessionStorage, getCurDate } from '@/common';
import { useRoute, useRouter } from 'vue-router';
import { inject, ref } from 'vue';
import  qs from 'qs';

const route = useRoute();
const router = useRouter();
const axios = inject("axios")
const user = getSessionStorage("user");
const business = ref({})
const cartList = ref([])
const totalPrice = ref(0);


async function init() {
    let response = await axios.post("/CartController/findList",
            qs.stringify({
                userId: user.userId,
                businessId: route.query.businessId
            }));
    const data =  response.data.data;
    if(data.length > 0) {
        business.value = data[0].business;
        totalPrice.value = parseFloat(route.query.totalPrice) + parseFloat(business.value.deliveryPrice);
        data.forEach((item, index) => {
            cartList.value[index] = item;
        })
    }
    
}
init();
let orderId = 0;
//跳转支付页面
async function goPayment() {
    //准备数据
    let data = {
        userId:user.userId,
        businessId: business.value.businessId,
        orderTotal: totalPrice.value,
        curDate: getCurDate()
    }
    //提交请求
    let response = await axios.post("/OrdersController/saveOrders", qs.stringify(data))
    if(response.data.code != 200) {
        alert("结算失败");
        return;
    }else {
        console.log('response.data.data :>> ', response.data.data);
        orderId = response.data.data
    }
    //路由跳转
    router.replace({
        path:"/payment",
        query: {
            orderId:orderId
        }
    
    })
}
</script>

<style scoped src="../assets/css/order.css">

</style>