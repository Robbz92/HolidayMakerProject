<template>
<div class="info"></div>
  <div id="hotelCard">
    <div id="imageBlock">
      <img :src="imageUrl">
    </div>
    <h2 id="hotelName">{{card.name}}</h2>
    <div id="priceDiv">
      <h2 id="price">From: {{card.price}}:-</h2>
    </div>
    <div id="descriptionBlock">
      <div id="distances">
        <h3>Distances:</h3>
        <p>Distance down town: {{ card.distanceDowntown }}</p>
        <p>Distance to beach: {{ card.distanceBeach }}</p>
      </div>
      <div v-if="card.comfortList != ''">
        <h3>Comforts:</h3>
        <ol id="comfyList">
          <li v-for="(comfort, index) in card.comfortList" :key="index">
            <p>
              {{comfort}}
            </p>
          </li>
        </ol>
      </div>
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
  position: relative;
  width: 25vw;
  height: 20vw;
  margin: 15px;
}

#comfyList{
  list-style: none;
  padding: 0;
}

#distances{
  margin: auto;
}

#bigger{
  font-weight: 600;
  font-size: larger;
  margin-top: 25px;
  margin-bottom: 5px;
}

#hotelName{
  text-shadow:.5px .5px 1px rgba(255, 255, 255, .5),.5px -.5px 1px rgba(255, 255, 255, .5),
                     -.5px .5px 1px rgba(255, 255, 255, .5),-.5px -.5px 1px rgba(255, 255, 255, .5);
  color: rgba(0, 0, 0, .7);
  mix-blend-mode: difference;
  }

#price{
  position: absolute;
  white-space: nowrap;
  z-index: 2;
  bottom: 0;
  right: 1vw;
  color: red;
  text-shadow:.5px .5px 1px rgba(255, 255, 255, .5),.5px -.5px 1px rgba(255, 255, 255, .5),
                     -.5px .5px 1px rgba(255, 255, 255, .5),-.5px -.5px 1px rgba(255, 255, 255, .5);
}

#priceDiv{
  position: relative;
}

#descriptionBlock{
  position: absolute;
  z-index: 3;
  display: flexbox;
  flex-wrap: wrap;
  width: 25vw;
  height: 20vw;

  color: black;
  text-align: left;

  background-color: white;
  opacity: 0%;
  transition: opacity .3s;
  border-radius: .8vw;
}

#descriptionBlock:hover{
  opacity: 45%;
}

#imageBlock {
  z-index: 1;
  position: absolute;
  object-fit: contain;
  width: 25vw;
  height: 20vw;
  overflow: hidden;
  border-radius: .8vw;
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
  border-radius: .8vw;
  transition: filter .3s;
}

p{
  margin-left: 25px;
  margin-top: 25px;
  font-weight: 600;
}

h2{
  position: relative;
  z-index: 2;
  font-weight: 900;
  margin: auto;
  margin-top: 0;
  opacity: 100%;
  transition: filter .3s;
}
</style>