<template>
  <div id="nav">
    <router-link to="/" id="backButton">⬅</router-link>
    <h4 class="loggedInUser" v-if="isLoggedIn">{{ loggedInUser.firstName }}</h4>
    <router-link to="/login" id="text" v-if="!isLoggedIn">Login |</router-link>
  <button class="sameBtns" v-if="isLoggedIn" @click="logout">Logga ut</button>
    <router-link to="/register" id="text">Register</router-link> |
    <router-link to="/rooms" id="text">Room</router-link>
  </div>

  <router-view/>
</template>

<script>

export default {

   computed: {
    loggedInUser(){
      return this.$store.state.loggedInUser;
    },

    isLoggedIn(){
      return this.loggedInUser !=null;
    },
  },

  methods:{
    async logout(){
      fetch("/logout", {mode:"no-cors"});

      this.$store.commit("setLoggedInUser",null);
      this.$router.push("/");
      alert("you have logged out! have fun on your holiday!")

    },
  },
  async mounted(){
    let user = await fetch("/api/auth/whoami");
    try{
      user = await user.json();
      this.$store.commit("setLoggedInUser", user);
    }catch{
      console.log("not logged in");
    }
  },
};
  

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  display: flex;
  padding: 5px;
  justify-content: flex-end;
}

#nav > #text{
  padding: 5px;
  padding-top: 0;
}

#backButton{
  margin-right: auto;
  text-decoration: none;
  font-size: xx-large;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
