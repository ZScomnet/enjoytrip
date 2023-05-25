<template>
  <div class="right-container">
    <div :class="{ 'side-bar': isOpen }">
      <h1>{{ this.planTitle }}</h1>
      <div class="search-area" :class="{ hide: !isOpen }">
        <b-button @click="copyPlan" style="width: 80%; margin-left: 10%">
          스크랩하기
        </b-button>
      </div>
      <div class="plan-area" :class="{ hide: !isOpen }">
        <div class="search-items"></div>
      </div>
      <div :class="{ hide: !isOpen }">
        <div class="plan-group">
          <!-- ListGroup -->
          <ul v-for="(tourList, idx) in plan" :key="idx" class="list-group">
            <li class="list-group-item active">{{ idx + 1 }} 일차</li>
            <li
              v-for="(tour, tourIdx) in tourList"
              :key="tourIdx"
              class="list-group-item"
              @click="moveMap(tour)">
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
  name: "ReadRightBar",
  components: {},
  data() {
    return {
      isOpen: true,
      title: "",
      plan: [],
      planTitle: "",
      markerPosition: [],
      marker: [],
      infoWindow: [],
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
    this.planUsername = this.$route.params.username;
    http
      .get("/attraction/myplanList/" + this.$route.params.plan_id)
      .then((res) => {
        this.planTitle = res.data.planTitle;
        this.plan = res.data.plan;
        let idx = 0;
        for (let i = 0; i < this.plan.length; i++) {
          // 마커를 표시할 위치입니다
          for (let j = 0; j < this.plan[i].length; j++) {
            var position = new window.kakao.maps.LatLng(
              this.plan[i][j].latitude,
              this.plan[i][j].longitude
            );
            this.markerPosition.push(position);
            // 마커를 생성합니다

            this.marker.push(
              new window.kakao.maps.Marker({
                position: this.markerPosition[idx],
              })
            );
            // 마커를 지도에 표시합니다.
            this.marker[idx].setMap(this.map);

            // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
            var iwContent =
              '<div style="padding:7px;">' + this.plan[i][j].title + "</div>"; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

            // 인포윈도우를 생성합니다
            var infowindow = new window.kakao.maps.InfoWindow({
              content: iwContent,
            });
            this.infoWindow.push(iwContent);
            // 마커에 마우스오버 이벤트를 등록합니다
            window.kakao.maps.event.addListener(
              this.marker[idx],
              "mouseover",
              () => {
                // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                infowindow.open(this.map, this.marker[idx]);
              }
            );

            // 마커에 마우스아웃 이벤트를 등록합니다
            window.kakao.maps.event.addListener(
              this.marker[idx++],
              "mouseout",
              () => {
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                infowindow.close();
              }
            );
          }
        }
      });
  },
  methods: {
    rightClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    copyPlan() {
      // 플랜 스크랩해오기
      http
        .post("/attraction/plan", {
          planTitle: this.$store.state.planTitle,
          user_id: this.userInfo.user_id,
          plan: this.$store.state.plan,
        })
        .then(() => {
          alert("스크랩에 성공했습니다. 마이페이지로 이동합니다.");
          this.$router.push("/plan/" + this.userInfo.username);
        });
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
    moveMap(tour) {
      const mapOption = new window.kakao.maps.LatLng(
        tour["latitude"],
        tour["longitude"]
      );

      this.map.setCenter(mapOption);
    },
    setMarker() {},
  },
  computed: {
    selectedDay() {
      return this.$store.state.selectedDay;
    },
    userInfo() {
      return this.$store.state.userInfo;
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
  text-align: center;
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
