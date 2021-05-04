import { createStore } from 'vuex'
//import axios from 'axios'

export default createStore({
  state: {
    email: String,
    password: String,
    loggedInUser: null,
    firstName: String,
    lastName: String,
  },
  mutations: {
    setLoggedInUser(state, user){
      state.loggedInUser=user;
    },
  },
  actions: {
  },
  modules: {
  }
})
