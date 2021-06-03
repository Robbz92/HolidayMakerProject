<template>
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
              <th>Old Cost</th>
              <th>From Date</th>
              <th>To Date</th>
              <th>New Total Cost</th>
              <th>Payment Status</th>
            </tr>
            <tr>
              <td>{{total_cost}} SEK</td>
              <td>{{fromDate}}</td>
              <td>{{toDate}}</td>
              <td>{{newTotalPrice}}</td>
              <td>{{paymentState}}</td>
            </tr>            
          </table>
        </div>
        <!--  <div class="date">
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
        </div> -->
      </div>
      <div class="rooms-container">
        <h3 id="bookedRoomsH3">Booked Room(s)</h3>
        <ul v-for="(theRoom, index) in getBookedRooms" :key="index">
          <li id="rooms">
            <div class="roomPicture">
              <img id="roomPic" :src="theRoom.room_img" />
            </div>
            <div class="room-text">
            <p>Room: {{ theRoom.type }}</p>
            <select
              v-model="editBoard"
              id="boardChoice"
              @change="calculatePrice(theRoom.price), calculateDateDiff()"
            >
              <option value="1">All inclusive</option>
              <option value="2">Full pension</option>
              <option value="3">Half pension</option>
              <option value="4">DIY</option>
            </select>
            <p>Extra Bed: {{ theRoom.extraBed }}</p>
            <p>Room price: {{ theRoom.price }}kr/night</p>
            </div>
            <button
              id="editBookingButton"
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
//import DatePicker from "vue3-datepicker";
import moment from "moment"; // Komponent som parsear, validerar, manipulerar och visar datum.

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
    "paymentState",
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
    // DatePicker,
  },

  computed: {
    getPaymentState(){
      console.log(this.$store.getters.getPaymentState)
      return this.$store.getters.getPaymentState;
    },

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

    // Tillsammans med "moment" beräknas antal dagar mellan två datum.
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
      let price = roomPlusDays;

      var boardChoice = document.getElementById("boardChoice");
      this.boardResult = parseInt(
        boardChoice.options[boardChoice.selectedIndex].value
      );

      if (this.boardResult == 1) {
        price *= 1.2;
      }
      if (this.boardResult == 2) {
        price *= 1.15;
      }
      if (this.boardResult == 3) {
        price *= 1.1;
      }
      this.newTotalPrice = parseFloat(price).toFixed(2); //newTotalPrice only has 2 decimals

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
  overflow: auto;
}

.nameAndImage {
  display: block;
  margin-left: 1em;
}
.nameAndImage img{
  height: 250px;
  width: 250px; 
}

.old-container {
  display: flex;
}

#list {
  list-style: none;
  display: flex;
}
ul{
  margin: 0;
  padding: 0;
  margin-left: 1em;
  margin-right: 1em;
  
}
.oldText {
  width:100%;
}
.oldText table{
  width:100%;
  margin-top: 75px;
}
#rooms {
  display: flex;
  width:100%;
  justify-content: space-evenly;
  border-bottom: 1px solid rgb(187, 184, 184);
  margin-bottom: 35px;
  padding-bottom: 15px;
  vertical-align: middle;
}
.room-text{
display:flex;
justify-content: space-evenly;
width:100%;
}
#roomPic{
  height: 150px;
  width: 200px;
  object-fit: cover;
}
#boardChoice{
  width: 130px;
  height:35px ;
  outline: none;
}
#editBookingButton{
width:100px;
height:45px;
}
#bookedRoomsH3{
  margin-bottom: 80px;
}
.buttons{
  float:right;
}
.buttons button{
  margin-left: 20px;

}

#deleteBooking{
  background-color:rgb(251, 76, 76);
}
#deleteBooking:hover{
  background-color: white;
  color: rgb(251, 76, 76);
}
</style>