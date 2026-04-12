import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue')
  },
  {
    path: '/assets',
    name: 'Assets',
    component: () => import('../views/AssetList.vue')
  },
  {
    path: '/maintenances',
    name: 'Maintenances',
    component: () => import('../views/MaintenanceList.vue')
  },
  {
    path: '/cliente',
    name: 'cliente',
    component: () => import('../views/CadastroClientesView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
