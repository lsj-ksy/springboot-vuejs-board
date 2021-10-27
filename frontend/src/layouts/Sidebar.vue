<template>
  <!--  버튼위치 조정해야합니다!-->
  <div>
  <header class="mb-3">
    <a href="#" class="burger-btn d-block d-xl-none">
      <i class="bi bi-justify fs-3"></i>
    </a>
  </header>
  <div id="sidebar" class="active mx-3">
    <div class="sidebar-wrapper active">
      <div class="sidebar-header">
        <div class="d-flex justify-content-between">
          <div class="logo">
            <a :href="`${mainUrl}`"><img src="../assets/images/logo/templogo.png" alt="Logo" srcset=""></a>
          </div>
          <div class="toggler">
            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
          </div>
        </div>
      </div>
      <div class="card" id="myinfo-area">
        <div class="card-body p-4">
          <div class="d-flex align-items-center">
            <div class="avatar avatar-xl">
              <img src="https://i.stack.imgur.com/34AD2.jpg" alt="profilepicture">
            </div>
            <div class="ms-3 name">
              <h5 class="font-bold">{{userInfo.nickname}}</h5>
              <h6 class="text-muted mb-0">가입 날짜</h6>
              <h6 class="text-muted mb-0" >{{ moment(userInfo.createdDate).format('YYYY-MM-DD') }}</h6>
            </div>
          </div>
        </div>
      </div>
      <router-link id="btn-write" class="btn btn-lg btn-dark w-75" :to="`/post_write/0/0`"> 글쓰기 </router-link>

      <div class="sidebar-menu">
        <ul class="menu">

          <li class="sidebar-title">
            <router-link :to="`/list/0/0`"> 전체 게시판 </router-link>

          </li>

          <!-- Categories -->
          <li class="sidebar-item  has-sub" :key="i" v-for="(category,i) in sidebarBoards" >
            <a href="#" class='sidebar-link'>
              <i class="bi bi-hexagon-fill"></i>
              <span>{{category.categoryName}}</span>
            </a>
            <!-- Boards -->
            <ul class="submenu ">
              <li class="submenu-item " :key="i" v-for="(board,i) in category.boards"  >
                <router-link :to="`/list/${category.categoryId}/${board.id}`">{{board.boardName}}</router-link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
    </div>
  </div>
  </div>
</template>

<script>
import "../assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"
import moment from 'moment' //Date formatting

export default {
  name: "Sidebar", //컴포넌트 이름
  emits: ["categories"],
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      moment: moment,
      //test 하드코딩
      userid : 1, //testuser id
      userInfo : '',
      sidebarBoards : '',
      mainUrl: process.env.BASE_URL
    };
  },
  updated() { //v-for 를 이용한 DOM 렌더링 이후에 main.js 적용
    this.$nextTick(function () {
      // 전체 화면내용이 다시 렌더링된 후에 아래의 코드가 실행됩니다.
      import('../assets/js/main')
    });
  },
  mounted() {
    var testUser01 = 1;
    this.getUserInfo(testUser01); //userInfo api data 호출
    this.getSidebarBoards();
    //main.js import for sidebar
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  methods: {
    async getUserInfo(user) { //파라미터 혹은 data에서 userid 받아와야함. 현재는 테스트코드
      this.userInfo = await this.$api(`${process.env.BASE_URL}api/v1/user/myinfo/${user}`, 'get')
    },
    async getSidebarBoards() {
      this.sidebarBoards = await this.$api(`${process.env.BASE_URL}api/v1/sidebar/board`, 'get')
    },
    sendCategories() {
      this.$emit('categories', this.sidebarBoards);
    }
  },
  watch : {
    sidebarBoards() {
      this.sendCategories(); //category 목록 부모컴포넌트로 보내기
    }
  }
}
</script>

<style>
@import '../assets/vendors/iconly/bold.css';
@import '../assets/vendors/perfect-scrollbar/perfect-scrollbar.css';
@import '../assets/vendors/bootstrap-icons/bootstrap-icons.css';
@import '../assets/css/app.css';

.sidebar-wrapper {
  box-shadow: 0px 10px 10px lightgrey;
}
#myinfo-area {
  margin: 3%;
  margin-block-start: 1.5rem;
  margin-block-end: 2.5rem;
}

#btn-write {
  margin-block-start: 1rem;
  margin-block-end: 1.5rem;
}

</style>

