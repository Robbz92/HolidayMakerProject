import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    hotelInfo: String,
  },
  mutations: {
    setHotelInfo(state, payload){
      state.hotelInfo = payload;
    }
  },
  actions: {
    //async fetchHotelInfo(store, hotelid){
     // await axios.get("http://localhost:3000/rest/hotelInformation/" + hotelid)
     // .then(response=>{
     //   this.commit("setHotelInfo", response.data)
     // })
     async fetchHotelInfo(){
      await axios.get("http://localhost:3000/rest/hotelInformation/")
      .then(response=>{
        this.commit("setHotelInfo", response.data)
      })
    },
  },
  getters:{
    getHotelInfo(state){
      return state.hotelInfo
    }
  },

  modules: {
  }
})
