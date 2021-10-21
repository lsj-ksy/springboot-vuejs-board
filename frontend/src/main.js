import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mixins from "../blob/mixins"
import CKEditor from '@ckeditor/ckeditor5-vue';
import VueSweetalert2 from "vue-sweetalert2";

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'sweetalert2/dist/sweetalert2.min.css'

const app = createApp(App)
app.use(router)
app.mixin(mixins)
app.use(CKEditor)
app.use(VueSweetalert2)
app.mount('#app')