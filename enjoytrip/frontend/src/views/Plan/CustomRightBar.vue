<template>
  <div class="right-container">
    <div :class="{ 'side-bar': isOpen }">
      <BInput
        :class="{ hide: !isOpen }"
        v-model="title"
        v-if="!this.$route.params.username"
        placeholder="플랜명을 입력해주세요.."
        @keyup="setPlanTitle(title)"
        style="padding: 10px; padding-top: 10px; font-size: 24px" />
      <BInput :class="{ hide: !isOpen }" v-model="this.planTitle" v-else placeholder="플랜명을 입력해주세요.." @keyup="setPlanTitle(title)" style="padding: 10px; padding-top: 10px; font-size: 24px" />
      <div class="search-area" :class="{ hide: !isOpen }">
        <b-button @click="addPlanDay"> 하루 연장 </b-button>
        <b-button @click="postPlan"> 플랜 저장 </b-button>
      </div>
      <div class="plan-area" :class="{ hide: !isOpen }">
        <div class="search-items"></div>
      </div>
      <div :class="{ hide: !isOpen }">
        <div class="plan-group">
          <!-- ListGroup -->
          <ul v-for="(tourList, idx) in plan" :key="idx" class="list-group">
            <li
              class="list-group-item active"
              :style="{
                backgroundColor: selectedDay == idx ? '#000000' : '',
                borderColor: selectedDay == idx ? '#000000' : '',
              }"
              @click="selectPlan(idx)">
              {{ idx + 1 }} 일차
              <span @click="deleteDay(idx)" style="cursor: pointer">&times;</span>
            </li>
            <li v-for="(tour, tourIdx) in tourList" :key="tourIdx" class="list-group-item">
              <div class="card">
                <div class="row">
                  <div class="col-md-4">
                    <img v-if="tour.firstImage !== ''" :src="tour.firstImage" class="img-field" />
                    <img v-else :src="require('@/assets/images/empty.jpg')" class="img-field" />
                  </div>
                  <div class="col-md-8">
                    <h4 style="text-align: center">{{ tour.title }}</h4>
                    <b-button @click="deleteTour(tourIdx, idx)"> 삭제 </b-button>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <button class="side-bar-btn" @click="rightClick">
        {{ isOpen ? ">" : "<" }}
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {
  name: "CustomRightBar",
  components: {},
  data() {
    return {
      isOpen: true,
      title: "",
    };
  },
  created() {
    this.$store.commit("SET_MAKE_PLAN");
  },
  methods: {
    rightClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    setPlanTitle(title) {
      this.$store.commit("SET_PLANTITLE", title);
    },
    getPlanTitle() {
      this.title = this.planTitle;
    },
    addPlanDay() {
      // 플랜 하루 추가
      this.$store.commit("ADD_DAY");
    },
    deleteTour(tourIdx, idx) {
      // 선택한 날짜의 tour 삭제
      this.$store.commit("DELETE_TOUR", {
        day: idx,
        idx: tourIdx,
      });
    },
    deleteDay(idx) {
      // 해당 날짜 tourList 통째로 삭제
      this.$store.commit("DELETE_DAY", idx);
    },
    selectPlan(idx) {
      // 관광지 추가할 날 선택
      this.$store.commit("SELECT_DAY", idx);
    },
    postPlan() {
      if (!this.$route.params.username && !this.$route.params.plan_id) {
        http.post("/attraction/plan", {
          planTitle: this.$store.state.planTitle,
          plan: this.$store.state.plan,
        });
        this.$router.push("/plan/" + this.userInfo.username);
      } else {
        http.post("/attraction/myplanUpdateList/" + this.$route.params.plan_id, {
          planTitle: this.$store.state.planTitle,
          plan: this.$store.state.plan,
        });
        this.$router.push("/plan/" + this.userInfo.username);
      }
    },
  },
  computed: {
    plan() {
      return this.$store.state.plan;
    },
    selectedDay() {
      return this.$store.state.selectedDay;
    },
    planTitle() {
      return this.$store.state.planTitle;
    },
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
};
</script>

<style lang="scss" scoped>
.right-container {
  position: absolute;
  top: 0;
  bottom: 0;
  > .side-bar {
    background-color: #ffffff;
    opacity: 0.9;
    width: 400px;
    height: 100%;
    > .search-area {
      padding: 20px 10px;

      input,
      input::placeholder,
      input:focus {
        font-size: 1.5rem;
        color: #000000;
        // box-shadow: none;
        // background: none;
        // border: none;
      }
    }
  }
}
.side-bar-btn {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translate(-70%, -50%);
}
.right-side-bar-title {
  text-align: center;
}
.hide {
  display: none;
}
h1 {
  padding-top: 5px;
}
.img-field {
  width: 100px;
  height: 101px;
}
.plan-group::-webkit-scrollbar {
  width: 10px;
  height: 0;
  -webkit-transform-origin-x: 10px;
}
.plan-group::-webkit-scrollbar-thumb {
  background: #000000;
  border-radius: 10px;
}
.plan-group::-webkit-scrollbar-track {
  background: #00000057;
  border-radius: 10px;
}
.plan-group {
  overflow-y: scroll;
  max-height: 82.9vh;
}
</style>
