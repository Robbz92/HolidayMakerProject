<template>
  <div class="main-container">
    <div class="hotel-container">
      <ul v-for="(theBook, index) in getTheBooking" :key="index">
        <li id="list">
          <div class="hotelPicture">
            <img id="hotelPic" :src="theBook.hotel_img" />
          </div>
          <h2>{{ theBook.name }}</h2>
          <p>Total Cost: {{ theBook.total_cost }} kr</p>
          <p>Date: {{ theBook.from_date }} - {{ theBook.to_date }}</p>
          <p>Room: {{ theBook.type }}</p>
          <p>Board: {{ theBook.board }}</p>
          <p>Address: {{ theBook.address }}</p>
          <div class="buttons">
            <button id="deleteBooking" @click="deleteBooking(theBook.id)">Delete Booking</button>
            <button id="saveBooking">Save</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    getTheBooking() {
      return this.$store.getters.getClickedBooking;
    },
  },

  methods: {
      deleteBooking(id) {
          if(confirm("Are you sure you want to delete the booking?")){
              this.$store.dispatch("fetchDeleteBooking", id);
              this.$router.push("/myBookings");
          }
      },
  }
};
</script>

<style scoped>
.hotel-container {
  background-color: white;
}

#list {
  list-style: none;
  display: flex;
}
</style>