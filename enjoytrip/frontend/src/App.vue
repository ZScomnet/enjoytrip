<template>
  <div id="app">
    <HeaderNav />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNav from "./components/HeaderNav.vue";
export default {
  name: "App",
  data() {
    return {};
  },
  components: {
    HeaderNav,
  },
  methods: {},
};
</script>

<style></style>
