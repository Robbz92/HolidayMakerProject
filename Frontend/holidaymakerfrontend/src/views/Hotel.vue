<template>
  <div class="hotel">
    <div id="info">
      <div class="theInfo">
        <ul v-for="(info, index) in getInfo" :key="index">
          <li id="justInfo">
            <img id="picture" :src="info.hotel_img" />
            <div class="text">
              <h3>{{ info.name }}</h3>
              <p>Downtown distance: {{ info.distance_downtown }} km</p>
              <p>Address: {{ info.address }}</p>
              <p>Beach distance: {{ info.distance_beach }} km</p>
              <p>Score: {{ info.total_score }}/5</p>
              <ul v-for="(temperature, index) in getTemperature" :key="index">
                <li id="justTemp">
                  <p>Temperature: {{ temperature.temperature }} C</p>
                  <p>City: {{ temperature.City }}</p>
                  <p>Country: {{ temperature.Country }}</p>
                </li>
              </ul>
            </div>
            <div class="attComf">
              <div class="attractions">
                <h3>Attractions</h3>
                <ul v-for="(attraction, index) in getAttraction" :key="index">
                  <li id="justAttraction">
                    <p>{{ attraction.name }}</p>
                  </li>
                </ul>
              </div>
              <div class="comforts">
                <h3>Comforts</h3>
                <ul v-for="(comfort, index) in getComfort" :key="index">
                  <li id="justComfort">
                    <p>{{ comfort.name }}</p>
                  </li>
                </ul>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div id="allRooms">
      <h2>Rooms</h2>
      <ul v-for="(room, index) in getRoomList" :key="index">
        <li id="liRoom">
          <div class="roomPicture">
            <img id="roomPic" :src="room.room_img" />
          </div>
          <div class="roomText">
            <h5>Room type:</h5>
            <p>{{ room.type }}</p>
            <p>Size: {{ room.size }}</p>
            <p>Description: {{ room.description }}</p>
            <p>Price: {{ room.price }} kr</p>
          </div>
        </li>
      </ul>
    </div>
    <div id="holder">
      <div class="reviews">
        <h2>Reviews</h2>
        <ul v-for="(review, index) in getReviews" :key="index">
          <li id="justOne">
            <p>{{ review.first_name }} {{ review.last_name }}</p>
            <p>{{ review.score }}/5</p>
            <p>{{ review.text }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    getReviews() {
      return this.$store.getters.getReviews;
    },
    getInfo() {
      return this.$store.getters.getInformation;
    },
    getTemperature() {
      return this.$store.getters.getTemperature;
    },
    getAttraction() {
      return this.$store.getters.getAttractions;
    },
    getComfort() {
      return this.$store.getters.getComforts;
    },
    getRoomList() {
      console.log(this.$store.getters.getRoomList);
      return this.$store.getters.getRoomList;
    },
  },
};
</script>

<style scoped>
#justInfo > img {
  margin-top: 2em;
  margin-left: 2em;
  margin-right: 2em;
  padding: 0px;
  width: 30%;
}

#allRooms {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.2);
  width: 75%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
}

#allRooms h2 {
  margin-bottom: 2em;
}

#liRoom {
  display: flex;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

.roomPicture {
  margin: 0;
  padding: 0;
  width: 400px;
  height: auto;
  overflow: hidden;
}

#roomPic {
  height: auto;
  width: 100%;
}

.roomText {
  text-align: left;
}

#holder {
  display: flex;
  justify-content: center;
}

h3 {
  margin: 0;
}

.text,
.attractions,
.comforts {
  text-align: left;
  margin-top: 2em;
  margin-right: 6em;
}

.reviews {
  margin-top: 35px;
  width: 65%;
  border: 1px solid rgb(187, 184, 184);
  padding: 10 px;
  border-radius: 5px;
}
#justOne {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  width: 95%;
  background-color: white;
  margin-bottom: 20px;
  padding: 4px;
  border-radius: 5px;
}

#justInfo {
  display: flex;
}

.hotel {
  width: 70%;
  backdrop-filter: blur(10px);
  background-color: rgba(255, 255, 255, 0.15);
  margin: 0 auto;
  height: 100vh;
  overflow: auto;
  position: fixed;
  left: 15%;
  top: 0;
}

ul {
  list-style-type: none;
  margin: 0px;
  padding: 0px;
}

.attComf {
  display: flex;
}

::-webkit-scrollbar {
  display: none;
}
</style>