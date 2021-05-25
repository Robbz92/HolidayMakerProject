<template>
  <div class="main-container">
    <div class="hotel-container">
      <p>{{board}}</p>
      <p>{{name}}</p>
     <img :src="hotel_img"/>
      <img :src="room_img"/>
      <p>{{extra_bed_amount}}</p>
      <p>{{type}}</p>
      <p>{{total_cost}}</p>
      <h1>{{id}}</h1>
      <div class="rooms-container">
         <h3>Booked Room(s)</h3>
       <ul v-for="(theRoom, index) in getBookedRooms" :key="index">
        <li id="rooms">
          <h5>{{index+1}} - </h5>
         <div class="roomPicture">
            <img id="roomPic" :src="theRoom.room_img" />
          </div>
          <p>Room: {{ theRoom.type }}</p>
          <p>roomId:{{theRoom.roomId}}</p>
          <p>bookedRoomID: {{theRoom.bookedRoomId}}</p>
          <select v-model="testBoard">
              <option value="1">All inclusive</option>
              <option value="2">Half pension</option>
              <option value="3">Full pension</option>
              <option value="4">DIY</option>
          </select>
          <p>Extra Bed: {{ theRoom.extraBed}}</p>
          <p>Price per night: {{theRoom.price}} kr</p>
          <button id="editBooking" @click="editBooking(theRoom.bookedRoomId,theRoom.roomId,theRoom.extraBed)">update</button>
        </li>
      </ul> 
      </div>
      <div class="buttons">
        <button id="deleteBooking" @click="deleteBooking(id)">
          Delete Booking
        </button>
          <button id="saveBooking">save</button>
      </div>
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
</template>


<script>
import DatePicker from "vue3-datepicker";

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
  data(){
    return{
          testBoard:this.board,
          testFromDate:this.fromDate,
          testToDate:this.toDate,
          bookingsId:this.id
    }
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
    }
  },
  methods: {
   deleteBooking(id) {
          if(confirm("Are you sure you want to delete the booking?")){
              this.$store.dispatch("fetchDeleteBooking", id);

              this.$router.push("/");
          }
      },

     async editBooking(bookedRoomId,roomId,extraBed){
    
       let bookingsIdObject={
         id:this.id
       }
        let editBookingObject={
          roomsId:roomId,
          board:this.testBoard,
          fromDate:this.testFromDate,
          toDate:this.testToDate,
          id:bookedRoomId,
          extraBedAmount:extraBed,
          bookings:bookingsIdObject
        };
        console.log(editBookingObject)
        await fetch("http://localhost:3000/rest/editBooking", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(editBookingObject),
      });

      }
  },
    

};
</script>

<style scoped>
.hotel-container {
  background-color: white;
}

#list {
  list-style: none;
  display: flex;
}
img{
  width:100px;
  height:auto;
}
</style>