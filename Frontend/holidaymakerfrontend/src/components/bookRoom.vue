<template>
  <div class="editRoom-div">
    <li id="rooms">
      <div class="roomPicture">
        <img id="roomPic" :src="chosenRoom.room_img" alt="" />
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
            {{ Math.round(chosenRoom.price * 0.15 * numberOfDays) }}:-
          </option>
          <option value="3">
            Half pension
            {{ Math.round(chosenRoom.price * 0.1 * numberOfDays) }}:-
          </option>
          <option value="4">DIY 0:-</option>
        </select>
        <p>Choose room type</p>
        <select id="chosenRoom" @change="calculatePrice()" v-model="chosenRoom">
          <option
            :value="roomItem"
            v-for="(roomItem, index) in getRoomList"
            :key="index"
          >
            {{ roomItem.type }} {{ roomItem.price }}:-
          </option>
        </select>
        <div class="extraBed">
          <input
            v-model="extraBed"
            type="checkbox"
            @change="calculatePrice()"
          />
          <label id="extraBedLabel"> Would you like an extra bed? </label>
        </div>
      </div>
    </li>
    <p id="roomPrice">
      Room price: {{ newPrice }}:- for {{ numberOfDays }} days
    </p>
  </div>
</template>

<script>
export default {
  props: ["room", "index"],

  data() {
    return {
      roomPrice: 0,
      newPrice: 0,
      extraBed: false,
      board: 1,
      chosenRoom: this.room, //chosenRoom är rummet som är valt i drop down menyn, var tvungen att ha den som en separat variabel för att kunna ändra dynamiskt vilket val som är gjort
      numberOfDays: 0,
    };
  },

  computed: {
    getRoomList() {
      return this.$store.getters.getRoomList;
    },
  },

  methods: {
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

      let calculatedPrice = Math.round(
        this.chosenRoom.price *
          boardMultiplier *
          bedMultiplier *
          this.numberOfDays
      );

      this.newPrice = calculatedPrice;

      this.updateRoomInList();
    },

    updateRoomInList() {
      let object = {
        roomID: this.chosenRoom.id,
        board: this.board,
        extraBedAmount: this.extraBed,
        totalCost: this.newPrice,
        name: this.chosenRoom.type,
      };
      // parent är bookings rad 89,
      this.$parent.fillBookingArray(this.index, object);
    },
  },

  mounted() {
    this.calculatePrice();
  },

  beforeMount() {
    this.numberOfDays = this.$store.getters.getNumberOfDays;
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
}

#chosenRoom {
  height: 25px;
  margin: 5px 0;
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