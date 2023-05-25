<template>
  <div v-if="!loading" class="total-rank-form">
    <div class="notice">
      <div class="page-title">
        <div class="rank-form">
          <h1 align="center">랭킹</h1>
          <h5 align="center">
            <span
              style="cursor: pointer"
              @click="setStatusGood"
              :style="{
                color: status === 0 ? '#56caff' : '#333333',
              }"
              >좋아요 순</span
            >
            <span
              style="cursor: pointer"
              @click="setStatusSelect"
              :style="{
                color: status === 1 ? '#56caff' : '#333333',
              }"
              >플랜 선택 순</span
            >
          </h5>
        </div>
      </div>

      <!-- board seach area -->
      <div v-if="status === 0" id="board-search">
        <div class="rank-form">
          <div class="first plan">
            <img
              v-if="plan_ranks[0][2] === '' || plan_ranks[0][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="plan_ranks[0][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="logo">
              <img :src="plan_ranks[0][6]" alt="" width="100%" height="100%" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plan_ranks[0][3] }} <br />
                Plan-title : {{ plan_ranks[0][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plan_ranks[0][5] }}</p>
            </div>
            <div class="plan-btn">
              <a
                style="cursor: pointer"
                @click="linkPlan(plan_ranks[0][3], plan_ranks[0][0])"
                >확인하기</a
              >
            </div>
          </div>
          <div class="second plan">
            <img
              v-if="plan_ranks[1][2] === '' || plan_ranks[1][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="plan_ranks[1][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="logo">
              <img :src="plan_ranks[1][6]" alt="" width="100%" height="100%" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plan_ranks[1][3] }} <br />
                Plan-title : {{ plan_ranks[1][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plan_ranks[1][5] }}</p>
            </div>
            <div class="plan-btn">
              <a
                style="cursor: pointer"
                @click="linkPlan(plan_ranks[1][3], plan_ranks[1][0])"
                >확인하기</a
              >
            </div>
          </div>
          <div class="third plan">
            <img
              v-if="plan_ranks[2][2] === '' || plan_ranks[2][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="plan_ranks[2][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="logo">
              <img :src="plan_ranks[2][6]" alt="" width="100%" height="100%" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plan_ranks[2][3] }} <br />
                Plan-title : {{ plan_ranks[2][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plan_ranks[2][5] }}</p>
            </div>
            <div class="plan-btn">
              <a
                style="cursor: pointer"
                @click="linkPlan(plan_ranks[2][3], plan_ranks[2][0])"
                >확인하기</a
              >
            </div>
          </div>
          <div
            v-for="(plan, idx) in plan_ranks.slice(3)"
            :key="idx"
            class="normal plan">
            <img
              v-if="plan[2] === '' || plan[2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="plan[2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="logo">
              <img :src="plan[6]" alt="" width="100%" height="100%" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plan[3] }} <br />
                Plan-title : {{ plan[1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plan[5] }}</p>
            </div>
            <div class="plan-btn">
              <a style="cursor: pointer" @click="linkPlan(plan[3], plan[0])"
                >확인하기</a
              >
            </div>
          </div>
        </div>
      </div>
      <div v-else id="board-search">
        <div class="rank-form">
          <div class="first plan">
            <img
              v-if="attr_ranks[0][2] === '' || attr_ranks[0][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="attr_ranks[0][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="main-text">
              <p>
                title :
                {{
                  attr_ranks[0][3].length > 10
                    ? attr_ranks[0][3].slice(0, 10) + "..."
                    : attr_ranks[0][3]
                }}
                <br />
                address :
                {{
                  attr_ranks[0][4].length > 10
                    ? attr_ranks[0][4].slice(0, 10) + "..."
                    : attr_ranks[0][4]
                }}
              </p>
            </div>
            <div class="like">
              <p>Pick : {{ attr_ranks[0][1] }}</p>
            </div>
            <div
              class="plan-btn"
              style="cursor: pointer"
              @click="openModal(0, attr_ranks[0][0])">
              상세보기
            </div>
          </div>

          <div class="second plan">
            <img
              v-if="attr_ranks[1][2] === '' || attr_ranks[1][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="attr_ranks[1][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="main-text">
              <p>
                title :
                {{
                  attr_ranks[1][3].length > 10
                    ? attr_ranks[1][3].slice(0, 10) + "..."
                    : attr_ranks[1][3]
                }}
                <br />
                address :
                {{
                  attr_ranks[1][4].length > 10
                    ? attr_ranks[1][4].slice(0, 10) + "..."
                    : attr_ranks[1][4]
                }}
              </p>
            </div>
            <div class="like">
              <p>Pick : {{ attr_ranks[1][1] }}</p>
            </div>
            <div
              class="plan-btn"
              style="cursor: pointer"
              @click="openModal(1, attr_ranks[1][0])">
              상세보기
            </div>
          </div>
          <div class="third plan">
            <img
              v-if="attr_ranks[2][2] === '' || attr_ranks[2][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="attr_ranks[2][2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="main-text">
              <p>
                title :
                {{
                  attr_ranks[2][3].length > 10
                    ? attr_ranks[2][3].slice(0, 10) + "..."
                    : attr_ranks[2][3]
                }}
                <br />
                address :
                {{
                  attr_ranks[2][4].length > 10
                    ? attr_ranks[2][4].slice(0, 10) + "..."
                    : attr_ranks[2][4]
                }}
              </p>
            </div>
            <div class="like">
              <p>Pick : {{ attr_ranks[2][1] }}</p>
            </div>
            <div
              class="plan-btn"
              style="cursor: pointer"
              @click="openModal(2, attr_ranks[2][0])">
              상세보기
            </div>
          </div>
          <div
            v-for="(rank, idx) in attr_ranks.slice(3)"
            :key="idx"
            class="normal plan">
            <img
              v-if="rank[2] === '' || rank[2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%"
              class="image" />
            <img
              v-else
              :src="rank[2]"
              alt=""
              class="image"
              style="width: 100%" />
            <div class="text"></div>
            <div class="main-text">
              <p>
                title :
                {{
                  rank[3].length > 10 ? rank[3].slice(0, 10) + "..." : rank[3]
                }}
                <br />
                address :
                {{
                  rank[4].length > 10 ? rank[4].slice(0, 10) + "..." : rank[4]
                }}
              </p>
            </div>
            <div class="like">
              <p>Pick : {{ rank[1] }}</p>
            </div>
            <div
              class="plan-btn"
              style="cursor: pointer"
              @click="openModal(idx + 3, attr_ranks[idx + 3][0])">
              상세보기
            </div>
          </div>
        </div>
        <div v-if="modal" class="modal">
          <div class="animate__animated animate__fadeInDown" id="modal-content">
            <h1 class="close" @click="closeModal">&times;</h1>
            <h3>{{ modalTitle }}</h3>
            <img
              v-if="modalImg !== ''"
              :src="modalImg"
              class="image"
              style="
                width: 80%;
                margin-top: 10px;
                margin-bottom: 10px;
                margin-left: 10%;
                margin-right: 10%;
              " />
            <img
              v-else
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              class="image"
              style="
                width: 80%;
                margin-top: 10px;
                margin-bottom: 10px;
                margin-left: 10%;
                margin-right: 10%;
              " />
            <p>
              {{ overview }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {
  data() {
    return {
      status: 0, // 0이면 좋아요 순, 1이면 플랜 순
      plan_ranks: [],
      attr_ranks: [],
      modal: false,
      modalTitle: "",
      modalImg: "",
      overview: "",
      loading: false,
    };
  },
  created() {
    this.loading = true;
    http.get("/attraction/allPlanLists").then((res) => {
      this.plan_ranks = res.data;
      for (let i = 0; i < this.plan_ranks.length; i++) {
        if (this.plan_ranks[i][6] !== null && this.plan_ranks[i][6] !== "")
          this.plan_ranks[i][6] =
            "http://localhost:8080/" + this.plan_ranks[i][6];
        else {
          this.plan_ranks[i][6] =
            "https://pixlok.com/wp-content/uploads/2022/02/Profile-Icon-SVG-09856789.png";
        }
      }
      this.loading = false;
    });
    http.get("/attraction/ranking/attr").then((res) => {
      this.attr_ranks = res.data;
    });
  },
  methods: {
    linkPlan(username, plan_id) {
      this.$router.push("/plan/" + username + "/" + plan_id);
    },
    getPlanGood(plan_id, idx) {
      http.get("/attraction/plan/" + plan_id + "/likeCnt").then((res) => {
        this.plan_ranks.splice(idx, 1, res.data);
      });
    },
    getProfileImg(username, idx) {
      http.get("/file/getProfileImg/" + username).then((res) => {
        if (res.data === "http://localhost:8080/null") {
          this.plan_ranks_profile.splice(
            idx,
            1,
            "https://pixlok.com/wp-content/uploads/2022/02/Profile-Icon-SVG-09856789.png"
          );
        } else {
          this.plan_ranks_profile.splice(idx, 1, res.data);
        }

        console.log(this.plan_ranks_profile);
      });
    },
    setStatusGood() {
      this.status = 0;
    },
    setStatusSelect() {
      this.status = 1;
    },
    openModal(idx, content_id) {
      this.modal = true;
      this.modalTitle = this.attr_ranks[idx][3];
      this.modalImg = this.attr_ranks[idx][2];
      http
        .get("/attraction/getDescription/" + content_id)
        .then((res) => (this.overview = res.data));
    },
    closeModal() {
      this.modal = false;
    },
  },
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
};
</script>

<style lang="scss" scoped>
h5 {
  margin: 30px;
}
span {
  margin: 0 30px;
}
p {
  text-align: left;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
.notice {
  padding-top: 5%;
  padding-left: 15%;
  padding-right: 15%;
}
#board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}
#board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}
#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}
#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}
#board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* reset */

* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

.clearfix:after {
  content: "";
  display: block;
  clear: both;
}
.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}
// Card CSS

* {
  text-align: center;
}

body {
  margin: 0;
  background-color: #eee;
  min-height: 100vh;
}

.plan {
  display: inline-block;
  position: relative;
  width: 400px;
  min-width: 400px;
  height: 400px;
  border-radius: 30px;
  overflow: hidden;
  box-shadow: 5px 5px 30px rgba(0, 0, 0, 0.3);
  margin: 30px;
}

.image {
  height: 70%;
}

.text {
  background-image: linear-gradient(0deg, #04a3ff, #a3cff7);
  border-radius: 30px;
  position: absolute;
  top: 55%;
  left: -5px;
  height: 65%;
  width: 108%;
  transform: skew(19deg, -9deg);
}

.first .text {
  background-image: linear-gradient(-20deg, #bb7413, #e7d25c);
}
.second .text {
  background-image: linear-gradient(-20deg, #969696, #ebebeb);
}
.third .text {
  background-image: linear-gradient(-20deg, #543325, #b4571d);
}

.logo {
  height: 80px;
  width: 80px;
  border-radius: 20px;
  background-color: #fff;
  position: absolute;
  bottom: 30%;
  left: 30px;
  overflow: hidden;
  box-shadow: 5px 5px 30px rgba(0, 0, 0, 0.7);
}

.logo img {
  height: 100%;
}

.main-text {
  position: absolute;
  color: #fff;
  font-weight: 900;
  left: 150px;
  bottom: 26%;
}

.plan-btn {
  position: absolute;
  color: #fff;
  right: 30px;
  bottom: 10%;
  padding: 10px 20px;
  border: 1px solid #fff;
  animation: button-anim 2s ease 0s infinite normal forwards;
}

.plan-btn:hover {
  animation: none;
}

.plan-btn a {
  color: #fff;
}

.like {
  position: absolute;
  color: #fff;
  left: 30px;
  bottom: 10%;
}

.btn i:before {
  width: 14px;
  height: 14px;
  position: fixed;
  color: #fff;
  background: #0077b5;
  padding: 10px;
  border-radius: 50%;
  top: 5px;
  right: 5px;
}
.modal {
  display: block;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}
#modal-content {
  background-color: #fefefe;
  margin-top: 10%;
  margin-left: 30%;
  padding: 20px;
  border: 1px solid #888;
  width: 40%;
  > .close {
    position: absolute;
    top: -5px;
    color: #aaa;
    right: 0;
    font-size: 50px;
    font-weight: bold;
  }
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
