<template>
  <div id="hotelCard">
    <div id="imageBlock">
      <img :src="imageUrl">
    </div>
    <h2>{{card.name}}</h2>
    <div id="descriptionBlock">
      <p>Hotel Name: {{ card.name }}</p>
      <p>Address: {{ card.address }}</p>
      <p v-if="card.totalScore != 0">Total score: {{ card.totalScore }}</p>
      <p v-if="card.distanceDowntown != 0">Distance down town: {{ card.distanceDowntown }}</p>
      <p v-if="card.distanceBeach != 0">distance to beach: {{ card.distanceBeach }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["card", "imageUrl"],
};
</script>

<style scoped>
#hotelCard {
  display: flex;
  width: 25vw;
  height: 20vw;
  margin: 15px;
}

#descriptionBlock{
  position: absolute;
  width: 25vw;
  height: 20vw;

  color: black;
  text-align: left;

  background-color: white;
  opacity: 0%;
  transition: opacity .3s;
  border-radius: 1vw;
}

#descriptionBlock:hover{
  opacity: 45%;
}

#imageBlock {
  position: absolute;
  object-fit: contain;
  width: 25vw;
  height: 20vw;
  overflow: hidden;
  border-radius: 1vw;
}

#hotelCard:hover img{
  filter: blur(5px);
}

#hotelCard:hover h2{
  filter: opacity(0);
}

img{
  width: 25vw;
  height: 20vw;
  border-radius: 1vw;
  transition: filter .3s;
}

p{
  margin-left: 25px;
  margin-top: 25px;
  font-weight: 600;
}

h2{
  position: relative;
  font-weight: 900;
  text-shadow:.5px .5px 1px rgba(255, 255, 255, .5),.5px -.5px 1px rgba(255, 255, 255, .5),
                     -.5px .5px 1px rgba(255, 255, 255, .5),-.5px -.5px 1px rgba(255, 255, 255, .5);
  color: rgba(0, 0, 0, .7);
  mix-blend-mode: difference;
  margin: auto;
  margin-top: 0;
  opacity: 100%;
  transition: filter .3s;
}
</style>