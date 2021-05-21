<template>
  <div class="shoppingList">
    <ul>
      <li v-for="(roomItem, index) in updateRoomList" :key="index">
          {{roomItem.type}} {{roomItem.price}}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ["roomCard"],
  
  computed:{
      updateRoomList(){
          return this.roomCard;
      }
  }
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