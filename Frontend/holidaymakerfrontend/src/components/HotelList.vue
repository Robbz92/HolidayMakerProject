<template>
    <div id="bigList">

        <h3 v-if="hotelList != ''">Hotels</h3> 
        <ol id="hotelList">
            <li v-for="(hotel, index) in setHotels" :key="index" @click="viewHotel(hotel.id)">
              <Card :card="hotel" :imageUrl="hotel.hotelImg"/>
            </li>
        </ol>
      
      <h3 v-if="cityList != ''">Cities</h3>
      <ol id="cityList">
          <li v-for="(city, index) in setCities"  :key="index" @click="viewHotel(city.id)">
            <Card :card="city" :imageUrl="city.hotelImg"/>
          </li>
      </ol>
    
      <h3 v-if="countryList != ''">Countries</h3>
      <ol id="countryList">
          <li v-for="(country, index) in setCountries"  :key="index" @click="viewHotel(country.id)">
            <Card :card="country" :imageUrl="country.hotelImg"/>
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
      this.$router.push("/hotel")
    },
  },

   mounted(){
  }
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