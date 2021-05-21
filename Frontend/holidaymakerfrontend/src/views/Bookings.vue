<template>
  <div class="perRoom" v-for="(room, index) in getRoomsToBook" :key="index">
    <label id="roomName">Room: {{index + 1}}</label>
    <div class="board">
      <p>Choose board</p>
      <select name="boardDropDown" id="board">
        <option value="1">All inclusive</option>
        <option value="2">Half pension</option>
        <option value="3">Full pension</option>
        <option value="4">DIY</option>
      </select>
    </div>
    <div class="room" id="room">
      <p>Choose room type</p>
      <select name="roomtype" id="roomtype" class="selector" @change="calculatePrice()" v-model="roomItem">
        <option
          v-for="(roomItem, index) in getRoomList"
          :key="index"
          :value="roomItem"
          :v-model="options.push(roomItem.id)"
        >
          {{ roomItem.type }} {{ roomItem.price }} kr
        </option>
      </select>
      <div class="totalCost">
        <p>total cost: {{totalPrice}} kr</p>
      </div>
    </div>

    <div class="extraBed">
      <p>Do you want an extra bed?</p>
      <label for="accept">Yes</label>
      <input v-model="accept" type="checkbox" id="extraBed" name="horns" />
    </div>
  </div>

  <div>
    <button type="submit" class="next" @click="makeBooking()">Next</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      accept: false,
      totalPrice:0,
      roomItem:"",
      boardResult:0,
      extraBed:0,
      bookingID: 0,
      options: []
    };
  },
  computed: {
    getBookings() {
      return this.$store.getters.getBookings;
    },

    loggedInUser() {
      return this.$store.state.loggedInUser;
    },

    isLoggedIn() {
      return this.loggedInUser != null;
    },

    getLoggedInUserID() {
      return this.$store.state.loggedInUser.id;
    },

    getHotelID() {
      return this.$store.state.myRoom.hotel_id;
    },

    getFromDate() {
      return this.$store.state.fromDate;
    },

    getToDate() {
      return this.$store.state.toDate;
    },

    getRoomList() {
      return this.$store.getters.getRoomList;
    },

    getRoomsToBook(){
      return this.$store.getters.getRoomsToBook;
    },
  },

  methods:{

      getNumberOfDays(){
        return this.$store.getters.getNumberOfDays;
      },
  
      calculatePrice() {
        let calculatedPrice = this.roomItem.price*this.$store.getters.getNumberOfDays;
        this.totalPrice = calculatedPrice;
        console.log(calculatedPrice)
      },
    async makeBooking(){
      //userId, hotelId, fromDate, toDate, totalCost
      // skapar bookings, //fungerar om vi är inloggade.
      //TODO: fixa catch error om inte inloggad.
     // console.log("value: " + price.target.value)
      var boardChoice = document.getElementById("board");
      this.boardResult = parseInt(boardChoice.options[boardChoice.selectedIndex].value);
  
  //Bytas i framtiden*
      if(this.boardResult==1){
        this.totalPrice *= 1.2;
      }
      if(this.boardResult==2){
        this.totalPrice *= 1.15;
      }
      if(this.boardResult==3){
        this.totalPrice *= 1.1
      }
       this.extraBed = null;
      if (this.accept == true) {//bockad checkbox
        this.extraBed = 1;
        this.totalPrice *= 1.05;
      } else { //avbockad checkboc
        this.extraBed = 0;
      }
      console.log(this.totalPrice)

      let bookingcredentials = {
        userId: this.getLoggedInUserID,
        hotelId: this.$store.state.chosenRoom.hotel_id,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        totalCost: this.totalPrice,
      };

      console.log(bookingcredentials);
      await fetch("http://localhost:3000/rest/makeBooking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingcredentials)
      });

      await this.$store.dispatch("fetchLatestBookingID");
      
      this.bookRoom()
    },

    async bookRoom() {    
      var roomID = this.roomItem.id;
      this.bookingID = this.$store.getters.getBookingId

      let bookingIDObject = {
        id: this.bookingID,
      };

      let BookRoomCredentials = {
        roomsId: roomID,
        board: this.boardResult,
        extraBedAmount: this.extraBed,
        fromDate: this.getFromDate,
        toDate: this.getToDate,
        bookings: bookingIDObject,
      };

      console.log(BookRoomCredentials);
      await fetch("http://localhost:3000/rest/bookRoom", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(BookRoomCredentials),
      });
    },

    findIndex(id, index){
      return id == index
    },

    async setSelectors(){
      var option = await document.getElementsByName("roomtype")
      
      console.log(option)
      option.forEach((element, index) => {

        console.log(element)
        console.log(index)

        for(var i = 0; i < this.getRoomsToBook.length; i++){
          for(var j = 0; j < option.item(0).length; j++){
            if(this.getRoomsToBook[i]){
              if(this.getRoomsToBook[i].id == this.options[j]){
                console.log(j)
                if(index == i)
                  element.selectedIndex = j
              }
            }
          }
        }
      }) //Foreach end
    }
  },

  mounted(){
    this.$nextTick().then(this.setSelectors)
  }
};
</script>

<style>
.perRoom{
  background-color: rgba(255, 255, 255, .3);
  margin: 10px auto;
  padding-top: 20px;
  display: flex;
  justify-content: center;
}

#roomName{
  margin: 0 0;
}
</style>