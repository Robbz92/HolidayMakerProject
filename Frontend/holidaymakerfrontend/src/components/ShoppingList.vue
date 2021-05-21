<template>
  <div class="shoppingList">
    <ul>
      <li class="test" v-for="(roomItem, index) in updateRoomList" :key="index">
        {{ roomItem.type }} {{ roomItem.price }}
        <button @click="removeRoom(index, roomItem.id)">x</button>
      </li>
    </ul>
    <button @click="bookRoom(roomList)" v-if="roomList != ''">KLICKA MIG?</button>
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
    
    loggedInUser() {
      return this.$store.state.loggedInUser;
    },
  },
  methods: {
    removeRoom(index, roomItemID) {
      this.roomList.splice(index, 1);
      this.$parent.showRoom(roomItemID)
    },

    bookRoom(room){
       if (this.loggedInUser == null) {
        alert("Du måste logga in eller skapa ett konto innan du ska boka.");
      } 
      else{
        this.$store.commit("setChosenRoom", room[0])
        this.$store.commit('setRoomsToBook', room)
        this.$router.push("/bookings/");
      }
    },
  },
};
</script>

<style>
.shoppingList {
  position: absolute;
  right: 0;
  width: 10%;
  height: 10%;
  background-color: white;
}
</style>