<template>
<div class="wrapper">
			<header>
				<div class="header-icon" onclick="history.back()">
					<i class="fa fa-chevron-left"></i>
				</div>
				
				<p>我的订单</p>
			</header>
			<!--订单列表部分-->
			<div class="order-wrapper">
		
				<!--未支付订单列表-->
				<div class="order-list-box">
					
					<h3>未支付订单信息：</h3>
					<ul  class="order" v-for="(unpaid,index) in orderUnPaid" :key="index">
						<li>
							<div class="order-info">
		                        <p>
		                          	  {{unpaid.business.businessName}}
		                            <i class="fa fa-caret-up"></i>
		                        </p>
		                        <div class="order-info-right">
		                            <p>&#165;{{unpaid.orderTotal}}</p>
		                            <div class="order-info-right-icon">去支付</div>
		                        </div>
		                    </div>
		                    <ul class="order-detailet">
		                        <li>
		                            <p>纯肉鲜肉（水饺） x 2</p>
		                            <p>&#165;15</p>
                                </li>
		                        <li>
		                            <p>配送费</p>
		                            <p>&#165;3</p>
		                        </li>
		                    </ul>
						</li>
					</ul>
				</div>
				
				
				<!--已支付订单-->
				<div class="order-list-box">
					
					<h3>已支付订单信息：</h3>
					<ul  class="order"  v-for="(paid,index) in orderPaid" :key="index">
						<li>
							<div class="order-info">
		                        <p>
		                          	  {{paid.business.businessName}}
		                            <i class="fa fa-caret-up"></i>
		                        </p>
		                        <div class="order-info-right">
		                            <p>&#165;{{ paid.orderTotal }}</p>
		                        </div>
		                    </div>
		                    <ul class="order-detailet">
		                        <li>
		                            <p>纯肉鲜肉（水饺） x 2</p>
		                            <p>&#165;15</p>
		                        </li>
		                        <li>
		                            <p>玉米鲜肉（水饺） x 1</p>
		                            <p>&#165;16</p>
		                        </li>
		                        <li>
		                            <p>配送费</p>
		                            <p>&#165;{{ paid.business.deliveryPrice }}</p>
		                        </li>
		                    </ul>
						</li>
					</ul>
				</div>
			</div>
</div>
</template>

<script setup>

import qs from "qs";
import { useRouter, useRoute } from "vue-router";
import { ref, inject } from "vue";
import { getSessionStorage } from "../common.js";

const axios = inject("axios");
const router = useRouter();
const route = useRoute();

const user = getSessionStorage("user");
const orderUnPaid = ref([]);
const orderPaid = ref([]);

function init() {
  axios
    .post(
      "OrdersController/findOrderByUserId",
      qs.stringify({
        userId: user.userId,
      })
    )
    .then((response) => {
      let result = response.data;
      for (let orders of result) {
        if(orders.orderState==0){
            orderUnPaid.value.push(orders);
        }
        if(orders.orderState==1){
            orderPaid.value.push(orders);
        }
      }
    })
}
init();
</script>

<style scoped>
/*总容器*/
.wrapper {
	width: 100%;
	height: 100%;
}
/*header部分*/
.wrapper header {
	width: 100%;
	height: 12vw;
	background: #0097FF;
	font-size: 4.8vw;
	color: #FFFFFF;
	
	display: flex;
	justify-content: center;
	align-items: center;
	
	/*固定定位*/
	position: fixed;
	top: 0;
	left: 0;
}

.wrapper header .header-icon {
	position: absolute;
	top: 3vw;
	left: 7vw;
}

/*订单列表部分*/
.wrapper .order-wrapper {
	margin-top: 12vw ;
}

.wrapper .order-wrapper .order-list-box {
	padding: 5vw;
}

.wrapper .order-list-box h3 {
	color: #999;
	font-size: 4vw;
	font-weight: 400;
}

.wrapper .order-list-box .order {
	margin-top: 3vw;
	margin-bottom: 4vw;
}

.order-list-box .order .order-info {
	
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.order-list-box .order .order-info p {
	font-size: 4vw;
	color: #666;
}
.order-info .order-info-right {
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.order-info .order-info-right .order-info-right-icon {
	background-color:  #f90;
	color: #FFFFFF;
	border-radius: 3px;
	margin-left: 2vw;
}

/*订单明细*/
.order .order-detailet {
	margin-top: 3vw;
}
.order .order-detailet li {
	
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 2vw;
	font-size: 3.5vw;
	color: #666666;
}
</style>