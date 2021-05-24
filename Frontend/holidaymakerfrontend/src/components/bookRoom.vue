<template>
<div>
    <label id="roomName">{{room.name}}</label>
    <div class="board">
      <p>Choose board</p>
      <select name="boardDropDown" id="board" @change="calculatePrice()" v-model="board">
        <option value="1">All inclusive</option>
        <option value="2">Half pension</option>
        <option value="3">Full pension</option>
        <option value="4">DIY</option>
      </select>
    </div>
    <div class="room" id="room">
      <p>Choose room type</p>
      <select name="roomtype" id="roomtype" class="selector" @change="calculatePrice()" v-model="chosenRoom">
        <option
          v-for="(roomItem, index) in getRoomList"
          :key="index"
          :value="roomItem"
        >
          {{ roomItem.type }} {{ roomItem.price }} kr
        </option>
      </select>
      <div class="totalCost">
        <p>total cost: {{newPrice}} kr</p>
      </div>
    </div>

    <div class="extraBed">
      <p>Do you want an extra bed?</p>
      <label for="accept">Yes</label>
      <input v-model="extraBed" type="checkbox" :id="'extraBed'" name="horns" @change="calculatePrice()"/>
    </div>
  </div>
</template>

<script>
export default {
    props:['room'],

    data(){
        return{
            roomPrice: 0,
            newPrice: 0,
            extraBed: false,
            board: 1,
            chosenRoom: this.room,
        }
    },

    computed:{
        getRoomList() {
        return this.$store.getters.getRoomList;
        }
    },

    methods:{
        calculatePrice(){
            let boardMultiplier = 1.2
    //Checks what board is chosen per room
            switch(this.board){
            case "1":
                boardMultiplier = 1.2
                break;
            case "2":
                boardMultiplier = 1.15
                break;
            case "3":
                boardMultiplier = 1.1
                break;
            case "4":
                boardMultiplier = 1
                break;
            }

            let bedMultiplier = 1
            if(this.extraBed)
                bedMultiplier = 1.05

            let numberOfDays = this.$store.getters.getNumberOfDays
            

            let calculatedPrice = Math.round(this.chosenRoom.price * boardMultiplier * bedMultiplier * numberOfDays);
            
            //console.log(calculatedPrice)
            this.newPrice = calculatedPrice
        },

        getNumberOfDays(){
            return this.$store.getters.getNumberOfDays;
        },
    },

    mounted(){
        this.calculatePrice()
    }
}
</script>