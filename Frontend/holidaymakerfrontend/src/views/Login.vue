<template>
<div class="loginContainer">
    <div class="loginHeadLiner">    
    <h1>Log in!</h1>
    </div>
    <div class="loginFormContainer">
    <form @submit.prevent="login">
    <input
    type ="email"
    class="form-control"
    placeholder="Enter email"
    v-model="email"
    required
    />
    <input
    type ="password"
    class="form-control"
    placeholder="Enter password"
    v-model="password"
    required
    />
<button type="submit" class="loginBtn">Log in</button>
</form>
</div>
</div>
</template>

<script>
export default {
  
  data() {
    return {
      email: "",
      password: "",
    };
  },

  mounted() {
    console.log("mounted Login");
  },

  methods: {
   
    async login() {
      const credentials =
        "username=" +
        encodeURIComponent(this.email) +
        "&password=" +
        encodeURIComponent(this.password);

      await fetch("http://localhost:3000/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        mode: "no-cors",
        body: credentials,
      });

      let user = await fetch("/api/auth/whoami");
      try {
        user = await user.json();
        this.$store.commit("setLoggedInUser", user);
      } catch {
        alert("Wrong credentials! Please check username or password.");
      }
      this.$router.push("/")
    },
  },
};
</script>

<style>
</style>