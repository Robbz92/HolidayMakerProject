<template>
  <div class="main-container">
    <Book/>
    
   
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
</template>

<script>
import DatePicker from "vue3-datepicker";
import Book from "../components/MyBooking.vue";




export default {
  components: {
    Book,
    DatePicker,
   
    
  },

  data() {
    return {
      fromDate: new Date,
      toDate: new Date,
      styleObject: {
        outline: "none",
        border: "none",
        height: "1.65em",
        width: "10em",
        fontFamily: "inherit",
      },
    };
  },

};
</script>

<style scoped>
</style>