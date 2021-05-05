<template>
  <div class="about">
    <input type="text" placeholder="Sök..." v-model="searchPhrase" id="searchBar">
    <input type="text" placeholder="From - YYYY-MM-DD" v-model="fdate" id="fromDate">
    <input type="text" placeholder="To - YYYY-MM-DD" v-model="tdate" id="toDate">
    <button @click="(searchFor(searchPhrase))">Search</button>
  </div>
</template>

<script>

export default {

  data(){
    return{
      searchPhrase: '',
      
    }
  },
 

  methods:{
    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)
      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
    },
  },

  mounted(){
    
  }
}
</script>