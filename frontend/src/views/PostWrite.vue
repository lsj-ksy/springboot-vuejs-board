<template>
  <div id="app">
    <div id="main">
      <div class="row">
        <div class="col-md-6 mb-1">
          <!-- 게시판 글쓰기 카테고리 & 게시판 선택 input start -->
          <div class="input-group mb-3">
            <label class="input-group-text">카테고리</label>
            <select class="form-select" v-model="selectedCategory">
              <option value="0" disabled>-- 카테고리 --</option>
              <option :value="category.categoryId" :key="i" v-for="(category,i) in categories">
                {{ category.categoryName }}
              </option>
            </select>
            <label class="input-group-text" >게시판</label>
            <select class="form-select" v-model="$route.params.boardId">
              <option value="0" disabled>-- 게시판 --</option>
              <option :value="board.id" :key="i" v-for="(board,i) in boardByCategory">
                {{ board.boardName }}
              </option>
            </select>
          </div>
          <!-- 게시판 글쓰기 카테고리 & 게시판 선택 input end -->
        </div>
      </div>

      <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
      <div class="mb-3 mt-1">
        <input class="form-control" type="file" id="formFileMultiple" multiple="">
      </div>
      <div class="right-btn">
        <a href="#" class="btn btn-outline-success mt-1 mb-10" @click="testEditor()">글쓰기</a>
      </div>
    </div>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default {
  props: ['categories'], //부모 컴포넌트에서 category 목록 받아옴
  name: 'app',
  data() {
    return {
      editor: ClassicEditor,
      editorData: '',
      editorConfig: {
        // The configuration of the editor.
      },
      selectedCategory: this.$route.params.categoryId, //파라미터로 받아온 선택된 카테고리
      boardByCategory: ''
    };
  },
  methods: {
    testEditor() {
      console.log(this.editorData);
    },
    //카테고리 선택시 게시판 목록 불러오는 함수
    setBoardByCategory() {
      for (var i = 0; i < this.categories.length ; i++) {
        if(this.categories[i].categoryId == this.selectedCategory) {
          this.boardByCategory = this.categories[i].boards;
          return;
        }
      }
    }
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