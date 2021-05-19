<template>
  <div class="checkbox-select">
    <div class="trigger" :class="{isActive: activeTrigger}"
    @click="showFilter">
      <button class="title">
        Filter
      </button>
    </div>
    <div v-for="(filter, index) in ChosenFilters" :key="index"></div>
    <div id="dropdown" class="dropdown">
      <label @click="showComforts">Things to do <br></label>

      <ul id="filters-wrapp">
        <li v-for="(filter, index) in filters" :key="index">
          <div id="com-wrapp">
            <input type="checkbox" :id="index"
            class="conditions-check" v-model="checkedFilters"
            :value="filter" v-if="index != 0 && index != 12">
            <label :for="index">{{filter.filter}}</label>
            <label v-if="index == 0 || index == 12" id="wideLabel">{{filter}}</label>
          </div>
        </li>      
      </ul>
    <label @click="showTemp">Temperature</label><br>
      <div id="temp" class="temp">
        <TempSlider/>
      </div>
    </div>
  </div>
</template>

<script>
import TempSlider from "./TemperatureSlider.vue"

export default {
    components:{
      TempSlider
    },

    data(){
        return{
            filters: [
                "Things to do at the hotel",
                {filter: "Pool", type:"comfort"},
                {filter: "Entertainment", type:"comfort"},
                {filter: "Childrens club", type:"comfort"},
                {filter: "Restaurant", type:"comfort"},
                {filter: "Free WiFi", type:"comfort"},
                {filter: "Parking", type:"comfort"},
                {filter: "Free Parking", type:"comfort"},
                {filter: "Bar", type:"comfort"},
                {filter: "Gym", type:"comfort"},
                {filter: "Spa", type:"comfort"},
                {filter: "Pets allowed", type:"comfort"},
                "Things to do in the city",
                {filter: "Carnival", type:"attraction"},
                {filter: "Museum", type:"attraction"},
                {filter: "Brewery", type:"attraction"},
                {filter: "Amusement Park", type:"attraction"},
                {filter: "Vodka Distillery", type:"attraction"},
                {filter: "Sand Dunes", type:"attraction"},
                {filter: "Ski Resort", type:"attraction"},
                {filter: "Samuraj training camp", type:"attraction"},
                {filter: "Malmskillnadsgatan", type:"attraction"},
                {filter: "Kallbadhuset", type:"attraction"},
                {filter: "Coffe shops", type:"attraction"},
                {filter: "Elephant riding", type:"attraction"},
                {filter: "Bat eating festival", type:"attraction"},
                {filter: "Great Wall", type:"attraction"},
                {filter: "Brun ost", type:"attraction"},
                {filter: "Eiffel Tower", type:"attraction"},
                {filter: "Baguette Museum", type:"attraction"},
                {filter: "Frog farm", type:"attraction"},
                {filter: "Big tower", type:"attraction"},
                {filter: "Nude beach", type:"attraction"},
                {filter: "Beach", type:"attraction"},
                {filter: "Cigar factory", type:"attraction"},
                {filter: "Vulcanos", type:"attraction"},
                {filter: "Casinos", type:"attraction"}
            ],
            checkedFilters: [],
            activeTrigger: false,
            dropdown: false,
            comforts: false,
            attractions: false,
        }
    },

    computed:{
      getFilters(){
        return this.filters
      },

      ChosenFilters(){
        this.$store.commit("setFilterAmmount", this.checkedFilters.length);
        this.$parent.onFilter(this.checkedFilters)
        return this.checkedFilters
      }
    },

    methods:{
      showFilter(){
        var e = document.getElementById("dropdown");

        if(this.dropdown == false){
          this.dropdown = true;
          this.activeTrigger = true;
          e.style.display = "inherit";
          e.style.opacity = 1;
        }else{
          this.dropdown = false;
          this.activeTrigger = false;
          e.style.display = "none";
          e.style.opacity = 0;
        }
      },

      showTemp(){
        var e = document.getElementById("temp");

        if(this.dropdown == false){
          this.dropdown = true;
          this.activeTrigger = true;
          e.style.display = "inherit";
          e.style.opacity = 1;
        }else{
          this.dropdown = false;
          this.activeTrigger = false;
          e.style.display = "none";
          e.style.opacity = 0;
        }
      },

      showComforts(){
        var e = document.getElementById("filters-wrapp")

        if(this.comforts == false){
          this.comforts = true;
          e.style.display = "inherit";
        }else{
          this.comforts = false;
          e.style.display = "none";
        }
      },

      testFunktion(filter){
        this.$parent.onFilter(filter)
      }
    }
}
</script>

<style scoped>
  .checkbox-select{
    max-height: 40vh;
    overflow-x: auto;
  }

  .title{
    font-size: small;
    padding: 5px;
  }

  #wideLabel{
    line-height: 25px;
    font-style: italic;
  }

  #filters-wrapp{
    list-style: none;
    text-align: left;
    display: none;
  }

  .info{
    background: rgba(255, 255, 255, .3);
    backdrop-filter: blur(5px);
  }

  .temp{
    display: none;
    backdrop-filter: blur(5px);
  }

  #dropdown{
    display: none;
    opacity: 0;
    transition: display .3s, opacity .3s;
    background: rgba(255, 255, 255, .3);
    backdrop-filter: blur(5px);
  }
</style>