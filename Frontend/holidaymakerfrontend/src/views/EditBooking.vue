<template>
  <div class="main-container">
    <div class="hotel-container">
      <div class="old-container">
        <div class="nameAndImage">
          <h1>{{ name }}</h1>
          <img :src="hotel_img" />
        </div>
        <div class="oldText">
          <h2>Old cost</h2>
          <p>{{ total_cost }} SEK</p>
          <h2>From date</h2>
          <p>{{ fromDate }}</p>
          <h2>To date</h2>
          <p>{{ toDate }}</p>
          <h2>New total cost</h2>
          <p>New total price: {{ newTotalPrice }}</p>
        </div>
        <div class="date">
          <div class="checkIn">
            <h6>Check-in</h6>
            <date-picker
              id="fromDate"
              v-model="getFromDate"
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
              v-model="getToDate"
              language="en"
              type="date"
              format="YYYY-MM-DD"
              :style="styleObject"
            ></date-picker>
          </div>
        </div>
      </div>
      <div class="rooms-container">
        <h3>Booked Room(s)</h3>
        <ul v-for="(theRoom, index) in getBookedRooms" :key="index">
          <li id="rooms">
            <div class="roomPicture">
              <img id="roomPic" :src="theRoom.room_img" />
            </div>
            <p>Room: {{ theRoom.type }}</p>
            <p>Room board: {{ theRoom.board }}</p>
            <select
              v-model="editBoard"
              id="boardChoice"
              @change="calculatePrice(theRoom.price), calculateDateDiff()"
            >
              <option value="1">All inclusive</option>
              <option value="2">Half pension</option>
              <option value="3">Full pension</option>
              <option value="4">DIY</option>
            </select>
            <p>Extra Bed: {{ theRoom.extraBed }}</p>
            <p>Room price: {{ theRoom.price }}kr/night</p>
            <button
              id="editBooking"
              @click="
                editBooking(
                  theRoom.bookedRoomId,
                  theRoom.roomId,
                  theRoom.extraBed
                )
              "
            >
              Update
            </button>
          </li>
        </ul>
      </div>
      <div class="buttons">
        <button id="deleteBooking" @click="deleteBooking(id)">
          Delete Booking
        </button>
        <button id="saveBooking" @click="saveBooking()">Save booking</button>
      </div>
    </div>
  </div>
</template>


<script>
import DatePicker from "vue3-datepicker";
import moment from "moment";

export default {
  props: [
    "fromDate",
    "toDate",
    "board",
    "name",
    "hotel_img",
    "extra_bed_amount",
    "type",
    "room_img",
    "total_cost",
    "id",
  ],
  data() {
    return {
      editBoard: this.board,
      bookingsId: this.id,
      newFromDate: this.getFromDate,
      newToDate: this.getToDate,
      numberOfDays: 0,
      newTotalPrice: 0,
      styleObject: {
        outline: "none",
        border: "none",
        height: "1.65em",
        width: "10em",
        fontFamily: "inherit",
      },
    };
  },
  components: {
    DatePicker,
  },

  computed: {
    getTheBooking() {
      return this.$store.getters.getClickedBooking;
    },
    getFromDate() {
      return Date.parse(this.fromDate);
    },
    getToDate() {
      return Date.parse(this.toDate);
    },
    getBookedRooms() {
      return this.$store.getters.getBookedRoom;
    },
  },
  methods: {
    deleteBooking(id) {
      if (confirm("Are you sure you want to delete the booking?")) {
        this.$store.dispatch("fetchDeleteBooking", id);

        this.$router.push("/");
      }
    },

    async editBooking(bookedRoomId, roomId, extraBed) {
      let bookingsIdObject = {
        id: this.id,
      };
      let editBookingObject = {
        roomsId: roomId,
        board: this.editBoard,
        fromDate: this.fromDate,
        toDate: this.toDate,
        id: bookedRoomId,
        extraBedAmount: extraBed,
        bookings: bookingsIdObject,
      };
      console.log(editBookingObject);
      await fetch("http://localhost:3000/rest/editBooking", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(editBookingObject),
      });
    },

    calculateDateDiff() {
      let start = moment(this.fromDate);
      let end = moment(this.toDate);
      let duration = moment.duration(end.diff(start));
      let days = duration.asDays();
      this.numberOfDays = Math.round(days);
      return this.numberOfDays;
    },

    calculatePrice(roomPrice) {
      let roomPlusDays = roomPrice * this.calculateDateDiff();
      this.newTotalPrice = roomPlusDays;

      var boardChoice = document.getElementById("boardChoice");
      this.boardResult = parseInt(
        boardChoice.options[boardChoice.selectedIndex].value
      );

      if (this.boardResult == 1) {
        this.newTotalPrice *= 1.2;
      }
      if (this.boardResult == 2) {
        this.newTotalPrice *= 1.15;
      }
      if (this.boardResult == 3) {
        this.newTotalPrice *= 1.1;
      }

      /* this.extraBed = null;
      if (this.accept == true) {
        //bockad checkbox
        this.extraBed = 1;
        this.newTotalPrice *= 1.05;
        console.log(this.newTotalPrice);
      } else {
        //avbockad checkboc
        this.extraBed = 0;
      } */
    },

    saveBooking() {
      this.$router.push("/myBookings");
    },
  },
};
</script>

<style scoped>
.main-container {
  backdrop-filter: blur(5px);
}

.hotel-container {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.438);
  width: 90%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
}

.nameAndImage {
  display: block;
}

.old-container {
  display: flex;
}

#list {
  list-style: none;
  display: flex;
}
img {
  width: 200px;
  height: auto;
}

#rooms {
  display: flex;
}

.oldText {
  display: block;
}
</style>