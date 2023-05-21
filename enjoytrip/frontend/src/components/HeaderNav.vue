<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container px-4 px-lg-5">
      <router-link to="/" class="navbar-brand">
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
        <ul v-if="!token" class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/auth"
              >로그인/회원가입</router-link
            >
          </li>
        </ul>
        <ul v-else class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/plan/username"
              >나의 플랜</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/board"
              >게시판</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/rank"
              >랭킹</router-link>
          </li>
          <li class="nav-item">
              <a href="/" class="nav-link" @click="logout">로그아웃</a>
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
      this.$router.push("/");
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
  box-shadow: 0px #888888;
}
a:link{
  color: #888888;
  text-decoration: none;
}
a:hover{
  color: #56caff;
  text-decoration: none;
}
a:visited{
  color: #888888;
  text-decoration: none;
}
a:visited:hover{
  color: #56caff;
  text-decoration: none;
}
a:active{
  color: #5667ff;
}
</style>
