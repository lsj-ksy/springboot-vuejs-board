import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mixins from "../blob/mixins"

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'


const app = createApp(App)
app.use(router)
app.mixin(mixins)
app.mount('#app')
