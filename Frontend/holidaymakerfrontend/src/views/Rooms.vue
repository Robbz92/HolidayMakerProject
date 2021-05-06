<template>
  <div>
      <button @click="test">Liiiiiden knapp</button>
      <ul>
          <button class="ScaleUP" @click="sortArrayUp()">ScaleUP</button>
          <button class="ScaleDown" @click="sortArrayDown()">ScaleDown</button>

          <li v-for="(roomItem, index) in getRooms" :key="index">
              <p>
                 Price {{roomItem.price}} 
              </p>
          </li>
      </ul>
  </div>
</template>

<script>
export default {
    computed:{
        getRooms(){
           return this.$store.state.price
      },
    },
    methods:{
        sortArrayUp(){
            let rooms = this.$store.state.price
            return rooms.sort((a, b) => (a.price > b.price) ? 1 : -1)
        },
        sortArrayDown(){
            let rooms = this.$store.state.price
            return rooms.sort((a, b) => (a.price > b.price) ? -1 : 1)
        },

        test(){
            return this.$store.dispatch("fetchRooms");
        }
    }
}
</script>

<style>

</style>