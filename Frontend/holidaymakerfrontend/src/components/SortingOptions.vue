<template>
  <div class="filters">
    <button @click="distanchBeach()">Distance beach</button>
    <button @click="distanceCentrum()">Distance centrum</button>
    <button class="ScaleUP" @click="sortArrayUp()">Lowest price</button>
    <button class="ScaleDown" @click="sortArrayDown()">Highest price</button>
    <button @click="highestRating()">Highest rating</button>
    <button @click="lowestRating()">Lowest rating</button>
  </div>
</template>

<script>
export default {
    methods:{
      /*
      Kollar om "a" är större/mindre än "b"
      om "a" är större/mindre än "b" så byta indexplats på elmentet
      om inte gå vidare
      (gäller för alla sorteringsalgoritmer)
      Filteroption är kopplad till Home View
      */
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
      return hotels.sort((a, b) =>
        a.distanceBeach > b.distanceBeach ? 1 : -1
      );
    },
    distanceCentrum() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) =>
        a.distanceDowntown > b.distanceDowntown ? 1 : -1
      );
    },
    highestRating() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) =>
        a.totalScore > b.totalScore ? -1 : 1
      );
    },
    lowestRating() {
      let hotels = this.$store.getters.getHotels;
      return hotels.sort((a, b) =>
        a.totalScore > b.totalScore ? 1 : -1
      );
    },
  },
};
</script>

<style scoped>

.filters {
  margin-top: 20px
}

.filters button {
  border: none;
  background-color: rgb(72, 210, 228);
  color: white;
  font-family: inherit;
  font-weight: bold;
  font-size: 1em;
  padding: 10px;
  margin-right: 1em;
  border-radius: 50px;
  cursor: pointer;
  transition: 0.2s ease;
  outline: none;
}

.filters button:hover {
  background-color: white;
  color: rgb(72, 210, 228);
}

</style>