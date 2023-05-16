<template>
  <div class="left-container">
    <div :class="{ 'side-bar': isOpen }">
      <h1 class="left-side-bar-title" :class="{ hide: !isOpen }">
        관광지 검색
      </h1>
      <div class="search-area" :class="{ hide: !isOpen }">
        <BInput
          v-model="inputValue"
          placeholder="관광지를 입력해주세요"
          @keyup.enter="searchTour()" />
      </div>
      <div :class="{ hide: !isOpen }">
        <div class="container mt-5">
          <!-- Card -->
          <div v-for="result in resultData" :key="result.id" class="card">
            <div class="row">
              <div class="col-md-4">
                <img :src="result.firstImage" class="img-field" />
              </div>
              <div class="col-md-8">
                <h4>{{ result.title }}</h4>
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
  name: "LeftBar",
  components: {},
  data() {
    return {
      isOpen: true,
      inputValue: "",
      resultData: [],
      markerPosition: [],
      marker: [],
    };
  },
  created() {
    console.log(this.map);
    console.log("leftbar", window);
  },
  methods: {
    leftClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    searchTour() {
      this.resultData = this.$store.attractions.filter((el) => {
        return el.addr1.includes(this.inputValue);
      });
      const mapOption = new window.kakao.maps.LatLng(
        this.resultData[0]["latitude"],
        this.resultData[0]["longitude"]
      );

      this.map.setCenter(mapOption);
      for (let i = 0; i < this.marker.length; i++) this.marker[i].setMap(null);
      this.markerPosition = [];
      for (let i = 0; i < this.resultData.length; i++) {
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
        this.marker[i].setMap(this.map);
      }
      console.log("this.marker", this.marker);
    },
  },
  props: ["map"],
  // mounted() {
  //   // window에 kakao 기능 추가
  //   if (!window.kakao || !window.kakao.maps) {
  //     const script = document.createElement("script");
  //     script.type = "text/javascript";
  //     script.src =
  //       "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=072af5d564b2fe0e2778a38436435f49&libraries=clusterer,drawing,services&autoload=false";
  //     /* global kakao */
  //     script.addEventListener("load", () => {
  //       kakao.maps.load(() => {
  //         // 카카오맵 API가 로딩이 완료된 후 지도의 기본적인 세팅을 시작해야 한다.
  //       });
  //     });
  //     document.head.appendChild(script);
  //   }
  // },
};
</script>

<style lang="scss" scoped>
.left-container {
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
</style>
