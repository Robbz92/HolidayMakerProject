import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Hotel from '../views/Hotel.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue' 
import MyBookings from '../views/MyPage.vue'
import Review from '../views/Review.vue'
import Bookings from '../views/Bookings.vue'
import EditBooking from '../views/EditBooking.vue'

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
    path: '/bookings',
    name: 'Bookings',
    component: Bookings
  },
  {
    path: '/myPage',
    name: 'My Page',
    component: MyBookings,
  },
  {
    path: '/review',
    name: 'Review',
    component: Review,
  },

  {
    path: '/editBooking',
    name: 'EditBooking',
    component: EditBooking,
  },


]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
