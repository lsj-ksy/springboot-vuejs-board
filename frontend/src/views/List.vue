<template>
  <div id="main">
    <header class="mb-3">
      <a href="#" class="burger-btn d-block d-xl-none">
        <i class="bi bi-justify fs-3"></i>
      </a>
    </header>
    <div class="page-heading">
      <section class="section">
        <div class="row" id="table-head">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title"> {{ postList.categoryName }}
                  <span style="font-size: 75%;"> > {{ postList.boardName }}</span></h4>
              </div>
              <div class="card-content">
                <!-- table head dark -->
                <div class="table-responsive">
                  <table class="table mb-0">
                    <thead class="thead-dark">
                    <tr>
                      <th>No.</th>
                      <th>제목</th>
                      <th>닉네임</th>
                      <th>작성일</th>
                      <th>좋아요</th>
                      <th>조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 테이블내용 v-for -->
                    <tr :key="i" v-for="(post,i) in postList.postList">
                      <td>{{ post.id }}</td>
                      <td>
                        <router-link :to="`/post_detail/${post.id}`">{{ post.subject }}</router-link>
                      </td>
                      <td>{{ post.nickname }}</td>
                      <td>{{ moment(post.createdDate).format('YYYY-MM-DD HH:mm') }}
                        ({{ this.korDay(moment(post.createdDate).day()) }})
                      </td>
                      <td>{{ post.likeCount }}</td>
                      <td>{{ post.readCount }}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <div class="right-btn">
        <router-link class="btn btn-outline-primary mt-1 mb-10" :to="`/post_write/${categoryId}/${boardId}`">글쓰기</router-link>
      </div>
    </div>
    <div class="align-center">
      <pagination v-model="page" :records="this.totalPostsCount" :per-page="this.perPage" @paginate="myCallback"/>
    </div>
    <Bfooter/>
  </div>
</template>

<script>

import Bfooter from '@/layouts/BoardFooter';
import moment from 'moment' //Date formatting
import Pagination from 'v-pagination-3'

export default {
  name: 'List',
  components: {
    Bfooter,
    Pagination
  }, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      moment: moment, //날짜 포맷 moment.js
      postList: '',   //게시글 리스트
      page: 1,        //보고있는 페이지 default 1
      perPage: 10,    //페이지당 게시글 갯수
      totalPostsCount: ''  //총 게시글 수
    };
  },
  methods: {
    myCallback() {
      //Page 클릭시 호출할 수 있는 callback 함수. page를 watch 해서 사용하고있기 때문에 지금은 필요없음
    },
    korDay(numberOfDay) {
      var day = '';
      switch (numberOfDay) {
        case 0:
          day = '일';
          break;
        case 1:
          day = '월';
          break;
        case 2:
          day = '화';
          break;
        case 3:
          day = '수';
          break;
        case 4:
          day = '목';
          break;
        case 5:
          day = '금';
          break;
        case 6:
          day = '토';
          break;
      }
      return day;
    },
    async getList(boardId) {
      this.postList = await this.$api(`http://localhost:8080/api/v1/post/list?board_id=${boardId}&page=${this.page}&per_page=${this.perPage}`, "get");
      this.totalPostsCount = await this.$api(`http://localhost:8080/api/v1/post/totalCount?board_id=${boardId}`, "get")
    }
  },
  computed: {
    boardId() {
      if (this.$route.params.boardId === undefined) {
        return 0;
      } else {
        return this.$route.params.boardId;
      }
    },
    categoryId() {
      if (this.$route.params.categoryId === undefined) {
        return 0;
      } else {
        return this.$route.params.categoryId;
      }
    }
  },
  watch: {
    boardId() { //다른 게시판으로 이동시 boardId가 변경된걸 감지해서 다시 리스트를 불러옴
      this.getList(this.boardId)
      this.page = 1; //page 초기화
    },
    async page() {
      await this.getList(this.boardId)
    }
  },
  mounted() {
    this.getList(this.boardId);
  }
}
</script>
