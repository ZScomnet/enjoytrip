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
        @submit.prevent="signIn"
        id="login"
        action=""
        class="input-group"
        :style="{ left: loginFormPos + 'px' }">
        <input
          type="text"
          v-model="email"
          class="input-field"
          placeholder="Email" />
        <input
          type="password"
          v-model="password"
          class="input-field"
          placeholder="Password" />

        <a class="find-password" @click="openModal">
          비밀번호를 잊으셨나요?
        </a>
        
        <button type="submit" class="button">Login</button>
      </form>
      <div v-if="modalOpen" class="modal">
          <div class="animate__animated animate__fadeInDown" id="modal-content">
            <h1 class="close" @click="closeModal">&times;</h1>
            <h3>비밀번호 찾기</h3>
            <input
              type="text"
              v-model="findEmail"
              class="input-field"
              placeholder="Email"
              required />

            <button class="emailSubmit" @click="findPassword">Send</button>
          </div>
        </div>
      <form
        @submit.prevent="signUp"
        id="register"
        action=""
        class="input-group"
        :style="{ left: registerFormPos + 'px' }"
        style="top: 120px">
        <input
          type="text"
          v-model="signUpEmail"
          class="input-field"
          placeholder="Email"
          :disabled="isDisabled"
          required />
        <h6 class="email-check" @click="emailCheck">이메일 중복 체크</h6>
        <input
          type="password"
          v-model="signUpPassword"
          class="input-field"
          placeholder="Password"
          required />
        <input
          type="text"
          v-model="username"
          class="input-field"
          placeholder="NickName"
          required />
        <input
          type="text"
          v-model="phone_number"
          class="input-field"
          placeholder="Tel ( without '-' )"
          required />
        <button type="submit" class="button">REGISTER</button>
      </form>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
import jwtDecode from "jwt-decode";
export default {
  data() {
    return {
      login: true,
      togglePos: 0,
      loginFormPos: 50,
      registerFormPos: 450,
      modalOpen: false,
      isDisabled: false,
      email: "",
      password: "",
      signUpEmail: "",
      signUpPassword: "",
      username: "",
      phone_number: "",
      findEmail: "",
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
      http
        .post("/auth/login", {
          email: this.email,
          password: this.password,
        })
        .then((res) => {
          let data = jwtDecode(res.data.token);
          this.$store.dispatch("login", {
            userInfo: {
              user_id: data.id,
              username: data.name,
              state: res.data.state,
            },
            token: res.data.token,
          });
          this.$router.push("/");
        })
        .catch(() => {
          alert("이메일이나 비밀번호를 다시 확인해주세요.");
        });
    },
    signUp() {
      // 회원가입 요청
      if (!this.isDisabled) alert("이메일 중복체크를 확인해 주세요!");
      else {
        http
          .post("/auth/signup", {
            email: this.signUpEmail,
            password: this.signUpPassword,
            username: this.username,
            phone_number: this.phone_number,
          })
          .then(() => {
            alert("회원가입이 완료됐습니다.");
            this.$router.go(0);
          })
          .catch(() => {
            alert("에러가 발생하였습니다.");
          });
      }
    },
    emailCheck() {
      // 이메일 중복체크 여부 반환
      http.get("/auth/checkEmail?email=" + this.signUpEmail).then((res) => {
        if (res.data === 1) {
          alert("이미 사용하고 있는 이메일입니다.");
        } else if (confirm("사용 가능한 이메일입니다. 사용하시겠습니까?")) {
          this.isDisabled = true;
        }
      });
    },
    openModal() {
      this.modalOpen = true;
    },
    closeModal() {
      this.modalOpen = false;
    },
    findPassword() {
      // 비밀번호 찾기 이메일 전송
      http.patch("/mail/send/" + this.findEmail).then(() => {
        alert("임시 비밀번호를 해당 이메일로 전송하였습니다.");
        this.$route.go(0);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
  font-family: "MaplestoryOTFBold";
  box-sizing: border-box;
}
.find-password {
  margin: 10px;
  margin-bottom: 40px;
  cursor: pointer;
  color: #888888;
  text-decoration: none;
}
.find-password:link {
  color: #888888;
  text-decoration: none;
}
.find-password:hover {
  color: #56caff;
  text-decoration: none;
}
.find-password:visited {
  color: #888888;
  text-decoration: none;
}
.find-password:visited:hover {
  color: #56caff;
  text-decoration: none;
}
.find-password:active {
  color: #5667ff;
}
.modal {
  display: block;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

#modal-content {
  background-color: #fefefe;
  margin-top: 20%;
  margin-left: 35%;
  padding: 20px;
  border: 1px solid #888;
  width: 30%;
  > .close {
    position: absolute;
    top: -5px;
    color: #aaa;
    right: 0;
    font-size: 50px;
    font-weight: bold;
  }
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
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
.email-check {
  color: #888888;
  padding: 5px 0;
  cursor: pointer;
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
.button {
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
.emailSubmit {
  width: 40%;
  padding: 10px 30px;
  cursor: pointer;
  display: block;
  margin: 10px auto;
  background: linear-gradient(to right, #00d0ff, #00a6ff1d);
  border: 0;
  outline: none;
  border-radius: 30px;
}
.checkbox {
  margin: 30px 10px 30px 0;
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
