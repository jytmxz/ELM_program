<template>
    <!--总容器-->
    <div class="wrapper">
        <Head></Head>
        <!--表单部分-->
        <ul class="form-box">
            <li>
                <div class="title">
                    账号：
                </div>
                <div class="content">
                    <input type="text" placeholder="账号" v-model="user.userId"/>
                </div>
            </li>
            <li>
                <div class="title">
                    密码：
                </div>
                <div class="content">
                    <input type="text" placeholder="密码" v-model="user.password"/>
                </div>
            </li>
        </ul>
        <div class="btn-login">
            <button @click="login">登录</button>
        </div>
        <div class="btn-register" @click="goRegester">
            <button >去注册</button>
        </div>
        
        <Foot></Foot>
        
    </div>
</template>

<script setup>
import Head from '@/components/head.vue'
import Foot from '@/components/foot.vue'
import { ref, inject } from 'vue'
import { setSessionStorage } from '@/common.js';
import qs from 'qs'
import { useRouter } from 'vue-router';
const axios = inject("axios")
const router = useRouter()
const user = ref({
    userId: '',
    password: '',
    userName: '',
    userSex: '',
    userImg: ''
})
async function login() {
    if(!user.value.userId) {
        alert("用户名不能为空");
        return ;
    }
    if(!user.value.password) {
        alert("密码不能为空");
        return;
    }

    //发送登录请求
    const response = await axios.post("/UserController/login", qs.stringify(user.value) );
    if(response.data.code == 200) {
        //登录成功，把登录信息保存到sessionStorage
        user.value = response.data.data;
        //注意： 为了节省浏览器空间，保存登录信息时，把用户img 属性值过滤到。
        user.value.userImg = '';
        setSessionStorage("user", user.value);
        //跳转页面
        router.go(-1);
    }
    if(response.data.code == 400) {
        //登录失败
        alert("用户名或密码错误");
    }
}
function goRegester(){
    router.replace("/Regester")
}

</script>

<style scoped src="../assets/css/login.css">

</style>