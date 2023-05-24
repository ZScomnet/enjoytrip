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
          <button
            v-if="userInfo.username == this.$route.params.username"
            class="btn btn-info"
            @click="routePlanMaker">
            플랜 생성
          </button>
          <button
            v-if="userInfo.username == this.$route.params.username"
            class="btn btn-info"
            @click="openModal">
            프로필 수정
          </button>
          <div v-if="modalOpen" class="modal">
            <div
              class="animate__animated animate__fadeInDown"
              id="modal-content">
              <h1 class="close" @click="closeModal">&times;</h1>
              <h3>프로필 사진 등록</h3>
              <img
                :src="pictureURI"
                :style="{
                  maxWidth: '500px',
                  maxHeight: '500px',
                  margin: 'auto',
                }" /><br />
              <input
                type="file"
                class="input-field"
                @change="previewImage"
                required />

              <button class="btn btn-info" @click="updateUserInfo">Send</button>
            </div>
          </div>
        </div>
      </div>
    </header>
    <div class="container px-4 px-lg-5 mt-5">
      <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4">
        <div v-for="(plan, idx) in myPlan" :key="idx" class="col mb-5">
          <div class="card h-100">
            <!-- Product image-->
            <img v-if="plan[2] !== ''" class="card-img-top" :src="plan[2]" />
            <img
              v-else
              class="card-img-top"
              :src="require('@/assets/images/empty.jpg')" />
            <!-- Product details-->
            <div class="card-body p-4">
              <div class="text-center">
                <!-- Product name-->
                <h5 cl ass="fw-bolder">{{ plan[1] }}</h5>
                <!-- Product price-->
                {{ getLikePoint(plan[0]) }}
                <span v-if="myGoods[idx]">Like : {{ myGoods[idx] }}</span>
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
                <h3 @click="like(plan[0])">♥</h3>
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
      myGoods: [],
      modalOpen: false,
      pictureURI:
        "https://pixlok.com/wp-content/uploads/2022/02/Profile-Icon-SVG-09856789.png",
      picture: null,
    };
  },
  created() {
    http
      .get("/attraction/myplanLists/" + this.$route.params.username)
      .then((res) => {
        this.myPlan = res.data;
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
    updateUserInfo() {
      // 프로필 사진 등록
      const uploadFile = new FormData();
      uploadFile.append("image", this.picture);
      console.log(this.picture);
      return http
        .post("/file/uploadFile/" + this.userInfo.user_id, uploadFile, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getLikePoint(plan_id) {
      // 좋아요 수 요청
      http.get("/attraction/plan/" + plan_id + "/likeCnt").then((res) => {
        this.myGoods.push(res.data);
      });
    },
    previewImage(event) {
      const input = event.target;
      const file = input.files[0];
      const reader = new FileReader();
      this.picture = event.target.files[0];
      reader.onload = () => {
        const img = new Image();
        img.src = reader.result;

        img.onload = () => {
          const canvas = document.createElement("canvas");
          const ctx = canvas.getContext("2d");
          ctx.clearRect(0, 0, canvas.width, canvas.height);

          let width = img.width;
          let height = img.height;

          if (width > height) {
            if (width > 500) {
              height *= 500 / width;
              width = 500;
            }
          } else {
            if (height > 500) {
              width *= 500 / height;
              height = 500;
            }
          }

          canvas.width = width;
          canvas.height = height;
          ctx.drawImage(img, 0, 0, width, height);
          this.pictureURI = canvas.toDataURL("image/jpeg");
        };
      };
      reader.readAsDataURL(file);
    },
    openModal() {
      this.modalOpen = true;
    },
    closeModal() {
      this.modalOpen = false;
    },
    like(plan_id) {
      // 좋아요 여부를 판단해야함. 이 때 필요한 것은 좋아요를 했는지 안했는 지 여부
      // like를 합쳐야함 로직은
      // 1. like를 했는지 http:요청 (plan_id, user_id 2개 보냄)select로 판단
      // 2. select로 결과 존재 시 해당 like 삭제, 결과 없을 시 like 추가
      // 3. alert로 변화
      http
        .get(
          "/attraction/plan/" + plan_id + "/checkLike/" + this.userInfo.user_id
        )
        .then((res) => {
          if (res.data == 0) {
            http
              .put(
                "/attraction/plan/" + plan_id + "/like/" + this.userInfo.user_id
              )
              .then(() => {
                alert("추천하셨습니다.");
                this.$router.go(0);
              })
              .catch(() => {
                alert("ERROR!");
              });
          } else {
            http
              .delete(
                "/attraction/plan/" +
                  plan_id +
                  "/deleteLike/" +
                  this.userInfo.user_id
              )
              .then(() => {
                alert("추천을 취소하셨습니다.");
                this.$router.go(0);
              })
              .catch(() => {
                alert("ERROR!");
              });
          }
        });
    },
  },
};
</script>

<style lang="scss" scoped>
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
button {
  margin: 10px;
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
  margin-top: 20%;
  margin-left: 35%;
  padding: 20px;
  border: 1px solid #888;
  width: 30%;
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
