<template>
  <div id="hotelCard">
    <p>Hotel Name: {{ card.hotelName }}</p>
    <p>City: {{ card.city }}</p>
    <p>Total score: {{ card.total_score }}</p>
    <p>Temperature: {{ card.temperature }}</p>
    <p>Country: {{ card.country }}</p>
  </div>
</template>

<script>
export default {
  props: ["card"],
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