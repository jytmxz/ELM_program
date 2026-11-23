import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import BusinessList from '../views/BusinessList'
import BusinessInfo from '../views/BusinessInfo'
import Login from '../views/Login.vue'
import Order from '../views/Order.vue'
import Payment from '../views/Payment.vue'
import OrderList from '../views/OrderList.vue'
import Regester from '../views/Regester.vue'
const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/businessList',
    name: 'BusinessList',
    component: BusinessList
  },
  {
    path: '/businessInfo',
    name: 'BusinessInfo',
    component: BusinessInfo
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/order',
    name: 'Order',
    component: Order
  },
  {
    path: '/Payment',
    name: 'Payment',
    component: Payment
  },
  {
    path: '/OrderList',
    name: 'OrderList',
    component: OrderList
  }
  ,
  {
    path: '/Regester',
    name: 'Regester',
    component: Regester
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
