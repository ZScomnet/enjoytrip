<template>
  <div class="total-container">
    <section class="notice">
      <div class="page-title">
        <div class="container">
          <h1>글 작성</h1>
        </div>
      </div>

      <!-- board seach area -->
      <div id="board-search">
        <div class="container">
          <form method="post">
            <div class="form-group">
              <label class="board_info" for="board_title">제목</label>
              <input
                type="text"
                class="form-control"
                id="board_title"
                v-model="title"
                placeholder="제목을 작성해주세요." />
            </div>
            <div class="form-group">
              <label class="board_info" for="board-type">게시판 종류</label
              ><br />
              <select v-model="boardType" class="info-box">
                <option
                  v-for="type in types"
                  :key="type.group_id"
                  :value="type.group_id">
                  {{ type.group_name }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label class="board_info" for="board-text">내용</label>
              <textarea
                class="form-control"
                id="board-text"
                v-model="text"
                rows="10"></textarea>
            </div>

            <button type="button" class="btn btn-secondary">목록으로</button>
            <button type="button" @click="writeReq" class="btn btn-info">
              등록하기
            </button>
          </form>
        </div>
        <!-- board button area -->
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {
  data() {
    return {
      title: "",
      boardType: 0,
      text: "",
      types: [],
      boardItems: [],
    };
  },
  created() {
    http.get("/board/getBoardGroup").then((res) => {
      this.types = res.data;
      for (let i = 0; i < this.types.length; i++)
        this.boardItems.push(this.types[i].group_name);
    });
  },
  methods: {
    writeReq() {
      console.log(this.boardType);
      console.log(this.types[this.boardType - 1].write_permission);

      if (
        this.userInfo.username !== "admin" &&
        !this.types[this.boardType - 1].write_permission
      ) {
        alert("Write permission error");
      } else {
        http
          .post("/board/insertBoard", {
            title: this.title,
            author: this.userInfo.username,
            text: this.text,
            group_id: this.boardType,
          })
          .then(() => {
            this.$router.push("/board");
          })
          .catch(() => {
            alert("Error!");
          });
      }
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
#board_title {
  padding: 10px;
}
.board_info {
  font-size: 20px;
  padding: 10px;
}
.info-box {
  width: 100%;
  height: 45px;
  border-radius: 4px;
  border: 1px solid #333333;
  padding: 10px;
}
#board-text {
  padding: 10px;
}
button {
  float: right;
  padding: 5px;
  padding-left: 10px;
  padding-right: 10px;
  margin: 10px;
}
#board_select,
.dropdown-item {
  width: 100%;
  padding: 10px;
  margin-left: 10px;
}
.total-container {
  justify-content: space-between;
}
.board-list {
  display: flex;
  > .container-menu {
    margin: 0 15px;
  }
  > .container-board {
    width: 100%;
  }
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
section.notice {
  padding: 80px 0;
}

.page-title {
  margin-bottom: 60px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}

#board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}
#board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}
#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}
#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}
#board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}
.board-table a:hover {
  text-decoration: underline;
}
.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 100px;
  text-align: center;
}

.board-table .th-date {
  width: 200px;
}

.board-table th,
.board-table td {
  padding: 14px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.board-table tbody th p {
  display: none;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* reset */

* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.clearfix:after {
  content: "";
  display: block;
  clear: both;
}
.container {
  margin: 0 auto;
}
.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}
.board_write_btn {
  display: flex;
  margin: 3px;
}
.board-lineup th,
td {
  padding: 3px;
}
</style>
