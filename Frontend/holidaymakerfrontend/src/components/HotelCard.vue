<template>


  <div id="hotelCard">
    <div id="imageBlock">
      <img :src="imageUrl">
    </div>
    <h2 id="hotelName">{{card.name}}</h2>
    <div id="priceDiv">
      <h2 id="price">From: {{card.price}}:-</h2>
    </div>
    <div id="descriptionBlock">
      <div id="bigger">
        <span>Hotel Name: {{ card.name }}</span>
        <br>
        <span>Address: {{ card.address }}</span>
      </div>
      <div id="distances">
        <span v-if="card.totalScore != 0">Total score: {{ card.totalScore }}<br></span>
        <span>Distance down town: {{ card.distanceDowntown }}<br></span>
        <span>Distance to beach: {{ card.distanceBeach }}<br></span>
      </div>
      <br>
      <div v-if="card.comfortList != ''">
        <span id="comfort">comforts:</span>
        <ol id="comfyList">
          <li v-for="(comfort, index) in card.comfortList" :key="index">
            <span>
              {{comfort}}
            </span>
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
  width: 25vw;
  height: 20vw;
  margin: 15px;
}

#comfyList{
  list-style: none;
  padding: 0;
}

#comfort{
  font-weight: 600;
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

span{
  margin-left: 15px;
  font-size: large;
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
  border-radius: 1vw;
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
  z-index: 2;
  font-weight: 900;
  margin: auto;
  margin-top: 0;
  opacity: 100%;
  transition: filter .3s;
}
</style>