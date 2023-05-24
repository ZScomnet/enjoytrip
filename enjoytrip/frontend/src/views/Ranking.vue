<template>
  <div v-if="loading" class="total-rank-form">
    <div class="notice">
      <div class="page-title">
        <div class="rank-form">
          <h1 align="center">랭킹</h1>
          <h5 align="center">
            <span @click="setStatusGood" :style="{
                        color: status === 0 ? '#56caff' : '#333333',
                      }">좋아요 순</span> <span @click="setStatusSelect" :style="{
                        color: status === 1 ? '#56caff' : '#333333',
                      }">플랜 선택 순</span>
          </h5>
        </div>
      </div>

      <!-- board seach area -->
      <div v-if="status === 0" id="board-search">
        <div class="rank-form">
          <div class="first plan">
            <img
              v-if="plans[0][2] === '' || plans[0][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="plans[0][2]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="logo">
              <img :src="require('@/assets/images/profile.png')" alt="" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plansUsername[0] }} <br />
                Plan-title : {{ plans[0][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plansGoods[0] }}</p>
            </div>
            <div class="plan-btn">
                <a href="#" @click="linkPlan(plansUsername[0],plans[0][0])">확인하기</a>
            </div>
          </div>
          <div class="second plan">
            <img
              v-if="plans[1][2] === '' || plans[1][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="plans[1][2]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="logo">
              <img :src="require('@/assets/images/profile.png')" alt="" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plansUsername[1] }} <br />
                Plan-title : {{ plans[1][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plansGoods[1] }}</p>
            </div>
            <div class="plan-btn">
                <a href="#" @click="linkPlan(plansUsername[1],plans[1][0])">확인하기</a>
            </div>
          </div>
          <div class="third plan">
            <img
              v-if="plans[2][2] === '' || plans[2][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="plans[2][2]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="logo">
              <img :src="require('@/assets/images/profile.png')" alt="" />
            </div>
            <div class="main-text">
              <p>
                username : {{ plansUsername[2] }} <br />
                Plan-title : {{ plans[2][1] }}
              </p>
            </div>
            <div class="like">
              <p>Like : {{ plansGoods[2] }}</p>
            </div>
            <div class="plan-btn">
                <a href="#" @click="linkPlan(plansUsername[2],plans[2][0])">확인하기</a>
            </div>
          </div>
          <span v-for="(plan, idx) in plans" :key=idx>
            <div v-if="idx > 2" class="normal plan">
              <img
              v-if="plans[idx][2] === '' || plans[idx][2] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="plans[idx][2]"
              alt=""
              class="image" 
              style="width: 100%;"/>
              <div class="text"></div>
              <div class="logo">
                <img :src="require('@/assets/images/profile.png')" alt="" />
              </div>
              <div class="main-text">
                <p>
                username : {{ plansUsername[idx] }} <br />
                Plan-title : {{ plans[idx][1] }}
              </p>
              </div>
              <div class="like">
                <p>Like : {{ plansGoods[idx] }}</p>
              </div>
              <div class="plan-btn">
                <a href="#" @click="linkPlan(plansUsername[idx],plans[idx][0])">확인하기</a>
              </div>
            </div>
          </span>
        </div>
      </div>
      <div v-else id="board-search">
        <div class="rank-form">
          <div class="first plan">
            <img
              v-if="attr_ranks[0][3] === '' || attr_ranks[0][3] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="attr_ranks[0][3]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="main-text">
              <p>
                title : {{ attr_ranks[0][12] }} <br>
                address : {{ attr_ranks[0][1] }}
              </p>
            </div>
            <div class="like">
              <p>Pick : 0</p>
            </div>
          </div>
          <div class="second plan">
            <img
              v-if="attr_ranks[1][3] === '' || attr_ranks[1][3] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="attr_ranks[1][3]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="main-text">
              <p>
                title : {{ attr_ranks[1][12] }} <br>
                address : {{ attr_ranks[1][1] }}
              </p>
            </div>
            <div class="like">
              <p>Pick : 0</p>
            </div>
          </div>
          <div class="third plan">
            <img
              v-if="attr_ranks[2][3] === '' || attr_ranks[2][3] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="attr_ranks[2][3]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="main-text">
              <p>
                title : {{ attr_ranks[2][12] }} <br>
                address : {{ attr_ranks[2][1] }}
              </p>
            </div>
            <div class="like">
              <p>Pick : 0</p>
            </div>
          </div>
          <span v-for="(rank, idx) in attr_ranks" :key=idx>
            <div v-if="idx > 2" class="normal plan">
              <img
              v-if="rank[3] === '' || rank[3] === null"
              :src="require('@/assets/images/empty.jpg')"
              alt=""
              style="width: 100%;"
              class="image" 
              />
              <img
              v-else
              :src="rank[3]"
              alt=""
              class="image" 
              style="width: 100%;"/>
            <div class="text"></div>
            <div class="main-text">
              <p>
                title : {{ rank[12] }} <br>
                address : {{ rank[1] }}
              </p>
            </div>
            <div class="like">
              <p>Pick : 0</p>
            </div>
            </div>
          </span>
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
      plans: [],
      plansUsername: [],
      plansGoods: [],
      attr_ranks: [],
      loading: false,
    };
  },
  created() {
    http.get("/attraction/allPlanLists").then((res) => {
      this.plans = res.data;
      for(let i=0;i<this.plans.length;i++){
        this.plansUsername.push("");
        this.plansGoods.push(0);
      }
        this.loading = true;
      for(let i=0;i<this.plans.length;i++){
        this.getPlanUsername(this.plans[i][0],i);
        this.getPlanGood(this.plans[i][0],i);
      }
    });
    http.get("/attraction/LikeAttractionList/1").then((res) => {
      this.attr_ranks = res.data;
      console.log(this.attr_ranks);
    });
  },
  methods: {
    linkPlan(username, plan_id) {
      this.$router.push("/plan/"+username+"/"+plan_id);
    },
    getPlanUsername(plan_id,idx){
      http.get("/attraction/plan/"+plan_id+"/planRank")
      .then((res)=>{
        this.plansUsername.splice(idx,1,res.data);
      })
    },
    getPlanGood(plan_id,idx){
      http.get("/attraction/plan/"+plan_id+"/likeCnt")
      .then((res)=>{
        this.plansGoods.splice(idx,1,res.data);
      })
    },
    setStatusGood(){
      this.status = 0;
    },
    setStatusSelect(){
      this.status = 1;
    }
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
</style>
