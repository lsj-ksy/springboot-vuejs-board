<template>
<!--  버튼위치 조정해야합니다!-->
  <header class="mb-3">
    <a href="#" class="burger-btn d-block d-xl-none">
      <i class="bi bi-justify fs-3"></i>
    </a>
  </header>
  <div id="sidebar" class="active">
    <div class="sidebar-wrapper active">
      <div class="sidebar-header">
        <div class="d-flex justify-content-between">
          <div class="logo">
            <a href="index.html"><img src="assets/images/logo/logo.png" alt="Logo" srcset=""></a>
          </div>
          <div class="toggler">
            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
          </div>
        </div>
      </div>
      <div class="card">
        <div class="card-body py-4 px-5">
          <div class="d-flex align-items-center">
            <div class="avatar avatar-xl">
              <img src="https://i.stack.imgur.com/34AD2.jpg" alt="profilepicture">
            </div>
            <div class="ms-3 name">
              <h5 class="font-bold">{{userInfo.nickname}}</h5>
              <h6 class="text-muted mb-0">{{userInfo.createdDate}}</h6>
            </div>
          </div>
        </div>
      </div>
      <a href="#" class="btn btn-lg btn-dark w-75">회원가입</a>
      <a href="#" class="btn btn-lg btn-dark w-75">글쓰기</a>
      <div class="sidebar-menu">
        <ul class="menu">
          <li class="sidebar-title">Menu</li>

          <li class="sidebar-item active ">
            <a href="index.html" class='sidebar-link'>
              <i class="bi bi-grid-fill"></i>
              <span>공지사항</span>
            </a>
          </li>

          <li class="sidebar-item  has-sub">
            <a href="#" class='sidebar-link'>
              <i class="bi bi-stack"></i>
              <span>카테고리1</span>
            </a>
            <ul class="submenu ">
              <li class="submenu-item ">
                <a href="component-alert.html">게시판1</a>
              </li>
              <li class="submenu-item ">
                <a href="component-badge.html">게시판2</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item  has-sub">
            <a href="#" class='sidebar-link'>
              <i class="bi bi-hexagon-fill"></i>
              <span>카테고리2</span>
            </a>
            <ul class="submenu ">
              <li class="submenu-item ">
                <a href="form-element-checkbox.html">게시판3</a>
              </li>
              <li class="submenu-item ">
                <a href="form-element-textarea.html">게시판4</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
    </div>
  </div>
</template>

<script>
import "../assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"

export default {
  name: "Sidebar", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      //test 하드코딩
      userid : 1, //testuser id
      userInfo : ''
    };
  },
  mounted() {
    //main.js import for sidebar
    import('../assets/js/main') //
    //api data
    var testUser01 = 1;
    this.getUserInfo(testUser01); //userInfo api data 호출
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  methods: {
    async getUserInfo(user) { //파라미터 혹은 data에서 userid 받아와야함. 현재는 테스트코드
      this.userInfo = await this.$api(`http://localhost:8080//api/v1/user/myinfo/${user}`, 'get')
    },
  } //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style>
  @import '../assets/vendors/iconly/bold.css';
  @import '../assets/vendors/perfect-scrollbar/perfect-scrollbar.css';
  @import '../assets/vendors/bootstrap-icons/bootstrap-icons.css';
  @import '../assets/css/app.css';
</style>

