<template>
  <div class="editRoom-div">
    <li id="rooms">
      <div class="roomPicture">
        <img id="roomPic" :src="room.room_img" />
      </div>
      <div class="room-text">
        <select v-model="chosenRoom" @change="calculatePrice()">
            <option :value="room" selected>
                {{getBookedRooms.type}} {{getBookedRooms.price}}
            </option>
          <option
            :value="roomsForEdit"
            v-for="(roomsForEdit, index) in getAllRooms"
            :key="index"
          >
            {{ roomsForEdit.type }} {{ roomsForEdit.price }}

          </option>
        </select>
        <select
          v-model="board"
          id="boardChoice"
          @change="calculatePrice()"
        >
          <option value="1">All inclusive</option>
          <option value="2">Full pension</option>
          <option value="3">Half pension</option>
          <option value="4">DIY</option>
        </select>
        <label for="extraBed"> Would you like an extra bed? </label>
        <input
          v-model="extraBed"
          type="checkbox"
          :id="'extraBed'"
          :checked="checked"
          @change="calculatePrice()"
        />
        <p>Room price: {{ newTotalPrice }}kr</p>
      </div>
    </li>
  </div>
</template>

<script>
import moment from "moment"
export default {
  data(){
    return{
      newTotalPrice: this.price,
      checked:"",
      board: this.room.board,
      bookedRoomById: "",
      extraBed: "",
      roomType: "",
      chosenRoom: this.room,
      roomId: "",
    }
  },

  props:[
      "room",
      "price",
      "index",
      "dates"
  ],

  mounted(){
    this.extraBed = this.room.extraBed
    if(this.extraBed>0){
        this.checked=true;
    }
    else{
        this.checked=false;
    }
    this.calculatePrice()
  },

  computed:{
    getAllRooms(){
      return this.$store.getters.getRoomList;
    },
    getBookedRooms(){
        return this.getBookedRoomById(this.index)
    }
  },

  methods:{
    calculateDateDiff() {
      let start = moment(this.dates.fromDate);
      let end = moment(this.dates.toDate);
      let duration = moment.duration(end.diff(start));
      let days = duration.asDays();
      this.numberOfDays = Math.round(days);
      return this.numberOfDays;
    },

    calculatePrice() {
      let boardMultiplier = 1.2;
      //Checks what board is chosen per room
      switch (this.board) {
        case "1":
          boardMultiplier = 1.2;
          break;
        case "2":
          boardMultiplier = 1.15;
          break;
        case "3":
          boardMultiplier = 1.1;
          break;
        case "4":
          boardMultiplier = 1;
          break;
      }

      let bedMultiplier = 1;
      console.log(this.extraBed)
      if (this.extraBed) bedMultiplier = 1.05;

      let numberOfDays = this.calculateDateDiff();

      let calculatedPrice = Math.round(
        this.chosenRoom.price * boardMultiplier * bedMultiplier * numberOfDays
      );

      this.newTotalPrice = calculatedPrice; //newTotalPrice only has 2 decimals

      this.updateRoomInList()
    },
  
    updateRoomInList(){
      let object={
          roomId:this.chosenRoom,
          board:this.board,
          extra_bed_amount:this.extraBed,
          roomCalculatedPrice:this.newTotalPrice,
          name:this.room.type
      }
      this.$parent.updateRoomInEditRoomList(this.index,object)
    },

    getBookedRoomById(id){
      return this.$store.getters.getBookedRoom[id];
    },
  },
}

</script>

<style scoped>
</style>