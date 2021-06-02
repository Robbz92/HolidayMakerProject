<template>
  <div class="checkbox-select">
    <div class="trigger">
      <button class="title" @click="showFilter">Filter</button>
    </div>
    <div v-for="(filter, index) in ChosenFilters" :key="index"></div>
    <div id="dropdown" class="dropdown" :class="{ isActive: activeTrigger }">
      <div class="filter-headline">
        <div class="ttd-container">
          <label @click="showComforts" class="ttd">Things to do</label>
        </div>
        <div class="tmp-container">
          <label @click="showTemp" class="tmp">Temperature</label>
        </div>
      </div>
      <div class="filtersAndTemp">
        <div class="things-container">
          <ul class="filters-wrapp" :class="{ filterActive: comfortTrigger }">
            <li v-for="(filter, index) in filters" :key="index">
              <div id="com-wrapp">
                <input
                  type="checkbox"
                  :id="index"
                  class="conditions-check"
                  v-model="checkedFilters"
                  :value="filter"
                  v-if="index != 0 && index != 12"
                />
                <label :for="index">{{ filter.filter }}</label>
                <label v-if="index == 0 || index == 12" id="wideLabel">{{
                  filter
                }}</label>
              </div>
            </li>
          </ul>
        </div>
        <div class="temp-container">
          <div id="temp" class="temp" :class="{ tempActive: tempTrigger }">
            <TempSlider />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TempSlider from "./TemperatureSlider.vue";

export default {
  components: {
    TempSlider,
  },

  data() {
    return {
      filters: [
        "Things to do at the hotel",
        { filter: "Pool", type: "comfort" },
        { filter: "Entertainment", type: "comfort" },
        { filter: "Childrens club", type: "comfort" },
        { filter: "Restaurant", type: "comfort" },
        { filter: "Free WiFi", type: "comfort" },
        { filter: "Parking", type: "comfort" },
        { filter: "Free Parking", type: "comfort" },
        { filter: "Bar", type: "comfort" },
        { filter: "Gym", type: "comfort" },
        { filter: "Spa", type: "comfort" },
        { filter: "Pets allowed", type: "comfort" },
        "Things to do in the city",
        { filter: "Carnival", type: "attraction" },
        { filter: "Museum", type: "attraction" },
        { filter: "Brewery", type: "attraction" },
        { filter: "Amusement Park", type: "attraction" },
        { filter: "Vodka Distillery", type: "attraction" },
        { filter: "Sand Dunes", type: "attraction" },
        { filter: "Ski Resort", type: "attraction" },
        { filter: "Samuraj training camp", type: "attraction" },
        { filter: "Malmskillnadsgatan", type: "attraction" },
        { filter: "Kallbadhuset", type: "attraction" },
        { filter: "Coffe shops", type: "attraction" },
        { filter: "Elephant riding", type: "attraction" },
        { filter: "Bat eating festival", type: "attraction" },
        { filter: "Great Wall", type: "attraction" },
        { filter: "Brun ost", type: "attraction" },
        { filter: "Eiffel Tower", type: "attraction" },
        { filter: "Baguette Museum", type: "attraction" },
        { filter: "Frog farm", type: "attraction" },
        { filter: "Big tower", type: "attraction" },
        { filter: "Nude beach", type: "attraction" },
        { filter: "Beach", type: "attraction" },
        { filter: "Cigar factory", type: "attraction" },
        { filter: "Vulcanos", type: "attraction" },
        { filter: "Casinos", type: "attraction" },
      ],
      checkedFilters: [],
      activeTrigger: false,
      comfortTrigger: false,
      tempTrigger: false,
      dropdown: false,
      comforts: false,
      temperature: false,
    };
  },

  computed: {
    getFilters() {
      return this.filters;
    },

    ChosenFilters() {
      this.$store.commit("setFilterAmmount", this.checkedFilters.length);
      this.$parent.onFilter(this.checkedFilters);
      return this.checkedFilters;
    },
  },

  methods: {
    // stänger/öppnar filter menyn
    showFilter() {
      if (this.dropdown == false) {
        this.dropdown = true;
        this.activeTrigger = true;
      } else {
        this.dropdown = false;
        this.activeTrigger = false;
      }
    },

    showTemp() {
      if (this.temperature == false) {
        this.temperature = true;
        this.tempTrigger = true;
      } else {
        this.temperature = false;
        this.tempTrigger = false;
      }
    },

    showComforts() {
      if (this.comforts == false) {
        this.comforts = true;
        this.comfortTrigger = true;
      } else {
        this.comforts = false;
        this.comfortTrigger = false;
      }
    },
  },
};
</script>

<style scoped>

.title {
  font-size: small;
  padding: 5px;
  width: 50px;
}

#wideLabel {
  line-height: 25px;
  font-style: italic;
}

.filters-wrapp {
  list-style: none;
  text-align: left;
  display: inherit;
  visibility: hidden;
  padding: 0;
  transition: visibility .1s ease-in-out, filter .1s ease-in-out, max-height .1s;
  max-height: 0;
  visibility: hidden;
  filter: opacity(0);
}

.filterActive{
  visibility: inherit;
  filter: opacity(1);
  height: auto;
  max-height: 100%;
}

.temp{
  display: inherit;
  visibility: hidden;
  filter: opacity(0);
  max-height: 0;
  transition: visibility .1s ease-in-out, filter .1s ease-in-out, max-height .1s;
}

.tempActive{
  visibility: inherit;
  filter: opacity(1);
  max-height: 100%;
}

.dropdown {
  display: none;
  background: linear-gradient(rgba(120, 120, 120, 1) 10%, rgba(255, 255, 255, 0.7) 100%);
  max-height: 40vh;
  min-height: 0;
  overflow-y: auto;
  width: 400px;
  margin-top: 10px;
  justify-content: space-evenly;
  padding-top: 5px;
  padding-bottom: 5px;
  filter: opacity(0);
  
  transition: visibility .1s ease-in-out, filter .1s ease-in-out;
}

.isActive{
  display: flex;
  filter: opacity(1);
}

.filter-headline {
  display: flex;
  width: 100%;
  margin: 0 auto;
  justify-content: space-evenly;
  position: absolute;
}

.ttd,
.tmp {
  font-size: 15px;
  font-weight: bold;
  cursor: pointer;
}

.things-container {
  margin-right: 40px;
  height: auto;
}

.temp-container {
  min-width: 150px;
  height: auto;
}

.filtersAndTemp {
  display: flex;
  justify-content: space-evenly;
  margin-top: 20px;
}

/* Hide scrollbar for Chrome, Safari and Opera */
.dropdown::-webkit-scrollbar {
  display: none;
}

/* Hide scrollbar for IE, Edge and Firefox */
.dropdown {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
</style>