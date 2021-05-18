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
      <div class="groupAdults">
        <h6>People</h6>
      <ul>
        <li  @click="showAll()">
         <h6>Adults: {{adults}} -
            Children: {{children}}
         </h6>
        </li>
        <div v-if="show">
        <li>
          Adults: 
        <select id="person" v-model="adults">
          <option v-for="(person, index) in cuantity" :key="index">
              {{person}}
          </option>
        </select>
        </li>
        
         <li>
          Children: 
        <select id="person" v-model="children">
          
          <option v-for="(children, index) in cuantity" :key="index">
              {{children}}
          </option>
          
        </select>
          </li>
        <ul id="children" v-for="child in Number(children)" :key="child" >
          <li>
            Age of child: {{child}}
            <select id="person" v-model="age"> 
              <option v-for="(age, index) in cuantity" :key="index">
              {{age}}
              </option>
            </select>
          </li>
        </ul>
      </div>
      </ul>  
      </div>
      <div class="rooms">
        <h6>Rooms</h6>
        <select id="room" v-model="room">
          <option v-for="(room, index) in cuantity" :key="index">
              {{room}}
          </option>
        </select>
      </div>
    </div>
    <div class="go-container">
      <button
        @click="
          searchFor(searchPhrase), sendFromDate(fromDate), sendToDate(toDate), sendSize(room, adults, children)
        "
      >
        Go!
      </button>
    </div>
    <filterOptions />
  </div>
</template>

<script>
import DatePicker from "vue3-datepicker";
import moment from "moment";
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
    filterOptions,
  },
  props: {},

  data() {
    return {
      room: 1,
      adults:1,
      children:0,
      age:0,
      show:false,
      cuantity: [0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12, 13, 14, 15, 16],
      
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
        this.$router.push("/");
        this.$parent.onSearch();
      } else if(this.$store.getters.getTempSearch != 0){
        this.tempSearch(this.$store.getters.getTempSearch)
        this.$router.push("/");
      } else if(this.$store.getters.getFilterAmmount >= 3){
        this.$store.dispatch("fetchAllHotels");
        this.$router.push("/");
        this.$parent.onSearch();
      } else
      alert("You need to search by phrase, temperature or at least 3 filters")
    },

    fetchAll() {
      this.$store.dispatch("fetchAllHotels");
      this.$router.push("/");
      this.$parent.onSearch();
    },

    tempSearch(temp) {
      console.log(temp);
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

    sendToDate(toDate){
    var d = new Date(toDate),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
        
    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    var newDate = [year, month, day].join('-');
      console.log(newDate)
      this.$store.commit('setToDate', newDate)
    },
    sendSize(room, adults, children){
      var person= (Number(adults)+Number(children));
      console.log(person);
      var size = person/room;
      console.log(size);
      this.$store.commit("setSize", size)
    },
    showAll(){
      this.show=!this.show
    }
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
  width: 50em;
}
ul, li {

  outline: none;
  border: none;
  height: 1.5em;
  background-color: white;
  list-style-type: none;
 margin-right: 1em;
 margin-left: 1em;
}
#children{
  margin-bottom: 1.2em;
  width:100%;
  background-color:white;
  display:flex;
  justify-content: space-evenly;

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