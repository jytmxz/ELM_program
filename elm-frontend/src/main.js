import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { getSessionStorage } from '@/common.js'

//引入图标库的css文件
import 'font-awesome/css/font-awesome.min.css'

createApp(App).use(router).mount('#app')

router.beforeEach((to, from, next) => {
    let user = getSessionStorage("user");
    let path = to.path;
    console.log('path :>> ', path);
    if(path == "/" || path == "/login" || path == '/businessList' || path == '/businessInfo' || path == '/Regester') {
        next();
    }else {
        //判断用户是否登录
        if(!user) {
            router.push("/login")
        }else {
            next();
        }
    }
    
})