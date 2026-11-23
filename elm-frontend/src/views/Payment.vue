<template>
    <div class="wrapper">
			<!--头部部分-->
			<Head>
				在线支付
            </Head>
			<!--支付部分-->
			<div class="payment-main">
				<h3>订单信息：</h3>
				<div class="order-info">
                	<p>
                   		{{orderInfo.business.businessName}}
                    	<i class="fa fa-caret-up" id="showBtn"></i>
                	</p>
	                <p>&#165;{{orderInfo.orderTotal}}</p>
	            </div>
	             <!-- 订单明细部分 -->
	            <ul class="order-detailet" id="detailetBox" v-for="food in orderInfo.foodList">
	                <li>
	                    <p>{{ food.foodName}} x {{food.quantity}}</p>
	                    <p>&#165;{{food.foodPrice}}</p>
	                </li>
	            </ul>
				<ul class="order-detailet" id="detailetBox">
				<li>
	                    <p>配送费</p>
	                    <p>&#165;{{orderInfo.business.deliveryPrice}}</p>
	                </li>
	            </ul>
	             <!-- 支付方式部分 -->
	            <ul class="payment-type">
	                <li>
	                    <img src="../assets/img/alipay.png">
	                    <i class="fa fa-check-circle"></i>
	                </li>
	                <li>
	                    <img src="../assets/img/wechat.png">
	                    <i class="fa fa-check-circle" style="display: none;"></i>
	                </li>
	            </ul>
	            <div class="payment-button" >
	                <button @click="goOrderList()">确认支付</button>
	            </div>
			</div>
			<Foot></Foot>
		</div>
</template>

<script setup>
import qs from "qs";
import Foot from '@/components/foot.vue';
import Head from '@/components/head.vue'
    import { ref, inject, computed } from 'vue'
    import { useRoute, useRouter } from 'vue-router';
    import { getSessionStorage } from '@/common.js';

	
 

    const axios = inject('axios')
    const route = useRoute()
	const router = useRouter()
    const user = ref(getSessionStorage("user"));
	const orderInfo = ref([]);
	
	console.log("OrderId",route.query.orderId);
	let orderId = route.query.orderId;
	
	axios.post(
      "/OrdersController/findOrderById",
      qs.stringify({
        orderId:orderId,
      })
    )
    .then((response) => {
		console.log("根据OrderId查询",response)
		orderInfo.value = response.data.data
		console.log(orderInfo.value)
    })


function goOrderList(){
router.push({
	path:"/OrderList",

})
}






</script>

<style scoped src="../assets/css/payment.css">

</style>