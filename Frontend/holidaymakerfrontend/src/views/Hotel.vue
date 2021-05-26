<template>
  <div class="hotel">
    <div id="info">
      <div class="theInfo">
        <ul v-for="(info, index) in getInfo" :key="index">
          <li id="justInfo">
            <img id="picture" :src="info.hotel_img" />
            <div class="text">
              <h3>{{ info.name }}</h3>
              <p>Address: {{ info.address }}</p>
              <p>Downtown distance: {{ info.distance_downtown }} km</p>
              <p>Beach distance: {{ info.distance_beach }} km</p>
              <p>Review score: {{ info.total_score }}/5</p>
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
      <h3 v-if="getRoomList.length==0">No rooms were found according to the search.</h3>
      <ul v-for="(room, index) in getRoomList" :key="index">
        <li id="liRoom" :class="'room' + room.id">
          <div class="roomPicture">
            <img id="roomPic" :src="room.room_img" />
          </div>
          <div class="roomText">
            <div class="type">
              <h4>Room type</h4>
              <p>{{ room.type }}</p>
            </div>
            <div class="size">
              <h4>Size</h4>
              <p>{{ room.size }}</p>
            </div>
            <div class="desc">
              <h4>Description</h4>
              <p>{{ room.description }}</p>
            </div>
            <div class="price">
              <h4>Price</h4>
              <p>Total price: {{calculatePrice(room.price)}}:- for {{getNumberOfDays}} nights.<br><br> Price per night: {{ room.price }}:-</p>
            </div>
            <div class="booking">
              <button @click="addRoom(room)">Book</button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div id="holder">
      <div class="reviews">
        <h2>Reviews</h2>
        <ul v-for="(review, index) in getReviews" :key="index">
          <li id="review">
            <div class="user">
              <h4>{{ review.first_name }} {{ review.last_name }}</h4>
            </div>
            <p>"{{ review.text }}"</p>
            <h5>Score: {{ review.score }}/5</h5>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <ShoppingList :roomCard="roomList" />
</template>

<script>
import ShoppingList from '../components/ShoppingList.vue'
export default {
  data(){
    return{
      roomList: []
    };
  },

  components: {
    ShoppingList,
  },
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
      return this.$store.getters.getRoomList;
    },
    getNumberOfDays() {
      return this.$store.getters.getNumberOfDays;
    },        
  },
  methods: {
    addRoom(room){
      this.roomList.push(room);
      document.getElementsByClassName("room" + room.id)[0].style.display = "none";
    },

    showRoom(room){
      console.log(room)
      document.getElementsByClassName("room" + room)[0].style.display = "flex";
    },

    calculatePrice(price) {
      let calculatedPrice = price*this.getNumberOfDays;
      return calculatedPrice;
    },   
  }
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

#allRooms p {
  margin: 0;
  margin-top: 5px;
  margin-bottom: 30px;
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
  width: 450px;
  height: auto;
  overflow: hidden;
}

#roomPic {
  height: auto;
  width: 100%;
}

.roomText {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-left: 20px;
}

#holder {
  display: flex;
  justify-content: center;
}

h3 {
  margin: 0;
}

h4{
  margin: 0;
}

.text,
.attractions,
.comforts {
  text-align: left;
  margin-top: 2em;
  margin-right: 6em;
}

#justInfo {
  display: flex;
}

.hotel {
  top: 0;
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

::-webkit-scrollbar {
    display: none;
}

ul {
  list-style-type: none;
  margin: 0px;
  padding: 0px;
}

.attComf {
  display: flex;
}

.reviews {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.2);
  width: 75%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
}

.reviews h2 {
  margin-bottom: 2em;
}

#review {
  display: block;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

#review p {
  font-style: italic;
}

::-webkit-scrollbar {
  display: none;
}


</style>