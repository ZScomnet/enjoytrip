import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // Home Route
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/Home.vue"),
  },
  // Auth Route
  {
    path: "/auth",
    name: "auth",
    component: () => import("@/views/Auth/AuthForm.vue"),
  },

  // Plan Route
  {
    path: "/plan/:username",
    name: "user_plan",
    component: () => import("@/views/PlanList.vue"),
  },{
    path: "/plan/:username/:plan_id",
    name: "plan_datail",
    component: () => import("@/views/KakaoMap.vue"),
  }



  // Board Route
  //   {
  //     path: "/board",
  //     name: "board",
  //     component: () => import("@/views/Board.vue"),
  //   },
  // Tour Route
  //   {
  //     path: "/tour",
  //     name: "tour",
  //     component: () => import("@/views/Tour.vue"),
  //   },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
