<template>
  <div class="main-container" v-if="getAllMyBookings != ''">
    <EditBooking
      v-if="show"
      :fromDate="fromDate"
      :toDate="toDate"
      :total_cost="total_cost"
      :hotel_img="hotel_img"
      :name="name"
      :id="id"
      :hotel_id="hotel_id"
      :room="{
        extra_bed_amount: extra_bed_amount,
        type: type,
        room_img: room_img,
        board: board,
      }"
    />
    <div class="bookings" v-if="!show">
      <h3>Upcoming bookings</h3>
      <ul v-for="(newBooking, index) in newBookings" :key="index">
        <li id="booking">
          <div class="hotelPicture" @click="sendToHotel(newBooking)">
            <h2>{{ newBooking.name }}</h2>
            <img id="hotelPic" :src="newBooking.hotel_img" />
          </div>
          <div class="booking-text">
            <table>
              <tr>
                <th>Address</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Booked Rooms</th>
                <th>Total Price</th>
                <th>Payment Status:</th>
              </tr>
              <tr>
                <td>{{ newBooking.address }}</td>
                <td>{{ newBooking.from_date }}</td>
                <td>{{ newBooking.to_date }}</td>
                <td>{{ newBooking.BookedRooms }}</td>
                <td>{{ newBooking.total_cost }}:-</td>
                <td>{{ newBooking.payment_state }}</td>
              </tr>
            </table>
          </div>
          <div class="buttons-container">
            <button
              v-if="checkIfOutOfDate(index, newBooking.payment_state)"
              @click="editBooking(newBooking)"
            >
              Edit
            </button>
            <Stripe
              :fromMyBookings="false"
              :totalPrice="newBooking.total_cost"
              :bookingID="newBooking.id"
              v-if="
                newBooking.payment_state != 'Paid' &&
                newBooking.from_date >= currentDate()
              "
            />
          </div>
        </li>
      </ul>
    </div>
    <div class="bookings" v-if="!show">
      <ul v-for="(oldBooking, index) in oldBookings" :key="index" id="oldBookings">
        <h3>Previous bookings</h3>
        <li id="booking">
          <div class="hotelPicture" @click="sendToHotel(bookings)">
            <h2>{{ oldBooking.name }}</h2>
            <img id="hotelPic" :src="oldBooking.hotel_img" />
          </div>
          <div class="booking-text">
            <table>
              <tr>
                <th>Address</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Booked Rooms</th>
                <th>Total Price</th>
                <th>Payment Status:</th>
              </tr>
              <tr>
                <td>{{ oldBooking.address }}</td>
                <td>{{ oldBooking.from_date }}</td>
                <td>{{ oldBooking.to_date }}</td>
                <td>{{ oldBooking.BookedRooms }}</td>
                <td>{{ oldBooking.total_cost }}:-</td>
                <td>{{ oldBooking.payment_state }}</td>
              </tr>
            </table>
          </div>
          <div class="buttons-container">
            <button
              v-if="checkIfReviewed(oldBooking.hotel_id, index)"
              @click="sendBookingId(oldBooking.id)"
            >
              Review
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
import Stripe from "../components/StripeCheckout.vue";
import EditBooking from "../components/EditBooking.vue";

export default {
  components: {
    EditBooking,
    Stripe,
  },

  data() {
    return {
      score: 0,
      text: "",
      hotel_id: "",
      show: false,
      showEdit: true,
      showPayNow: false,
      fromDate: "",
      toDate: "",
      board: "",
      name: "",
      hotel_img: "",
      extra_bed_amount: "",
      type: "",
      room_img: "",
      total_cost: 0,
      id: "",
      payment_state: "Not Paid",
      oldBookings: [],
      newBookings: [],
    };
  },

  computed: {
    getAllMyBookings() {
      return this.$store.getters.getMyBookings;
    },
    getRoomsForEdit() {
      return this.$store.getters.getRoomsForEdit;
    },
  },

  async mounted() {
    await this.$store.dispatch("fetchMyBookings");
    await this.$store.dispatch("fetchHotelListForReviews");
    await this.renderOldBookings();
    await this.renderNewBookings();
  },

  methods: {
    // ifall det inte är betalat sedan innan => betalning görs uppdatera payment_state = "Paid"
    // Metoden andropas från STRIPE
    updateBooking(bookingID) {
      this.$store.dispatch("UpdatePaymentState", bookingID);
    },

    checkIfOutOfDate(index, payment) {
      // kollar om datumet har gått ut, såfall ska man inte kunna editera något.
      if (
        this.getAllMyBookings[index].from_date >= this.currentDate() &&
        payment === "Not Paid"
      ) {
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

    checkIfReviewed(hotelId, index) {
      let bool = false;

      // kollar ifall vi inte redan har gjort en review samt att vi har kommit hem från resan.
      if (
        !this.$store.getters.getHotelListForReview.includes(hotelId) &&
        this.getAllMyBookings[index].to_date <= this.currentDate()
      ) {
        bool = true;
      } else {
        bool = false;
      }

      return bool;
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

    async editBooking(booking) {
      this.payment_state = booking.payment_state;
      this.$store.dispatch("fetchBookedRoom", booking.id);
      await this.$store.dispatch("fetchClickedBooking", booking.id);

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
      this.show = true;
      this.hotel_id = booking.hotel_id;
    },

    toggleShow(value) {
      this.show = value;
    },

    renderOldBookings() {
      let allBookings = this.$store.getters.getMyBookings;

      for (let i = 0; i < allBookings.length; i++) {
        if (allBookings[i].to_date < this.currentDate()) {
          this.oldBookings.push(allBookings[i]);
        }
      }
    },

    renderNewBookings() {
      let allBookings = this.$store.getters.getMyBookings;

      for (let i = 0; i < allBookings.length; i++) {
        if (allBookings[i].from_date >= this.currentDate()) {
          this.newBookings.push(allBookings[i]);
        }
      }
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

h3{
  font-size: 25px;
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