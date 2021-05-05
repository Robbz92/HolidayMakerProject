<template>
  <div class="about">
    <input type="text" placeholder="Sök..." v-model="searchPhrase" id="searchBar">
    <button @click="(searchFor(searchPhrase))">Search</button>
    
    <h3 v-if="countryList != ''">Countries</h3>
        <ol id="countryList">
            <li v-for="(country, index) in setCountries"  :key="index">
              <p>{{country.id}} - {{country.name}} - {{country.temperature}}</p>
            </li>
        </ol>
    
    <h3 v-if="cityList != ''">Cities</h3>
        <ol id="cityList">
            <li v-for="(city, index) in setCities"  :key="index">
              <p>{{city.id}} - {{city.name}} - {{city.country_id}}</p>
            </li>
        </ol>

        <h3 v-if="hotelList != ''">Hotels</h3> 
        <ol id="hotelList">
            <li v-for="(hotel, index) in setHotels"  :key="index" @click="viewHotel(hotel.id)">
              <p>{{hotel.id}} - {{hotel.name}} - {{hotel.address}}</p>
            </li>
        </ol>
  </div>
</template>

<script>
export default {
  data(){
    return{
      countryList: [],
      cityList: [],
      hotelList: [],
      searchPhrase: ''
    }
  },

  computed:{
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
    }
  },

  methods:{
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

    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)
      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
    },

    viewHotel(id){
      this.$router.push("/hotel/" + id)
    }
  },

  mounted(){
  }
}
</script>

<style scoped>
  li{
   list-style-type: none; 
  }
</style>