<template>
  <div class="shoppingList">
    <ul class="theLiost">
      <div v-if="!hotelCardStorage.length">
        <li
          class="roomItemList"
          v-for="(roomItem, index) in updateRoomList"
          :key="index"
        >
          {{ getHotel }} - {{ roomItem.type }}
          <button class="removeBtn" @click="removeRoom(index, roomItem.id)">
            x
          </button>
        </li>
      </div>

      <div v-if="hotelCardStorage.length">
        <p>{{ hotelCard }}</p>
      </div>
    </ul>
    <button id="checkOutBtn" @click="bookRoom(roomList)" v-if="roomList != ''">
      Check out
    </button>
  </div>
</template>

<script>
export default {
  // skapas för att kunna mutera roomList senare
  data() {
    return {
      roomList: this.roomCard,
      hotelCardStorage: "",
    };
  },

  props: ["roomCard"],

  mounted() {
    //  this.beforeDestroy rensa storage..
    if (localStorage.hotelCard) {
      this.hotelCardStorage = localStorage.hotelCard;
    }
  },

  watch: {
    hotelCard(roomType) {
      localStorage.hotelCard = roomType;
    },
  },

  computed: {
    updateRoomList() {
      return this.roomList;
    },

    getHotel() {
      return this.$store.getters.getInformation[0].name;
    },

    loggedInUser() {
      return this.$store.state.loggedInUser;
    },

    hotelCard() {
      return this.hotelCardStorage;
    },

    beforeDestroy() {
      return localStorage.removeItem("hotelCard");
    },
  },
  methods: {
    removeRoom(index, roomItemID) {
      this.roomList.splice(index, 1);
      this.$parent.showRoom(roomItemID);
    },

    bookRoom(room) {
      if (this.loggedInUser == null) {
        alert("Du måste logga in eller skapa ett konto innan du ska boka.");

        this.hotelCardStorage =
          this.$store.getters.getInformation[0].name + " " + room[0].type;
      } else {
        this.beforeDestroy; // rensar local storage
        //Sparar för hotell id't
        this.$store.commit("setChosenRoom", room[0]);
        //Sparar ner listan med rum du valt, för att visa dom i "Booking"
        this.$store.commit("setRoomsToBook", room);
        this.$router.push("/bookings/");
      }
    },
  },
};
</script>

<style scoped>
.shoppingList {
  position: absolute;
  right: 0;
  width: 15%;
  min-height: 10%;
  backdrop-filter: blur(10px);
  background-color: rgba(255, 255, 255, 0.15);
  box-shadow: inset 0 0 5px rgba(255, 255, 255, 0.3);
}

.theLiost {
  padding-left: 0;
}

.roomItemList {
  list-style: none;
  margin-left: 0;
  min-width: 100%;
  margin-top: 10px;
}

#checkOutBtn {
  margin: 5px;
  margin-top: 30px;
}

.removeBtn {
  display: flex;
  justify-content: flex-end;

  margin-top: -20px;
}
</style>