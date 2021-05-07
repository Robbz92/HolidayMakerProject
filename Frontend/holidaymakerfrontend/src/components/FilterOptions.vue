<template>
    <div class="filters">
        <button @click="distanchBeach()">Distance beach</button>
        <button @click="distanceCentrum()">Distance centrum</button>
        <button class="ScaleUP" @click="sortArrayUp()">Lowest price</button>
        <button class="ScaleDown" @click="sortArrayDown()">Highest price</button>
    </div>
</template>

<script>
export default {
    methods:{
    sortArrayUp() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) => (a.price > b.price ? 1 : -1));
    },
    sortArrayDown() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) => (a.price > b.price ? -1 : 1));
    },
    distanchBeach() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) => (a.distanceBeach > b.distanceBeach ? 1 : -1));
    },
    distanceCentrum() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) => (a.distanceDowntown > b.distanceDowntown ? 1 : -1));
    },
    }
}
</script>

<style scoped>
  .filters{
    margin-top: 15px;
  }
</style>