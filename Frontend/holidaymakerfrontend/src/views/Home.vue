<template>
  <div class="home">
    <hotelList :searchResultText="searchResultText" :filters="filters"/>
    <div id="search">
      <searchBar />
      <sortingOptions v-if="gotClicked" />
    </div>
  </div>
</template>

<script>
import hotelList from "../components/HotelList.vue";
import searchBar from "../components/SearchBar.vue";
import sortingOptions from "../components/SortingOptions.vue";

export default {
  name: "Home",
  components: {
    hotelList,
    searchBar,
    sortingOptions,
  },

    data(){
      return{
        hasClicked: false,
        searchResultText: "Loading..",
        filters: []
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
          document.getElementById("headline").style.display="none";
          e.style.top = "3%";
          this.$store.commit("setHasSearched", true)
          console.log("set to true")
          document.getElementById("forAnimationOnly").style.top = "7.5vh"

        setTimeout(() => this.searchResultText = "Cannot find any hotels matching searchphrase", 1500)
        }
      },

      onFilter(filter){
        console.log(filter)
        this.filters = filter
      }
    },

    mounted(){
      this.hasClicked = this.$store.getters.getHasSearched
      if(this.hasClicked == true){
          var e = document.getElementById('search')
          e.style.top = "3%";
      }
    }
  
};
</script>

<style scoped>
#search {
  position: absolute;
  top: 25%;
  left: 30%;
  width: 40%;
  transition: bottom 0.5s;
}

.home {
  margin: none;
  overflow: hidden;
}
</style>
