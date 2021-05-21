<template>
  <div class="shoppingList">
    <ul>
      <li class="test" v-for="(roomItem, index) in updateRoomList" :key="index">
        {{ roomItem.type }} {{ roomItem.price }}
        <button @click="removeRoom(index, roomItem.id)">x</button>
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
    removeRoom(index, roomItemID) {
      this.roomList.splice(index, 1);
      this.$parent.showRoom(roomItemID)
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