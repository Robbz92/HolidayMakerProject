<template>
    <div>
    <p v-if="tempRange != 0">{{ temp - tempRange}}° - {{ getTempPlus }}°</p>
    <p v-else>{{ temp }}°</p>
        <vue3-slider
          v-model="temp"
          id="slider"
          :update="changeSliderColor()"
          :color="sliderColor"
          track-color="#FEFEFE"
          :max="30"
          :height="10"
        />
        <p style="font-style: italic">Temperature Range</p>
        <input type="number" style="width: 35px" min="0" max="10" v-model="tempRange" :update="changeSliderColor()">
    </div>
</template>

<script>
import slider from "vue3-slider";

export default {
    components:{
        "vue3-slider": slider,
    },

  data() {
    return {
      sliderColor: this.changeSliderColor(),
      temp: 0,
      tempRange: 0,
    };
  },

  computed:{
    getTempPlus(){
      return parseInt(this.temp) + parseInt(this.tempRange)
    }
  },

  methods:{
    updateTemp(){
      if(this.tempRange > 10)
        this.tempRange = 10
      if(this.tempRange < 0)
        this.tempRange = 0
      this.$store.commit("setSearchedTemperature", this.temp);
      this.$store.commit("setTempRange", this.tempRange)
    
    },

    changeSliderColor() {
        this.updateTemp()
      let color = "";
      if (this.temp <= 10) {
        color = "rgb(3,173,252)";
        this.sliderColor = color;
      }
      if (this.temp > 10 && this.temp <= 15) {
        color = "rgb(3,252,190)";
        this.sliderColor = color;
      }
      if (this.temp > 15 && this.temp <= 20) {
        color = "rgb(252,219,3)";
        this.sliderColor = color;
      }
      if (this.temp > 20 && this.temp <= 25) {
        color = "rgb(252, 148, 3)";
        this.sliderColor = color;
      }
      if (this.temp > 25) {
        color = "rgb(252, 69, 3)";
        this.sliderColor = color;
      }
    },
  }
}
</script>