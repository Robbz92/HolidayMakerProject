<template>
  <div id="popup" class="popup" @click="hide">
    <div class="window" v-on:click.stop.prevent>
      <h1>Your booking details</h1>
      <div v-for="(hotelInfo, index) in getInformation" :key="index">
        <p>Hotel: {{ hotelInfo.name }}</p>
        <p>Address: {{ hotelInfo.address }}</p>
      </div>
      <div v-for="(tempItem, index) in getTemperature" :key="index">
        <p>City: {{ tempItem.City }}</p>
        <p>Country: {{ tempItem.Country }}</p>
        <br />
      </div>

      <div v-for="(roomItem, index) in getRoomList" :key="index">
        <p>Room: {{ roomItem.name }}</p>
        <p>Board: {{ getcheckBoards[index] }}</p>
        <br />
      </div>

      <div>
        <p>Total price: {{ totalCost }} SEK</p>
      </div>

      <div class="pay">
         <Stripe :totalPrice="totalCost"/>
        <button @click="paylater()">Pay later</button>
      </div>
    </div>
  </div>
</template>

<script>
import Stripe from "../components/StripeCheckout.vue";
export default {
  props: ["roomList"],

  data() {
    return {
      boardNames: [],
      totalCost: 0,
    };
  },

  components: {
    Stripe,
  },

  computed: {
    getInformation() {
      return this.$store.getters.getInformation;
    },
    getTemperature() {
      return this.$store.getters.getTemperature;
    },
    getRoomList() {
      return this.roomList; // uppdateras när props gör det.
    },

    getcheckBoards() {
      this.getBoardName();
      return this.boardNames;
    },
  },

  methods: {
    paynow() {
      this.$parent.makeBooking();
    },

    paylater() {
      this.$parent.makeBooking();
      this.$router.push("/myBookings")
    },

    getBoardName() {
      for (var i = 0; i < this.roomList.length; i++) {
        this.totalCost += this.roomList[i].totalCost;
        var boardName = this.roomList[i].board.toString(); // fixa autostart för drop down.
        switch (boardName) {
          case "1":
            boardName = "All inclusive";
            break;
          case "2":
            boardName = "Full pension";
            break;
          case "3":
            boardName = "Half pension";
            break;
          case "4":
            boardName = "DIY";
            break;
        }
        this.boardNames[i] = boardName;
      }
    },

    hide() {
      this.$parent.clickPopup(false);
    },
  },
};
</script>

<style scoped>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
}

.window {
  position: fixed;
  width: 60%;
  height: 80%;
  top: 10%;
  left: 20%;
  background-color: white;
  box-shadow: 10px 10px 3px rgba(0, 0, 0, 0.3);
  visibility: inherit;
}
</style>