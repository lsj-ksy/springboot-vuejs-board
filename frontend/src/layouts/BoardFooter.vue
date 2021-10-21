<template>
  <footer>
    <div class="footer clearfix px-2 py-3 text-muted bg-dark">
      <div class="float-start">
        <p> </p>
      </div>
      <div class="float-end">
        <p class="text-white">Crafted by <a href="https://github.com/lsj-ksy/springboot-vuejs-board">LSJ & KSY</a></p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      sampleData: ""
    };
  },
  setup() {
  }, //컴포지션 API
  created() {
  }, //컴포넌트가 생성되면 실행
  mounted() {
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  unmounted() {
  }, //unmount가 완료된 후 실행
  methods: {} //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style scoped>
@import '../assets/vendors/iconly/bold.css';
@import '../assets/vendors/perfect-scrollbar/perfect-scrollbar.css';
@import '../assets/vendors/bootstrap-icons/bootstrap-icons.css';
@import '../assets/css/app.css';
</style>

