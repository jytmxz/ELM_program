<template>
    <!--footer部分-->
    <ul class="footer">
        <li>
            <i class="fa fa-home"></i>
            <p>首页</p>
        </li>
        <li>
            <i class="fa fa-compass"></i>
            <p>发现</p>
        </li>
        <li onclick="location.href='orderList.html'">
            <i class="fa fa-file-text-o"></i>
            <p>订单</p>
        </li>
        <li>
            <i class="fa fa-user-o"></i>
            <p>我的</p>
        </li>
    </ul>
</template>

<style scoped>
/*footer部分*/
.wrapper .footer {
	width: 100%;
	height: 14vw;
	border-top: 1px solid #DDDDDD;
	background-color: #FFFFFF;
	
	display: flex;
	justify-content: space-around;
	align-items: center;
	
	/*fixed 固定定位。页面元素会固定在指定位置*/
	position: fixed;
	left: 0;
	bottom: 0;
}

.footer li {
	color: #999;
	
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.footer li i {
	font-size: 5vw;
}
.footer li p {
	font-size: 2.8vw;
}
</style>