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
  },
  mutations: {
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
