<template>
  <div class="full-container">
    <div class="login-container">
      <div class="loginHeadLiner">
        <h1>Login!</h1>
      </div>
      <div class="loginFormContainer">
        <form @submit.prevent="login">
          <input
            type="email"
            class="form-control"
            placeholder="Enter email"
            v-model="email"
            required
          />
          <input
            type="password"
            class="form-control"
            placeholder="Enter password"
            v-model="password"
            required
          />
          <button type="submit" class="loginBtn">Login</button>
        </form>
      </div>
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

  methods: {
   /*
   Skapar login object och postar det till back end
   */
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
     
    /*
    kollar vem/om som är inloggad
    */
      let user = await fetch("/rest/whoami");
      try {
        user = await user.json();
        this.$store.commit("setLoggedInUser", user);
      } catch {
        alert("Wrong credentials! Please check username or password.");
      }
      //this.$router.push("/");
      window.history.back();
    },
  },
};
</script>

<style scoped>

.login-container {
  position: fixed;
  top: 50%;
  left: 50%;
  margin-top: -205px;
  margin-left: -100px;
}

.loginFormContainer{
  display: block;
  width: 10em;
  align-items: center;
}

input {
  outline: none;
  border: none;
  height: 1.5em;
  margin-bottom: 1em;
}

button {
  border: none;
  background-color: rgb(72, 210, 228);
  color: white;
  font-family: inherit;
  font-weight: bold;
  font-size: 1em;
  padding: 10px;
  border-radius: 100px;
  cursor: pointer;
  transition: 0.2s ease;
  outline: none;
}

button:hover {
  color: rgb(72, 210, 228);
  background-color: white;
}
</style>