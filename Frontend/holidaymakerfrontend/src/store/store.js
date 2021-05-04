import { createStore} from "vuex";
import axios from 'axios';

export default createStore({
  state: {
    countryList: [],
    cityList: [],
    searchPhrase: '',
    hotelInfo: String,
  },
  mutations: {
    setCountryList(state,payload){
      state.countryList = payload
    },

    setCityList(state,payload){
      state.cityList = payload
    },

    setSearchPhrase(state,payload) {
      state.searchPhrase = payload
    },

    setHotelInfo(state, payload){
      state.hotelInfo = payload;
    }
  },
  actions: {
    async fetchCountries(){
      await axios.get("http://localhost:3000/rest/getCountry")
      .then(response => {
        console.log(response.data)
        this.commit("setCountryList", response.data)
      })
    },

    async fetchCities(){
      await axios.get("http://localhost:3000/rest/getCity")
      .then(response => {
        console.log(response.data)
        this.commit("setCityList", response.data)
      })
    },

  async searchFor() {
    await axios.get("http://localhost:3000/rest/citySearch/" + this.state.searchPhrase)
      .then(response => {
        console.log(response.data)
        this.commit("setCityList", response.data)
      })
    
    await axios.get("http://localhost:3000/rest/countrySearch/" + this.state.searchPhrase)
      .then(response => {
        console.log(response.data)
        this.commit("setCountryList", response.data)
      })
    },
  
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

    getters: {
      getCountries(state){
        return state.countryList
      },
      
      getCities(state){
        return state.cityList
      },

      getSearchPhrase(state) {
        return state.searchPhrase
      },

      getHotelInfo(state){
        return state.hotelInfo
      }
    },
    modules: {
    
  }
})
