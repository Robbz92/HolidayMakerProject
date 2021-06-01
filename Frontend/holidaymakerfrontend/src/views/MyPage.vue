<template>
<div id="Selectors">
  <label id="BookTitle" @click="toggleBookings">Bookings</label>
  <label id="FavTitle" @click="toggleFavorites">Favorites</label>
</div>
  <div>
    <MyBooking v-if="showBookings"/>
    <h3 v-if="showFavorites">Hej sebbe</h3>
  </div>
</template>

<script>
import MyBooking from "../components/MyBookings.vue"

export default {
  name: "MyBookings",

  components: {
    MyBooking
  },

  data(){
    return{
      showBookings: true,
      showFavorites: false
    }
  },

  methods:{
    toggleBookings(){
      if(this.showFavorites == true){
        this.showFavorites = false
      }
      this.showBookings = true
    },

    toggleFavorites(){
      if(this.showBookings == true){
        this.showBookings = false
      }
      this.showFavorites = true
    },
  }
};
</script>

<style scoped>
  #Selectors{
    font-size: 35px;
    display: flex;
    justify-content: center;
  }
  #Selectors > label{
    margin: 50px;
    font-weight: 600;
  }
</style>