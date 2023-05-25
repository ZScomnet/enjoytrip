<template>
  <div class="right-container">
    <div :class="{ 'side-bar': isOpen }">
      <h1 :class="{ hide: !isOpen }" align="center">Scheduler</h1>
      <BInput
        :class="{ hide: !isOpen }"
        v-model="title"
        v-if="!this.$route.params.username"
        placeholder="플랜명을 입력해주세요.."
        @keyup="setPlanTitle(title)"
        style="padding: 10px; padding-top: 10px; font-size: 24px" />

      <BInput
        :class="{ hide: !isOpen }"
        v-model="title"
        v-else
        placeholder="플랜명을 입력해주세요.."
        @keyup="setPlanTitle(title)"
        style="padding: 10px; padding-top: 10px; font-size: 24px" />
      <div class="search-area" :class="{ hide: !isOpen }">
        <b-button
          @click="addPlanDay"
          :class="{ hide: !isOpen }"
          style="width: 40%">
          일차 추가
        </b-button>
        <b-button
          @click="postPlan"
          :class="{ hide: !isOpen }"
          style="width: 40%">
          플랜 저장
        </b-button>
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
              <span @click="deleteDay(idx)" style="cursor: pointer"
                >&times;</span
              >
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
                    <b-button @click="deleteTour(tourIdx, idx)" style="width: 70%; display: flex; justify-content: center; margin: auto; margin-top : 10px">
                      삭제
                    </b-button>
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
      markers: [],
      images: [
        "travel-marker01.png",
        "travel-marker02.png",
        "travel-marker03.png",
        "travel-marker04.png",
        "travel-marker05.png",
        "travel-marker06.png",
        "travel-marker07.png",
        "travel-marker08.png",
        "travel-marker09.png",
        "travel-marker10.png",
        "travel-marker11.png",
        "travel-marker12.png",
        "travel-marker13.png",
        "travel-marker14.png",
      ],
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
      if (this.plan.length === 0) return;
      if (this.markers) {
        for (let i = 0; i < this.markers.length; i++)
          this.markers[i].setMap(null);
        this.markers = [];
      }
      // 관광지 추가할 날 선택
      this.$store.commit("SELECT_DAY", idx);
      const tourlist = this.plan[this.selectedDay];
      for (let i = 0; i < tourlist.length; i++) {
        const mapOption = new window.kakao.maps.LatLng(
          tourlist[i]["latitude"],
          tourlist[i]["longitude"]
        );
        var imageSrc = require("@/assets/images/" + this.images[i]), // 마커이미지의 주소입니다
          imageSize = new window.kakao.maps.Size(34, 40), // 마커이미지의 크기입니다
          imageOption = { offset: new window.kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        console.log(imageSrc);
        var markerImage = new window.kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        );
        this.map.setCenter(mapOption);
        this.markers.push(
          new window.kakao.maps.Marker({
            position: mapOption,
            image: markerImage,
          })
        );
        this.markers[i].setMap(this.map); // 마커를 지도에 표시
      }
    },
    postPlan() {
      if (!this.$route.params.username && !this.$route.params.plan_id) {
        http.post("/attraction/plan", {
          planTitle: this.$store.state.planTitle,
          user_id: this.$store.state.userInfo.user_id,
          plan: this.$store.state.plan,
        }).then(()=>{
          alert("플랜이 등록되었습니다.")
          this.$router.push("/plan/" + this.userInfo.username);
      });
      } else {
        http.post(
          "/attraction/myplanUpdateList/" + this.$route.params.plan_id,
          {
            planTitle: this.$store.state.planTitle,
            plan: this.$store.state.plan,
          }
        ).then(()=>{
          alert("플랜이 수정되었습니다.");
        this.$router.push("/plan/" + this.userInfo.username);
      }).catch((err)=> alert("ERROR! : " + err));
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
  watch: {
    "$store.state.plan": {
      handler() {
        this.selectPlan(this.selectedDay);
      },
      deep: true,
    },
  },
  props: ["map"],
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
      display: flex;
      justify-content: space-around;
      padding: 20px 10px;
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
