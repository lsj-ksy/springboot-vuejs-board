<template>
  <div id="app">
    <div id="main">
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
      <div class="mb-3">
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
      </div>
      <div class="card">
        <div class="card-header align-left">
          <h4 class="upload-text">파일 첨부</h4>
        </div>
        <div class="card-body">
          <!-- 파일 미리보기 버튼들어갈 div -->
          <div class="badges">
          </div>
        </div>
      </div>


      <!-- 파일 업로드 -->
      <div class="file-upload mb-3 mt-1 align-left">
        <a href="#" class="btn btn-lg btn-outline-primary" @click.self.prevent="filesUpload(this.formData, this.editorData)">파일첨부</a>
      </div>

      <!-- 글쓰기 버튼 -->
      <div>
        <a href="#" class="btn btn-lg btn-outline-primary" @click.self.prevent="postWrite( this.formData)">글쓰기</a>
      </div>
    </div>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'; //ckeditor
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
      elem: '', //input type=file 에 쓰일 element
      formData: new FormData(), //글쓰기 전송에 쓰일 formData
      targetId : 0
    };
  },
  methods: {
    //카테고리 선택시 게시판 목록 불러오는 함수
    setBoardByCategory() {
      for (let i = 0; i < this.categories.length; i++) {
        if (this.categories[i].categoryId == this.selectedCategory) {
          this.boardByCategory = this.categories[i].boards;
          return;
        }
      }
    },
    filesUpload(formData, editorData) { //파일첨부
      let t = this
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true

      elem.onchange = function (event) {
        for (let index = 0; index < this.files.length; index++) { //업로드 파일 form에 전송
          formData.append('files', this.files[index])
          console.log(this.files[index])
        }
        for (let image of this.files) {

          let reader = new FileReader();
          reader.onload = function (event) {
            t.targetId += 1;
            document.querySelector(".badges").innerHTML += `<button type="button" class="btn btn-outline-dark watch-file"
                    data-bs-toggle="modal" data-bs-target="#fileImg${t.targetId}">${image.name}</button>
                    <div class="modal-dark me-1 mb-1 d-inline-block"><div class="modal fade text-left" id="fileImg${t.targetId}" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable" role="document">
                          <div class="modal-content">
                            <div class="modal-header bg-dark white"><span class="modal-title" >미리보기</span>
                              <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><i data-feather="x"></i>
                              </button></div>
                           <div class="modal-body image_container">
                              <img src="${event.target.result}">
                          </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-dark ml-1" data-bs-dismiss="modal">
                                <i class="bx bx-check d-block d-sm-none"></i><span class="d-none d-sm-block">닫기</span></button>
                            </div></div></div></div></div>`
          };
          reader.readAsDataURL(image);
        }
      }
      elem.click()
      this.editorData = editorData
      this.elem = elem
    },
    postWrite(formData) {  //글쓰기

      //글쓰기 check (category/제목/내용)
      if(this.$route.params.boardId == 0 || this.subject == '' || this.editorData == '' ) {
        this.$swal.fire({
          icon: 'warning',
          title: '글 등록에 실패했습니다',
          text: '카테고리 설정 혹은 빈 내용이 있는지 확인해주세요!',
        })
        return;
      }

      formData.append('userId', 1);
      formData.append('boardId', this.$route.params.boardId);
      formData.append('subject', this.subject);
      formData.append('content', this.editorData)
      formData.append('ref', 0);
      formData.append('depth', 0);

      axios.post(`${process.env.BASE_URL}api/v1/post/write`, formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
        this.$router.push(`/post_detail/${response.data}`); //글쓰기 성공시 상세보기 이동
      }).catch(error => {
        alert('글쓰기 도중 오류가 발생했습니다. console을 확인해주세요')
        console.log(error.message) //error발생시 메세지출력
      })
    },
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

@import '../assets/css/app.css';

.ck.ck-editor__editable_inline {
  overflow: auto;
  padding: 3rem;
  border: 1px solid transparent;
}

.input-group {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  margin-right: 0.2rem;
  width: auto;
}

.file-upload {
  float: left;
}

.upload-text {
  text-align: left;
}

.badges {
  text-align: left;
}

.card-header {
  padding: 1.5rem 1.5rem;
  margin-bottom: 0;
  background-color: white;
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}

.watch-file {
  margin-right: 5px;
}

.badge {
  display: inline-block;
  padding: 0.35em 0.65em;
  font-size: 0.85em;
  font-weight: 700;
  line-height: 1;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: 0.25rem;
  margin-right: 5px;
}


.image_container {
  overflow: hidden;
  display: contents;
  -o-object-fit: cover;
  width: 100%;
  height: 100%;
}

.form-select {
  margin-right: 1rem;
}


</style>