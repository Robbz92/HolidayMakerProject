<template>


  <div id="hotelCard">
    <p>Hotel Name: {{ card.hotelName }}</p>
    <p>City: {{ card.city }}</p>
    <p>Total score: {{ card.total_score }}</p>
    <p>Temperature: {{ card.temperature }}</p>
    <p>Country: {{ card.country }}</p>
    <p>Distance to beach: {{ card.distance_beach }} km</p>
    <p>Distance down town: {{ card.distance_downtown }} km</p>
  </div>
</template>

<script>
export default {
  props: ["card"],
  computed:{

  },
  methods:{
    
  },
};
</script>

<style scoped>
#hotelCard {
  display: block;
  width: 45vw;
  height: 20vh;
  background-color: gray;
  margin: 15px;
}
</style>