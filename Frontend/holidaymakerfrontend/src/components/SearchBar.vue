<template>
  <div class="headline" id="headline">
    <h1>
      Search by country, city, hotel<br />OR<br />
      by attractions, comforts and temperature!
    </h1>
  </div>
  <div class="bar-container">
    <div class="bar">
      <div class="search-container">
        <h6>Search hotel, city or country</h6>
        <input
          type="text"
          placeholder="..."
          v-model="searchPhrase"
          id="searchBar"
        />
      </div>
      <div class="date">
        <div class="checkIn">
          <h6>Check-in</h6>
          <date-picker
            id="fromDate"
            v-model="fromDate"
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
            language="en"
            type="date"
            format="YYYY-MM-DD"
            :style="styleObject"
          ></date-picker>
        </div>
      </div>
      <div class="go-container">
        <button
          @click="
            searchFor(searchPhrase),
              sendFromDate(fromDate),
              sendToDate(toDate),
              calculateDateDiff()
          "
        >
          Go!
        </button>
        <!--<button @click="fetchAll">fetchAll</button>-->
      </div>
    </div>
    <filterOptions />
  </div>
  <div class="temperature">
    <p>{{ temp }}</p>
    <vue3-slider
      v-model="temp"
      id="slider"
      :update="changeSliderColor()"
      :color="sliderColor"
      track-color="#FEFEFE"
      :max="30"
      :height="10"
    />
    <br />
    <button
      @click="
        tempSearch(temp),
          sendFromDate(fromDate),
          sendToDate(toDate),
          calculateDateDiff()
      "
    >
      HOT AND COLD
    </button>
  </div>
</template>

<script>
import DatePicker from "vue3-datepicker";
import moment from "moment";
import slider from "vue3-slider";
import filterOptions from "../components/FilterOptions.vue";

// Anger dagens datum
var today = new Date();

// Lägger på 7 dagar från dagens datum
function addDays() {
  const copy = new Date();
  copy.setDate(today.getDate() + 7);
  return copy;
}

/*
Datepicker är ett bibliotek för att lättare hantera datum
*/
export default {
  name: "SearchBar",
  components: {
    DatePicker,
    "vue3-slider": slider,
    filterOptions,
  },
  props: {},

  data() {
    return {
      // Skapar styleObject för att kunna kalla på datepickers attribut.
      // Går inte att CSSa på dessa inputs med id eller class.
      styleObject: {
        outline: "none",
        border: "none",
        height: "1.65em",
        width: "10em",
        fontFamily: "inherit",
      },
      sliderColor: this.changeSliderColor(),
      searchPhrase: "",
      fromDate: today, // Lägger in dagens datum som standardvärde i kalendern
      toDate: addDays(), // Lägger in 7 dagar framåt från dagens datum som standardvärde i kalendern
      numberOfDays: 0,
      temp: 0,
    };
  },

  methods: {
    searchFor(phrase) {
      console.log(phrase);
      this.$store.commit("setSearchPhrase", phrase);

      console.log(this.$store.getters.getSearchPhrase);
      if (this.searchPhrase != "") {
        this.$store.dispatch("searchFor");
      } else {
        this.$store.dispatch("fetchAllHotels");
      }
      this.$router.push("/");
      this.$parent.onSearch();
    },

    fetchAll() {
      this.$store.dispatch("fetchAllHotels");
      this.$router.push("/");
      this.$parent.onSearch();
    },

    tempSearch(temp) {
      console.log(temp);
      this.$store.commit("setSearchedTemperature", temp);
      this.$store.dispatch("fetchHotelByTemperature");
      this.$router.push("/");
      this.$parent.onSearch();
    },

    /*
      För att formatera datumet rätt
      */
    sendFromDate(fromDate) {
      var d = new Date(fromDate),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      var newDate = [year, month, day].join("-");
      this.$store.commit("setFromDate", newDate);
    },
    /*
      För att formatera datumet rätt
      */

    sendToDate(toDate) {
      var d = new Date(toDate),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      var newDate = [year, month, day].join("-");
      this.$store.commit("setToDate", newDate);
    },

    calculateDateDiff() {
      let start = moment(this.fromDate);
      let end = moment(this.toDate);
      let duration = moment.duration(end.diff(start));
      let days = duration.asDays();
      this.numberOfDays = Math.round(days);
      this.$store.commit("setNumberOfDays", this.numberOfDays);
    },

    onFilter(filter) {
      this.$parent.onFilter(filter);
    },

    changeSliderColor() {
      let color = "";
      if (this.temp <= 10) {
        color = "rgb(3,173,252)";
        this.sliderColor = color;
      }
      if (this.temp > 10 && this.temp <= 15) {
        color = "rgb(3,252,190)";
        this.sliderColor = color;
      }
      if (this.temp > 15 && this.temp <= 20) {
        color = "rgb(252,219,3)";
        this.sliderColor = color;
      }
      if (this.temp > 20 && this.temp <= 25) {
        color = "rgb(252, 148, 3)";
        this.sliderColor = color;
      }
      if (this.temp > 25) {
        color = "rgb(252, 69, 3)";
        this.sliderColor = color;
      }
    },
  },
};
</script>

<style scoped>

.bar-container {
  background: linear-gradient(rgba(255, 255, 255, 0.5),rgba(120, 120, 120, 0.5) 100%);
  border-radius: 15px;
}

.bar {
  display: flex;
  justify-content: center;
  align-items: center;
   
  padding-bottom: 1em;
  margin: 0 auto;
  width: 40em;
}

input {
  outline: none;
  border: none;
  height: 1.5em;
}

.search-container {
  margin-right: 1em;
}

.date {
  display: flex;
  margin-right: 2em;
}

h6 {
  margin-bottom: 1em;
}

.go-container button {
  border: none;
  background-color: rgb(72, 210, 228);
  color: white;
  font-family: inherit;
  font-weight: bold;
  font-size: 1.5em;
  padding: 15px;
  margin-top: 20px;
  border-radius: 100px;
  cursor: pointer;
  transition: 0.2s ease;
  outline: none;
}

.go-container button:hover {
  color: rgb(72, 210, 228);
  background-color: white;
}
</style>