<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container px-4 px-lg-5">
      <a @click="home" href="#" class="navbar-brand">Main Logo</a>
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
        <ul class="navbar-nav">
          <li class="nav-item">
            <a @click="plan" class="nav-link" href="#">플랜</a>
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
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
export default {
  name: "HeaderNav",
  methods: {
    home() {
      console.log("home");
      this.$emit("event", "home");
    },
    plan() {
      console.log("plan");
      this.$emit("event", "plan");
    },
    board() {
      console.log("board");
      this.$emit("event", "board");
    },
    tour() {
      console.log("tour");
      this.$emit("event", "tour");
    },
    logout() {
      console.log("logout");
      this.$emit("event", "logout");
    },
  },
};
</script>

<style>
@import "../../assets/css/styles.css";
</style>
