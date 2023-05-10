<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container px-4 px-lg-5">
      <router-link to="/home" class="navbar-brand">Main Logo</router-link>
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
      console.log("logout");
    },
  },
};
</script>

<style>
@import "../assets/css/styles.css";
</style>
