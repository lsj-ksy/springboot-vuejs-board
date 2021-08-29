import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mixins from "../blob/mixins"
import store from "../blob/store";

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'

const app = createApp(App)
app.use(router)
app.use(store)
app.mixin(mixins)
app.mount('#app')
