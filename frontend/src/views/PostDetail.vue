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
      <div class="mb-3">
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig" :disabled="editorDisabled"></ckeditor>
      </div>
      <!-- files Div -->
      <div class="card" v-if="fileExist">
        <div class="card-header align-left">
          <h4 class="upload-text">파일</h4>
        </div>
        <div class="card-body">
          <!-- 파일 미리보기 버튼들어갈 div -->
          <div class="badges">
          </div>
        </div>
      </div>

    </div>
    <div class="my-3">
      <a href="#" class="btn btn-lg btn-outline-primary mx-2" @click.self.prevent="moveToModify()">수정</a>
      <a href="#" class="btn btn-lg btn-outline-primary" @click.self.prevent="postDelete()">삭제</a>
    </div>

    <!-- post replyList, replyExist 가 true면 렌더링됨 -->
    <div class="col-12" v-if="replyExist">
      <div class="card">
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover table-lg">
              <thead>
              <tr>
              </tr>
              </thead>
              <tbody id="reply-table">
                <tr :key="i" v-for="(reply,i) in replyList">
                  <td class="col-3">
                    <div class="d-flex align-items-center">
                      <div class="avatar avatar-md">
                        <img src="https://i.stack.imgur.com/34AD2.jpg">
                      </div>
                      <p class="font-bold ms-3 mb-0">{{ reply.user.nickname }}</p>
                    </div>
                  </td>
                  <td :id="'reply-list-' + reply.id" class="col-auto">
                    <p class="reply-content mb-0">{{ reply.content }}</p>
                  </td>
                  <td class="col-2">
                    <p class="mb-0">{{ moment(reply.modifiedDate).format('YYYY-MM-DD HH:mm') }}</p>
                  </td>
                  <td class="col-1" :id="'reply-list-button-' + reply.id">
                    <!-- fontawesome 을 이용한다면..
                    <span @click.self.prevent="replyModifyEdit(reply.id)"><font-awesome-icon icon="edit"/></span>
                    <span @click.self.prevent="replyDelete(reply.id)"><font-awesome-icon icon="trash-alt" /></span>
                    -->
                    <span class="clickable" @click.self.prevent="replyModifyEdit(reply.id)">수정 /</span>
                    <span class="clickable" @click.self.prevent="replyDelete(reply.id)"> 삭제</span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- post replyWrite -->
    <div class="col-12">
      <div class="card">
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover table-lg">
              <thead>
              <tr>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td class="col-3" style="border-bottom-width: 0px;">
                  <div class="d-flex align-items-center">
                    <div class="avatar avatar-md">
                      <img src="https://i.stack.imgur.com/34AD2.jpg">
                    </div>
                    <p class="font-bold ms-3 mb-0">타락파워수연</p>
                  </div>
                </td>
                <td class="col-auto" style="border-bottom-width: 0px;">
                  <textarea class="form-control" placeholder="댓글을 써보세요!" id="floatingTextarea"
                            v-on:keyup.enter="replyWrite"></textarea>
                </td>
                <td class="col-2" style="border-bottom-width: 0px;">
                  <a href="#" class="btn btn-primary" @click.self.prevent="replyWrite">등록</a>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import moment from 'moment' //Date formatting
import axios from "axios";

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
      image: '',
      replyList: '',
      fileExist : false //파일 존재여부
    };
  },
  computed: {
    replyExist() {  //댓글 존재여부
      return this.replyList != ''
    }
  },
  mounted() {
    this.getPostDetail()
    this.getPostReplyList()
  }, //unmount가 완료된 후 실행
  methods: {
    async getPostDetail() {
      this.postDetail = await this.$api(`http://localhost:8080/api/v1/post/${this.$route.params.id}`, 'get')
      this.editorData = this.postDetail.content
      let t = this //vue


      for (let index = 0; index < this.postDetail.postFileNameList.length; index++) {
        t.fileExist = true
        //data의 image를 Promise default밸류로 지정 (해당 이미지를 import해옴으로서 vue서버에서 만들어진 이미지 주소가 들어감)
        await Promise.resolve(import("@/assets/images/upload/postfile/" + this.postDetail.postFileNameList[index])).then(function (value) {
          t.image = value.default
        }).catch(error => {
          console.log('해당 에러는 서버, chunk에 파일이 없어서 생기는 에러입니다..')
          console.log(error)
        })
        //기존 modal 초기화
        document.querySelector(".badges").innerHTML = '';
        //modal 삽입
        document.querySelector(".badges").innerHTML += `<button type="button" class="btn btn-outline-dark watch-file"
                    data-bs-toggle="modal" data-bs-target="#fileImg${index}">${this.postDetail.postOrigNameList[index]}</button>
                    <div class="modal-dark me-1 mb-1 d-inline-block"><div class="modal fade text-left" id="fileImg${index}" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable" role="document">
                          <div class="modal-content">
                            <div class="modal-header bg-dark white"><span class="modal-title" >미리보기</span>
                              <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><i data-feather="x"></i>
                              </button></div>
                           <div class="modal-body image_container">
                               <img src=${t.image}>
                          </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-dark ml-1" data-bs-dismiss="modal">
                                <i class="bx bx-check d-block d-sm-none"></i><span class="d-none d-sm-block">닫기</span></button>
                            </div></div></div></div></div>`
      }
    },
    async getPostReplyList() { //댓글리스트 api data response
      this.replyList = await this.$api(`http://localhost:8080/api/v1/post/${this.$route.params.id}/reply/list`, 'get')
    },
    moveToModify() { // 게시글 수정 이동
      this.$swal.fire({
        title: '게시글을 수정하시겠습니까?',
        showDenyButton: true,
        confirmButtonText: '네',
        denyButtonText: `아니오`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) { // '네' 클릭시 라우터 이동
          this.$router.push(`/post_modify/${this.$route.params.id}/${this.postDetail.categoryId}/${this.postDetail.boardId}`)
        }
      })
    },
    postDelete() {  //글삭제 (enabled 를 0 으로 변경)
      this.$swal.fire({
        title: '게시글을 삭제하시겠습니까?',
        showDenyButton: true,
        confirmButtonText: '네',
        denyButtonText: `아니오`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) { // '네' 클릭시 게시글 삭제
          this.$swal.fire('삭제되었습니다', '', 'success').then(() => {
            this.$api(`/api/v1/post/delete/${this.postDetail.postId}/2`, 'patch') //userid 하드코딩
            this.$router.push(`/list/${this.postDetail.categoryId}/${this.postDetail.boardId}`)
          })
        } else if (result.isDenied) { // '아니오' 클릭시 삭제 취소
          this.$swal.fire('삭제를 취소했습니다', '', 'info')
        }
      })
    },
    replyWrite(e) { //댓글 쓰기

      if (e.ctrlKey) {  // ctrl + enter 라면 enter 이벤트 대신 줄 바꾸기 입력됨
        document.getElementById('floatingTextarea').value = e.path[0].value + "\n"
        return;
      }

      let replyContent = document.querySelector('#floatingTextarea').value;
      let checkBlank = replyContent.trim()

      if (checkBlank == '') {
        this.$swal.fire({
          icon: 'error',
          title: '등록 실패!',
          text: '내용을 입력해 주세요!',
        })
        document.getElementById('floatingTextarea').value = ''; //댓글창 비우기
        return;
      }

      const formData = new FormData()
      formData.append('userId', 2); //userId 하드코딩
      formData.append('postId', this.postDetail.postId);  //postId
      formData.append('content', replyContent);  //textarea에 입력한 내용

      axios.post('/api/v1/reply/save', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
        this.getPostReplyList() //댓글 다시 불러오기
        document.getElementById('floatingTextarea').value = ''; //댓글창 비우기
      }).catch(error => {
        console.log(error)
      })
    },
    replyDelete(replyId) { //댓글 삭제

      this.$swal.fire({
        title: '댓글을 삭제하시겠습니까?',
        showDenyButton: true,
        confirmButtonText: '네',
        denyButtonText: `아니오`,
      }).then((result) => {
        if (result.isConfirmed) {
          this.$swal.fire('삭제되었습니다', '', 'success').then(() => {
            axios.delete(`/api/v1/reply/delete/${replyId}`).then(response => {
              this.getPostReplyList() //댓글 다시 불러오기
              console.log(response)
            }).catch(error => {
              console.log(error)
            })
          })
        }
      })
    },
    replyModifyEdit(replyId) { //댓글 수정
      console.log(document.getElementById('reply-list-' + replyId));

      let v = this;

      //원래 댓글내용
      let originElement = document.getElementById('reply-list-' + replyId)

      //댓글 입력창 element 만들기
      let modifyTextarea = document.createElement('textarea');
      modifyTextarea.className = 'form-control'
      modifyTextarea.id = 'floatingTextarea'
      modifyTextarea.innerHTML = originElement.firstChild.textContent;

      //기존 댓글 내용 대신 댓글 입력창 삽입
      originElement.firstChild.remove();
      originElement.appendChild(modifyTextarea);

      //기존 수정/삭제 버튼 있던 테이블 선택
      let button = document.getElementById('reply-list-button-' + replyId)

      //기존 버튼 삭제
      while (button.hasChildNodes()) { //자식노드 모두 삭제
        button.removeChild(button.firstChild);
      }

      //수정 버튼 삽입
      let modifyButton = document.createElement('a')
      modifyButton.href = '#'
      modifyButton.className = 'btn btn-primary'
      modifyButton.innerHTML = '수정'

      modifyButton.addEventListener('click', function (e) {  //버튼클릭시 수정완료
        v.replyModifySubmit(e, replyId);
      })
      modifyTextarea.addEventListener('keypress', function (e) {   //엔터키 수정완료
        v.replyModifySubmit(e, replyId);
      })

      button.appendChild(modifyButton)

    },
    replyModifySubmit(e, replyId) {
      console.log('testListener! replyId : ' + replyId)
      console.log(e)
    }
  }
}
</script>

<style>

.ck-editor__editable_inline {
  min-height: 400px !important;
  padding: 1.5rem 3rem 1.5rem 3rem !important;
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

.image_container {
  overflow: hidden;
  display: contents;
  -o-object-fit: cover;
  width: 100%;
  height: 100%;
}

.reply-content {
  white-space: pre-line;
  text-align: left;
}

.clickable {
  cursor: pointer;
}

</style>

