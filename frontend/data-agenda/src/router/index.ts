import { createRouter, createWebHistory } from 'vue-router'

import CadastroClientesView from '../views/CadastroClientesView.vue'
import CadastroTecnicosView from '../views/CadastroTecnicosView.vue'
import CadastroSistemasView from '../views/CadastroSistemasView.vue'
import DashboardView from '../views/DashboardView.vue'
import CadastroManutencoesView from '../views/CadastroManutencoesView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: DashboardView,
    },
    {
      path: '/tecnico',
      name: 'tecnico',
      component: CadastroTecnicosView,
    },
    {
      path: '/cliente',
      name: 'cliente',
      component: CadastroClientesView,
    },
    {
      path: '/sistema',
      name: 'sistema',
      component: CadastroSistemasView,
    },
   {
      path: '/manutencao',
      name: 'manutencao',
      component: CadastroManutencoesView,
    },
  ],
})

export default router
