<template>
  <div class="main-container">
    <div class="hotel-container">
      <h3>Your booking</h3>
      <ul v-for="(theBook, index) in getTheBooking" :key="index">
        <li id="list">
          <div class="hotelPicture">
            <img id="hotelPic" :src="theBook.hotel_img" />
          </div>
          <h2>{{ theBook.name }}</h2>
          <p>Total Cost: {{ theBook.total_cost }} kr</p>
          <p>Date: {{ theBook.from_date }} - {{ theBook.to_date }}</p>
          <p>Booked room(s): {{ theBook.BookedRooms }}</p>
          <p>Address: {{ theBook.address }}</p>
          <div class="buttons">
            <button id="deleteBooking" @click="deleteBooking(theBook.id)">
              Delete Booking
            </button>
            <button id="saveBooking">Save</button>
          </div>
        </li>
      </ul>
      <h3>Booked Room(s)</h3>
       <ul v-for="(theRoom, index) in getBookedRooms" :key="index">
        <li id="rooms">
          <h5>{{index+1}} - </h5>
         <div class="roomPicture">
            <img id="roomPic" :src="theRoom.room_img" />
          </div> 
          <p>Type: {{ theRoom.type }}</p>
          <p>Board: {{ theRoom.board }}</p>
          <p>Extra Bed: {{ theRoom.extraBed}}</p>
          <p>Price per night: {{theRoom.price}} kr</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default { 
    
    computed: {
    getTheBooking() {
      return this.$store.getters.getClickedBooking;
    },
    getBookedRooms() {
      var printa = this.$store.getters.getBookedRoom;
      console.log(printa + "desde componente")
      return printa;
    },
  },

  methods: {
      deleteBooking(id) {
          if(confirm("Are you sure you want to delete the booking?")){
              this.$store.dispatch("fetchDeleteBooking", id);
              this.$router.push("/myBookings");
          }
      },
  },

  mounted () {
 this.$emit('bookings', this.bookings);
}
  
  
};
</script>

<style scoped>
.main-container{
  width: 90%;
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.322);
   width: 75%;
  margin-bottom: 20px;
  border-radius: 5px;
  display: flex;
  justify-self: center;
  margin: 1em;
 
}

#list, #rooms{
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.322);
  width: 85%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
  display: flex;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}
#hotelPic{
  width: 100%;
  height:100%;
  border-radius: 5px;
  margin-top: 5em;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
}
 #roomPic{
  width: 30%;
  height:30%;
  border-radius: 5px;
  margin-top: 5em;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
}


</style>