import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/exam',
      name: 'exam',
      component: () => import('@/views/ExamView/index.vue')
    },
    {
      path: '/examlist/:repoId',
      name: 'examlist',
      props: true,
      component: () => import('@/views/ExamView/ExamListView.vue')
    }

  ],
})

export default router
