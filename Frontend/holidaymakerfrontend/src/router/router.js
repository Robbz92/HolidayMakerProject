import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Hotel from '../views/Hotel.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue' 
import MyBookings from '../components/MyBookings.vue'
import Review from '../views/Review.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/hotel',
    name: 'Hotel',
    component: Hotel,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/myBookings',
    name: 'MyBookings',
    component: MyBookings,
  },
  {
    path: '/review',
    name: 'Review',
    component: Review,
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
