<template>
  <div>
    <h3>My Bookings</h3>
    <div class="bookings">
      <ul v-for="(bookings, index) in getAllMyBookings" :key="index">
        <li id="booking">
          <div class="hotelPicture">
            <img id="hotelPic" :src="bookings.hotel_img" />
          </div>
          <h4>{{ bookings.name }}</h4>
          <p>Date: {{ bookings.from_date }} - {{ bookings.to_date }}</p>
          <p>Booked rooms: {{ bookings.BookedRooms}}</p>
          <h4>Total price: {{ bookings.total_cost }} kr</h4>
          <button @click="sendBookingId(bookings.id)">Review</button>
          <button @click="editBooking(bookings.id), getBookedRooms(bookings.id)">Edit Booking</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyBookings",

  data() {
    return {
      score: 0,
      text: "",
      hotel_id: "",
      show: true,
    };
  },
  computed: {
    getAllMyBookings() {
      return this.$store.getters.getMyBookings;
    },
  },
  mounted() {
    this.$store.dispatch("fetchMyBookings");
  },
  methods: {
    sendBookingId(bookingId) {
      this.$store.dispatch("fetchClickedBooking", bookingId);
      this.$router.push("/review");
    },
    getBookedRooms(bookingId) {
      this.$store.dispatch("fetchBookedRoom", bookingId);
      this.$router.push("/editBooking")
    },

    editBooking(bookingId) {
      this.$store.dispatch("fetchClickedBooking", bookingId);
      this.$router.push("/editBooking")
    }
  },
};
</script>

<style>
.bookings {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.438);
  width: 90%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
}
#booking {
  display: flex;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}
.roomPicture,
.hotelPicture {
  margin: 0;
  padding: 0;
  width: 300px;
  height: auto;
  overflow: hidden;
}
p,
h4 {
  margin: 0.5em;
}

#roomPic,
#hotelPic {
  height: auto;
  width: 100%;
}
</style>