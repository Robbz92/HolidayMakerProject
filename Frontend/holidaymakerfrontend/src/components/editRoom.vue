<template>
  <div class="editRoom-div">
    <li id="rooms">
      <div class="roomPicture">
        <img id="roomPic" :src="room.room_img" />
      </div>
      <div class="room-text">
        <select v-model="chosenRoom">
            <option :value="room.id" selected>
                {{getBookedRooms.type}} {{getBookedRooms.roomId}}
            </option>
          <option
            :value="roomsForEdit.id"
            v-for="(roomsForEdit, index) in getAllRooms"
            :key="index"
          >
            {{ roomsForEdit.type }} {{ roomsForEdit.id }}

          </option>
        </select>
        <select
          v-model="board"
          id="boardChoice"
          @change="calculatePrice(price)"
        >
          <option value="1">All inclusive</option>
          <option value="2">Full pension</option>
          <option value="3">Half pension</option>
          <option value="4">DIY</option>
        </select>
        <p>Extra Bed: {{ extraBed }}</p>
        <label for="accept">Yes</label>
        <input
          type="checkbox"
          :id="'extraBed'"
          :checked="checked"
        />
        <p>Room price: {{ newTotalPrice }}kr/night</p>
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
      chosenRoom: this.room.id,
      roomId: "",
    }
  },

  props:[
      "room",
      "price",
      "index",
  ],

  mounted(){
    if(this.extraBed>0){
        this.checked=true;
    }
    else{
        this.checked=false;
    }
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
    },
  
    updateRoomInList(){
      let object={
          roomId:this.chosenRoom,
          board:this.board,
          extra_bed_amount:this.extraBed,
          total_cost:this.newTotalPrice,
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