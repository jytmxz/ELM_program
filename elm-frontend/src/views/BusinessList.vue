<template>
    <div class="wrapper">
			
			<Head>商家列表</Head>
			
			<!--商家列表-->
			<ul class="business-list">
				<li v-for="business in businessList" :key="business.businessId" @click="goBusinessInfo(business.businessId)">
					<div class="business-img">
						<img :src="business.businessImg" />
						<div class="business-img-quantity" v-show="business.cartTotalCount">{{ business.cartTotalCount }}</div>
					</div>
					<div class="business-info">
						<h3>{{ business.businessName }}</h3>
            			<p>起送费&yen;{{ business.starPrice }} | 配送费&yen;{{ business.deliveryPrice }}</p>
            			<p>{{ business.businessExplain }}</p>
					</div>
				</li>
			</ul>
			<div style="height: 14vw;"></div>
			<Foot></Foot>
		</div>
</template>

<script setup>
import { useRoute,useRouter } from 'vue-router';
import {ref, inject} from 'vue'
import { getSessionStorage } from '@/common.js';
import Foot from '@/components/foot.vue'
import Head from '@/components/head.vue'
const axios = inject("axios")

//商家列表
const businessList = ref([]);
const route = useRoute();
const router = useRouter();
const user = getSessionStorage("user");
async function init() {
    //向服务器发送异步请求，获取商家列表
    let response = await axios.get("/BusinessController/findBusinessByOrderTypeId?orderTypeId=" + route.query.orderTypeId)
	businessList.value = response.data.data
	//每个商家对应购物车中商品数量
	if(user) {
		response = await axios.get("/CartController/findCartSumByUserIdGroupBusinessId?userId=" + user.userId);
		//为商家添加购物车数量属性
		businessList.value.forEach(business => business.cartTotalCount = response.data.data[business.businessId]);
	}
		
}
init()

//跳转商家信息页面
function goBusinessInfo(businessId) {
    router.push({
        path: "/businessInfo",
        query: {
			businessId
		}
    })
}
</script>

<style scoped src="../assets/css/businessList.css">

</style>