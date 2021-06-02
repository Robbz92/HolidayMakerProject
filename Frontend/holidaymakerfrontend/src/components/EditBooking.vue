<template>
<div id="backButton" @click="showFalse"><label id="returnButtonText">Return</label></div>
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
            </tr>
            <tr>
              <td>{{total_cost}} SEK</td>
              <td>{{fromDate}}</td>
              <td>{{toDate}}</td>
              <td>{{newTotalPrice}}</td>
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
        <editRoom :room="theRoom" :dates="{fromDate:fromDate,toDate:toDate}" :price="theRoom.price" :index="index" v-for="(theRoom, index) in getBookedRooms" :key="index"/>  
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
import editRoom from "./editRoom.vue";

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
  ],
  data() {
    return {

      bookingsId: this.id,
      newFromDate: this.getFromDate,
      newToDate: this.getToDate,
      numberOfDays: 0,
      newTotalPrice: 0,
      chosenRoom: [],
      editRoomList:[],


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
    editRoom,
  },
  mounted(){
    this.$store.commit("setChosenHotel" , this.hotel_id)
    this.$store.commit("setFromDate",this.fromDate) 
    this.$store.commit("setToDate" ,this.toDate)
    this.$store.dispatch("fetchHotel");
  },

  computed: {
    getRoomList(){
      return this.$store.getters.getRoomList;
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
    getExtraBed(){
      console.log(this.extra_bed_amount)
      return this.extra_bed_amount;
    },

  },
  methods: {
    deleteBooking(id) {
      if (confirm("Are you sure you want to delete the booking?")) {
        this.$store.dispatch("fetchDeleteBooking", id);

        this.$router.push("/");
      }
    },
    updateRoomInEditRoomList(index, room){
      this.editRoomList[index]=room
      this.calculatePrice()

    },

    async editBooking(bookedRoomId, extraBed) {
      let bookingsIdObject = {
        id: this.id,
      };
      let editBookingObject = {
        roomsId: this.chosenRoom,
        board: this.editBoard,
        fromDate: this.fromDate,
        toDate: this.toDate,
        id: bookedRoomId,
        extraBedAmount: extraBed,
        bookings: bookingsIdObject,
      };
      console.log(this.chosenRoom)
      console.log(editBookingObject);
      await fetch("http://localhost:3000/rest/editBooking", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(editBookingObject),
      });
    },
    showFalse(){
      this.$parent.toggleShow(false)
    },

    calculatePrice(){
      let newPrice =0;
      this.editRoomList.forEach(roomInList =>{
      newPrice +=roomInList.roomCalculatedPrice
      });
      this.newTotalPrice=newPrice;
    },

    saveBooking() {
      
      this.$router.push("/myBookings");
    },
    getBookedRoomById(id){
      return this.$store.getters.getBookedRoom[id];
    },
  },
};
</script>

<style scoped>
#backButton{
  width: 8vw;
  height: 6vh;
  background-color: rgba(255, 255, 255, .3);
  position: absolute;
  top: 25.3vh;
  right: 5vw;
  z-index: 5;
  border-radius: 5px;
  font-size: larger;
}

#returnButtonText{
  display: block;
  margin: 18px auto;
}

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