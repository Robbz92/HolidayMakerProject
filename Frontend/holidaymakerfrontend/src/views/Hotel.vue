<template>
  <div>
       <ul>
      <li>
        <div class="text">
          <ol>
            <li v-for="(room, index) in getRoomList"  :key="index">
              <p>id: {{room.id}}</p>
              <p>type: {{room.type}}</p>
              <p>size: {{room.size}}</p>
              <p>description: {{room.description}}</p>
              <p>price: {{room.price}}</p>
            </li>
          </ol>
        </div>          
      </li>
    </ul>
  </div>
</template>

<script>
export default {

  computed: {
    getRoomList(){
      return this.$store.getters.getRoomList
    }
  },

  methods:{
      
  },

}
</script>

<style>

</style>