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
  </div>
</template>

<script>
export default {
  data(){
    return{
      countryList: [],
      cityList: [],
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

    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)
      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
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