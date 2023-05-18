<template>
  <div id="main-container">
    <transition appear enter-active-class="animate__animated animate__fadeIn">
      <img
        class="main-background"
        v-if="show"
        :src="require('@/assets/images/mainBackground.png')" />
    </transition>
  </div>
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

<style></style>
