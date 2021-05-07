<template>
  <div class="bar">
    <input type="text" placeholder="Sök..." v-model="searchPhrase" id="searchBar">
    <div class="date">
      <div class="checkIn">
        <h6>Check-out date</h6>
        <date-picker id="fromDate" v-model="fromDate" language="en"  type="date" format="YYYY-MM-DD" width="500"></date-picker>
      </div>
      <div class="checkOut">
        <h6>Check-in date</h6>
      <date-picker id="toDate" v-model="toDate" language="en"  type="date" format="YYYY-MM-DD" width="500" ></date-picker>
      </div>      
    </div>
      <button @click="searchFor(searchPhrase), sendFromDate(fromDate), sendToDate(toDate) ">Search</button>
  </div>
</template>

<script>
import DatePicker from "vue3-datepicker";
export default {
name:'SearchBar',
  components: {
    DatePicker
  },
  data(){
    return{
      searchPhrase: '',
      today: new Date().toISOString().slice(0, 10),
      fromDate: this.today,
      toDate: this.today,
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
  background: linear-gradient(rgba(255,255,255,0.5), rgba(120,120,120,0.5) 100%)
}

.date {
  display: flex;
  justify-content: center;
  align-items: center;
}

.date h6 {
  padding: 0px;
  margin: 0px;
}
</style>