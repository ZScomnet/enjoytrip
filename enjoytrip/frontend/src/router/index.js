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
    path: "/plan/:plan_id",
    name: "planList",
    component: () => import("@/views/PlanList.vue"),

    children: [
      // 여기에 사이드바 구현한 vue 등록
    ],
  },
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
