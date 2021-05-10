<template>
  <div class="bar">
    <div class="search-container">
      <h6>Search hotel, city or country</h6>
      <input type="text" placeholder="..." v-model="searchPhrase" id="searchBar">
    </div>
    <div class="date">
      <div class="checkIn">
        <h6>Check-in</h6>
        <date-picker id="fromDate" v-model="fromDate" language="en" type="date" format="YYYY-MM-DD" :style="styleObject"></date-picker>
      </div>
      <div class="checkOut">
        <h6>Check-out</h6>
      <date-picker id="toDate" v-model="toDate" language="en" type="date" format="YYYY-MM-DD" :style="styleObject"></date-picker>
      </div>      
    </div>
    <div class="go-container">
      <button @click="searchFor(searchPhrase), sendFromDate(fromDate), sendToDate(toDate) ">Go!</button>
    </div>
  </div>
</template>

<script>
import DatePicker from "vue3-datepicker";
/*
Datepicker är ett bibliotek för att lättast hantera datum
*/
export default {
name:'SearchBar',
  components: {
    DatePicker
  },
  props: {
    
  },

  data(){
    return{
      // Skapar styleObject för att kunna kalla på datepickers attribut. 
      // Går inte att CSSa på dessa inputs med id eller class.
      styleObject: {
        outline: 'none',
        border: 'none',
        height: '1.65em',
        width: '10em',
        fontFamily: 'inherit'
      },
      searchPhrase: '',
      fromDate: '',
      toDate: '',
      today: ''
    }
  },

  methods:{

    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)

      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
      this.$router.push("/");
      this.$parent.onSearch();
    },
      /*
      För att formatera datumet rätt
      */
     sendFromDate(fromDate){
       var d = new Date(fromDate),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
        
    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    var newDate = [year, month, day].join('-');

      console.log(newDate)
      this.$store.commit('setFromDate',newDate)
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
    }
  },

  mounted(){
    console.log(this.today)
  }
}
</script>

<style scoped>
.bar {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  background: linear-gradient(rgba(255,255,255,0.5), rgba(120,120,120,0.5) 100%);
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