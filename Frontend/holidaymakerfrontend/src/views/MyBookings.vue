<template>
  <div class="main-container" v-if="getAllMyBookings != ''">
    <h3 id="MyBookingsH3">My Bookings</h3>
    <EditBooking v-if="show" :fromDate="fromDate" :toDate="toDate" :total_cost="total_cost" :hotel_img="hotel_img" 
    :name="name" :id="id" :hotel_id="hotel_id" :room="{extra_bed_amount:extra_bed_amount, type:type, room_img:room_img, board:board,}"/>
    <div class="bookings" v-if="!show">
      <ul v-for="(bookings, index) in getAllMyBookings" :key="index">
        <li id="booking"> 
          <div class="hotelPicture">
             <h2>{{ bookings.name }}</h2>
            <img id="hotelPic" :src="bookings.hotel_img" />
          </div>
          <div class="booking-text">
            <table>
            <tr>
              <th>Address</th>
              <th>From Date</th>
              <th>To Date</th>
              <th>Booked Rooms</th>
              <th>Total Cost</th>
            </tr>
            <tr>
              <td>{{bookings.address}}</td>
              <td>{{ bookings.from_date }}</td>
              <td>{{ bookings.to_date }}</td>
              <td>{{ bookings.BookedRooms}}</td>
              <td>{{ bookings.total_cost }}</td>
            </tr>            
          </table>
          </div>
          <div class="buttons-container" >
            <button @click="sendBookingId(bookings.id)">Review</button>
            <button @click="editBooking(bookings)">Edit</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
  <div v-else>
    <h3>You haven't made any bookings yet</h3>
  </div>
</template>

<script>
import EditBooking from "../components/EditBooking.vue";
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

    async editBooking(booking) {
      this.$store.dispatch("fetchBookedRoom", booking.id);
      await this.$store.dispatch("fetchClickedBooking", booking.id);
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
      this.show = true;
      this.hotel_id=booking.hotel_id;
    },
    toggleShow(value){
      this.show = value
    }

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
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
  display: flex;
  
}
.booking-text{
  width: 100%;
  margin-top: 70px;
}
.booking-text table{
  width:100%;
}
.booking-text table th{
  margin: 0;
 
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
.buttons-container{
  display:flex;
  
}
.buttons-container button{
  width:75px;
  height: 40px;
  margin-top: 63px;
  margin-right: 10px;

}

</style>