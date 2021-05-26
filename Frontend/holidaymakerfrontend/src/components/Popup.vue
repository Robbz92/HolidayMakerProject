<template>
  <div id="popup" class="popup" @click="hide">
    <div class="window" v-on:click.stop.prevent>
      <h1>Your booking details</h1>
      <br />
      <div class="description">
        <div v-for="(hotelInfo, index) in getInformation" :key="index">
          <p>Hotel: {{ hotelInfo.name }}</p>
          <p>Address: {{ hotelInfo.address }}</p>
          <img id="hotelImage" :src="hotelInfo.hotel_img" />
        </div>
        <br />
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
      </div>

      <div class="pay">
        <p>Total price: {{ totalCost }} SEK</p>

        <div class="payBtn">
          <Stripe id="btn" :totalPrice="totalCost" />
          <button id="btn" @click="paylater()">Pay later</button>
        </div>
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

    async paylater() {
      this.$parent.makeBooking();
      setTimeout(() => this.$router.push("/myBookings"), 500)
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
  background-color: rgba(0, 0, 0, 0.2);
  backdrop-filter: blur(3px);
}

.window {
  position: fixed;
  width: 40%;
  min-height: 40%;
  max-height: 70%;
  top: 11.2%;
  left: 30%;
  background-color:rgba(255, 255, 255, 0.7);
  box-shadow: 10px 10px 3px rgba(0, 0, 0, 0.1);
  visibility: inherit;
  border-radius: 30px;
  overflow-y: auto;
}

#hotelImage {
  width: 30%;
  height: auto;
  position: absolute; /* lägger sig ovan allt.*/
  left: 2%;
  top: 20%;
}

.pay {
  position: relative;
  display: flex;
  float: right;
  flex-direction: column;
}

.payBtn {
  margin: 5px;
  display: flex;
  justify-content: center;
}

#btn {
  margin: 5px;
}

.description{
  text-align: left;
  padding-left: 35%;
  width: 50%;
}
</style>