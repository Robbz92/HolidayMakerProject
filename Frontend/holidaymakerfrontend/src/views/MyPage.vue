<template>
<div id="Selectors">
  <label id="BookTitle" @click="toggleComponents(true)">Bookings</label>
  <label id="FavTitle" @click="toggleComponents(false)">Favorites</label>
</div>
  <div>
    <MyBooking v-if="showToggle"/>
    <Favorites v-if="!showToggle"/>
  </div>
</template>

<script>
import MyBooking from "../components/MyBookings.vue"
import Favorites from "../components/Favorites.vue"

export default {
  name: "MyBookings",

  components: {
    MyBooking,
    Favorites
  },

  data(){
    return{
      showToggle: true,
    }
  },

  methods:{
    toggleComponents(value){
      this.showToggle = value
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

  #BookTitle {
    cursor: pointer;
  }

  #BookTitle:hover {
    color: white;
    transition: 0.2s ease;
  }

  #FavTitle {
    cursor: pointer;
  }

  #FavTitle:hover {
    color: white;
    transition: 0.2s ease;
  }
</style>