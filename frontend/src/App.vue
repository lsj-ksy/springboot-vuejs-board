<template>
  <header-title/>
  <sidebar v-on:categories="categories"/>
  <router-view :categories="this.categories" />
  <bfooter/>
</template>

<script>
  import HeaderTitle from "@/layouts/HeaderTitle";
  import Sidebar from "@/layouts/Sidebar";
  export default {
    components: {HeaderTitle, Sidebar},
    data() {
      return {
        categories: '123'
      };
    },
    methods : {
      categories(data) {
        this.categories = data;
      }
    }
  }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
