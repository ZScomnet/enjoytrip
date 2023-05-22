<template>
  <div
    class="left-container"
    :style="{ left: isOpen ? -400 + pos + 'px' : -400 + 'px' }">
    <div
      class="side-bar"
      :style="{ left: isOpen ? -400 + pos + 'px' : -400 + 'px' }">
      <h1
        class="left-side-bar-title"
        :style="{ left: isOpen ? -400 + pos + 'px' : -400 + 'px' }">
        관광지 검색
      </h1>
      <div
        class="search-area"
        :style="{ left: isOpen ? -400 + pos + 'px' : -400 + 'px' }">
        <BInput
          v-model="inputValue"
          placeholder="관광지를 입력해주세요"
          @keyup.enter="searchTour()" />
      </div>
      <div>
        <div class="container mt-5">
          <!-- Card -->
          <div class="card_scroll">
            <div v-for="result in resultData" :key="result.id" class="card">
              <div class="row">
                <div class="col-md-4" @click="moveMap(result)">
                  <img
                    v-if="result.firstImage !== ''"
                    :src="result.firstImage"
                    class="img-field" />
                  <img
                    v-else
                    :src="require('@/assets/images/empty.jpg')"
                    class="img-field" />
                </div>
                <div class="col-md-8">
                  <h5 style="text-align: center">{{ result.title }}</h5>
                  <b-button @click="insertPlan(result)"> 플랜 추가 </b-button>
                  <b-button @click="introModal"> 정보 보기 </b-button>
                  <div v-if="modalOpen" class="modal">
                    <div
                      class="animate__animated animate__fadeInDown"
                      id="modal-content">
                      <h1 class="close" @click="closeModal">&times;</h1>
                      TEXT
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <button class="side-bar-btn" @click="leftClick">
        {{ isOpen ? "<" : ">" }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CustomLeftBar",
  components: {},
  data() {
    return {
      isOpen: true,
      inputValue: "",
      resultData: [],
      markerPosition: [],
      marker: [],
      infoWindow: [],
      modalOpen: false,
      pos: 400,
    };
  },
  created() {},
  methods: {
    leftClick() {
      this.isOpen = this.isOpen ? false : true;
      this.pos = this.isOpen ? 400 : 0;
    },
    moveMap(result) {
      const mapOption = new window.kakao.maps.LatLng(
        result["latitude"],
        result["longitude"]
      );

      this.map.setCenter(mapOption);
    },
    searchTour() {
      this.resultData = this.$store.attractions.filter((el) => {
        return el.addr1.includes(this.inputValue);
      });
      for (let i = 0; i < this.marker.length; i++) this.marker[i].setMap(null);
      if (this.resultData.length == 0) {
        this.markerPosition = null;
        this.marker = null;
        this.infoWindow = null;
        return;
      } else {
        this.markerPosition = [];
        this.marker = [];
        this.infoWindow = [];
      }
      console.log(this.resultData);
      const mapOption = new window.kakao.maps.LatLng(
        this.resultData[0]["latitude"],
        this.resultData[0]["longitude"]
      );

      this.map.setCenter(mapOption);

      for (let i = 0; i < this.resultData.length; i++) {
        // 검색 결과들 마커를 생성
        this.markerPosition.push(
          new window.kakao.maps.LatLng(
            this.resultData[i]["latitude"],
            this.resultData[i]["longitude"]
          )
        );
        this.marker.push(
          new window.kakao.maps.Marker({
            position: this.markerPosition[i],
          })
        );
        this.marker[i].setMap(this.map); // 마커를 지도에 표시
        var iwContent =
          '<div style="padding: 7px;">' + "\t" + this.plan[i].title + "</div>";

        this.infoWindow.push(iwContent);
        var infowindow = new window.kakao.maps.InfoWindow({
          content: this.infoWindow[i],
        });
        // 마커에 마우스오버 이벤트를 등록합니다
        window.kakao.maps.event.addListener(this.marker[i], "mouseover", () => {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          infowindow.open(this.map, this.marker[i]);
        });
        // 마커에 마우스아웃 이벤트를 등록합니다
        window.kakao.maps.event.addListener(this.marker[i], "mouseout", () => {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
          infowindow.close();
        });
      }
    },
    insertPlan(plan) {
      // 관광지 추가
      this.$store.commit("ADD_TOUR", {
        day: this.$store.state.selectedDay,
        tourInfo: plan,
      });
    },
    introModal() {},
  },
  props: ["map"],
  computed: {
    selectedDay() {
      return this.$store.state.selectedDay;
    },
  },
};
</script>

<style lang="scss" scoped>
.left-container {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  animation-name: move;
  animation-duration: 1s;
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
  right: 0;
  top: 50%;
  transform: translate(70%, -50%);
}
.left-side-bar-title {
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
.card_scroll::-webkit-scrollbar {
  width: 10px;
  height: 0;
  -webkit-transform-origin-x: 10px;
}
.card_scroll::-webkit-scrollbar-thumb {
  background: #000000;
  border-radius: 10px;
}
.card_scroll::-webkit-scrollbar-track {
  background: #00000057;
  border-radius: 10px;
}
.card_scroll {
  overflow-y: scroll;
  max-height: 75vh;
}
</style>
