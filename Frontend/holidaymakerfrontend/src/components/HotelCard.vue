<template>
    <div id="hotelCard"><p>{{card.name}}</p></div>
</template>

<script>
export default{
    props:["card"],
}
</script>

<style scoped>
#hotelCard{
    display: block;
    width: 45vw;
    height: 20vh;
    background-color: gray;
    margin: 15px;
}
</style>