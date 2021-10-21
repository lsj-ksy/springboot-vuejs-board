<template>
  <div id="title">
    <div class="container">
      <div class="card mt-5">
          <a class="card-title" :href="`${mainUrl}`">
            <img id="title-img" src="../assets/images/samples/temp_title.png">
          </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderTitle", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      sampleData: "",
      mainUrl: ''
    };
  },
  setup() {
  }, //컴포지션 API
  created() {
  }, //컴포넌트가 생성되면 실행
  mounted() {
    this.mainUrl = process.env.BASE_URL;
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  unmounted() {
  }, //unmount가 완료된 후 실행
  methods: {} //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style scoped>

</style>

