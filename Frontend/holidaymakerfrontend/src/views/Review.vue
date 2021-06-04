<template>
  <div class="review">
    <ul v-for="(theBooking, index) in getTheBooking" :key="index">
      <li id="list">
        <h3>Review</h3>
        <div id="theInfo">
          <div class="hotelPicture">
            <img id="hotelPic" :src="theBooking.hotel_img" />
          </div>
           <div class="booking-text">
            <table>
            <tr>
              <th>Address</th>
              <th>From Date</th>
              <th>To Date</th>
              <th>Booked Rooms</th>
              <th>Total Cost</th>
            </tr>
            <tr>
              <td>{{theBooking.address}}</td>
              <td>{{ theBooking.from_date }}</td>
              <td>{{ theBooking.to_date }}</td>
              <td>{{ theBooking.BookedRooms}}</td>
              <td>{{ theBooking.total_cost }}</td>
            </tr>            
          </table>
          </div>
        </div> 

        <form>
          <h3>Score: {{ score }}</h3>
          <input id="score" type="range" v-model="score" min="1" max="5" />
          <h3>Write your opinion</h3>
          <textarea id="text" cols="40" rows="10" v-model="text"></textarea>
        </form>
        <button id="adding" @click="addReview(theBooking.hotel_id, theBooking.id)">
          Add Review
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Review",
  data() {
    return {
      score: 1,
      text: "",
      hotel_id: "",
      booking: "",
      theBook: "",
      
    };
  },
  computed: {
    getTheBooking() {
      return this.$store.getters.getClickedBooking;
    },

    getCheckReview() {
      return this.$store.getters.getCheckReview;
    },
  },
  methods: {
    setId(id) {
      this.hotel_id = id;
    },

    async addReview(id, bookingID) {
      let newReview = {
        text: this.text,
        hotel_id: id,
        score: this.score,
        bookingId: bookingID,
      };
      await fetch("http://localhost:3000/api/rest/newReview", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newReview),
      });
      alert("Thanks for sharing your opinion!");

      this.$router.push("/myPage");
    },
  },


};
</script>

<style scoped>
#text {
  width: 80%;
}
#theInfo {
  display: flex;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

.booking-Text {
 width: 100%;
  margin-top: 70px;
  margin-left: 1px;
}
.booking-text{
  width: 100%;
  margin-top: 70px;
}
.booking-text table{
  width:100%;
}
.booking-text table th{
  margin: 0;
 
}

#list {
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.322);
  width: 75%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
  
}
.hotelPicture {
  margin: 0;
  padding: 0;
  width: 300px;
  height: auto;
  overflow: hidden;
}
#hotelPic {
  width: 100%;
  border-radius: 5px;
  margin-top: 5em;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
  display: flex;
  justify-content: space-between;
}
button {
  margin-bottom: 2em;
}
</style>