<template>
  <div class="total-container">
    <section class="notice">
      <div class="page-title">
        <div class="container">
          <h1 align="center" v-if="!this.$route.params.idx"> 게시판 </h1>
          <h1 align="center" v-else > {{ boardGroup[board.group_id-1].group_name }} </h1>
        </div>
      </div>

      <!-- board seach area -->
      <div id="board-search">
        <div class="container">
          <div class="search-window">
            <form action="">
              <div class="search-wrap">
                
              </div>
            </form>
          </div>
        </div>
        <div class="container">
          <div class="board-list">
            <div class="container-menu">
              <table class="board-lineup">
                <tr>
                  <th class="board-types" scope="col" style="width: 100px">
                    
                    <a :href="'/board/'" > 게시판 </a></th>
                </tr>
                <hr style="color: white" />

                <tr v-for="group in boardGroup" :key=group.group_id>
                  <td v-if="group.read_permission == 1" class="board-types"> 
                    <a :href="'/board/'+group.group_id" >{{ group.group_name }} </a> 
                  </td>
                </tr>
              </table>
            </div>
            <div class="container-board">
              <table class="board-table">
                
                  <tr>
                    <td scope="col"><h1>{{ board.title }}</h1></td>
                  </tr>
                  <tr>
                    <td> <h4>작성자 : {{ board.author }}</h4> </td>
                    <td> <h4>작성 날짜 : {{ board.created.substr(0,10) }}</h4> </td>
                  </tr>
                  <tr>
                    <td colspan="2"><h4 style="margin-bottom: 20px;">글 내용</h4>
                    <p>
                     {{ board.text }}
                    </p>
                     </td>
                  </tr>
              </table>
            </div>
          </div>
        </div>
        <!-- board button area -->
      <div class="container">
        <button @click="rollbackPage" type="button" class="btn btn-dark" style="border-radius: 4px; float: right; margin : 10px; padding : 7px 7px;">목록으로</button>
      </div>
      </div>

      
    </section>
  </div>
</template>

<script>
import http from "@/util/http.js";
export default {

  data(){
    return {
      boardGroup: [],
      board: [],
    };
  },
  created(){
    http.get("/board/getBoardGroup")
    .then((res)=>{
      this.boardGroup = res.data;
      console.log(this.boardGroup);
    });
    http.get("/board/getBoardDetail/"+this.$route.params.idx)
    .then((res)=>{
      this.board = res.data;
    })
  },
  methods:{
    rollbackPage(){
      this.$router.push("/board/"+this.boardGroup[this.board.group_id-1].group_id);
    }
  }

};
</script>

<style lang="scss" scoped>
.total-container {
  justify-content: space-between;
}
p{
  font-size: 20px;
}
.board-types{
  > a:link{
    color: #333333;
  }
  > a:hover{
    color: #56caff;
  }
  > a:visited{
    color: #333333;
  }
  > a:visited:hover{
    color: #56caff;
  }
  > a:active{
    color: #5667ff;
  }
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
  background-color: #FFFFFF;
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

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
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
.board_write_btn{
  display: flex;
  margin: 3px;
  
}
.board-lineup th, td{
  padding: 3px;
}
.board-lineup td{
  padding-left: 10px;
}
</style>
