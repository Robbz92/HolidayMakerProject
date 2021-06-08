<template>
  <div class="main-container">
    <div id="Selectors">
      <label id="BookTitle" @click="toggleComponents(true)">Bookings</label>
      <label id="FavTitle" @click="toggleComponents(false)">Favorites</label>
    </div>
    <div class="bokFav-container">
      <MyBooking v-if="showToggle" />
      <Favorites v-if="!showToggle" />
    </div>
  </div>
</template>

<script>
import MyBooking from "../components/MyBookings.vue";
import Favorites from "../components/Favorites.vue";

export default {
  name: "MyBookings",

  components: {
    MyBooking,
    Favorites,
  },

  data() {
    return {
      showToggle: true,
    };
  },

  methods: {
    toggleComponents(value) {
      this.showToggle = value;
      if (value) {
        document.getElementById("FavTitle").style.color = "#2c3e50";
        document.getElementById("BookTitle").style.color = "white";
      } else {
        document.getElementById("BookTitle").style.color = "#2c3e50";
        document.getElementById("FavTitle").style.color = "white";
      }
    },
  },
};
</script>

<style scoped>

#Selectors {
  font-size: 35px;
  display: flex;
  justify-content: center;
}
#Selectors > label {
  margin: 50px;
  font-weight: 600;
}

.bokFav-container {
  max-height: 78vh;
  overflow: auto;
  margin-top: 0;
}

::-webkit-scrollbar {
  display: none;
}

#BookTitle {
  cursor: pointer;
}

#BookTitle:hover {
  color: white !important;
  transition: 0.2s ease !important;
}

#FavTitle {
  cursor: pointer;
}

#FavTitle:hover {
  color: white !important;
  transition: 0.2s ease !important;
}
</style>