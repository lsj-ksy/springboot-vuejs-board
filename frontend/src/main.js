import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mixins from "../blob/mixins"
import CKEditor from '@ckeditor/ckeditor5-vue';
import VueSweetalert2 from "vue-sweetalert2";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

// Font awesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons'
import { faEdit } from '@fortawesome/free-solid-svg-icons'

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'sweetalert2/dist/sweetalert2.min.css'

//Font awesome
library.add(faTrashAlt) //삭제버튼
library.add(faEdit)     //수정버튼

const app = createApp(App)
app.use(router)
app.mixin(mixins)
app.use(CKEditor)
app.use(VueSweetalert2)
app.mount('#app')
app.component('font-awesome-icon', FontAwesomeIcon)

