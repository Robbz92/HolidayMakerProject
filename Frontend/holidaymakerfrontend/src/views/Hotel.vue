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
            <div
              class="favImg"
              id="favImg"
              @click="addFavorite(info.id)"
              v-if="loggedInUser"
            >
              <img
                src="../assets/heart-regular.svg"
                id="heart"
                v-if="!isFavorite"
              />
              <img src="../assets/heart-solid.svg" id="heart" v-else />
            </div>
          </li>
        </ul>
      </div>
    </div>
    <button @click="toggleSearchMenu" id="menuButton">
      <p v-if="!showUpdateSearch">Show search menu</p>
      <p v-else>Hide search menu</p>
    </button>
    <!-- Div som håller hela UPDATE datum och personer -->
    <div id="updateSearch" v-if="showUpdateSearch">
      <!-- Datum för att ändra inne på hotel -->
      <div class="date">
        <div class="checkIn">
          <h6>Check-in</h6>
          <date-picker
            id="fromDate"
            v-model="fromDate"
            :lowerLimit="fromDateLimit"
            language="en"
            type="date"
            format="YYYY-MM-DD"
            :style="styleObject"
          ></date-picker>
        </div>
        <div class="checkOut">
          <h6>Check-out</h6>
          <date-picker
            id="toDate"
            v-model="toDate"
            :lowerLimit="toDateLimit"
            language="en"
            type="date"
            format="YYYY-MM-DD"
            :style="styleObject"
          ></date-picker>
        </div>
      </div>
      <!-- People container, för att ändra antalet personer i bokningen -->
      <div class="groupAdults">
        <div class="label-container">
          <h6>People</h6>
          <div class="label" @click="showAll()">
            <label>Adults: {{ adults }} - Children: {{ children }}</label>
          </div>
        </div>
        <div id="ppl-list" v-if="show">
          <div id="adultChildren">
            <li>
              Adults:
              <select id="person" v-model="adults">
                <option v-for="(person, index) in cuantity" :key="index">
                  {{ person }}
                </option>
              </select>
            </li>
            <li>
              Children:
              <select id="person" v-model="children">
                <option v-for="(children, index) in cuantity" :key="index">
                  {{ children }}
                </option>
              </select>
            </li>
          </div>

          <div id="children">
            <li v-for="child in Number(children)" :key="child">
              Age of child: {{ child }}
              <select id="childAge" v-model="childrenAges[child]">
                <option v-for="(age, index) in cuantity" :key="index">
                  {{ age }}
                </option>
              </select>
            </li>
          </div>
        </div>
      </div>
      <!-- Ändra antalet rum  -->
      <div class="rooms">
        <h6>Rooms</h6>
        <select id="room" v-model="room">
          <option v-for="(room, index) in cuantity" :key="index">
            {{ room }}
          </option>
        </select>
      </div>
      <button @click="updateSearch()" id="updateButton">Update search</button>
    </div>
    <div id="allRooms">
      <h2>Rooms</h2>
      <h3 v-if="getRoomList.length == 0">
        No rooms were found according to the search.
      </h3>
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
              <p>
                Total price: {{ calculatePrice(room.price) }}:- for
                {{ getNumberOfDays }} nights.<br /><br />
                Price per night: {{ room.price }}:-
              </p>
            </div>
          </div>
          <div class="booking">
            <button @click="addRoom(room)">Book</button>
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
import ShoppingList from "../components/ShoppingList.vue";
import DatePicker from "vue3-datepicker";

var today = new Date();

function dateLimit() {
  const limitDate = new Date();
  limitDate.setDate(today.getDate() + 1);
  return limitDate;
}

export default {
  data() {
    return {
      roomList: [],
      hotelName: "",
      fromDate: "",
      toDate: "",
      fromDateLimit: new Date(),
      toDateLimit: dateLimit(),
      room: 1,
      adults: 1,
      children: 0,
      childrenAges: [],
      show: false,
      cuantity: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
      showUpdateSearch: false,
    };
  },

  components: {
    ShoppingList,
    DatePicker,
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
    loggedInUser() {
      return this.$store.state.loggedInUser;
    },
    isLoggedIn() {
      return this.loggedInUser != null;
    },
    getFavoriteList() {
      return this.$store.getters.getFavoriteList;
    },
    isFavorite() {
      let favList = [];
      let name = this.getInfo[0].name;

      this.getFavoriteList.forEach((favorite) => {
        favList.push(favorite.name);
      });

      if (favList.includes(name)) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    updateSearch() {
      this.sendSizeSearch();
      this.sendDateSearch();
    },

    sendDateSearch() {
      var d = new Date(this.fromDate),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      var newDate = [year, month, day].join("-");
      this.$store.commit("setFromDate", newDate);

      (d = new Date(this.toDate)),
        (month = "" + (d.getMonth() + 1)),
        (day = "" + d.getDate()),
        (year = d.getFullYear());

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      newDate = [year, month, day].join("-");
      this.$store.commit("setToDate", newDate);

      this.$store.dispatch("fetchHotel");
    },

    sendSizeSearch() {
      this.$store.state.roomAmount = this.room;
      this.$store.state.adults = this.adults;
      this.$store.state.children = this.children;
      this.$store.state.childrenAges = this.childrenAges;

      var person = Number(this.adults) + Number(this.children);
      var size = person / this.room;
      this.$store.commit("setSize", size);
    },

    addRoom(room) {
      this.roomList.push(room);
      localStorage.setItem("localStorageRoom", JSON.stringify(this.roomList));
      document.getElementsByClassName("room" + room.id)[0].style.display =
        "none"; //adds a roomobject to the basket
    },

    showRoom(room) {
      document.getElementsByClassName("room" + room)[0].style.display = "flex";
    },

    calculatePrice(price) {
      let calculatedPrice = price * this.getNumberOfDays;
      return calculatedPrice;
    },

    // En favorit innehåller user_id och hotel_id
    // Vi hämtar den inloggade användarens id via "store.state"
    // Sedan skickar vi hotel_id in till funktionen via "id"
    // Alla ens favoriters namn läggs i en ny array
    // för att kunna använda "includes()" och kolla om detta
    // hotell finns med i ens favoriter
    async addFavorite(id) {
      let favoriteCredentials = {
        userId: this.$store.state.loggedInUser.id,
        hotelId: id,
      };

      let favList = [];
      this.hotelName = this.getInfo[0].name;

      this.getFavoriteList.forEach((favorite) => {
        favList.push(favorite.name);
      });

      if (favList.includes(this.hotelName)) {
        alert("You already have this as a favorite.");
      } else {
        fetch("http://localhost:3000/auth/favorites", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(favoriteCredentials),
        });
        alert("Favorite added!");
        this.$store.dispatch("fetchFavorites");
      }
    },

    showAll() {
      this.show = !this.show;
    },

    toggleSearchMenu() {
      this.showUpdateSearch = !this.showUpdateSearch;
    },
  },

  beforeMount() {
    if (this.$store.state.loggedInUser) {
      this.$store.dispatch("fetchFavorites");
    }

    this.fromDate = new Date(this.$store.state.fromDate);
    this.toDate = new Date(this.$store.state.toDate);
  },

  async mounted() {
    if (this.$store.state.adults != 0) {
      this.adults = this.$store.state.adults;
    }

    if (this.$store.state.children != 0) {
      this.children = this.$store.state.children;
    }

    if (this.$store.state.roomAmount != 0) {
      this.room = this.$store.state.roomAmount;
    }

    if (this.$store.state.childrenAges != "") {
      this.childrenAges = this.$store.state.childrenAges;
    }
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

#allRooms p {
  margin: 0;
  margin-top: 5px;
  margin-bottom: 30px;
}

#liRoom {
  display: flex;
  justify-self: center;
  justify-content: space-between;
  margin-top: 0;
  margin: 1em;
  padding-bottom: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

.roomPicture {
  margin: 0;
  padding: 0;
  min-width: 450px;
  max-width: 450px;
  max-height: 400px;
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

h4 {
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

.booking button {
  width: 100px;
  height: 45px;
  font-size: 20px;
  margin-top: 280px;
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

.favImg {
  height: 40px;
  width: 40px;
  margin-top: 2em;
}

#heart:hover {
  cursor: pointer;
  filter: invert(1);
  transition: 0.2s ease;
}

.label {
  margin: 0 auto;
  background-color: white;
  margin-bottom: 0.8em;
  height: 1.43em;
  border: none;
  width: 200px;
  border-radius: 2px;
  display: flex;
}

.label label:hover {
  cursor: pointer;
}

.label label {
  font-family: inherit;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  margin-top: 2.5px;
}

#ppl-list {
  z-index: 5;
  display: block;
  position: absolute;
  top: 50.5%;
  right: 42%;
  background-color: white;
  border-radius: 0 0 5px 5px;
}

#adultChildren {
  display: flex;
  padding: 10px;
  outline: none;
}

#person {
  outline: none;
}

#childAge {
  margin: 5px;
  margin-right: 10px;
  outline: none;
}

li {
  list-style: none;
  outline: none;
}

#menuButton {
  width: 200px;
  height: 45px;
}

#menuButton p {
  margin: 0;
}

#updateSearch {
  margin: 15px auto;
}

#updateSearch h6 {
  font-size: 13px;
  margin: 5px auto;
}

.date {
  display: flex;
  flex-direction: row;
  margin: 15px;
  margin-left: 38.8%;
}

#updateButton{
  margin-top: 15px;
}

.checkIn{
  margin: 5px;
}

.checkOut{
  margin: 5px;
}
</style>