<template>
  <div class="about">
    <h1>This is an about page</h1>
    <ul v-for="(hotelInformation, index) in getHotelInfo" :key="index">
      <li>
        <div class="text">
          <h3>Name: {{hotelInformation.name}}</h3>
          <p>Addres: {{hotelInformation.address}}</p>
          <p>Total Score: {{hotelInformation.totalScore}}</p>
          <p>Distance downTown: {{hotelInformation.distanceDowntown}}</p>
          <p>Distance beach: {{hotelInformation.distanceBeach}}</p>
        </div>          
      </li>
    </ul>
  </div>
</template>

<script>
export default {

  computed:{
    getHotelInfo(){
      return this.$store.getters.getHotelInfo;
    }
  },

  methods:{
  },

  mounted(){
     this.$store.dispatch("fetchHotelInfo")
    // this.$store.dispatch("fetchHotelInfo", this.$router.params.id)
  }
}
</script>
