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
    fromDate: String,
    toDate: String,
    chosenHotel: '',
    roomList: []
  },
  mutations: {
    setCountryList(state,payload){
      state.countryList = payload
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

    setFromDate(state, payload) {
      state.fromDate = payload
    },

    setToDate(state, payload) {
      state.toDate = payload
    },

    setChosenHotel(state, payload) {
      state.chosenHotel = payload
    },

    setRoomList(state, payload) {
      state.roomList = payload
    }
  },
  actions: {
    async fetchCountries() {
      await axios.get("http://localhost:3000/rest/getCountry")
        .then(response => {
          console.log(response.data)
          this.commit("setCountryList", response.data)
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

    async fetchHotel() {
      await axios.get("http://localhost:3000/rest/getRoomOnDate/" + this.state.fromDate + "/" + this.state.toDate + "/" + this.state.chosenHotel)
        .then(response => {
          console.log(response.data)
          this.commit("setRoomList", response.data)
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

      getHotels(state) {
        return state.hotelList
      },

      getSearchPhrase(state) {
        return state.searchPhrase
      },

      getRoomList(state) {
        return state.roomList
      }
    },
    modules: {
    
  }
})
