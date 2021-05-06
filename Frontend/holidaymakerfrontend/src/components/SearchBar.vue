<template>
  <div class="about">
    <input type="text" placeholder="Sök..." v-model="searchPhrase" id="searchBar" >
    <button @click="(searchFor(searchPhrase))">Search</button>
  </div>
</template>

<script>
export default {
  data(){
    return{
      searchPhrase: ''
    }
  },

  methods:{
    searchFor(phrase){
      console.log(phrase)
      this.$store.commit('setSearchPhrase',phrase)

      this.$store.commit('setFromDate', fromDate)
      this.$store.commit('setToDate', toDate)

      console.log(this.$store.getters.getSearchPhrase)
      this.$store.dispatch("searchFor")
    },
  },

  mounted(){
    
  }
}
</script>