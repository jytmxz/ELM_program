<template>
<div class="wrapper">
			<header>
				用户注册
			</header>
			<ul class="form-box">
				<li>
					<div class="title">
						手机号码：
					</div>
					<div class="content">
						<input type="text" v-model="user.userId" placeholder="手机号码" />
					</div>
				</li>
				<li>
					<div class="title">
						密码：
					</div>
					<div class="content">
						<input type="text" v-model="user.password" placeholder="密码" />
					</div>
				</li>
				<li>
					<div class="title">
						密码确认：
					</div>
					<div class="content">
						<input type="text"  v-model="confirmPassword" placeholder="密码确认" />
					</div>
				</li>
				<li>
					<div class="title">用户名称：</div>
					<div class="content">
					<input type="text"  v-model="user.userName" placeholder="用户名称" />
					</div>
				</li>
				<li>
					<div class="title">
						性别：
					</div>
					<div class="content" style="font-size: 3vw;">
						<input type="radio" v-model="user.userSex" value="1" name="sex"/> 男
						<input type="radio" v-model="user.userSex" value="0" name="sex" checked /> 女
					</div>
				</li>
			</ul>
			<div class="btn-login">
				<button @click="regester">注册</button>
			</div>
		</div>
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

<script setup>

import qs from "qs";
import { useRouter} from "vue-router";
import { ref, inject } from "vue";
import { setSessionStorage } from '@/common.js';
const axios = inject("axios");
const router = useRouter();


const user = ref({
  userId: "",
  password: "",
  userName: "",
  userSex: "",
});

const confirmPassword = ref([]);

function regester(){
  if (user.value.userId == "") {
    alert("手机号码不能为空");
    return;
  }
  if (user.value.password == "") {
    alert("密码不能为空");
    return;
  }
  if (user.value.password != confirmPassword.value) {
    alert("两次输入的密码不一致");
    return;
  }
  if (user.value.userName == "") {
    alert("用户名不能为空");
    return;
  }

  axios
    .post("UserController/saveUser", qs.stringify(user.value))
    .then((response) => {
      if (response.data.data > 0) {
        console.log("成功")
		setSessionStorage("user", user.value);
		router.push("/")
		
      } 
    })
  
};




</script>

<style  scoped>
/*总容器*/
.wrapper {
	width: 100%;
	height: 100%;
}

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
/*表单部分*/
.wrapper .form-box {
	margin-top: 12vw;
}

.form-box li {
	
	display: flex;
	align-items: center;
	padding: 4vw 3vw 0 3vw;
}
.form-box li .title {
	font-size: 3vw;
	color: #666;
	font-weight: 700;
	
	flex: 0 0 18vw;
}

.form-box li .content {
	flex: 1;
}

.form-box li .content input {
	border: 0;
	outline: 0;
	font-size: 3vw;
	height: 4vw;
}
.form-box li .content input[type='radio'] {
	vertical-align: middle;
	margin-left: 3vw;
}

.wrapper .btn-login,
.wrapper .btn-register  {
	width: 100%;
	padding: 4vw 3vw 0 3vw;
}
.btn-login button,
.btn-register button {
	width: 100%;
	height: 10vw;
	border: 0;
	outline: 0;
	border-radius: 1.5vw;
	font-weight: 700;
	letter-spacing: 1vw;
	font-size: 3.8vw;
}
.btn-login button {
	background: #38CA73;
	color: #FFFFFF;
}
.btn-register button {
	background: #EEEEEE;
	color: #666666;
}
.footer {
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