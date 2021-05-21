<template>
  <div class="home">
    <hotelList :searchResultText="searchResultText" :filters="filters"/>
    <div id="search">
      <searchBar />
      <sortOptions v-if="gotClicked"/>
    </div>
  </div>
</template>

<script>
import searchBar from "../components/SearchBar.vue";
import sortOptions from "../components/SortingOptions.vue"
import hotelList from "../components/HotelList.vue";

export default {
  name: "Home",
  components: {
    hotelList,
    searchBar,
    sortOptions
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
          e.style.top = "3%";
          this.$store.commit("setHasSearched", true)
          console.log("set to true")
          document.getElementById("forAnimationOnly").style.top = "7.5vh";
          document.getElementById("headline").style.display="none";

        setTimeout(() => this.searchResultText = "Cannot find any hotels matching searchphrase", 1000)
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
          document.getElementById("headline").style.display="none";
      }
    }
  
};
</script>

<style scoped>
#search {
  position: absolute;
  top: 25%;
  transition: top 0.5s;
}

.home {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  overflow: hidden;
}
</style>
