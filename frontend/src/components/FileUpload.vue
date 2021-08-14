<template>
  <div>
    <h3>파일 업로드 결과 (하드코딩 테스트용): </h3>
    <div>
      <button @click="selectUploadFile()">이미지 선택</button>
    </div>
    <div>
      <button @click="selectUploadFileForMain()">메인 이미지들 선택</button>
    </div>
    <div>
      <button @click="selectUploadFileForTitle()">타이틀 이미지 업로드</button>
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
  setup() {
  }, //컴포지션 API
  created() {
  }, //컴포넌트가 생성되면 실행
  mounted() {
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  unmounted() {
  }, //unmount가 완료된 후 실행
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
        formData.append('subject', '파일업로드 테스트');
        formData.append('content', '제발되게해주세요흑흑')
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/post/save', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
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
        formData.append('content', '수연이 정말똑똑하네')
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/mainsave', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
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
        formData.append('content', '수연아 행복해..?')
        for (var index = 0; index < this.files.length; index++) {
          formData.append('files', this.files[index])
        }
        axios.post('/api/v1/titlesave', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(response => {
          vue.response = response.data
        }).catch(error => {
          vue.response = error.message
        })
      }

    }
  } //컴포넌트 내에서 사용할 메소드 정의
}
</script>

<style scoped>

</style>