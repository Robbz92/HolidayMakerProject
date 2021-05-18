<template>
  <div class="board">
    <p>Choose board</p>

    <select name="boardDropDown" id="board">
      <option value="1">All inclusive</option>
      <option value="2">Half pension</option>
      <option value="3">Full pension</option>
      <option value="4">DIY</option>
    </select>
  </div>
  <div class="room">
    <p>Choose room type</p>
    <select name="roomtype" id="roomtype">
      <option
        v-bind:value="roomItem.id"
        v-for="(roomItem, index) in getRoomList"
        :key="index"
      >
        {{ roomItem.type }} {{ roomItem.price }} kr
      </option>
    </select>
  </div>

  <div class="extraBed">
    <p>Do you want an extra bed?</p>
    <label for="accept">Yes</label>
    <input v-model="accept" type="checkbox" id="extraBed" name="horns" />
  </div>

  <div>
    <button type="submit" class="next" @click="makeBooking()">Next</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      accept: false,
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
  },

  methods: {
    async makeBooking() {
      //userId, hotelId, fromDate, toDate, totalCost
      // skapar bookings, //fungerar om vi är inloggade.
      //TODO: fixa catch error om inte inloggad.

      let bookingcredentials = {
        userId: this.getLoggedInUserID,
        hotelId: this.$store.state.bookings.hotel_id,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        totalCost: 0,
      };

      console.log(bookingcredentials);
      await fetch("http://localhost:3000/rest/makeBooking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingcredentials),
      });

      this.bookRoom();
    },
    async bookRoom() {
      // hämtar board id
      var boardChoice = document.getElementById("board");
      var boardResult = boardChoice.options[boardChoice.selectedIndex].value;
      // console.log(boardResult);

      // logik för extrabed
      var extraBed = null;
      if (this.accept == true) {
        //bockad checkbox
        extraBed = 1;
      } else {
        //avbockad checkboc
        extraBed = 0;
      }
      //console.log(extraBed);

      //roomID
      var roomtype = document.getElementById("roomtype");
      var roomID = roomtype.options[roomtype.selectedIndex].value;
      //console.log(roomID);

      //bookingID
      this.$store.dispatch("fetchLatestBookingID");
      var bookingID = this.$store.state.bookings.hotel_id;
      //console.log(bookingID)

      // toDate
      // console.log(this.getToDate)

      // fromDate
      // console.log(this.getFromDate)
      let bookingIDObject = {
        id: bookingID,
      };

      let BookRoomCredentials = {
        roomsId: roomID,
        board: boardResult,
        extraBedAmount: extraBed,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        bookings: bookingIDObject,
      };

      console.log(BookRoomCredentials);
      await fetch("http://localhost:3000/rest/bookRoom", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(BookRoomCredentials),
      });
    },
  },
};
</script>

<style>
</style>