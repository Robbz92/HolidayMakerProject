import { createStore} from "vuex";
import axios from 'axios';

export default createStore({
  state: {
    countryList: [],
    cityList: [],
    hotelList: [],
    searchPhrase: '',
    email: String,
    password: String,
    loggedInUser: null,
    firstName: String,
    lastName: String,
    reviewList: String,
    information: String,
    temperature: String,
    attractions: String,
    comforts: String,
    price: []
  },
  mutations: {
    setComforts(state,payload){
      state.comforts = payload
    },
    setCountryList(state,payload){
      state.countryList = payload
    },
    setAttractions(state,payload){
      state.attractions = payload
    },

    setTemperature(state,payload){
      state.temperature = payload
    },

    setInformation(state,payload){
      state.information = payload
    },

    setReviewList(state,payload){
      state.reviewList = payload
    },

    setCityList(state,payload){
      state.cityList = payload
    },

    setHotelList(state, payload) {
      state.hotelList = payload
    },

    setSearchPhrase(state,payload) {
      state.searchPhrase = payload
    },

    setLoggedInUser(state, user){
      state.loggedInUser=user;
    },
    setRooms(state, payload){
      state.price=payload;
    },
  },
  actions: {
    async fetchCountries(){
      await axios.get("http://localhost:3000/rest/getCountry")
      .then(response => {
        console.log(response.data)
        this.commit("setCountryList", response.data)
      })
    },

    async fetchReviews(store, hotelId){
      console.log(hotelId);
      await axios.get("http://localhost:3000/rest/reviews/" + hotelId)
      .then(response => {
        console.log(response.data)
        this.commit("setReviewList", response.data)
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
    
      await axios.get("http://localhost:3000/rest/hotelSearch/" + this.state.searchPhrase)
      .then(response => {
        console.log(response.data)
        this.commit("setHotelList", response.data)
      })
    },

    async fetchInformation (store, hotelId){
      console.log(hotelId);
      await axios.get("http://localhost:3000/rest/hotelInfo/" + hotelId)
      .then(response => {
        this.commit("setInformation", response.data)
      })
    },
    async fetchTemperature (store, hotelId){
      await axios.get("http://localhost:3000/rest/hotelCity/" + hotelId)
      .then(response => {
        this.commit("setTemperature", response.data)
      })
    },
    async fetchAttractions (store, hotelId){
      await axios.get("http://localhost:3000/rest/hotelAttraction/" + hotelId)
      .then(response => {
        this.commit("setAttractions", response.data)
      })
    },
    async fetchComforts (store, hotelId){
      await axios.get("http://localhost:3000/rest/hotelComforts/" + hotelId)
      .then(response => {
        this.commit("setComforts", response.data)
      })
    },
    async fetchRooms (){
      await axios.get("http://localhost:3000/rest/getRooms")
      .then(response => {
        console.log(response.data)
        this.commit("setRooms", response.data)
      })
    },
  },

    getters: {
      getComforts(state){
        return state.comforts
      },
      getAttractions(state){
        return state.attractions
      },
      getTemperature(state){
        return state.temperature
      },
      getInformation(state){
        return state.information
      },
      getCountries(state){
        return state.countryList
      },

      getReviews(state){
        return state.reviewList
      },
      
      getCities(state){
        return state.cityList
      },

      getHotels(state) {
        return state.hotelList
      },

      getSearchPhrase(state) {
        return state.searchPhrase
      },
    },
    modules: {
    
  }
})
