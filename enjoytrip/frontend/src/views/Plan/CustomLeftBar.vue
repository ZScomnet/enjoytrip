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
            <div v-for="(result, idx) in resultData" :key="idx" class="card">
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
                  <h5 style="text-align: center; margin-top: 15px">
                    {{ result.title }}
                  </h5>
                  <div style="display: flex; justify-content: center">
                    <b-button @click="insertPlan(result)" style="width: 40%">
                      플랜 추가
                    </b-button>
                    <b-button
                      @click="openModal(idx)"
                      style="width: 40%; margin-left: 10px">
                      정보 보기
                    </b-button>
                  </div>
                  <div v-if="modalOpen[idx]" class="modal">
                    <div
                      class="animate__animated animate__fadeInDown"
                      id="modal-content">
                      <h1 class="close" @click="closeModal(idx)">&times;</h1>
                      <h3>{{ result.title }}</h3>
                      <p>
                        {{ result.title }}
                      </p>
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
      marker: null,
      modalOpen: [],
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
      if (this.marker !== null) this.marker.setMap(null);
      const mapOption = new window.kakao.maps.LatLng(
        result["latitude"],
        result["longitude"]
      );
      var imageSrc =
          "https://cdn.icon-icons.com/icons2/567/PNG/512/marker_icon-icons.com_54388.png", // 마커이미지의 주소입니다
        imageSize = new window.kakao.maps.Size(34, 40), // 마커이미지의 크기입니다
        imageOption = { offset: new window.kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new window.kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      this.map.setCenter(mapOption);
      this.marker = new window.kakao.maps.Marker({
        position: mapOption,
        image: markerImage,
      });
      this.marker.setMap(this.map); // 마커를 지도에 표시
    },
    searchTour() {
      this.modalOpen = [];
      this.resultData = this.$store.attractions.filter((el) => {
        return el.addr1.includes(this.inputValue);
      });
      for (let i = 0; i < this.resultData.length; i++)
        this.modalOpen.push(false);
    },
    insertPlan(plan) {
      // 관광지 추가
      this.$store.commit("ADD_TOUR", {
        day: this.$store.state.selectedDay,
        tourInfo: plan,
      });
    },
    openModal(idx) {
      this.modalOpen.splice(idx, 1, true);
    },
    closeModal(idx) {
      this.modalOpen.splice(idx, 1, false);
    },
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
  border-radius: 10px;
}
.card_scroll {
  overflow-y: scroll;
  max-height: 75vh;
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
