<template>
  <div class="editRoom-div">
    <li id="rooms">
      <div class="roomPicture">
        <img id="roomPic" :src="chosenRoom.room_img" />
      </div>
      <div class="room-text">
        <p>Choose board</p>
        <select id="boardChoice" @change="calculatePrice()" v-model="board">
          <option value="1">
            All inclusive
            {{ Math.round(chosenRoom.price * 0.2) * numberOfDays }}:-
          </option>
          <option value="2">
            Full pension
            {{ Math.round(chosenRoom.price * 0.15) * numberOfDays }}:-
          </option>
          <option value="3">
            Half pension
            {{ Math.round(chosenRoom.price * 0.1) * numberOfDays }}:-
          </option>
          <option value="4">DIY 0:-</option>
        </select>
        <p>Choose room type</p>
        <select id="chosenRoom" @change="calculatePrice()" v-model="chosenRoom">
          <option :value="room" selected>
            {{ getBookedRooms.type }} {{ getBookedRooms.price }}:-
          </option>
          <option
            :value="roomsForEdit"
            v-for="(roomsForEdit, index) in getAllRooms"
            :key="index"
          >
            {{ roomsForEdit.type }} {{ roomsForEdit.price }}:-
          </option>
        </select>
        <div id="extraBed">
          <input
            v-model="extraBed"
            type="checkbox"
            :checked="checked"
            @change="calculatePrice()"
          />
          <label id="extraBedLabel"> Would you like an extra bed? </label>
        </div>
      </div>
    </li>
    <p id="roomPrice">
      Room price: {{ newTotalPrice }}:- for {{ numberOfDays }} days
    </p>
  </div>
</template>

<script>
import moment from "moment";
export default {
  data() {
    return {
      newTotalPrice: this.price,
      checked: "",
      board: this.room.board,
      bookedRoomById: "",
      extraBed: "",
      roomType: "",
      chosenRoom: this.room,
      roomId: "",
      bookedRoomId: "",
      numberOfDays: 0,
    };
  },

  props: ["room", "price", "index", "dates"],

  mounted() {
    this.extraBed = this.room.extraBed;
    if (this.extraBed > 0) {
      this.checked = true;
    } else {
      this.checked = false;
    }
    this.bookedRoomId = this.chosenRoom.bookedRoomId;
    this.calculatePrice();
  },

  computed: {
    getAllRooms() {
      return this.$store.getters.getRoomList;
    },
    getBookedRooms() {
      return this.getBookedRoomById(this.index);
    },
  },

  methods: {
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
      if (this.extraBed) bedMultiplier = 1.05;

      let numberOfDays = this.calculateDateDiff();

      let calculatedPrice = Math.round(
        this.chosenRoom.price * boardMultiplier * bedMultiplier * numberOfDays
      );

      this.newTotalPrice = calculatedPrice; //newTotalPrice only has 2 decimals
      this.updateRoomInList();
    },

    updateRoomInList() {
      if (this.board == "") {
        this.board = this.chosenRoom.board;
      }
      if (this.roomId == "") {
        this.roomId = this.chosenRoom.roomId;
      }
      if (this.chosenRoom.id) {
        this.roomId = this.chosenRoom.id;
      }

      let object = {
        roomsId: this.roomId,
        board: this.board,
        extraBed: this.extraBed == true ? 1 : 0,
        roomCalculatedPrice: this.newTotalPrice,
        name: this.room.type,
        bookedRoomId: this.bookedRoomId,
      };

      this.$parent.updateRoomInEditRoomList(this.index, object);
    },

    getBookedRoomById(id) {
      return this.$store.getters.getBookedRoom[id];
    },
  },
};
</script>

<style scoped>
.editRoom-div {
  background-color: rgba(0, 0, 0, 0.1);
  height: 25vh;
  margin: 10px 10px;
  width: 100%;
  max-height: 25vh;
}

#rooms {
  display: flex;
  flex-direction: row;
  height: 25vh;
}

.roomPicture {
  height: 25vh;
  width: 22vw;
  overflow: hidden;
  order: 1;
}

#roomPic {
  height: 25vh;
  object-fit: contain;
}

.room-text {
  order: 2;
  display: flex;
  flex-direction: column;
  margin: auto;
}

#boardChoice {
  height: 25px;
  margin: 5px 0;
  outline: none;
}

#chosenRoom {
  height: 25px;
  margin: 5px 0;
  outline: none;
}

#roomPrice {
  position: relative;
  margin-top: 22vh;
  left: 45%;
}

#extraBed {
  margin-left: -4.5px;
  display: flex;
  width: 150%;
}

#extraBedLabel {
  margin-left: 5px;
  margin-top: 4.5px;
}
</style>