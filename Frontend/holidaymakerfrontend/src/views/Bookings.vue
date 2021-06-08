<template>
  <div class="main-container">
    <div class="hotel-container">
      <div class="rooms-container">
        <BookRoom
          v-for="(room, index) in getRoomsToBook"
          :key="index"
          class="perRoom"
          :room="room"
          :index="index"
          id="roomFlexList"
        />
      </div>
    </div>
    <div id="next">
      <button type="submit" class="next" @click="clickPopup(true)">Next</button>
      <popup v-if="showPopup" :roomList="bookingsArray" />
    </div>
  </div>
</template>

<script>
import popup from "../components/Popup.vue";
import BookRoom from "../components/BookRoom.vue";
export default {
  components: {
    BookRoom,
    popup,
  },

  data() {
    return {
      newPrice: [],
      accept: [],
      roomItem: [],
      boardResult: [],
      extraBed: [],
      bookingID: 0,
      options: [],
      bookingsArray: [],
      showPopup: false,
    };
  },

  computed: {
    getBookings() {
      return this.$store.getters.getBookings;
    },

    loggedInUser() {
      return this.$store.state.loggedInUser;
    },

    isLoggedIn() {
      return this.loggedInUser != null;
    },

    getLoggedInUserID() {
      return this.$store.state.loggedInUser.id;
    },

    getHotelID() {
      return this.$store.state.myRoom.hotel_id;
    },

    getFromDate() {
      return this.$store.state.fromDate;
    },

    getToDate() {
      return this.$store.state.toDate;
    },

    getRoomList() {
      return this.$store.getters.getRoomList;
    },

    getRoomsToBook() {
      return this.$store.getters.getRoomsToBook;
    },
  },
  
  mounted(){
    console.log(this.$store.getters.getRoomsToBook)
  },
  methods: {
    clickPopup(boolean) {
      if (!this.checkForDuplicates()) {
        alert("Rooms are not unique");
      } else {
        this.showPopup = boolean;
      }
    },

    fillBookingArray(index, object) {
      this.bookingsArray[index] = object;
    },

    getNumberOfDays() {
      return this.$store.getters.getNumberOfDays;
    },

    getPriceFromObject() {
      let totalPrice = 0;
      this.bookingsArray.forEach((object) => {
        totalPrice += object.totalCost;
      });

      return totalPrice;
    },

    checkForDuplicates() {
      var idList = [];
      var isUnique = true;
      this.bookingsArray.forEach((object) => {
        idList.push(object.roomID);
      });

      for (var i = 0; i < idList.length; i++) {
        if (idList.includes(idList[i], i + 1)) isUnique = false;
      }

      return isUnique;
    },

    async makeBooking(isPayed) {
      // hanterar betalnings status
      var paymentState = isPayed == true ? "Paid" : "Not Paid";

      var boardChoice = document.getElementById("board");
      this.boardResult = parseInt(
        boardChoice.options[boardChoice.selectedIndex].value
      );

      let bookingcredentials = {
        userId: this.getLoggedInUserID,
        hotelId: this.$store.state.chosenRoom.hotel_id,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        totalCost: this.getPriceFromObject(),
        paymentState: paymentState,
      };

      console.log(bookingcredentials);
      await fetch("http://localhost:3000/auth/makeBooking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingcredentials),
      });

      await this.$store.dispatch("fetchLatestBookingID");

      this.bookRoom();
    },

    async bookRoom() {
      this.bookingID = this.$store.getters.getBookingId;
      var fromDate = this.$store.state.fromDate;
      var toDate = this.$store.state.toDate;

      let bookingIDObject = {
        id: this.bookingID,
      };
      for (let i = 0; i < this.bookingsArray.length; i++) {
        let BookRoomCredentials = {
          roomsId: this.bookingsArray[i].roomID,
          board: this.bookingsArray[i].board,
          extraBedAmount: this.bookingsArray[i].extraBedAmount == true ? 1 : 0,
          fromDate: fromDate,
          toDate: toDate,
          bookings: bookingIDObject,
        };

        await fetch("http://localhost:3000/auth/bookRoom", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(BookRoomCredentials),
        });
      }
    },
  },
};
</script>

<style scoped>
#roomName {
  margin: 0 0;
}

.main-container {
  max-height: 80vh;
  overflow-y: auto;
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

::-webkit-scrollbar {
  display: none;
}

#next {
  margin: 10px auto;
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