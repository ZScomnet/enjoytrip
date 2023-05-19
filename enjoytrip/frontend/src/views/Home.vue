<template>
  <transition appear enter-active-class="animate__animated animate__fadeIn">
    <div id="main-container">
      <div class="animate__animated animate__fadeInRight animate__delay-0.3s">
        <div class="plan-item1">
          <img :src="require('@/assets/images/plan1.png')" />
        </div>
      </div>
      <div class="animate__animated animate__fadeInRight animate__delay-1s">
        <div class="plan-item2">
          <img :src="require('@/assets/images/plan2.png')" />
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  data() {
    return {
      show: false,
    };
  },
  created() {
    this.show = true;
  },
};
</script>

<style lang="scss" scoped>
#main-container {
  width: 100%;
  height: 100%;
  background-image: url("../assets/images/mainBackground.png");
  background-repeat: no-repeat;
  background-size: cover;
}
.plan-item1 {
  position: absolute;
  top: 100px;
  right: 15%;
}
.plan-item2 {
  position: absolute;
  top: 400px;
  right: 15%;
}
</style>
