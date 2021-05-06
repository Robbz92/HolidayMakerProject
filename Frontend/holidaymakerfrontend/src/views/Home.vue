<template>
  <div class="home">
    <hotelList/>
  </div>
</template>

<script>
import hotelList from "../components/HotelList.vue"

export default {
  name: 'Home',
    components:{
        hotelList
    },


}
</script>

<style scoped>
  .home{
    margin: none;
  }
</style>
