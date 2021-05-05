<template>
  <div id="hotelCard">
    <p>Hotel Name: {{ card.name }}</p>
    <p>Address: {{ card.address }}</p>
    <p>Total score: {{ card.totalScore }}</p>
    <p>Distance down town: {{ card.distanceDowntown }}</p>
    <p>distance to beach: {{ card.distanceBeach }}</p>
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