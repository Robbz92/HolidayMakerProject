<template>
  <div id="bigList">
    <div class="lists" id="forAnimationOnly">
      <div>
      </div>
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
      <h3 v-if="hotelList == ''">
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
      'searchResultText', 
      'filters'
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

//Vi har inte lagt till filtrerings funktionaliteten på länder eller städer
//Hade bara varit att klippa och klistra från setHotels,
//Vi har distkuterat om vi skulle ha allt som en lista,
//eller fortfarande ha dom som separata
  computed: {
    setHotels() {
      var countries = this.$store.getters.getCountries;
      var cities = this.$store.getters.getCities;
      var hotels = this.$store.getters.getHotels;

      let newHotelList = []
      let duplicateChecker = []

      countries.forEach(hotel => {
        console.log(hotel)
        newHotelList.push(hotel)
        duplicateChecker.push(hotel.id)
      })

      cities.forEach(hotel => {
        if(duplicateChecker.includes(hotel.id)){
          console.log("is already in list")
        }
        else{
          newHotelList.push(hotel)
          duplicateChecker.push(hotel.id)
        }
      })

      hotels.forEach(hotel => {
        if(duplicateChecker.includes(hotel.id)){
          console.log("is already in list")
        }
        else{
          newHotelList.push(hotel)
        }
      })

      this.combineHotelLists(newHotelList)
      
      var filteredHotelList = [];
      //Kör en filtrering på en ny variabel
      let filteredHotels = newHotelList.filter((hotel) => {
        if(this.filters.length > 0){
          this.filters.forEach(element => {
            //Sparar en variabel ifall hotellet redan är tillagd i listan
            let hotelToCheck = newHotelList.find(hotelToCompareAgainst => 
              hotelToCompareAgainst.name == hotel.name)
            //Kollar så att hotellet inte är empty, och ifall den är tillagd
            if(this.filterComfortsAndAttractions(hotel) != undefined){
              if(!hotelToCheck){
                if(element) //ANVÄNDS ENDAST FÖR ATT ELEMENT SKA ANVÄNDAS
                  console.log()

                filteredHotelList.push(this.filterComfortsAndAttractions(hotel))
              }
            }
            return hotel
          });
        }else
        return hotel
      });
      //Ifall där finns filter
      if(this.filters.length > 0){
        return filteredHotelList
      }else{ //Eller inte
        if(filteredHotels)
          console.log()
        filteredHotelList = newHotelList
        return filteredHotelList
      }
    },
  },

  /*
    skickar ID till funktionerna som finns i store.
    Som hämtar ut rätt information baserat på ID:et
  */

  methods: {
    async combineHotelLists(hotelList){
      this.hotelList = hotelList
    },

    filterComfortsAndAttractions(hotel){
      //Boolean som bestämmer ifall ett hotell finns med i båda
      //filtrerings listorna, i så fall return, annars är den empty
      let willShow = true;
      this.filters.forEach(element => {
        if(!hotel.comfortList.includes(element.filter) && element.type == "comfort" ||
        !hotel.attractionList.includes(element.filter) && element.type == "attraction"){
          willShow = false
        }
      });

      if(willShow){
        return hotel
      }
    },

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
      if(this.$store.state.searchPhrase != ""){
        this.$store.dispatch("searchFor");
      }
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
      var hasSearched = this.$store.getters.getHasSearched
      if(hasSearched == true){
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
  margin-top: 12vh;
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