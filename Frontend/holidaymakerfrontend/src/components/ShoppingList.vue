<template>
  <div class="shoppingList">
    <ul>
      <li v-for="(roomItem, index) in updateRoomList" :key="index">
        {{ roomItem.type }} {{ roomItem.price }}
        <button class="removeRoom" @click="removeRoom(index)">x</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
    // skapas för att kunna mutera roomList senare , ta bort saker från listan
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
  },
  methods: {
    removeRoom(roomItemID) {
      this.roomList.splice(roomItemID, 1);
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