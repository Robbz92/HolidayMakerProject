<template>
  <div class="main-container">
    <h3 id="MyBookingsH3">My Bookings</h3>
    <EditBooking v-if="show" :fromDate="fromDate" :toDate="toDate" :board="board" :extra_bed_amount="extra_bed_amount" :type="type"
    :total_cost="total_cost" :room_img="room_img" :hotel_img="hotel_img" :name="name" :id="id"/>
    <div class="bookings" v-if="!show">
      <ul v-for="(bookings, index) in getAllMyBookings" :key="index">
        <li id="booking">
          <div class="hotelPicture">
            <img id="hotelPic" :src="bookings.hotel_img" />
          </div>
          <h4>{{ bookings.name }}</h4>
          <p>{{bookings.address}}</p>
          <p>Date: {{ bookings.from_date }} - {{ bookings.to_date }}</p>
           <p>Booked rooms: {{ bookings.BookedRooms}}</p>
          <p>Price: {{ bookings.total_cost }} kr</p>
          <button @click="sendBookingId(bookings.id)">Review</button>
          <button @click="editBooking(bookings)">Edit Booking</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import EditBooking from "../views/EditBooking.vue";
export default {
  name: "MyBookings",

  components: {
    EditBooking,
  },

  data() {
    return {
      score: 0,
      text: "",
      hotel_id: "",
      show: false,
      fromDate: "",
      toDate: "",
      board:"",
      name:"",
      hotel_img:"",
      extra_bed_amount:"",
      type:"",
      room_img:"",
      total_cost:"",
      id:"",

    };
  },
  computed: {
    getAllMyBookings() {
      return this.$store.getters.getMyBookings;
    },
    getRoomsForEdit(){
      return this.$store.getters.getRoomsForEdit;
    }
  
  },
  mounted() {
    this.$store.dispatch("fetchMyBookings");
  },
  methods: {
    sendFromDate(fromDate){
      this.$store.commit("setFromDate", fromDate);
    },
    sendToDate(toDate){
      this.$store.commit("setToDate", toDate);
    },
    sendBookingId(bookingId) {
      this.$store.dispatch("fetchClickedBooking", bookingId);
      this.$router.push("/review");
    },


    editBooking(booking) {
      this.fromDate = booking.from_date;
      this.toDate = booking.to_date;
      this.board=booking.board;
      this.name = booking.name;
      this.hotel_img = booking.hotel_img;
      this.extra_bed_amount = booking.extra_bed_amount;
      this.type = booking.type;
      this.room_img = booking.room_img;
      this.total_cost = booking.total_cost;
      this.id=booking.id;
      this.$store.dispatch("fetchBookedRoom", booking.id);
      this.$store.dispatch("fetchClickedBooking", booking.id);
      this.show = true;
    },

  },
};
</script>

<style scoped>
.bookings {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.438);
  width: 90%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
  backdrop-filter: blur(5px);
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
#MyBookingsH3{
  font-size:35px;
}
</style>