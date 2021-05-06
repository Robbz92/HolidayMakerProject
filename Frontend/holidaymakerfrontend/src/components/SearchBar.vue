<template>
  <div class="about">
    <input type="text" placeholder="Sök..." v-model="searchPhrase" id="searchBar">
      <input type="text" placeholder="from YYYY_MM_DD" v-model="fromDate" id="searchBar">
      <input type="text" placeholder="to YYYY_MM_DD" v-model="toDate" id="searchBar">
    <button @click="searchFor(searchPhrase), sendFromDate(fromDate), sendToDate(toDate) ">Search</button>
   
    <h6>From...</h6>
    <date-picker id="fromDate" v-model="fomDate" language="en"  type="date" format="YYYY-MM-DD" width="500"></date-picker>
    <h6>To...</h6>
    <date-picker id="toDate" v-model="tDate" language="en"  type="date" format="YYYY-MM-DD" width="500"></date-picker> 
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
      fromDate: '',
      toDate: '',
      
    }
  },
 

  methods:{
    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)

      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
    },
    sendFromDate(fromDate){
      console.log(fromDate)
      this.$store.commit('setFromDate',fromDate)
    },
    sendToDate(toDate){
      console.log(toDate)
      this.$store.commit('setToDate', toDate)
      
    }
  },

  mounted(){
    
  }
}
</script>
<style scoped>
.about {
  display: block;
  
}
</style>