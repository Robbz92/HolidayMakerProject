<template>
  <div class="shoppingList" v-if="roomList != 0 || getStorage != null">
    <div v-if="getStorage && loggedInUser"></div>
    <ul class="theList">
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
    </ul>
    <button id="checkOutBtn" @click="bookRoom(roomList)">Check out</button>
  </div>
</template>

<script>
export default {
  // skapas för att kunna mutera roomList senare
  data() {
    return {
      roomList: this.roomCard,
    };
  },

  props: ["roomCard"],

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

    getStorage() {
      return JSON.parse(localStorage.getItem("localStorageRoom"));
    },
  },

  mounted() {
    let storeList = JSON.parse(localStorage.getItem("localStorageRoom"));
    if (storeList != null) {
      console.log(storeList);
      storeList.forEach((element) => {
        this.$parent.addRoom(element);
      });
    }
  },

  methods: {
    removeRoom(index, roomItemID) {
      this.roomList.splice(index, 1);

      // TODO: ta bort från local storage...
      window.localStorage.removeItem("localStorageRoom", 1);
      localStorage.setItem("localStorageRoom", JSON.stringify(this.roomList));

      this.$parent.showRoom(roomItemID);
    },

    bookRoom(room) {
      console.log(JSON.parse(localStorage.getItem("localStorageRoom")));
      if (this.loggedInUser == null) {
        alert("you have to be logged in or create an account before you make a booking.");
      } else {
        if (localStorage.length > 0) {
          //Sparar för hotell id't
          this.$store.commit(
            "setChosenRoom",
            JSON.parse(localStorage.getItem("localStorageRoom"))[0]
          );
          //Sparar ner listan med rum du valt, för att visa dom i "Booking"
          this.$store.commit(
            "setRoomsToBook",
            JSON.parse(localStorage.getItem("localStorageRoom"))
          );
          this.$router.push("/bookings/");
          //TODO: töm localStorage i Home.vue i mounted.
        } else {
          //Sparar för hotell id't
          this.$store.commit("setChosenRoom", room[0]);
          //Sparar ner listan med rum du valt, för att visa dom i "Booking"
          this.$store.commit("setRoomsToBook", room);
          this.$router.push("/bookings/");
        }
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

.theList {
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