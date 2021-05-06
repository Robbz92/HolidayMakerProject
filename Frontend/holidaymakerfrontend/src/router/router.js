import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Hotel from '../views/Hotel.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Rooms from '../views/Rooms.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/hotel',
    name: 'Hotel',
    component: Hotel
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
    path: '/rooms',
    name: 'Rooms',
    component: Rooms
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
