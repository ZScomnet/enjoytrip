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

<style>
#app {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
</style>
