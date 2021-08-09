<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <FileUpload />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import FileUpload from "@/components/FileUpload";

export default {
  name: 'Home',
  components: {
    HelloWorld,
    FileUpload
  }
}
</script>
