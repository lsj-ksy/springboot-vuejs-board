import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/List.vue')
  },
  {
    path: '/post_write/:categoryId/:boardId',
    name: 'PostWrite',
    component: () => import('../views/PostWrite')
  },
  {
    path: '/post_modify/:postDetail',
    name: 'PostModify',
    component: () => import('../views/PostModify')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/list/:categoryId/:boardId',
    name: 'List',
    component: () => import(/* webpackChunkName: "about" */ '../views/List.vue')
  },
  {
    path: '/post_detail/:id',
    name: 'postDetail',
    component: () => import(/* webpackChunkName: "about" */ '../views/PostDetail.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
