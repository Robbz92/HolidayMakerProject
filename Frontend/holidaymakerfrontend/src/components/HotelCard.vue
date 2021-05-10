<template>
<div class="info"></div>
  <div id="hotelCard">
    <div id="imageBlock">
      <img :src="imageUrl">
    </div>
    <div class="nameScore">
      <h2 id="hotelName">{{card.name}}</h2>
      <h3 id="hotelName">{{card.placeName}}</h3>
      <span v-if="card.totalScore != 0" id="score">
        <span v-for="(score, index) in card.totalScore" 
        :key="index" id="rating">⭐</span>
      </span><br>
    </div>
    <div id="priceDiv">
      <h2 id="price">From: {{card.price}}:-</h2>
    </div>

    <div id="descriptionBlock">
      <div id="distances">
        <h3>Distances:</h3>
        <p>Distance down town: {{ card.distanceDowntown }}</p>
        <p>Distance to beach: {{ card.distanceBeach }}</p>
      </div>
      <div v-if="card.comfortList != ''" class="cList">
        <span id="listTitle">comforts:</span>
        <ol id="comfyList">
          <li v-for="(comfort, index) in card.comfortList" :key="index">
            <p>
              {{comfort}}
            </p>
          </li>
        </ol>
      </div>
      <div v-if="card.attractionList != ''" class="aList">
        <span id="listTitle">attractions:</span>
        <ol id="attractiveList">
          <li v-for="(attraction, index) in card.attractionList" :key="index">
            <span>
              {{attraction}}
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
#rating{
  margin: 0;
}

#hotelCard {
  display: flex;
  position: relative;
  width: 25vw;
  height: 20vw;
  margin: 15px;
}

#listTitle{
  font-weight: 600;
}

.cList{
  float: left;
  margin-left: 1em;
}

.aList{
  float: left;
  margin-left: 1em;
}

#comfyList{
  list-style: none;
  padding: 0;
}

#attractiveList{
  list-style: none;
  padding: 0;
}

#bigger{
  font-weight: 600;
  font-size: larger;
  margin-top: 5px;
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

#hotelCard:hover h3{
  filter: opacity(0);
}

#hotelCard:hover #score{
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

h3{
  position: relative;
  z-index: 2;
  font-weight: 900;
  margin: auto;
  margin-top: 0;
  opacity: 100%;
  transition: filter .3s;
}

#score{
  position: relative;
  z-index: 2;
  font-weight: 900;
  margin: auto;
  margin-top: 0;
  opacity: 100%;
  transition: filter .3s;
}

.nameScore{
  margin: auto;
  margin-top: 0;
}
</style>