<template>
  <div>
    <bookRoom v-for="(room, index) in getRoomsToBook" :key="index" class="perRoom" :room="room"/>
  </div>

  <div>
    <button type="submit" class="next" @click="makeBooking()">Next</button>
  </div>
</template>

<script>
import bookRoom from "../components/bookRoom.vue"
export default {
  components: {
    bookRoom
  },

  data() {
    return {
      newPrice: [],
      accept: [],
      roomItem: [],
      boardResult: [],
      extraBed: [],
      bookingID: 0,
      options: []
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

    getRoomsToBook(){
      return this.$store.getters.getRoomsToBook;
    }
  },

  methods:{

      getNumberOfDays(){
        return this.$store.getters.getNumberOfDays;
      },

    async makeBooking(){
      //userId, hotelId, fromDate, toDate, totalCost
      // skapar bookings, //fungerar om vi är inloggade.
      //TODO: fixa catch error om inte inloggad.
     // console.log("value: " + price.target.value)
      var boardChoice = document.getElementById("board");
      this.boardResult = parseInt(boardChoice.options[boardChoice.selectedIndex].value);
  
  //Bytas i framtiden*
      if(this.boardResult==1){
        this.totalPrice *= 1.2;
      }
      if(this.boardResult==2){
        this.totalPrice *= 1.15;
      }
      if(this.boardResult==3){
        this.totalPrice *= 1.1
      }
       this.extraBed = null;
      if (this.accept == true) {//bockad checkbox
        this.extraBed = 1;
        this.totalPrice *= 1.05;
      } else { //avbockad checkboc
        this.extraBed = 0;
      }
      console.log(this.totalPrice)

      let bookingcredentials = {
        userId: this.getLoggedInUserID,
        hotelId: this.$store.state.chosenRoom.hotel_id,
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

      await this.$store.dispatch("fetchLatestBookingID");
      
      this.bookRoom()
    },

    async bookRoom() {    
      var roomID = this.roomItem.id;
      this.bookingID = this.$store.getters.getBookingId

      let bookingIDObject = {
        id: this.bookingID,
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
  }
}
</script>

<style>
.perRoom{
  background-color: rgba(255, 255, 255, .3);
  margin: 10px auto;
  padding-top: 20px;
  display: flex;
  justify-content: center;
}

#roomName{
  margin: 0 0;
}
</style>