<template>
    <div id="info">
        <div class="theInfo">
        <h3>Information:</h3>
          <ul  v-for="(info, index) in getInfo" :key="index">
            <li id="justInfo">
              <h4>Hotel: {{info.name}}</h4>
              <img id="picture" :src="info.hotel_img">
              <p>Downtown distance: {{info.distance_downtown}} km</p>
              <p>Address: {{info.address}}</p>
              <p>Beach distance: {{info.distance_beach}} km</p>
              <p>Score: {{info.total_score}}/5</p>
            </li>  
          </ul>
           <ul  v-for="(temperature, index) in getTemperature" :key="index">
            <li id="justTemp">
              <p>Temperature: {{temperature.temperature}} C
                
              </p>
              <p>City: {{temperature.City}}</p>
              <p>Country: {{temperature.Country}}</p>
            </li>  
          </ul>
          <h3>Attractions:</h3>
             <ul  v-for="(attraction, index) in getAttraction" :key="index">
            <li id="justAttraction">
              <p> {{attraction.name}} </p>
            </li>  
          </ul>
          <h3>Comforts:</h3>
             <ul  v-for="(comfort, index) in getComfort" :key="index">
            <li id="justComfort">
              <p> {{comfort.name}} </p>
            </li>  
          </ul>
        </div>   
    </div>  
        <div id="allRooms"> <h2>Rooms:</h2>
            <ul v-for="(room, index) in getRoomList"  :key="index">
            <li id="liRoom">
              <img id="roomPic" :src="room.room_img">
              <p>id: {{room.id}}</p>
              <p>type: {{room.type}}</p>
              <p>size: {{room.size}}</p>
              <p>description: {{room.description}}</p>
              <p>price: {{room.price}} kr</p>
            </li>
          </ul>
        </div>
      <div id="holder">
        <div class="reviews">
        <h2>Reviews</h2>
          <ul  v-for="(review, index) in getReviews" :key="index">
            <li id="justOne">
              <p>{{review.first_name}} {{review.last_name}}</p>
              <p>{{review.score}}/5</p>
              <p>{{review.text}}</p>
            </li> 
          </ul>
   
        </div>        
      </div> 
</template>

<script>
export default {

  computed: {
    getReviews(){
      return this.$store.getters.getReviews;
    },
    getInfo(){
      return this.$store.getters.getInformation;
    },
    getTemperature(){
      return this.$store.getters.getTemperature;
    },
    getAttraction(){
      return this.$store.getters.getAttractions;
    },
    getComfort(){
      return this.$store.getters.getComforts;
    },
    getRoomList(){
      console.log(this.$store.getters.getRoomList)
      return this.$store.getters.getRoomList;
    }
    
  },

}
</script>

<style scoped>

#picture {
  width: 50%;
}

#roomPic{
  width: 30%;
}
#liRoom{
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  width: 95%;
  background-color:white;
  margin-bottom: 20px;
  padding: 4px;
  border-radius: 5px;
}
#holder{
  display: flex;
  justify-content: center;
}
.reviews{
  margin-top: 35px;
  width: 65%;
  border: 1px solid rgb(187, 184, 184);
  padding: 10 px;
  border-radius: 5px;
}
#justOne{
  list-style-type: none;
  border: 1px solid rgb(187, 184, 184);
  width: 95%;
  background-color:white;
  margin-bottom: 20px;
  padding: 4px;
  border-radius: 5px;
}
</style>