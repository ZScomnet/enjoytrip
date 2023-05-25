<template>
  <div class="left-container">
    <div :class="{ 'side-bar': isOpen }">
      <h1 class="left-side-bar-title" :class="{ hide: !isOpen }">Profile</h1>
      <div class="search-area" :class="{ hide: !isOpen }">
        <img
          :src="pictureURI"
          class="img-field"
          style="width: 80%; margin-left: 10%; height: 300px" />
      </div>
      <div class="search-area" :class="{ hide: !isOpen }" style="margin: 10%">
        <h3>Planner : {{ profile.username }}</h3>
        <h3>Tel : {{ profile.phone_number }}</h3>
        <h3 @click="routeMyPage">>> Profile Page</h3>
      </div>
      <div :class="{ hide: !isOpen }">
        <div class="container mt-5">
          <!-- Card -->
          <div class="card_scroll"></div>
        </div>
      </div>

      <button class="side-bar-btn" @click="leftClick">
        {{ isOpen ? "<" : ">" }}
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {
  name: "ReadLeftBar",
  components: {},
  data() {
    return {
      isOpen: true,
      inputValue: "",
      profile: [],
      pictureURI:
        "https://pixlok.com/wp-content/uploads/2022/02/Profile-Icon-SVG-09856789.png",
    };
  },
  created() {
    http.get("/user/info/" + this.$route.params.username).then((res) => {
      this.profile = res.data;
      http.get("/file/getProfileImg/" + this.profile.username).then((res) => {
        if (res.data === null || res.data === "")
          this.pictureURI =
            "https://pixlok.com/wp-content/uploads/2022/02/Profile-Icon-SVG-09856789.png";
        else this.pictureURI = res.data;
      });
    });
  },
  methods: {
    leftClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    moveMap(result) {
      const mapOption = new window.kakao.maps.LatLng(
        result["latitude"],
        result["longitude"]
      );

      this.map.setCenter(mapOption);
    },
    insertPlan(plan) {
      // 관광지 추가
      this.$store.commit("ADD_TOUR", {
        day: this.$store.state.selectedDay,
        tourInfo: plan,
      });
    },
    routeMyPage() {
      this.$route.push("/plan/" + this.profile.username);
    },
  },
  props: ["map"],
  computed: {
    tourList() {
      return this.$store.state.tourList;
    },
    selectedDay() {
      return this.$store.state.selectedDay;
    },
  },
};
</script>

<style lang="scss" scoped>
img.profile-image {
  width: 100%;
}
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
