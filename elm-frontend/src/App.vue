<template>
  <router-view/>
</template>

<script setup>
import axios from 'axios'
import { provide } from 'vue'

//设置axios的基础url部分
axios.defaults.baseURL = 'http://localhost:8080/ELMDemo/';
provide('axios', axios);
</script>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

html,body {
	width: 100%;
	height: 100%;
	font-family: "微软雅黑";
}

ul,ol {
	list-style: none;
}

a {
	text-decoration: none;
}
</style>
