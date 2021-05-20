<template>
    <div id="popup" class="popup" @click="hide">
        <div class="window" v-on:click.stop.prevent>
            {{item}}
        </div>
    </div>
</template>

<script>
export default {
    props:['item'],

    methods:{
        hide(){
            /*
            Parent needs  the clickPopup function
            clickPopup(value){
            this.showPopup = value
            },
            
            AND 
            
            showPopup: false,

            in data
            */
            
            this.$parent.clickPopup(false)
        }
    }
}
</script>

<style scoped>
    .popup{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .3);
    }

    .window{
        position: fixed;
        width: 60%;
        height: 80%;
        top: 10%;
        left: 20%;
        background-color: white;
        box-shadow: 10px 10px 3px rgba(0, 0, 0, .3);
        visibility: inherit;
    }
</style>