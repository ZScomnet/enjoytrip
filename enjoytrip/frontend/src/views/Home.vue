<template>
  <transition appear enter-active-class="animate__animated animate__fadeIn">
    <div id="main-container">
      <div class="animate__animated animate__fadeInRight animate__delay-0.2s">
        <img :src="require('@/assets/images/blank.png')" />
        <h3 class="animate__animated animate__fadeInUp animate__delay-1s">
          Our Plan, And you
        </h3>
        <img
          class="animate__animated animate__fadeInUp animate__delay-2s"
          :src="require('@/assets/images/mainlogoBig.png')"
          style="
            width: 400px;
            height: 130px;
            position: absolute;
            left: 60%;
            top: 20%;
          " />
        <p class="animate__animated animate__fadeInUp animate__delay-3s">
          계획짜기 귀찮고 2% 부족한 여행 계획<br />
          Shared.trip이 도와드리겠습니다.
        </p>
        <p
          class="animate__animated animate__fadeInUp animate__delay-4s"
          style="top: 50%">
          멋있는 여행 계획을 스크랩하여 나만의 여행 계획을 세워도 Ok!<br />
          나만 알고 있는 인생 여행루트를 공유하여 랭킹에 도전하는 것도 Ok!<br />
          간편하고 재밌는 여행계획 이곳에서 찾아가세요!
        </p>
        <transition v-if="!userInfo.username" name="fadein-bounce">
          <h1 v-if="showElement" class="animated-element" @click="routeSignUp">
            >> 가입하기
          </h1>
        </transition>
        <transition v-else name="fadein-bounce">
          <h1 v-if="showElement" class="animated-element" @click="routePlan">
            >> 계획하기
          </h1>
        </transition>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  data() {
    return {
      showElement: false,
    };
  },
  created() {
    setTimeout(() => {
      this.showElement = true;
    }, 5000);
  },
  methods: {
    routeSignUp() {
      this.$router.push("/auth");
    },
    routePlan() {
      this.$router.push("/plan/" + this.userInfo.username);
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
#main-container {
  display: block;
  width: 100%;
  height: 100%;
  background-image: url("../assets/images/mainmap.png");
  background-repeat: no-repeat;
  background-size: cover;
  > .animate__animated {
    height: 100%;
    > img {
      position: absolute;
      width: 40%;
      height: 100%;
      right: 0;
    }
    > h3 {
      position: absolute;
      left: calc(60% + 20px);
      top: 12%;
      font-size: 40px;
      font-family: "Cafe24Ssurround";
      color: #119ed1;
    }
    > p {
      position: absolute;
      left: calc(60% + 20px);
      top: 35%;
      font-size: 20px;
      font-weight: 600;
      color: #222222;
    }
    > h1 {
      position: absolute;
      left: 70%;
      bottom: 10%;
      font-family: "Cafe24Ssurround";
      font-size: 70px;
      color: #119ed1;
      cursor: pointer;
    }
  }
}
.fadein-bounce-enter-active {
  animation: fadein 1s;
  > .animated-element {
    animation: bounce infinite 1s;
  }
}
@keyframes fadein {
  0% {
    opacity: 0;
    transform: translateX(-100px);
  }
  100% {
    opacity: 1;
    transform: translateX(0);
  }
}
@keyframes bounce {
  0%,
  100% {
    transform: translateX(0);
  }
  50% {
    transform: translateX(-20px);
  }
}

@keyframes fadeout {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
</style>
