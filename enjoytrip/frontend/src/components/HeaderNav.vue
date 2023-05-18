<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container px-4 px-lg-5">
      <router-link to="/home" class="navbar-brand">
        <!-- <img
          src="https://github.com/enjoytripProject/enjoytrip/assets/48237348/d67d02e8-bae5-4a71-ae8d-5fcfdb606bee" /> -->
        <img :src="require('@/assets/images/mainlogo.png')" />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarResponsive"
        aria-controls="navbarResponsive"
        aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <!-- <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/plan">플랜</router-link>
          </li>
          <li class="nav-item">
            <a @click="board" class="nav-link" href="#">게시판</a>
          </li>
          <li class="nav-item">
            <a @click="tour" class="nav-link" href="#">관광지</a>
          </li>
          <li class="nav-item">
            <a @click="logout" class="nav-link" href="#">로그아웃</a>
          </li>
        </ul> -->
        <ul v-if="!token" class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/auth"
              >로그인/회원가입</router-link
            >
          </li>
        </ul>
        <ul v-else class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/plan">플랜</router-link>
          </li>
          <li class="nav-item">
            <a @click="board" class="nav-link" href="/board">게시판</a>
          </li>
          <li class="nav-item">
            <a @click="tour" class="nav-link" href="/tour">관광지</a>
          </li>
          <li class="nav-item">
            <a @click="logout" class="nav-link">로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "HeaderNav",
  data() {
    return {};
  },
  created() {},
  methods: {
    home() {
      console.log("home");
    },
    plan() {
      console.log("plan");
    },
    board() {
      console.log("board");
    },
    tour() {
      console.log("tour");
    },
    logout() {
      this.$store.dispatch("logout");
      this.$router.push("/home");
    },
  },
  computed: {
    token() {
      return this.$store.state.token;
    },
  },
};
</script>

<style scoped>
nav {
  box-shadow: 0px 3px 2px #888888;
}
</style>
