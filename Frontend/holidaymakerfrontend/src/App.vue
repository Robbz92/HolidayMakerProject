<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link to="/register">Register</router-link>
  </div>
  <div id="search"><searchBar/></div>

  <h4 class="loggedInUser" v-if="isLoggedIn">{{ loggedInUser.firstName }}</h4>
  <button class="sameBtns" v-if="isLoggedIn" @click="logout">Logga ut</button>

  <router-view/>
</template>

<script>
import searchBar from "./components/SearchBar.vue"

export default {
  components:{
    searchBar
  },

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
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
