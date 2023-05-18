import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http.js";

// jwt decode module
// import jwtDecode from "jwt-decode";
// state를 localstorage에 저장
import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createVuexPersistedState()],
  state: {
    // 저장할 정보
    userInfo: {}, // 유저의 정보
    token: null, // jwt
    attractions: {},
    planTitle: "",
    plan: [],
    selectedDay: 0,
  },
  mutations: {
    SET_MAKE_PLAN(state) {
      // 플랜 사이트 입장했을 때 초기화
      state.plan = [];
      state.selectedDay = 0;
      state.planTitle = "";
    },
    // 언더바 권장, method 구현
    SET_USER_INFO(state, payload) {
      state.userInfo = payload;
    },
    SET_TOKEN(state, payload) {
      state.token = payload;
    },
    CLEAR_USER(state) {
      state.userInfo = {};
      state.token = null;
    },
    SET_ATTRACTIONS(state, payload) {
      state.attractions = payload;
    },
    SET_PLANTITLE(state, title) {
      // 플랜 타이틀 지정
      state.planTitle = title;
    },
    ADD_DAY(state) {
      // 하루 일차 추가
      state.plan.push([]);
    },
    ADD_TOUR(state, payload) {
      // 여행정보 넣기
      state.plan[payload.day].push(payload.tourInfo);
    },
    DELETE_TOUR(state, payload) {
      // idx 일차 몇번째 여행지 삭제
      state.plan[payload.day].splice(payload.idx, 1);
    },
    DELETE_DAY(state, day) {
      // idx 일차 통째로 삭제
      state.plan.splice(day, 1);
    },
    SELECT_DAY(state, day) {
      // 관광지 추가할 날짜 선택
      state.selectedDay = day;
    },
  },
  actions: {
    // 비동기 통신 method
    login(context, loginInfo) {
      console.log(loginInfo);
      // id, pw 정보를 갖고 서버에 로그인 요청
      context.commit("SET_USER_INFO", {
        userInfo: {
          id: "ssafy",
          name: "ssafy",
        },
      });
      context.commit("SET_TOKEN", "ssafy");
      // http
      //   .post("/auth/login", loginInfo)
      //   // 로그인 성공 -> userInfo, token 세팅
      //   .then((res) => {
      //     let token = res.data.token;

      //     const decodeToken = jwtDecode(token);

      //     context.commit("SET_USER_INFO", {
      //       userInfo: {
      //         id: decodeToken.id,
      //         name: decodeToken.name,
      //       },
      //     });
      //   })
      //   .catch((err) => {
      //     // 로그인 실패 -> 예외 처리
      //     console.log(err);
      //   });
    },

    logout(context) {
      context.commit("CLEAR_USER");
    },
    getAllAttractions(context) {
      http.get("/attraction").then((res) => {
        context.commit("SET_ATTRACTIONS", res.data);
      });
    },
  },
});
