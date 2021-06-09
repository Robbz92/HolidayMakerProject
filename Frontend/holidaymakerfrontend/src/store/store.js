import { createStore } from "vuex";
import axios from 'axios';


export default createStore({
  state: {
    countryList: [],               // Används för att fetcha alla hotell i ett land
    cityList: [],                     // Används för att fetcha alla hotell i en stand
    hotelList: [],                   // Alla hotell som matchar sökning + ihoplagda land och stad
    searchPhrase: '',             // Vad du sökt på, om du söker med text
    loggedInUser: null,       // Hämtar den nuvarande användaren
    reviewList: String,        // Hämtar alla reviews på ett hotell
    information: String,      // Hämtar all info från valt hotell
    temperature: String,      // Hämtar temperaturen, staden och landet hotellet ligger i
    attractions: String,        // Alla attractions på ett hotell
    comforts: String,          // Alla comforts på ett hotell
    fromDate: '',                 // sök funktionen
    toDate: '',                     // sök funktionen
    numberOfDays: '',       // Sparar ner hur lång din bokning ska vara
    chosenHotel: '',           // För att hämta lediga rum
    roomList: [],               // Används vid Edit av rum på bokning
    hasSearched: false,    // Kollar vart searchbaren ska vara på skärmen, för att inte täcka hotell
    chosenRoom: "",       // Väljer ett rum under bokningen
    roomsToBook: [],      // Rummen som ska skickas till bokningen
    bookingId: 0,             // ID på bokning
    size: 1,                       // Sökfunktionen, antalet personer
    myBookings: [],        // Alla bokningar på en användare
    clickedBooking: '',    // ID på bokningen du markerat
    searchedTemperature: '',  //Vad du sökt på, om du söker efter temperatur
    filterAmmount: 0,     //Hur många filter du använder
    bookedRoom: '',          // Varje rum du har på en booking sen innan, används vid edit
    favoriteList: [],           // Listan på alla dina favorithotell
    hotelListReviews: [], // Används för att kolla om du redan gjort en review på ett hotell
    room: '',                     // sparas för sök funktionen
    adults: 0,                   // ~||~
    children: 0,               // ~||~
    childrenAges: [],      // ~||~
    roomAmount: 0       // ~||~
  },

  mutations: {
    setBookings(state, payload) {
      state.bookings = payload;
    },

    setChosenRoom(state, payload) {
      state.chosenRoom = payload;
    },

    setRoomsToBook(state, payload) {
      state.roomsToBook = payload;
    },

    setFilterAmmount(state, payload) {
      state.filterAmmount = payload
    },

    setClickedBooking(state, payload) {
      state.clickedBooking = payload
    },

    setMyBookings(state, payload) {
      state.myBookings = payload
    },

    setHasSearched(state, payload) {
      state.hasSearched = payload
    },

    setSize(state, payload) {
      state.size = payload
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
    },

    setDeleteBooking(state, payload) {
      state.deleteBooking = payload
    },

    setBookedRoom(state, payload) {
      state.bookedRoom = payload;
    },

    setTempRange(state, payload) {
      state.temperatureRange = payload
    },

    setBookingId(state, payload) {
      state.bookingId = payload
    },

    setFavoriteList(state, payload) {
      state.favoriteList = payload
    },

    setFavoriteId(state, payload) {
      state.favoriteId = payload
    },

    setHotelListForReview(state, payload) {
      state.hotelListReviews = payload;
    },
    setRoom(state, payload) {
      state.room = payload
    }

  },

  actions: {
    async UpdatePaymentState(store, bookingsID) {
      await axios.put("http://localhost:3000/auth/updatePaymentState/" + bookingsID)
        .then(response => {
          console.log(response.data)
        })
    },

    async fetchHotelListForReviews() {
      await axios.get("http://localhost:3000/rest/findReviews/")
        .then(response => {
          this.commit("setHotelListForReview", response.data)
        })
    },


    async fetchLatestBookingID() {
      await axios.get("http://localhost:3000/auth/getLatestBookings/")
        .then(response => {
          this.commit("setBookingId", response.data)
        })
    },

    async fetchClickedBooking(store, bookingId) {
      await axios.get("http://localhost:3000/auth/bookingById/" + bookingId)
        .then(response => {
          this.commit("setClickedBooking", response.data)
        })
    },

    async fetchMyBookings() {
      await axios.get("http://localhost:3000/auth/allMyBooknings")
        .then(response => {
          this.commit("setMyBookings", response.data)
        })
    },

    async fetchHotel() {
      await axios.get("http://localhost:3000/rest/getRoomOnDate/" + this.state.chosenHotel + "/"
        + this.state.fromDate + "/" + this.state.toDate + "/" + this.state.size)
        .then(response => {
          this.commit("setRoomList", response.data)
        })
    },

    async fetchAllHotels() {
      await axios.get("http://localhost:3000/rest/getAllHotels/" + this.state.fromDate +
        "/" + this.state.toDate + "/" + this.state.size + "/" + this.state.room)
        .then(response => {
          this.commit("setHotelList", response.data)
        })
    },

    async fetchHotelByTemperature() {
      await axios.get("http://localhost:3000/rest/tempSearch/" + this.state.fromDate +
        "/" + this.state.toDate + "/" + this.state.size + "/" + this.state.room + "/" + this.state.searchedTemperature + "/" + this.state.temperatureRange)
        .then(response => {
          this.commit("setHotelList", response.data)
        })
    },

    async fetchCountries() {
      await axios.get("http://localhost:3000/rest/getCountry")
        .then(response => {
          this.commit("setCountryList", response.data)
        })
    },

    async fetchReviews(store, hotelId) {
      await axios.get("http://localhost:3000/rest/reviews/" + hotelId)
        .then(response => {
          this.commit("setReviewList", response.data)
        })
    },

    async fetchCities() {
      await axios.get("http://localhost:3000/rest/getCity")
        .then(response => {
          this.commit("setCityList", response.data)
        })
    },

    async searchFor() {
      await axios.get("http://localhost:3000/rest/citySearch/" + this.state.searchPhrase + "/" + this.state.fromDate +
        "/" + this.state.toDate + "/" + this.state.size + "/" + this.state.room)
        .then(response => {
          this.commit("setCityList", response.data)
        })

      await axios.get("http://localhost:3000/rest/countrySearch/" + this.state.searchPhrase + "/" + this.state.fromDate +
        "/" + this.state.toDate + "/" + this.state.size + "/" + this.state.room)
        .then(response => {
          this.commit("setCountryList", response.data)
        })

      await axios.get("http://localhost:3000/rest/hotelSearch/" + this.state.searchPhrase + "/" + this.state.fromDate +
        "/" + this.state.toDate + "/" + this.state.size + "/" + this.state.room)
        .then(response => {
          this.commit("setHotelList", response.data)
        })
    },

    async fetchInformation(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelInfo/" + hotelId)
        .then(response => {
          this.commit("setInformation", response.data)
        })
    },

    async fetchTemperature(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelCityTemparatureByHotelId/" + hotelId)
        .then(response => {
          this.commit("setTemperature", response.data)
        })
    },

    async fetchAttractions(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelAttraction/" + hotelId)
        .then(response => {
          this.commit("setAttractions", response.data)
        })
    },

    async fetchComforts(store, hotelId) {
      await axios.get("http://localhost:3000/rest/hotelComforts/" + hotelId)
        .then(response => {
          this.commit("setComforts", response.data)
        })
    },

    async fetchDeleteBooking(store, bookingId) {
      await axios.delete("http://localhost:3000/auth/deleteBooking/" + bookingId)
        .then(response => {
          console.log(response.data)
        })
    },

    async fetchBookedRoom(store, bookingId) {
      await axios.get("http://localhost:3000/auth/bookedRoomsById/" + bookingId)
        .then(response => {
          this.commit("setBookedRoom", response.data)
        })
    },

    async fetchFavorites() {
      await axios.get("http://localhost:3000/auth/favorites")
        .then(response => {
          console.log(response.data)
          this.commit("setFavoriteList", response.data)
        })
    },

    async deleteFavorite(store, favoriteId) {
      await axios.delete("http://localhost:3000/auth/favorites/" + favoriteId)
        .then(response => {
          console.log(response.data)
        })
    },
  },

  getters: {
    getHotelListForReview(state) {
      return state.hotelListReviews;
    },

    getBookings(state) {
      return state.bookings;
    },

    getRoomsToBook(state) {
      return state.roomsToBook;
    },

    getBookingId(state) {
      return state.bookingId;
    },

    getFilterAmmount(state) {
      return state.filterAmmount
    },

    getClickedBooking(state) {
      return state.clickedBooking
    },

    getMyBookings(state) {
      return state.myBookings
    },

    getHasSearched(state) {
      return state.hasSearched
    },

    getRoomList(state) {
      return state.roomList
    },

    getComforts(state) {
      return state.comforts
    },

    getAttractions(state) {
      return state.attractions
    },

    getTemperature(state) {
      return state.temperature
    },

    getInformation(state) {
      return state.information
    },

    getCountries(state) {
      return state.countryList
    },

    getReviews(state) {
      return state.reviewList
    },

    getCities(state) {
      return state.cityList
    },

    getHotels(state) {
      return state.hotelList
    },

    getSearchPhrase(state) {
      return state.searchPhrase
    },

    getNumberOfDays(state) {
      return state.numberOfDays
    },

    getTempSearch(state) {
      return state.searchedTemperature
    },

    getBookedRoom(state) {
      return state.bookedRoom;
    },

    getTempRange(state) {
      return state.temperatureRange
    },

    getChosenHotel(state) {
      return state.chosenHotel
    },

    getFavoriteList(state) {
      return state.favoriteList
    },
    getRoom(state) {
      return state.room
    },
  },
})