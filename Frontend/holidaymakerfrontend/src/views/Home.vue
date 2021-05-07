<template>
  <div class="home">
    <hotelList/>
    <div id="search">
      <searchBar/>
      <filterOptions v-if="gotClicked" />
    </div>
  </div>
</template>

<script>
import searchBar from "../components/SearchBar.vue"
import filterOptions from "../components/FilterOptions.vue"
import hotelList from "../components/HotelList.vue"

export default {
  name: 'Home',
    components:{
        hotelList,
        searchBar,
        filterOptions,
    },

    data(){
      return{
        hasClicked: false
      }
    },

    computed:{
      gotClicked(){
        return this.$store.getters.getHasSearched
      }
    },

    methods:{
      onSearch(){
        if(!this.hasClicked){
          var e = document.getElementById('search')
          e.style.bottom = "90%";
          this.$store.commit("setHasSearched", true)
          console.log("set to true")
          document.getElementById("forAnimationOnly").style.top = "5vh"
        }
      },
    },

    mounted(){
      this.hasClicked = this.$store.getters.getHasSearched
      if(this.hasClicked == true){
          var e = document.getElementById('search')
          e.style.bottom = "90%";
      }
    }
}
</script>

<style scoped>
  #search{
    position: absolute;
    bottom: 50%;
    left: 30%;
    width: 40%;
    transition: bottom .5s;
  }

  .home{
    margin: none;
    overflow: hidden;
  }
</style>
