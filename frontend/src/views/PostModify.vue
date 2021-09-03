<template>
  <div id="app">
    <div id="main">
      <h1>Post modify Demo page</h1>
      <h1>{{ postDetail }}</h1>
      <div class="row">
        <div class="col-md-6 mb-3">
          <!-- 게시판 글쓰기 카테고리 & 게시판 선택 input start -->
          <div class="input-group mb-3">
            <label class="input-group-text">카테고리</label>
            <select class="form-select" v-model="selectedCategory">
              <option value="0" disabled>-- 카테고리 --</option>
              <option :value="category.categoryId" :key="i" v-for="(category,i) in categories">
                {{ category.categoryName }}
              </option>
            </select>
            <label class="input-group-text">게시판</label>
            <select class="form-select" v-model="$route.params.boardId">
              <option value="0" disabled>-- 게시판 --</option>
              <option :value="board.id" :key="i" v-for="(board,i) in boardByCategory">
                {{ board.boardName }}
              </option>
            </select>
          </div>
          <!-- 게시판 글쓰기 카테고리 & 게시판 선택 input end -->

          <!-- 글제목 -->
          <input type="text" class="form-control" id="basicInput" placeholder="제목" v-model="subject">
        </div>
      </div>

      <!-- 글내용 (ckEditor) -->
      <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>

      <!-- 파일 업로드 -->
      <div class="mb-3 mt-1">
        <a href="#" class="btn btn-outline-success mt-1 mb-10" @click.self.prevent="filesUpload(this.formData)">파일첨부</a>
      </div>

      <!-- 글쓰기 버튼 -->
      <div>
        <a href="#" class="btn btn-outline-success mt-1 mb-10" @click.self.prevent="postWrite( this.formData)">글쓰기</a>
      </div>
    </div>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import axios from "axios";

export default {
  props: ['categories'], //부모 컴포넌트에서 category 목록 받아옴
  name: 'app',
  data() {
    return {
      subject: '', //글제목
      editor: ClassicEditor,
      editorData: '',
      editorConfig: {
        // The configuration of the editor.
      },
      selectedCategory: this.$route.params.categoryId, //파라미터로 받아온 선택된 카테고리
      boardByCategory: '',
      ref: 0, //글쓰기 기본값
      depth: 0, //글쓰기 기본값
      elem: '',
      formData: new FormData(),
      postDetail: ''
    };
  },
  methods: {
    //카테고리 선택시 게시판 목록 불러오는 함수
    setBoardByCategory() {
      for (var i = 0; i < this.categories.length; i++) {
        if (this.categories[i].categoryId == this.selectedCategory) {
          this.boardByCategory = this.categories[i].boards;
          return;
        }
      }
    },
    async getPostDetail() { //글내용받아오기
      this.postDetail = await this.$api(`http://localhost:8080/api/v1/post/${this.$route.params.id}`, 'get')
      this.subject = this.postDetail.subject; //기존 제목
      this.editorData = this.postDetail.content; // 기존 내용
    },
    filesUpload(formData) { //파일첨부
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true

      elem.onchange = function () {
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
      }
      elem.click();

      this.elem = elem;
    },
    postWrite(formData) {  //글쓰기

      formData.append('userId', 2);
      formData.append('boardId', this.$route.params.boardId);
      formData.append('subject', this.subject);
      formData.append('content', this.editorData)
      formData.append('ref', 0);
      formData.append('depth', 0);

      axios.post('/api/v1/post/write', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
        this.$router.push(`/post_detail/${response.data}`); //글쓰기 성공시 상세보기 이동
      }).catch(error => {
        alert('글쓰기 도중 오류가 발생했습니다. console을 확인해주세요')
        console.log(error.message) //error발생시 메세지출력
      })
    },
  },
  beforeMount() {
    this.getPostDetail()
  },
  mounted() {
    //선택된 카테고리에 따른 게시판 목록
    this.setBoardByCategory()
  },
  watch: {
    selectedCategory() {
      this.setBoardByCategory();  //선택된 카테고리 변경시 게시판 목록도 변경됨
      this.$route.params.boardId = 0; //선택된 카테고리 변경시 선택된 boardId 도 0으로 바꿔줌 (--게시판--이 선택됨)
    }
  },
}
</script>
<style>
.input-group {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  margin-right: 0.2rem;
  width: auto;
}

.right-btn {
  float: right;
}

/*ckeditor 기본길이*/
.ck-editor__editable {
  min-height: 500px;
}

.form-select {
  margin-right: 1rem;
}


</style>