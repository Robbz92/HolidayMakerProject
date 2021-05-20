<template>
  <div class="review">
    <ul v-for="(theBook, index) in getTheBooking " :key="index">
      <li id="list">
        <h3>Review</h3>
        <div id="theInfo">
        <div class="hotelPicture">
          <img id="hotelPic" :src="theBook.hotel_img"/>
        </div>
          <p>Total Cost: {{theBook.total_cost}} kr</p>
          <p>Date: {{theBook.from_date}} - {{theBook.to_date}}</p>
          <p>Room: {{theBook.type}}</p>
          <p>Board: {{theBook.board}} </p> 
          <p>Address: {{theBook.address}} </p>
        </div>
        <form>
          <h3>Score: {{score}}</h3>
            <input id="score" type="range" v-model="score" min="1" max="5"/>
           <h3>Write your opinion</h3>
            <textarea
            id="text"
            cols="40"
            rows="10"
            v-model="text"
            ></textarea>  
        </form>
        <button id="adding" @click="addReview(theBook.hotel_id)">Add Review</button> 
      </li>  
    </ul> 
   </div> 
</template>

<script>
export default {
name: "Review",
data(){
  return{
    score: '',
    text: '',
    hotel_id: '',
    booking:'',
    theBook:'',
  }
},
computed: {
    getTheBooking(){
     return this.$store.getters.getClickedBooking
    },
},
 methods: {
    setId(id){
      this.hotel_id = id
    },
    async addReview(id) {

      let newReview = {
        text: this.text,
        hotel_id: id,
        score:this.score,
        
      };
      console.log(newReview);
      await fetch("http://localhost:3000/api/rest/newReview" , {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newReview),
      });
      alert("Thanks for sharing your opinion!");

      this.$router.push("/myBookings");
    },
  
 },
  async mounted() {
     this.booking =  this.$store.getters.getClickedBooking
    
     console.log("From Mounted "+this.booking)
     
   }
}
</script>

<style scoped>

#text{
  width: 60%;
}
#theInfo{
  display: flex;
  justify-self: center;
  margin-top: 0;
  margin: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

#list{
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.322);
  width: 55%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
}
#hotelPic{
  width: 90%;
  border-radius: 5px;
  margin-top: 5em;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
}
button{
  margin-bottom:2em;
}
</style>