<template>
  <div class="right-container">
    <div :class="{ 'side-bar': isOpen }">
      <BInput
        v-model="title"
        placeholder="플랜명을 입력해주세요.."
        @keyup="setPlanTitle(title)" />
      <div class="search-area" :class="{ hide: !isOpen }">
        <b-button @click="addPlanDay"> 하루 연장 </b-button>
        <b-button @click="postPlan"> 플랜 저장 </b-button>
      </div>
      <div class="plan-area" :class="{ hide: !isOpen }">
        <div class="search-items"></div>
      </div>
      <div :class="{ hide: !isOpen }">
        <!-- ListGroup -->
        <ul v-for="(tourList, idx) in plan" :key="idx" class="list-group">
          <li class="list-group-item active" @click="selectPlan(idx)">
            {{ idx + 1 }} 일차
            <b-button @click="deleteDay(idx)"> X </b-button>
          </li>
          <li
            v-for="(tour, tourIdx) in tourList"
            :key="tourIdx"
            class="list-group-item">
            <div class="card">
              <div class="row">
                <div class="col-md-4">
                  <img
                    v-if="tour.firstImage !== ''"
                    :src="tour.firstImage"
                    class="img-field" />
                  <img
                    v-else
                    :src="require('@/assets/images/empty.jpg')"
                    class="img-field" />
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

      <button class="side-bar-btn" @click="rightClick">
        {{ isOpen ? ">" : "<" }}
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {
  name: "RightBar",
  components: {},
  data() {
    return {
      isOpen: true,
      title: "",
    };
  },
  methods: {
    rightClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    setPlanTitle(title) {
      this.$store.commit("SET_PLANTITLE", title);
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
      console.log(this.$store.state.planTitle);
      console.log(this.$store.state.plan);
      http.post("/plan", {
        planTitle: this.$store.state.planTitle,
        plan: this.$store.state.plan,
      });
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
</style>
