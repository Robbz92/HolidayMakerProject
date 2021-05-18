import { createStore } from "vuex";
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
    fromDate: '',
    toDate: '',
    numberOfDays: '',
    chosenHotel: '',
    roomList: [],
    hasSearched: false,
    searchedTemperature: ''
  },
  mutations: {
    setHasSearched(state, payload) {
      state.hasSearched = payload
    },

    setChosenHotel(state, payload) {
      state.chosenHotel = payload
    },

    setRoomList(state, payload) {
      state.roomList = payload
    },
    setComforts(state, payload) {
      state.comforts = payload
    },
    setCountryList(state, payload) {
      state.countryList = payload
    },
    setAttractions(state, payload) {
      state.attractions = payload
    },

    setTemperature(state, payload) {
      state.temperature = payload
    },

    setInformation(state, payload) {
      state.information = payload
    },

    setReviewList(state, payload) {
      state.reviewList = payload
    },

    setCityList(state, payload) {
      state.cityList = payload
    },

    setHotelList(state, payload) {
      state.hotelList = payload
    },

    setSearchPhrase(state, payload) {
      state.searchPhrase = payload
    },

    setLoggedInUser(state, user) {
      state.loggedInUser = user;
    },

    setFromDate(state, payload) {
      state.fromDate = payload
    },

    setToDate(state, payload) {
      state.toDate = payload
    },

    setNumberOfDays(state, payload) {
      state.numberOfDays = payload
    },
    
    setSearchedTemperature(state, payload) {
      state.searchedTemperature = payload
    }
  },

  actions: {
    async fetchHotel() {
      await axios.get("http://localhost:3000/rest/getRoomOnDate/" + this.state.chosenHotel + "/" + this.state.fromDate + "/" + this.state.toDate)
        .then(response => {
          console.log(response.data)
          this.commit("setRoomList", response.data)
        })
    },

    async fetchAllHotels() {
      await axios.get("http://localhost:3000/rest/getAllHotels")
      .then(response => {
        console.log(response.data)
        this.commit("setHotelList", response.data)
      })
    },

    async fetchHotelByTemperature() {
      await axios.get("http://localhost:3000/rest/tempSearch/" + this.state.searchedTemperature)
      .then(response => {
        console.log(response.data)
        this.commit("setHotelList", response.data)
      })
    },

      await axios.get("http://localhost:3000/rest/getCountry")
        .then(response => {
          console.log(response.data)
          this.commit("setCountryList", response.data)
        })
    },

    async fetchReviews(store, hotelId) {
      await axios.get("http://localhost:3000/rest/reviews/" + hotelId)
        .then(response => {
          console.log(response.data)
          this.commit("setReviewList", response.data)
        })
    },

    async fetchCities() {
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

    async fetchInformation(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelInfo/" + hotelId)
        .then(response => {
          console.log(response.data)
          this.commit("setInformation", response.data)
        })
    },
    async fetchTemperature(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelCityTemparatureByHotelId/" + hotelId)
        .then(response => {
          console.log(response.data)
          this.commit("setTemperature", response.data)
        })
    },
    async fetchAttractions(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelAttraction/" + hotelId)
        .then(response => {
          console.log(response.data)
          this.commit("setAttractions", response.data)
        })
    },
    async fetchComforts(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelComforts/" + hotelId)
        .then(response => {
          console.log(response.data)
          this.commit("setComforts", response.data)
        })
    },

  },

  getters: {
    getHasSearched(state) {
      return state.hasSearched
    },

    getRoomList(state) {
      return state.roomList
    },
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

    getSearchPhrase(state) {
      return state.searchPhrase
    },

    getNumberOfDays(state) {
      return state.numberOfDays
    },
  },
  modules: {

  }
})
