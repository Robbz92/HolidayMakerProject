<template>
  <div class="home">
    <hotelList :searchResultText="searchResultText"/>
    <div id="search">
      <searchBar />
      <filterOptions v-if="gotClicked" />
    </div>
  </div>
</template>

<script>
import searchBar from "../components/SearchBar.vue";
import filterOptions from "../components/FilterOptions.vue";
import hotelList from "../components/HotelList.vue";

export default {
  name: "Home",
  components: {
    hotelList,
    searchBar,
    filterOptions,
  },

    data(){
      return{
        hasClicked: false,
        searchResultText: "Loading.."
      }
    },

    computed:{
      gotClicked(){
        return this.$store.getters.getHasSearched
      }
    },

    methods:{
      /*
      ifall du inte redan har klickat på search animera,
      men har du redan klickat animera inte igen
      */
      onSearch(){
        if(!this.hasClicked){
          this.searchResultText = "Loading.."
          var e = document.getElementById('search')
          e.style.bottom = "83%";
          this.$store.commit("setHasSearched", true)
          console.log("set to true")
          document.getElementById("forAnimationOnly").style.top = "7.5vh"

        setTimeout(() => this.searchResultText = "Cannot find any hotels matching searchphrase", 1000)
        }
      },
    },

    mounted(){
      this.hasClicked = this.$store.getters.getHasSearched
      if(this.hasClicked == true){
          var e = document.getElementById('search')
          e.style.bottom = "83%";
      }
    }
  
};
</script>

<style scoped>
#search {
  position: absolute;
  bottom: 50%;
  left: 30%;
  width: 40%;
  transition: bottom 0.5s;
}

.home {
  margin: none;
  overflow: hidden;
}
</style>
