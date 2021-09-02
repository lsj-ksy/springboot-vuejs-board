<template>
  <!-- style 적용전 임시 api data -->
  <p>{{postDetail}}</p>
  <div>
    <button @click.self.prevent="moveToModifyTest()">글수정이동테스트다</button>
  </div>
</template>

<script>
export default {
  name: "PostDetail", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      postDetail : '',
    };
  },
  mounted() {
    this.getPostDetail(this.$route.params.id)
  }, //unmount가 완료된 후 실행
  methods: {
    async getPostDetail(postid) { //파라미터 혹은 data에서 userid 받아와야함. 현재는 테스트코드
      this.postDetail = await this.$api(`http://localhost:8080/api/v1/post/${postid}`, 'get')
      console.log(this.postDetail);
    },
    moveToModifyTest() {
      this.$router.push(`/post_modify/${this.postDetail}`)
    }
  }
}
</script>

<style scoped>

</style>

