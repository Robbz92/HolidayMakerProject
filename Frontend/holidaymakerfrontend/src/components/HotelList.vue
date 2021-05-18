<template>
  <div id="bigList">
    <div class="lists" id="forAnimationOnly">
      <h3 v-if="hotelList != ''"></h3>
      <ol id="hotelList">
        <li
          v-for="(hotel, index) in setHotels"
          :key="index"
          @click="onClick(hotel.id)"
        >
          <Card :card="hotel" :imageUrl="hotel.hotelImg" />
        </li>
      </ol>
      <h3 v-if="cityList != ''"></h3>
      <ol id="cityList">
        <li
          v-for="(city, index) in setCities"
          :key="index"
          @click="onClick(city.id)"
        >
          <Card :card="city" :imageUrl="city.hotelImg"/>
        </li>
      </ol>

      <h3 v-if="countryList != ''"></h3>
      <ol id="countryList">
        <li
          v-for="(country, index) in setCountries"
          :key="index"
          @click="onClick(country.id)"
        >
          <Card :card="country" :imageUrl="country.hotelImg" />
        </li>
      </ol>
      <h3 v-if="hotelList == '' && cityList == '' && countryList == ''">
        {{searchResultText}}
      </h3>
    </div>
  </div>
</template>

<script>
/*
Vi importerar Card och lägger det som en komponent,
vilket gör att vi kan använda den som ett element.
I data skapar vi tomma arrayer som vi populerar via våra
functions.
Som vi sen skriver ut på hemsidan på rätt ställen
*/
import Card from "./HotelCard.vue";

export default {
  props:[
      "searchResultText"
  ],

  components: {
    Card,
  },

  data() {
    return {
      countryList: [],
      cityList: [],
      hotelList: [],
    };
  },

  computed: {
    getHotels() {
      return this.$store.getter.getHotels;
    },

    setCountries() {
      var countries = this.$store.getters.getCountries;
      this.updateCountryList();
      return countries;
    },

    setCities() {
      var cities = this.$store.getters.getCities;
      this.updateCityList();
      return cities;
    },

    setHotels() {
      var hotels = this.$store.getters.getHotels;
      this.updateHotelList();
      return hotels;
    },
  },

  /*
    skickar ID till funktionerna som finns i store.
    Som hämtar ut rätt information baserat på ID:et
  */

  methods: {
    onClick(id) {
      this.viewHotel(id);
      this.allReviews(id);
      this.hotelInfo(id);
      this.hotelTemperature(id);
      this.hotelAttraction(id);
      this.hotelComforts(id);
    },

    updateCountryList() {
      this.countryList = this.$store.getters.getCountries;
      console.log(this.countryList);
    },

    updateCityList() {
      this.cityList = this.$store.getters.getCities;
      console.log(this.cityList);
    },

    updateHotelList() {
      this.hotelList = this.$store.getters.getHotels;
      console.log(this.hotelList);
    },
    viewHotel(id) {
      this.$store.commit("setChosenHotel", id);
      this.$store.dispatch("searchFor");
      this.$store.dispatch("fetchHotel");
      this.$router.push("/hotel");
    },
    allReviews(hotelId) {
      this.$store.dispatch("fetchReviews", hotelId);
      this.$router.push("/hotel");
    },
    hotelInfo(hotelId) {
      this.$store.dispatch("fetchInformation", hotelId);
      this.$router.push("/hotel");
    },
    hotelTemperature(hotelId) {
      this.$store.dispatch("fetchTemperature", hotelId);
      this.$router.push("/hotel");
    },
    hotelAttraction(hotelId) {
      this.$store.dispatch("fetchAttractions", hotelId);
      this.$router.push("/hotel");
    },
    hotelComforts(hotelId) {
      this.$store.dispatch("fetchComforts", hotelId);
      this.$router.push("/hotel");
    },
      /*
      Animations funktion
      */
    animateLists(){
        document.getElementById("forAnimationOnly").style.top = "7.5vh"
    }
  },

      /*
      Animations funktion
      */
    mounted(){
      var x = this.$store.getters.getHasSearched
      if(x == true){
        document.getElementById("forAnimationOnly").style.top = "7.5vh"
      }
    }
};
</script>

<style scoped>

#hotelList {
  margin-top: 100px;
  margin-bottom: 10%;
}

.lists{
  position: fixed;
  top: 100vh;
  left: 8.75vw;
  max-height: 100vh;
  overflow: auto;
  overflow-x: hidden;
  width: 82.5vw;
  margin: auto;
  margin-top: 5vh;
  padding: 0;
  transition: top .5s;
}

::-webkit-scrollbar {
    display: none;
}

ol {
  display: flex;
  flex-wrap: wrap;
  list-style-type: none;
  width: 85vw;
  margin: auto;
}

</style>