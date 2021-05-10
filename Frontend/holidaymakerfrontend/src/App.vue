<template>
  <div id="nav">
    <router-link to="/" id="logo"><img src="./assets/logo.png" /></router-link>
    <h4 class="loggedInUser" v-if="isLoggedIn">{{ loggedInUser.firstName }}</h4>
    <div class="buttons">
      <router-link to="/login" id="text" v-if="!isLoggedIn">Login</router-link>
      <button class="sameBtns" v-if="isLoggedIn" @click="logout">Log out</button>
      <router-link to="/register" id="text">Register</router-link>
    </div>
  </div>

  <router-view />
</template>

<script>
export default {
  computed: {
    loggedInUser() {
      return this.$store.state.loggedInUser;
    },

    isLoggedIn() {
      return this.loggedInUser != null;
    },
  },

    /*
    logout funktion
    */
  methods: {
    async logout() {
      fetch("/logout", { mode: "no-cors" });

      this.$store.commit("setLoggedInUser", null);
      this.$router.push("/");
      alert("You have logged out! Have fun on your holiday!");
    },
  },
  async mounted() {
    let user = await fetch("/api/auth/whoami");
    try {
      user = await user.json();
      this.$store.commit("setLoggedInUser", user);
    } catch {
      console.log("Not logged in");
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
  background-image: url("assets/BG.jpg");
  height: 100%;
  min-height: 100vh;
  background-size: cover;
  background-position-y: -100px;
  background-repeat: no-repeat;
  background-attachment: fixed, scroll;
}

#nav {
  display: flex;
  padding: 5px;
  justify-content: space-between;
  align-items: center;
}

.buttons {
  margin: 1em;
}

.buttons a {
  margin-right: 2em;
}

#logo img {
  width: 15em;
  height: auto;
  margin: 1em;
}

#nav a {
  font-weight: bold;
  color: #ffffff;
}

</style>
