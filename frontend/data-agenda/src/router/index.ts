import { createRouter, createWebHistory } from 'vue-router'

import CadastroClientesView from '../views/cliente/CadastroClientesView.vue'
import CadastroTecnicosView from '../views/tecnico/CadastroTecnicosView.vue'
import CadastroSistemasView from '../views/sistema/CadastroSistemasView.vue'
import DashboardView from '../views/DashboardView.vue'
import CadastroManutencoesView from '../views/manutencao/CadastroManutencoesView.vue'
import VisualizarClienteView from '@/views/cliente/VisualizarClienteView.vue'
import VisualizarSistemaView from '@/views/sistema/VisualizarSistemaView.vue' 



const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: DashboardView,
    },
    {
      path: '/tecnico/cadastrar',
      name: 'tecnico',
      component: CadastroTecnicosView,
    },
    {
      path: '/cliente/cadastrar',
      name: 'cliente',
      component: CadastroClientesView,
    },
    {
      path: '/sistema/cadastrar',
      name: 'sistema',
      component: CadastroSistemasView,
    },
   {
      path: '/manutencao/cadastrar',
      name: 'manutencao',
      component: CadastroManutencoesView,
    },
  {
      path: '/cliente/visualizar',
      name: 'visualizacaoCliente',
      component: VisualizarClienteView,
    },
    {
      path: '/sistema/visualizar',
      name: 'visualizacaoSistema',
      component: VisualizarSistemaView,
    },
  ],
})

export default router
