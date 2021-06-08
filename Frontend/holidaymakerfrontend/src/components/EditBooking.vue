<template>
  <div id="backButton" @click="showFalse">
    <label id="returnButtonText">Return</label>
  </div>
  <div class="main-container">
    <div class="hotel-container">
      <div class="old-container">
        <div class="nameAndImage">
          <h1>{{ name }}</h1>
          <img :src="hotel_img" />
        </div>
        <div class="oldText">
          <table>
            <tr>
              <th>From Date</th>
              <th>To Date</th>
              <th>Old Price</th>
              <th>New Total Price</th>
              <th>Payment Status</th>
            </tr>
            <tr>
              <td>{{ fromDate }}</td>
              <td>{{ toDate }}</td>
              <td>{{ total_cost }}:-</td>
              <td>{{ newTotalPrice }}:-</td>
              <td>{{ paymentState }}</td>
            </tr>
          </table>
        </div>
      </div>
      <h3 id="bookedRoomsH3">Booked Room(s)</h3>
      <div class="rooms-container">
        <EditRoom
          :room="theRoom"
          :dates="{ fromDate: fromDate, toDate: toDate }"
          :price="theRoom.price"
          :index="index"
          v-for="(theRoom, index) in getBookedRooms"
          :key="index"
          id="roomFlexList"
        />
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
import EditRoom from "./EditRoom.vue";

export default {
  props: [
    "fromDate",
    "toDate",
    "name",
    "hotel_img",
    "total_cost",
    "id",
    "hotel_id",
    "room",
    "paymentState",
  ],
  data() {
    return {
      bookingsId: this.id,
      newFromDate: this.getFromDate,
      newToDate: this.getToDate,
      numberOfDays: 0,
      newTotalPrice: 0,
      chosenRoom: [],
      editRoomList: [],
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
    // DatePicker,
    EditRoom,
  },
  mounted() {
    this.$store.commit("setChosenHotel", this.hotel_id);
    this.$store.commit("setFromDate", this.fromDate);
    this.$store.commit("setToDate", this.toDate);
    this.$store.dispatch("fetchHotel");
  },

  computed: {
    getFromDate() {
      return Date.parse(this.fromDate);
    },
    getToDate() {
      return Date.parse(this.toDate);
    },
    getBookedRooms() {
      return this.$store.getters.getBookedRoom;
    },
    getPaymentState() {
      return this.$store.getters.getPaymentState;
    },
  },
  methods: {
    deleteBooking(id) {
      if (confirm("Are you sure you want to delete the booking?")) {
        this.$store.dispatch("fetchDeleteBooking", id);

        location.reload();
      }
    },

    updateRoomInEditRoomList(index, room) {
      this.editRoomList[index] = room;
      this.calculatePrice();
    },

    showFalse() {
      this.$parent.toggleShow(false);
    },

    calculatePrice() {
      let newPrice = 0;
      this.editRoomList.forEach((roomInList) => {
        newPrice += roomInList.roomCalculatedPrice;
      });
      this.newTotalPrice = newPrice;
    },

    async saveBooking() {
      let bookingsIdObject = {
        id: this.id,
      };
      for (let i = 0; i < this.editRoomList.length; i++) {
        let editRoomObject = {
          roomsId: this.editRoomList[i].roomsId,
          board: this.editRoomList[i].board,
          fromDate: this.fromDate,
          toDate: this.toDate,
          id: this.editRoomList[i].bookedRoomId,
          extraBedAmount: this.editRoomList[i].extraBed,
          bookings: bookingsIdObject,
        };

        await fetch("http://localhost:3000/auth/editBooking", {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(editRoomObject),
        });
      }
      let bookingsObject = {
        id: this.id,
        userId: this.$store.state.loggedInUser.id,
        hotelId: this.hotel_id,
        fromDate: this.fromDate,
        toDate: this.toDate,
        totalCost: this.newTotalPrice,
        paymentState: "Not Paid",
      };
      await fetch("http://localhost:3000/auth/updateBooking", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingsObject),
      });

      location.reload();
    },

    getBookedRoomById(id) {
      return this.$store.getters.getBookedRoom[id];
    },
  },
};
</script>

<style scoped>
#backButton {
  width: 8vw;
  height: 6vh;
  background-color: rgba(255, 255, 255, 0.3);
  position: absolute;
  top: 23.8vh;
  right: 5vw;
  z-index: 5;
  border-radius: 5px;
  font-size: larger;
}

#returnButtonText {
  display: block;
  margin: 18px auto;
}

.main-container {
  max-height: 70vh;
}

.hotel-container {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.438);
  width: 90%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 0;
  overflow: auto;
  backdrop-filter: blur(5px);
}

.nameAndImage {
  display: block;
  margin-left: 1em;
}

.nameAndImage img {
  height: 250px;
  width: 250px;
}

.old-container {
  display: flex;
}

.oldText {
  width: 100%;
}

.oldText table {
  width: 100%;
  margin-top: 75px;
}

#editBookingButton {
  width: 100px;
  height: 45px;
}

#bookedRoomsH3 {
  margin-bottom: 80px;
}

.buttons {
  float: right;
}

.buttons button {
  margin-left: 20px;
}

#deleteBooking {
  background-color: rgb(251, 76, 76);
}

#deleteBooking:hover {
  background-color: white;
  color: rgb(251, 76, 76);
}

.rooms-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

#roomFlexList {
  display: flex;
  flex-direction: row;
}
</style>