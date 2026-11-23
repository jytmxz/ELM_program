const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8081,  //修改启动端口
    // proxy:{
    //   '/juheNews':{
    //     target:'http://localhost:8080/OrderTypeController/findAll',
    //     ws:true,
    //     changeOrigin:true,
    //     pathRewrite:{
    //       '^/juheNwes':''
    //     }
    //   }}
   
  }
})
