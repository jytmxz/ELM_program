<template>
    <div class="wrapper">
			<!--头部区域-->
			<header>
				<div class="icon-location-box">
			      <div class="icon-location"></div>
			    </div>
				<div class="location-text">
					沈阳市规划大厦<i class="fa fa-caret-down"></i>
				</div>
			</header>
            <div class="search-fixed-top" id="fixedBox">
                <!-- 搜索框部分中间的白框 -->
                <div class="search-box">
                    <i class="fa fa-search"></i>搜索饿了么商家、商品名称
                </div>
            </div>
            
            <!--食品分类部分-->
            <ul class="foodtype">
                <li v-for="type in orderTypeList" :key="type.orderTypeId" @click="goBusinessList(type.orderTypeId)">
                    <img :src="type.orderTypeImg" alt="">
                    <p>{{ type.orderTypeName }}</p>
                </li>
            </ul>
            <!--横幅广告部分-->
            <div class="banner">
                <h3>品质套餐</h3>
                <p>搭配齐全吃得好</p>
                <a>立即抢购 &gt;</a>
            </div>
            
            <!--超级会员-->
            <div class="supermember">
            	<div class="supermember-left">
            		<img src="../assets/img/super_member.jpg" />
            		<h3>超级会员</h3>
            		<p>&#8226; 每月享超值权益</p>
            	</div>
            	<div class="supermember-right">
            		立即开通 &gt;
            	</div>
            </div>
            <!--商家推荐-->
            <div class="recommend">
            	<div class="recommend-line"></div>
            	<p>商家推荐</p>
            	<div class="recommend-line"></div>
            </div>
            
            <!--推荐方式部分-->
            <ul class="recommendtype">
            	<li>综合排序<i class="fa fa-caret-down"></i></li>
            	<li>距离最近</li>
            	<li>销量最高</li>
            	<li>筛选<i class="fa fa-filter"></i></li>
            </ul>
            <!--推荐商家列表部分-->
            <ul class="business">
            	<li  onclick="location.href='businessInfo.html'">
            		<img src="../assets/img/sj01.jpg"/>
	            	<div class="business-info">
	            		<div class="business-info-h">
            				<h3>万家饺子（软件园E18店）</h3>
            				<div class="business-info-like">&#8226;</div>
            			</div>
            			<div class="business-info-star">
            				<div class="business-info-star-left">
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<p>4.9 月售345单</p>
            				</div>
            				<div class="business-info-star-right">
            					蜂鸟专送
            				</div>
            			</div>
            			<div class="business-info-delivery">
            				<p>&#165;15起送 | &#165;3配送</p>
            				<p>3.22km | 30分钟</p>
            			</div>
            			<div class="business-info-explain">
            				<div>各种饺子</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon">新</div>
            					<p>饿了么新用户首单立减9元</p>
            				</div>
            				<div class="business-info-promotion-right">
            					<p>2个活动</p>
            					<i class="fa fa-caret-down"></i>
            				</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon" style="background-color: #F1884F;">特</div>
            					<p>特价商品5元起</p>
            				</div>
            			</div>
	            	</div>
            	</li>
            	<li>
            		<img src="../assets/img/sj02.jpg"/>
	            	<div class="business-info">
	            		<div class="business-info-h">
            				<h3>万家饺子（软件园E18店）</h3>
            				<div class="business-info-like">&#8226;</div>
            			</div>
            			<div class="business-info-star">
            				<div class="business-info-star-left">
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<p>4.9 月售345单</p>
            				</div>
            				<div class="business-info-star-right">
            					蜂鸟专送
            				</div>
            			</div>
            			<div class="business-info-delivery">
            				<p>&#165;15起送 | &#165;3配送</p>
            				<p>3.22km | 30分钟</p>
            			</div>
            			<div class="business-info-explain">
            				<div>各种饺子</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon">新</div>
            					<p>饿了么新用户首单立减9元</p>
            				</div>
            				<div class="business-info-promotion-right">
            					<p>2个活动</p>
            					<i class="fa fa-caret-down"></i>
            				</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon" style="background-color: #F1884F;">特</div>
            					<p>特价商品5元起</p>
            				</div>
            			</div>
	            	</div>
            	</li>
            	<li>
            		<img src="../assets/img/sj03.jpg"/>
	            	<div class="business-info">
	            		<div class="business-info-h">
            				<h3>万家饺子（软件园E18店）</h3>
            				<div class="business-info-like">&#8226;</div>
            			</div>
            			<div class="business-info-star">
            				<div class="business-info-star-left">
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<p>4.9 月售345单</p>
            				</div>
            				<div class="business-info-star-right">
            					蜂鸟专送
            				</div>
            			</div>
            			<div class="business-info-delivery">
            				<p>&#165;15起送 | &#165;3配送</p>
            				<p>3.22km | 30分钟</p>
            			</div>
            			<div class="business-info-explain">
            				<div>各种饺子</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon">新</div>
            					<p>饿了么新用户首单立减9元</p>
            				</div>
            				<div class="business-info-promotion-right">
            					<p>2个活动</p>
            					<i class="fa fa-caret-down"></i>
            				</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon" style="background-color: #F1884F;">特</div>
            					<p>特价商品5元起</p>
            				</div>
            			</div>
	            	</div>
            	</li>
            	<li>
            		<img src="../assets/img/sj04.jpg"/>
	            	<div class="business-info">
	            		<div class="business-info-h">
            				<h3>万家饺子（软件园E18店）</h3>
            				<div class="business-info-like">&#8226;</div>
            			</div>
            			<div class="business-info-star">
            				<div class="business-info-star-left">
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<p>4.9 月售345单</p>
            				</div>
            				<div class="business-info-star-right">
            					蜂鸟专送
            				</div>
            			</div>
            			<div class="business-info-delivery">
            				<p>&#165;15起送 | &#165;3配送</p>
            				<p>3.22km | 30分钟</p>
            			</div>
            			<div class="business-info-explain">
            				<div>各种饺子</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon">新</div>
            					<p>饿了么新用户首单立减9元</p>
            				</div>
            				<div class="business-info-promotion-right">
            					<p>2个活动</p>
            					<i class="fa fa-caret-down"></i>
            				</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon" style="background-color: #F1884F;">特</div>
            					<p>特价商品5元起</p>
            				</div>
            			</div>
	            	</div>
            	</li>
            	<li onclick="location.href='businessInfo.html'">
            		<img src="../assets/img/sj05.jpg"/>
	            	<div class="business-info">
	            		<div class="business-info-h">
            				<h3>万家饺子（软件园E18店）</h3>
            				<div class="business-info-like">&#8226;</div>
            			</div>
            			<div class="business-info-star">
            				<div class="business-info-star-left">
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<i class="fa fa-star"></i>
            					<p>4.9 月售345单</p>
            				</div>
            				<div class="business-info-star-right">
            					蜂鸟专送
            				</div>
            			</div>
            			<div class="business-info-delivery">
            				<p>&#165;15起送 | &#165;3配送</p>
            				<p>3.22km | 30分钟</p>
            			</div>
            			<div class="business-info-explain">
            				<div>各种饺子</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon">新</div>
            					<p>饿了么新用户首单立减9元</p>
            				</div>
            				<div class="business-info-promotion-right">
            					<p>2个活动</p>
            					<i class="fa fa-caret-down"></i>
            				</div>
            			</div>
            			<div class="business-info-promotion">
            				<div class="business-info-promotion-left">
            					<div class="business-info-promotion-left-icon" style="background-color: #F1884F;">特</div>
            					<p>特价商品5元起</p>
            				</div>
            			</div>
	            	</div>
            	</li>
            </ul>
            
            <div style="height: 14vw;"></div>
            
            <Foot></Foot>
          
		</div>
</template>

<script setup>

confirm()

import { useRouter } from 'vue-router';
import Foot from '../components/foot.vue'
import { ref, inject } from 'vue';
const router = useRouter()
const axios = inject("axios");
//分类列表
const orderTypeList = ref([]);
//向服务器发送异步请求，获取分类列表
axios.get("/OrderTypeController/findAll")
    .then((response) => {
        orderTypeList.value = response.data.data
    })

//跳转到商家列表页面
function goBusinessList(orderTypeId) {
    router.push({
        path: '/businessList',
        query: {
            orderTypeId
        }
    })
}
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
	background-color: #0097FF;
	
	font-size: 4.5vw;
	color: #FFFFFF;
	font-weight: 700;
	padding: 3vw;
	
	display: flex;
	align-items: center;
	
	position: fixed;
	left: 0;
	top: 0;
	
}
.wrapper header .icon-location-box {
	width: 3.5vw;
	height: 3.5vw;
	margin-right: 2vw;
}
.wrapper header i {
	margin-left: 2vw;
}

/*search部分*/
.wrapper .search-fixed-top {
	width: 100%;
	height: 13vw;
	background-color: #0097FF;
	
	display: flex;
	justify-content: center;
	align-items: center;
	
	position: fixed;
	left: 0;
	top: 12vw;
}
.wrapper .search-fixed-top .search-box {
	width: 90%;
	height: 9vw;
	background-color: #FFFFFF;
	font-size: 3.5vw;
	color: #AAAAAA;
	border-radius: 2px ;
	
	display: flex;
	justify-content: center;
	align-items: center;
}
.wrapper .search-fixed-top .search-box i {
	margin-right: 2vw;
}
/*食品分类*/
.wrapper .foodtype {
	width: 100%;
	height: 48vw;
	
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
	padding: 3vw;

	margin-top: 25vw;
}
.wrapper .foodtype li {
	width: 18vw;
	height: 20vw;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.wrapper .foodtype li p {
	font-size: 3.2vw;
	color: #666666;
}
.wrapper .foodtype li img {
	width: 10.3vw;
	height: 10.3vw;
}
/*横幅广告区域*/
.wrapper .banner {
	width: 95%;
	height: 29vw;
	background-color: #EEEEEE;
	margin: 0 auto;
	padding: 3vw 6vw;
	box-shadow: 1px 1px 1px rgba(50,50,50,0.2);
	
	background-image: url(/src/assets/img/index_banner.png);
	background-repeat: no-repeat;
	background-size: 40%;
	background-position: center right;
}
.wrapper .banner h3 {
	font-size: 4.2vw;
	margin-bottom: 1.5vw;
}
.wrapper .banner p {
	font-size: 3.4vw;
	color: #666666;
	margin-bottom: 2.5vw;
}
.wrapper .banner a {
	font-size: 3vw;
	color: #C79060;
}

/*超级会员部分*/
.wrapper .supermember {
	width: 95%;
	height: 11.5vw;
	background-color: #FEEDC1;
	margin: 1vw auto;
	border-radius: 1vw;
	
	display: flex;
	justify-content: space-between;
	align-items: center;
	
	padding: 0 6vw;
}
.wrapper .supermember .supermember-left {
	display: flex;
	align-items: center;
}
.wrapper .supermember .supermember-left img {
	width: 6vw;
	height: 6vw;
	margin-right: 2vw;
}
.wrapper .supermember .supermember-left h3 {
	font-size: 4vw;
	margin-right: 2vw;
}
.wrapper .supermember .supermember-left p {
	font-size: 3vw;
	color: #666666;
}
.wrapper .supermember .supermember-right {
	font-size: 3vw;
}
/*商家推荐部分*/
.wrapper .recommend {
	width: 100%;
	height: 14vw;
	
	display: flex;
	justify-content: center;
	align-items: center;
}
.wrapper .recommend .recommend-line {
	width: 10vw;
	height: 0.2vw;
	background-color: #888;
}
.wrapper .recommend p {
	font-size: 4vw;
	margin: 0 4vw;
}

/*推荐方式部分*/
.wrapper .recommendtype {
	width: 100%;
	height: 5vw;
	
	display: flex;
	justify-content: space-around;
	align-items: center;
	
	margin-bottom: 3vw;
}
.wrapper .recommendtype li {
	font-size: 3.5vw;
	color: #555555;
}
.wrapper .recommendtype li i {
	margin-left: 1vw;
}

/*商家推荐列表*/
.wrapper .business {
	width: 100%;
	
}
.business li {
	width: 95%;
	display: flex;
	border-bottom: 1px solid #AAAAAA;
	margin: 0 auto;
	padding: 3vw 0 5vw 0;
}
.business li img {
	width: 18vw;
	height: 18vw;
}

.business li .business-info {
	width: 100%;
	margin-left: 3vw;
}

.business-info .business-info-h {
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.business-info .business-info-h h3 {
	font-size: 4vw;
	color: #333333;
}
.business-info .business-info-h .business-info-like {
	width: 1.6vw;
	height: 3.4vw;
	background-color: #666666;
	color: #FFFFFF;
	font-size: 4vw;
	border-radius: 0.2vw;
	margin-right: 4vw;
	
	display: flex;
	justify-content: center;
	align-items: center;
}
.business-info .business-info-star {
	
	margin: 2vw 0;
	
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.business-info .business-info-star .business-info-star-left {
	display: flex;
	align-items: center;
}

.business-info .business-info-star .business-info-star-left i {
	font-size: 0.5vw;
	color: #FEC80E;
	margin-right: 0.5vw;
}
.business-info .business-info-star .business-info-star-left p {
	font-size: 1vw;
	color: #666666;
	margin-left: 2vw;
}
.business-info .business-info-star .business-info-star-right {
	font-size: 2.4px;
	background-color: #0097FF;
	color: #FFFFFF;
	border-radius: 0.7vw;
	
	padding: 0.5vw;
}
.business-info .business-info-delivery {
	
	display: flex;
	justify-content: space-between;
	align-items: center;
	
	font-size: 3.1vw;
	color: #666666;
	margin: 2vw 0;
}
.business-info .business-info-explain {
	display: flex;
	align-items: center;
	margin: 2vw 0;
}
.business-info .business-info-explain div{
	
	font-size: 3.1vw;
	color: #666666;
	border: 1px solid #AAAAAA;
	border-radius: 0.5vw;
	padding: 0.3vw;
	
}

.business-info .business-info-promotion {
	display: flex;
	justify-content: space-between;
	align-items: center;
	
	font-size: 3.1vw;
	color: #666666;
}

.business-info .business-info-promotion .business-info-promotion-left {
	display: flex;
	align-items: center;
}
.business-info-promotion .business-info-promotion-left .business-info-promotion-left-icon {
	width: 4vw;
	height: 4vw;
	font-size: 3vw;
	color: #FFFFFF;
	background-color: #70BC46;
	border-radius: 1vw;
	margin: 2vw 3vw 2vw 0;
	
	display: flex;
	justify-content: center;
	align-items: center;
}

.business-info .business-info-promotion .business-info-promotion-right {
	display: flex;
	align-items: center;
	color: #AAAAAA;
}
.business-info .business-info-promotion .business-info-promotion-right p {
	margin-right: 0.5vw;
}


</style>