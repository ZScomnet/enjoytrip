import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueSelect from "vue-select";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import 'animate.css';
Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.component("v-select",VueSelect);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
