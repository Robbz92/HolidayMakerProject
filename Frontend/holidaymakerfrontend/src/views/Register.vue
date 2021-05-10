<template>
  <div class="registerContainer">
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
            <button type="submit" class="RegisterBtn">register</button>
        </form>      
  </div>
</template>

<script>
export default {
    data(){
        return{
            firstName:"",
            lastName:"",
            email:"",
            password:"",
        };
    },
    /*
    Skapar registrerings object
    */
    methods:{
        async register(){
            let credentials={
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email,
                password: this.password,

            };
            await fetch("http://localhost:3000/api/rest/register",{
                method: "POST",
                headers: {"Content-Type":"application/json"},
                body: JSON.stringify(credentials),

            });
            alert("register complete");

            this.$router.push("/");
        }

    }
};
</script>

<style>
</style>