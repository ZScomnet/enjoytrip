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
    path: "/auth",
    name: "auth",
    component: () => import("@/views/Auth/AuthForm.vue"),
  },

  // Plan Route
  { // 플랜 새로 만들기 페이지
    path: "/plan/edit",
    name: "plan_edit",
    component: () => import("@/views/KakaoMap.vue"),
  },{ // 특정 유저의 플랜들을 보여준다.
    path: "/plan/:username",
    name: "user_plan",
    component: () => import("@/views/PlanList.vue"),
  },{ // 특정 유저의 플랜 중 plan_id에 해당되는 플랜을 보여준다.
    path: "/plan/:username/:plan_id",
    name: "plan_datail",
    component: () => import("@/views/KakaoMap.vue"),
  },
  // Board Route
    { // 전체 게시판
      path: "/board",
      name: "board",
      component: () => import("@/views/BoardList.vue"),
    },{ // 게시글 작성 페이지
      path: "/board/write",
      name: "write",
      component: () => import("@/views/BoardWrite.vue"),
    },{ // 특정 게시판
      path: "/board/:idx",
      name: "specialBoard",
      component: () => import("@/views/BoardList.vue"),
    },{ // 게시글 디테일 페이지
      path: "/board/detail/:idx",
      name: "boardDetail",
      component: () => import("@/views/BoardDetail.vue"),
    },
  // Tour Route
    {
      path: "/rank",
      name: "rank",
      component: () => import("@/views/Ranking.vue"),
    },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
