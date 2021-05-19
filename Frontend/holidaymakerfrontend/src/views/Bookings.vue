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
    <select name="roomtype" id="roomtype" @change="calculatePrice()" v-model="roomItem">
      <option
        v-for="(roomItem, index) in getRoomList"
        :key="index"
        :value="roomItem"
      >
        {{ roomItem.type }} {{ roomItem.price }} kr
      </option>
    </select>
    <div class="totalCost">
      <p>total cost: {{totalPrice}} kr</p>
    </div>
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
      totalPrice:0,
      roomItem:"",
      boardResult:0,
      extraBed:0,
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

  methods:{
      getNumberOfDays(){
        return this.$store.getters.getNumberOfDays;
      },
  
      calculatePrice() {
        let calculatedPrice = this.roomItem.price*this.$store.getters.getNumberOfDays;
        this.totalPrice = calculatedPrice;
        console.log(calculatedPrice)
      },
    async makeBooking(){
      //userId, hotelId, fromDate, toDate, totalCost
      // skapar bookings, //fungerar om vi är inloggade.
      //TODO: fixa catch error om inte inloggad.
     // console.log("value: " + price.target.value)
      var boardChoice = document.getElementById("board");
      this.boardResult = parseInt(boardChoice.options[boardChoice.selectedIndex].value);
  
      if(this.boardResult==1){
        this.totalPrice *= 1.2;
        console.log("boardresult 1")
      }
      if(this.boardResult==2){
        this.totalPrice *= 1.15;
        console.log("boardresult 2")
      }
      if(this.boardResult==3){
        this.totalPrice *= 1.1
        console.log("boardresult 3")
      }
       this.extraBed = null;
      if (this.accept == true) {//bockad checkbox
        this.extraBed = 1;
        this.totalPrice *= 1.05;
        console.log(this.totalPrice)
      } else { //avbockad checkboc
        this.extraBed = 0;
      }
      console.log(this.totalPrice)

      let bookingcredentials = {
        userId: this.getLoggedInUserID,
        hotelId: this.$store.state.bookings.hotel_id,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        totalCost: this.totalPrice,
      };

      console.log(bookingcredentials);
      await fetch("http://localhost:3000/rest/makeBooking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingcredentials)
      });

      this.bookRoom()
    },
    async bookRoom() {    
      //roomID
      var roomID = this.roomItem.id;

      //bookingID
      this.$store.dispatch("fetchLatestBookingID");
      var bookingID = this.$store.state.bookings.hotel_id;

      let bookingIDObject = {
        id: bookingID,
      };

      let BookRoomCredentials = {
        roomsId: roomID,
        board: this.boardResult,
        extraBedAmount: this.extraBed,
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