<template>
  <div>
       <ul>
      <li>
        <div class="text">
          <h3>Name: {{getHotelInfo.name}}</h3>
          <p>Addres: {{getHotelInfo.address}}</p>
          <p>Total Score: {{getHotelInfo.totalScore}}</p>
          <p>Distance downTown: {{getHotelInfo.distanceDowntown}}</p>
          <p>Distance beach: {{getHotelInfo.distanceBeach}}</p>
        </div>          
      </li>
    </ul>
  </div>
</template>

<script>
export default {

  computed: {
    getHotelInfo(){
      return this.$store.getters.getHotelInfo;
    },
  },

  methods:{
      
  },

  async mounted(){
     this.$store.dispatch("fetchHotelInfo", this.$route.params.id)
  }
}
</script>

<style>

</style>