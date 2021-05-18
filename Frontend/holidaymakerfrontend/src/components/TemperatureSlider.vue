<template>
    <div>
    <p>{{ temp }}</p>
        <vue3-slider
        v-model="temp"
        id="slider"
        :update="changeSliderColor()"
        :color="sliderColor"
        track-color="#FEFEFE"
        :max="30"
        :height="10"
        />
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
    };
  },

  methods:{
    updateTemp(){
      this.$store.commit("setSearchedTemperature", this.temp);
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