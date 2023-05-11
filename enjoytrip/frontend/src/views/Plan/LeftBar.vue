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
    };
  },
  methods: {
    leftClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    searchTour() {
      console.log("SEARCH");
    },
  },
};
</script>

<style lang="scss" scoped>
.left-container {
  position: absolute;
  top: 0;
  bottom: 0;
  padding-top: 56px;
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
</style>
