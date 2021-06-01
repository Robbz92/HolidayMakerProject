<template>
  {{ currentDate() }}
  <div class="main-container" v-if="getAllMyBookings != ''">
    <EditBooking
      v-if="show"
      :fromDate="fromDate"
      :toDate="toDate"
      :board="board"
      :extra_bed_amount="extra_bed_amount"
      :type="type"
      :total_cost="total_cost"
      :room_img="room_img"
      :hotel_img="hotel_img"
      :name="name"
      :id="id"
    />
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
                <td>{{ bookings.address }}</td>
                <td>{{ bookings.from_date }}</td>
                <td>{{ bookings.to_date }}</td>
                <td>{{ bookings.BookedRooms }}</td>
                <td>{{ bookings.total_cost }}</td>
              </tr>
            </table>
          </div>
          <div class="buttons-container">
            <button
              v-if="checkIfReviewed(bookings.hotel_id)"
              @click="sendBookingId(bookings.id)"
            >
              Review
            </button>
            <button v-if="checkIfOutOfDate(index)" @click="editBooking(bookings)">
              Edit
            </button>
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
import EditBooking from "../views/EditBooking.vue";
export default {
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
      board: "",
      name: "",
      hotel_img: "",
      extra_bed_amount: "",
      type: "",
      room_img: "",
      total_cost: "",
      id: "",
    };
  },

  computed: {
    getAllMyBookings() {
      return this.$store.getters.getMyBookings;
    },
    getRoomsForEdit() {
      return this.$store.getters.getRoomsForEdit;
    },

    getHotelList() {
      return this.$store.getters.getHotelListForReview;
    },
  },

  mounted() {
    this.$store.dispatch("fetchMyBookings");
    this.$store.dispatch("fetchHotelListForReviews");
  },

  methods: {
    checkIfOutOfDate(index) {
      // formaterar datum samt kollar om datumet har gått ut, såfall ska man inte kunna editera något.
      if (this.getAllMyBookings[index].from_date > this.currentDate()) {
        return true;
      } else {
        return false;
      }
    },
    // Dagens datum
    currentDate() {
      var date = new Date().toISOString().slice(0, 10);
      return date;
    },

    checkIfReviewed(hotelId) {
      if (this.$store.getters.getHotelListForReview.includes(hotelId)) {
        return false;
      } else {
        return true;
      }
    },
    sendFromDate(fromDate) {
      this.$store.commit("setFromDate", fromDate);
    },
    sendToDate(toDate) {
      this.$store.commit("setToDate", toDate);
    },
    sendBookingId(bookingId) {
      this.$store.dispatch("fetchClickedBooking", bookingId);
      this.$router.push("/review");
    },

    editBooking(booking) {
      this.fromDate = booking.from_date;
      this.toDate = booking.to_date;
      this.board = booking.board;
      this.name = booking.name;
      this.hotel_img = booking.hotel_img;
      this.extra_bed_amount = booking.extra_bed_amount;
      this.type = booking.type;
      this.room_img = booking.room_img;
      this.total_cost = booking.total_cost;
      this.id = booking.id;
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
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
  display: flex;
}

.booking-text {
  width: 100%;
  margin-top: 70px;
}

.booking-text table {
  width: 100%;
}

.booking-text table th {
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

.buttons-container {
  display: flex;
}

.buttons-container button {
  width: 75px;
  height: 40px;
  margin-top: 63px;
  margin-right: 10px;
}
</style>