<template>
  <div id="app">
    <HeaderNav />
    <img alt="Vue logo" src="./assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
import "@/assets/js/scripts.js";
import HelloWorld from "./components/HelloWorld.vue";
import HeaderNav from "./components/HeaderNav.vue";
export default {
  name: "App",
  components: {
    HelloWorld,
    HeaderNav,
  },
};
</script>

<style>
@import "@/assets/css/styles.css";
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
