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
        <h4>Distances</h4>
        <p>Downtown: {{ card.distanceDowntown }} km</p>
        <p>Beach: {{ card.distanceBeach }} km</p>
      </div>
      <div v-if="card.comfortList != ''" class="cList">
        <h4>Comforts</h4>
        <ol id="comfyList">
          <li v-for="(comfort, index) in card.comfortList" :key="index">
            <p>
              {{comfort}}
            </p>
          </li>
        </ol>
      </div>
      <div v-if="card.attractionList != ''" class="aList">
        <h4>Attractions</h4>
        <ol id="attractiveList">
          <li v-for="(attraction, index) in card.attractionList" :key="index">
            <p>
              {{attraction}}
            </p>
          </li>
        </ol>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  /*
  hämtar information från HotelList via props
  */
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
  color: rgba(0, 0, 0, 0.5);
  mix-blend-mode: difference;
  margin-top: 10px;
  }

#price{
  position: absolute;
  white-space: nowrap;
  z-index: 2;
  bottom: 10px;
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
  display: flex;
  flex-wrap: wrap;
  width: 25vw;
  height: 20vw;
  justify-content: space-evenly;

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

#distances {
  justify-content: space-between;
}

img{
  width: 25vw;
  height: 20vw;
  border-radius: .8vw;
  transition: filter .3s;
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