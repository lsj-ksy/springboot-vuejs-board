<template>
  <div id="main">
    <header class="mb-3">
      <a href="#" class="burger-btn d-block d-xl-none">
        <i class="bi bi-justify fs-3"></i>
      </a>
    </header>

    <div class="page-heading">
      <div class="page-title">
        <div class="row">
          <div class="col-12 col-md-6 order-md-1 order-last">
          </div>
          <div class="col-12 col-md-6 order-md-2 order-first">
            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">{{ postDetail.categoryName }}</li>
                <li class="breadcrumb-item">
                  <router-link :to="`/list/${postDetail.categoryId}/${postDetail.boardId}`">{{ postDetail.boardName }}
                  </router-link>
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
      <section class="section">
        <div class="card">
          <div class="card-body py-4 px-5">
            <div class="d-flex align-items-center">
              <div class="avatar avatar-xl">
                <img src="https://i.stack.imgur.com/34AD2.jpg" alt="profile">
              </div>
              <div class="ms-3 name">
                <h5 class="font-bold">이승준</h5>
                <h6 class="text-muted mb-0">{{ moment(postDetail.modifiedDate).format('YYYY-MM-DD HH:mm') }}</h6>

              </div>
              <h4 class="card-title m-lg-4">{{ postDetail.subject }}</h4>

            </div>
          </div>
          <ckeditor :editor="editor" v-model="editorData" :config="editorConfig" :disabled="editorDisabled"></ckeditor>
        </div>
      </section>
    </div>
  </div>
  <div>
    <button @click.self.prevent="moveToModifyTest()">글수정이동테스트다</button>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import moment from 'moment' //Date formatting


export default {
  name: "PostDetail", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      postDetail: '',
      editor: ClassicEditor,
      editorData: '',
      editorConfig: {
        toolbar: null //toolbar 없음
      },
      editorDisabled: true, //ckEditor read-only
      moment: moment, //날짜 포맷 moment.js
    };
  },
  mounted() {
    this.getPostDetail()
  }, //unmount가 완료된 후 실행
  methods: {
    async getPostDetail() {
      this.postDetail = await this.$api(`http://localhost:8080/api/v1/post/${this.$route.params.id}`, 'get')
      this.editorData = this.postDetail.content
    },
    moveToModifyTest() {
      this.$router.push(`/post_modify/${this.$route.params.id}/${this.postDetail.categoryId}/${this.postDetail.boardId}`)
    },
  }
}
</script>

<style scoped>

</style>

