<template>
  <!-- Section-->
  <section class="py-5">
    <header class="py-5">
      <div class="profile-container">
        <img
          :src="require('@/assets/images/profile.png')"
          class="profile-image" />
        <div class="profile-detail">
          <h1 class="display-4 fw-bolder">{{ userInfo.username }}</h1>
          <h5>Plan : {{ myPlan.length }}</h5>
          <p class="lead fw-normal text-black-50 mb-0">안녕하세요</p>
          <button
            v-if="userInfo.username == this.$route.username"
            class="btn btn-info"
            @click="routePlanMaker">
            플랜 생성하기
          </button>
          <button
            v-else-if="!this.$route.username"
            class="btn btn-info"
            @click="routePlanMaker">
            플랜 생성하기
          </button>
        </div>
      </div>
    </header>
    <div class="container px-4 px-lg-5 mt-5">
      <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4">
        <div v-for="(plan, idx) in myPlan" :key="idx" class="col mb-5">
          <div class="card h-100">
            <!-- Product image-->
            <img
              class="card-img-top"
              :src="require('@/assets/images/empty.jpg')"
              alt="..." />
            <!-- Product details-->
            <div class="card-body p-4">
              <div class="text-center">
                <!-- Product name-->
                <h5 cl ass="fw-bolder">{{ plan[1] }}</h5>
                <!-- Product price-->
                Like : {{ idx }}
              </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center">
                <button
                  class="btn btn-outline-dark mt-auto"
                  href="#"
                  @click="viewPlan(plan[0])">
                  View Plan
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import http from "@/util/http.js";
export default {
  data() {
    return {
      myPlan: [],
    };
  },
  created() {
    http
      .get("/attraction/myplanLists/" + this.$route.params.username)
      .then((res) => {
        this.myPlan = res.data;
        console.log(this.myPlan);
      });
  },
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
  methods: {
    viewPlan(planIdx) {
      this.$router.push("/plan/" + this.userInfo.username + "/" + planIdx);
    },
    routePlanMaker() {
      this.$router.push("/plan/edit");
    },
  },
};
</script>

<style lang="scss">
.profile-container {
  display: flex;
  justify-content: space-around;
  padding-left: 15%;
  padding-right: 15%;
  align-items: center;
  > .profile-image {
    width: 300px;
    height: 300px;
  }
  > .profile-detail {
    left: 0;
  }
}
</style>
