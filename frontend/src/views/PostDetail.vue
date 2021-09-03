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
    this.getPostDetail()
  }, //unmount가 완료된 후 실행
  methods: {
    async getPostDetail() {
      this.postDetail = await this.$api(`http://localhost:8080/api/v1/post/${this.$route.params.id}`, 'get')
      console.log(this.postDetail);
    },
    moveToModifyTest() {
      this.$router.push(`/post_modify/${this.$route.params.id}/${this.postDetail.categoryId}/${this.postDetail.boardId}`)
    }
  }
}
</script>

<style scoped>

</style>

