<template>
    <div id="bigList">

        <h3 v-if="hotelList != ''">Hotels</h3> 
        <ol id="hotelList">
            <li v-for="(hotel, index) in setHotels" :key="index" @click="onClick(hotel.id)">
              <Card :card="hotel" :imageUrl="hotel.hotelImg"/>
            </li>
        </ol>
      
      <h3 v-if="cityList != ''">Cities</h3>
      <ol id="cityList">
          <li v-for="(city, index) in setCities"  :key="index" @click="onClick(city.id)">
            <Card :card="city" :imageUrl="city.hotelImg"/>
          </li>
      </ol>
    
      <h3 v-if="countryList != ''">Countries</h3>
      <ol id="countryList">
          <li v-for="(country, index) in setCountries"  :key="index" @click="onClick(country.id)">
            <Card :card="country" :imageUrl="country.hotelImg"/>
          </li>
      </ol>

        <h3 v-if="hotelList != ''">Hotels</h3> 
          <button @click="distanchBeach()">Distance beach</button>
          <button @click="distanceCentrum()">Distance centrum</button>
        
          <button @click="test">Liiiiiden knapp</button>
          <button class="ScaleUP" @click="sortArrayUp()">ScaleUP</button>
          <button class="ScaleDown" @click="sortArrayDown()">ScaleDown</button>
        <ol id="hotelList">
            <li v-for="(hotel, index) in setHotels" :key="index"  @click="allReviews(hotel.id), hotelInfo(hotel.id),
            hotelTemperature(hotel.id), hotelAttraction(hotel.id), hotelComforts(hotel.id)">
              <Card :card="hotel"/>
            </li>
        </ol>
      <h3 v-if="hotelList == '' && cityList == '' && countryList == ''">No hotels matching searchphrase</h3>
    </div>
</template>

<script>
import Card from "./HotelCard.vue"

export default {
 
  components:{
    Card,
  },

    data(){
      return{
      countryList: [],
      cityList: [],
      hotelList: [],
      }
    },

  computed:{
    getRooms(){
        return this.$store.state.price
      },
    setCountries(){
      var countries = this.$store.getters.getCountries
      this.updateCountryList()
      return countries
    },

    setCities(){
      var cities = this.$store.getters.getCities
      this.updateCityList()
      return cities
    },

    setHotels(){
      var hotels = this.$store.getters.getHotels
      this.updateHotelList()
      return hotels
    },
  },

  methods:{
    sortArrayUp(){
        let rooms = this.$store.state.price
        return rooms.sort((a, b) => (a.price > b.price) ? 1 : -1)
    },
    sortArrayDown(){
        let rooms = this.$store.state.price
        return rooms.sort((a, b) => (a.price > b.price) ? -1 : 1)
    },

    test(){
        return this.$store.dispatch("fetchRooms");
    },
    distanchBeach(){
        let rooms = this.$store.getters.getHotels
        return rooms.sort((a, b) => (a.distance_beach > b.distance_beach) ? 1 : -1)
    },
    distanceCentrum(){
      let rooms = this.$store.getters.getHotels
      return rooms.sort((a, b) => (a.distance_downtown > b.distance_downtown) ? 1 : -1)
    },
    onClick(id){
      this.viewHotel(id)
      this.allReviews(id)
      this.hotelInfo(id)
      this.hotelTemperature(id)
      this.hotelAttraction(id)
      this.hotelComforts(id)
    },

    updateCountryList(){
      this.countryList = this.$store.getters.getCountries
      console.log(this.countryList)
    },

    updateCityList(){
      this.cityList = this.$store.getters.getCities
      console.log(this.cityList)
    },

    updateHotelList(){
      this.hotelList = this.$store.getters.getHotels
      console.log(this.hotelList)
    },
    viewHotel(id){
      this.$store.commit('setChosenHotel', id)
      this.$store.dispatch("searchFor")
      this.$store.dispatch("fetchHotel")
      this.$router.push("/hotel")
    },
    allReviews(hotelId){
      this.$store.dispatch("fetchReviews", hotelId);
      this.$router.push("/hotel")
    },
    hotelInfo(hotelId){
      this.$store.dispatch("fetchInformation", hotelId);
      this.$router.push("/hotel")
    },
    hotelTemperature(hotelId){
      this.$store.dispatch("fetchTemperature", hotelId);
      this.$router.push("/hotel")
    },
    hotelAttraction(hotelId){
      this.$store.dispatch("fetchAttractions", hotelId);
      this.$router.push("/hotel")
    },
    hotelComforts(hotelId){
      this.$store.dispatch("fetchComforts", hotelId);
      this.$router.push("/hotel")
    },
  },
} 
</script>

<style scoped>
  ol{
    display: flex;
    flex-wrap: wrap;
    list-style-type: none; 
    width: 85vw;
    margin: auto;
  }
</style>