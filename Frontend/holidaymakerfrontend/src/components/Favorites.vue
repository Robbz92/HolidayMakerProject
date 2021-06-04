<template>
  <div class="main-container" v-if="getFavoriteList != ''">
    <div class="routes-container">
    </div>
    <div class="favoriteList-container">
      <div
        class="favorite"
        v-for="(favorite, index) in getFavoriteList"
        :key="index"
      >      
        <div class="name_and_img">
          <h4>{{ favorite.name }}</h4>
          <img :src="favorite.hotel_img" />
        </div>
        <div class="favText">
          <table>
            <tr>
              <th>Address</th>
              <th>Distance to downtown</th>
              <th>Distance to beach</th>
              <th>Score</th>
            </tr>
            <tr>
              <td>{{ favorite.address }}</td>
              <td>{{ favorite.distance_downtown }} km</td>
              <td>{{ favorite.distance_beach }}km</td>
              <td>{{ favorite.total_score }}/5</td>
            </tr>
          </table>
        </div>
        <div class="buttons">
          <!-- <button @click="openFavorite(favorite.name)">Open</button> -->
          <button @click="deleteFavorite(favorite.favorite_id)" id="deleteButton">Delete</button>
        </div>
      </div>
    </div>
  </div>
  <div class="noFavorites" v-else>
        <h3>You have no favorites</h3>
      </div>
</template>

<script>
export default {

  computed: {
    getFavoriteList() {
      return this.$store.getters.getFavoriteList;
    },
  },

  methods: {

    deleteFavorite(favoriteId) {
      if(confirm("Are you sure you want to delete this favorite?")) {
        this.$store.dispatch("deleteFavorite", favoriteId);
        this.$store.dispatch("fetchFavorites")
      }
    },
  },

  mounted() {
    this.$store.dispatch("fetchFavorites");
  },
};
</script>

<style scoped>

.favoriteList-container {
  border: 1px solid rgb(187, 184, 184);
  background-color: rgba(255, 255, 255, 0.438);
  width: 90%;
  margin-bottom: 20px;
  border-radius: 5px;
  margin: 0 auto;
  margin-top: 5em;
  backdrop-filter: blur(5px);
}

.favorite {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1em;
  padding-bottom: 1em;
  border-bottom: 1px solid rgb(187, 184, 184);
}

.name_and_img {
  width: 300px;
  height: auto;
  margin-left: 1em;
}

.name_and_img img {
  width: 100%;
}

.favText {
  margin-top: 3.5em;
}

th, td{
  width: 250px;
}

.buttons {
  margin: 0;
  padding: 0;
}

.buttons button {
  width: 100px;
  height: 45px;
  margin-right: 1em;
  margin-top: 240px;
}

#deleteButton {
  background-color: rgb(251, 76, 76);
}

#deleteButton:hover {
  background-color: white;
  color: rgb(251, 76, 76);
}
</style>