<template>
  <h3>로그인 테스트</h3>
  <div>
    <button id="loginBtn" @click="login">로그인</button>
    <hr>
    <button id="logoutBtn" @click="logout">로그아웃</button>
  </div>
</template>

<script>
export default {
  name: "LoginTest", //컴포넌트 이름
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
  methods: {
    async login() {
      await this.$api('/oauth2/authorization/google', 'post', '')
      .then(res => {
        console.log(res);
      })
    },
    async logout() {
      await this.$api('/logout', 'post')
      .then(res => {
        console.log(res);
      })
    }
  } //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style scoped>

</style>