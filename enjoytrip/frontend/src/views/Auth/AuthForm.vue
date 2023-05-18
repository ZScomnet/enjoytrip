<template>
  <div class="wrap">
    <div class="form-wrap">
      <div class="button-wrap">
        <div id="btn" :style="{ left: togglePos + 'px' }"></div>
        <button type="button" class="togglebtn" @click="loginPos">
          LOG IN
        </button>
        <button type="button" class="togglebtn" @click="registerPos">
          REGISTER
        </button>
      </div>
      <form
        id="login"
        action=""
        class="input-group"
        :style="{ left: loginFormPos + 'px' }">
        <input type="text" class="input-field" placeholder="Email" required />
        <input
          type="password"
          class="input-field"
          placeholder="Password"
          required />
        <button class="submit" @click="signIn">Login</button>
      </form>
      <form
        id="register"
        action=""
        class="input-group"
        :style="{ left: registerFormPos + 'px' }"
        @click="signUp">
        <input type="text" class="input-field" placeholder="Email" required />
        <input
          type="password"
          class="input-field"
          placeholder="Password"
          required />
        <input
          type="email"
          class="input-field"
          placeholder="NickName"
          required />
        <input type="text" class="input-field" placeholder="Tel" required />
        <button class="submit">REGISTER</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      login: true,
      togglePos: 0,
      loginFormPos: 50,
      registerFormPos: 450,
    };
  },
  methods: {
    loginPos() {
      this.togglePos = 0;
      this.loginFormPos = 50;
      this.registerFormPos = 450;
    },
    registerPos() {
      this.togglePos = 150;
      this.loginFormPos = -400;
      this.registerFormPos = 50;
    },
    signIn() {
      this.$store.dispatch("login");
      this.$router.push("/home");
    },
    signUp() {
      // 회원가입 요청
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: "MaplestoryOTFBold";
  box-sizing: border-box;
}
.wrap {
  height: 100%;
  width: 100%;
  background-image: url(@/assets/images/background.jpg);
  background-position: center;
  background-size: cover;
  position: absolute;
}
.form-wrap {
  width: 380px;
  height: 480px;
  position: relative;
  margin: 6% auto;
  background: #fff;
  padding: 5px;
  overflow: hidden;
}
.button-wrap {
  width: 300px;
  margin: 35px auto;
  position: relative;
  box-shadow: 0 0 600px 9px #fcae8f;
  border-radius: 30px;
}
.togglebtn {
  padding: 10px 30px;
  width: 150px;
  cursor: pointer;
  background: transparent;
  border: 0;
  outline: none;
  position: relative;
}
#btn {
  top: 0;
  left: 0;
  position: absolute;
  width: 150px;
  height: 100%;
  background: linear-gradient(to right, #00d0ff, #00a6ff1d);
  border-radius: 30px;
  transition: 0.5s;
}
.social-icons {
  margin: 30px auto;
  text-align: center;
}
.social-icons img {
  width: 30px;
  cursor: pointer;
}
.input-group {
  top: 180px;
  position: absolute;
  width: 280px;
  transition: 0.5s;
}
.input-field {
  width: 100%;
  padding: 10px 0;
  margin: 5px 0;
  border: none;
  border-bottom: 1px solid #999;
  outline: none;
  background: transparent;
}
.submit {
  width: 100%;
  padding: 10px 30px;
  cursor: pointer;
  display: block;
  margin: auto;
  background: linear-gradient(to right, #00d0ff, #00a6ff1d);
  border: 0;
  outline: none;
  border-radius: 30px;
}
.checkbox {
  margin: 30px 10px 30px 0;
}
span {
  color: #777;
  font-size: 12px;
  bottom: 68px;
  position: absolute;
}
#login {
  left: 50px;
}
#register {
  left: 450px;
}

@font-face {
  font-family: "MaplestoryOTFBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
