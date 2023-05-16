<template>
  <div class="right-container">
    <div :class="{ 'side-bar': isOpen }">
      <h1 class="right-side-bar-title" :class="{ hide: !isOpen }">플랜</h1>
      <div class="search-area" :class="{ hide: !isOpen }">
        <b-button> 등록 </b-button>
        <b-button> 삭제 </b-button>
      </div>
      <div class="search-items">
        <div
          v-for="data in resultData"
          :key="data.eee"
          :class="{ hide: !isOpen }">
          {{ data.eee }}
        </div>
      </div>
      <div :class="{ hide: !isOpen }">
        <div class="container mt-5">
          <!-- Card -->
          <div class="card">
            <div class="row">
              <div class="col-md-4">
                <img :src="require('@/assets/logo.png')" class="img-field" />
              </div>
              <div class="col-md-8">
                <h1>content</h1>
              </div>
            </div>
          </div>
        </div>
      </div>

      <button class="side-bar-btn" @click="rightClick">
        {{ isOpen ? ">" : "<" }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "RightBar",
  components: {},
  data() {
    return {
      isOpen: true,
      inputValue: "",
      resultData: [],
    };
  },
  methods: {
    rightClick() {
      this.isOpen = this.isOpen ? false : true;
    },
    searchTour() {
      console.log("SEARCH");
      this.resultData = this.attractions.filter((el) => {
        return el.addr1.includes(this.inputValue);
      });
    },
  },
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
}
</style>
