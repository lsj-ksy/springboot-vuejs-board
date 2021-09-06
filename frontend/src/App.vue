<template>
  <header-title/>
  <sidebar v-on:categories="categories"/>
  <router-view :categories="this.categories"/>
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
  methods: {
    categories(data) {
      this.categories = data;
    }
  }
}

</script>

<style>

@font-face {
  font-family: 'GmarketSansBold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'GmarketSansMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}


#app {
  font-family: GmarketSansMedium, GmarketSansBold, Avenir, Helvetica, Arial, sans-serif;
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
