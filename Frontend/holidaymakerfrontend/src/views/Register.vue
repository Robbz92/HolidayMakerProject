<template>
  <div class="full-container">
    <div class="register-container">
      <div class="register-h1">
        <h1>Register!</h1>
      </div>
      <form @submit.prevent="register" class="registerForm">
        <input
          type="firstName"
          class="form-control"
          placeholder="First name"
          v-model="firstName"
          required
        />
        <input
          type="lastName"
          class="form-control"
          placeholder="Last name"
          v-model="lastName"
          required
        />
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
          placeholder="Password"
          v-model="password"
          required
        />
        <button type="submit" class="RegisterBtn">Register</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
    };
  },
  methods: {
    async register() {
      let credentials = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
      };
      await fetch("http://localhost:3000/api/rest/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(credentials),
      });
      alert("register succefurry");

      this.$router.push("/");
    },
  },
};
</script>

<style>
.register-container {
  position: fixed;
  top: 50%;
  left: 50%;
  margin-top: -205px;
  margin-left: -100px;
}

.registerForm {
  display: block;
  width: 10em;
  margin: 0 auto;
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