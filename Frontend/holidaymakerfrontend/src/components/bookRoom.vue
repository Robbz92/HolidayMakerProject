<template>
  <div>
    <label id="roomName">{{ room.name }}</label>
    <div class="board">
      <p>Choose board</p>
      <select
        name="boardDropDown"
        id="board"
        @change="calculatePrice()"
        v-model="board"
      >
        <option value="1">All inclusive {{Math.round(chosenRoom.price * 0.2) * numberOfDays}}:-</option>
        <option value="2">Full pension {{Math.round(chosenRoom.price * 0.15 * numberOfDays)}}:-</option>
        <option value="3">Half pension {{Math.round(chosenRoom.price * 0.1 * numberOfDays)}}:-</option>
        <option value="4">DIY 0:-</option>
      </select>
    </div>
    <div class="room" id="room">
      <p>Choose room type</p>
      <select
        name="roomtype"
        id="roomtype"
        class="selector"
        @change="calculatePrice()"
        v-model="chosenRoom"
      >
        <option
          v-for="(roomItem, index) in getRoomList"
          :key="index"
          :value="roomItem"
        >
          {{ roomItem.type }} {{ roomItem.price }}:-
        </option>
      </select>
      <div class="totalCost">
        <p>Room price: {{ newPrice }}:- for {{numberOfDays}} days</p>
      </div>
    </div>

    <div class="extraBed">
      <p>Do you want an extra bed?</p>
      <label for="accept">Yes</label>
      <input
        v-model="extraBed"
        type="checkbox"
        :id="'extraBed'"
        name="horns"
        @change="calculatePrice()"
      />
    </div>
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
        this.chosenRoom.price * boardMultiplier * bedMultiplier * this.numberOfDays
      );

      this.newPrice = calculatedPrice;
  
      this.updateRoomInList()
    },

    updateRoomInList(){
      let object = {
        roomID: this.chosenRoom.id,
        board: this.board,
        extraBedAmount: this.extraBed,
        totalCost: this.newPrice,
        name: this.chosenRoom.type
      }
      // parent är bookings rad 89,
      this.$parent.fillBookingArray(this.index, object)
    },
  },

  mounted() {
    this.calculatePrice();
  },

  beforeMount(){
    this.numberOfDays = this.$store.getters.getNumberOfDays;
  }
};
</script>