<template xmlns="http://www.w3.org/1999/html">
  <div>
    <h3>파일 업로드 결과 (하드코딩 테스트용): </h3>
    <div>
      <button @click="selectUploadFile()">글쓰기</button>
    </div>
    <div>
      <button @click="selectReply()">답글쓰기</button>
    </div>
    <div>
      <button @click="selectUploadFileForMain()">메인 이미지들 선택</button>
    </div>
    <div>
      <button @click="selectUploadFileForTitle()">타이틀 이미지 업로드</button>
    </div>
    <div>
      <button @click="updateNickname()">닉네임변경 test</button>
    </div>

    <span>response : {{ response }}</span>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "FileUpload", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      response: ""
    };
  },
  methods: {
    selectUploadFile() {
      var vue = this
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true
      // 클릭
      elem.click();
      // 이벤트 감지
      elem.onchange = function () {
        const formData = new FormData()
        formData.append('userId', 2);
        formData.append('boardId', 1);
        formData.append('subject', '글쓰기 테스트');
        formData.append('content', 'Testing contents')
        formData.append('ref', 0);
        formData.append('depth', 0);
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/post/write', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
          vue.response = response.data
        }).catch(error => {
          vue.response = error.message
        })
      }

    },
    selectReply() {
      var vue = this
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true
      // 클릭
      elem.click();
      // 이벤트 감지
      elem.onchange = function () {
        const formData = new FormData()
        formData.append('userId', 2);
        formData.append('boardId', 1);
        formData.append('subject', '답글테스트!!');
        formData.append('content', '답글이에요....ㅠ')
        formData.append('ref', 7);
        formData.append('depth', 4);
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/post/reply', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
          vue.response = response.data
        }).catch(error => {
          vue.response = error.message
        })
      }

    },
    selectUploadFileForMain() {
      var vue = this
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true
      // 클릭
      elem.click();
      // 이벤트 감지
      elem.onchange = function () {
        const formData = new FormData()
        formData.append('userId', 2);
        formData.append('boardId', 1);
        formData.append('subject', '메인파일업로드 테스트');
        formData.append('content', 'ㅎㅎ')
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/main/save', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
          vue.response = response.data
        }).catch(error => {
          vue.response = error.message
        })
      }

    },
    selectUploadFileForTitle() {
      var vue = this
      let elem = document.createElement('input')
      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true
      // 클릭
      elem.click();
      // 이벤트 감지
      elem.onchange = function () {
        const formData = new FormData()
        formData.append('userId', 2);
        formData.append('boardId', 1);
        formData.append('subject', '메인파일업로드 테스트');
        formData.append('content', '행복해')
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/title/save', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
          vue.response = response.data
        }).catch(error => {
          vue.response = error.message
        })
      }

    },
    updateNickname() {
      var vue = this
      let elem = document.createElement('input')
      elem.type = 'text'

      const formData = new FormData()
      formData.append('id', 1);
      formData.append('nickname', '이승준지존짱짱짱');
      axios.patch('/api/v1/user/myinfo/update', formData).then(response => {
        vue.response = response.data
      }).catch(error => {
        vue.response = error.message
      })


    }
  } //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style scoped>

</style>