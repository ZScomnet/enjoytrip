import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // Home Route
  {
    path: "/",
    name: "home",
    component: () => import("@/views/Home.vue"),
  },
  // Auth Route
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/Auth/LoginForm.vue"),
  },
  {
    path: "/sign-up",
    name: "sign-up",
    component: () => import("@/views/Auth/SignUpForm.vue"),
  },
  {
    path: "/find-pw",
    name: "find-pw",
    component: () => import("@/views/Auth/ForgotPassword.vue"),
  },

  // Plan Route
  {
    path: "/plan",
    name: "plan",
    component: () => import("@/views/KakaoMap.vue"),
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
