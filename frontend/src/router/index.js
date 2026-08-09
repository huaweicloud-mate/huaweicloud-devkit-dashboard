import { createRouter, createWebHashHistory } from 'vue-router'
import Dashboard from '../views/Dashboard.vue'
import Scenarios from '../views/Scenarios.vue'
import VodFeedback from '../views/VodFeedback.vue'

const routes = [
  { path: '/', name: 'Dashboard', component: Dashboard },
  { path: '/scenarios', name: 'Scenarios', component: Scenarios },
  { path: '/vod', name: 'VodFeedback', component: VodFeedback }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
