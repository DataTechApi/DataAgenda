import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layouts/MainLayout.vue'

// Dashboards
import DashboardView from '../views/DashboardView.vue'
// Usando lazy loading para os dashboards que ainda serão criados
const ClienteDashboardView = () => import('../views/cliente/ClienteDashboardView.vue')
const ManutencaoDashboardView = () => import('../views/manutencao/ManutencaoDashboardView.vue')
const SistemaDashboardView = () => import('../views/sistema/SistemaDashboardView.vue')
const TecnicoDashboardView = () => import('../views/tecnico/TecnicoDashboardView.vue')

// Forms and Visualizations
import VisualizarClienteView from '@/views/cliente/VisualizarClienteView.vue'
import VisualizarSistemaView from '@/views/sistema/VisualizarSistemaView.vue'
import CadastroClientesView from '../views/cliente/CadastroClientesView.vue'
import CadastroManutencoesView from '../views/manutencao/CadastroManutencoesView.vue'
import VisualizarManutencoesView from '../views/manutencao/VisualizarManutencoesView.vue'
import CadastroSistemasView from '../views/sistema/CadastroSistemasView.vue'
import CadastroTecnicosView from '../views/tecnico/CadastroTecnicosView.vue'
import VisualizarTecnicoView from '../views/tecnico/VisualizarTecnicoView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: MainLayout,
      children: [
        {
          path: '',
          name: 'dashboard',
          component: DashboardView,
        },
        // Cliente Routes
        {
          path: 'cliente/dashboard',
          name: 'cliente-dashboard',
          component: ClienteDashboardView,
        },
        {
          path: 'cliente/cadastrar',
          name: 'cliente-cadastrar',
          component: CadastroClientesView,
        },
        {
          path: 'cliente/visualizar',
          name: 'cliente-visualizar',
          component: VisualizarClienteView,
        },
        // Manutencao Routes
        {
          path: 'manutencao/dashboard',
          name: 'manutencao-dashboard',
          component: ManutencaoDashboardView,
        },
        {
          path: 'manutencao/cadastrar',
          name: 'manutencao-cadastrar',
          component: CadastroManutencoesView,
        },
        {
          path: 'manutencao/visualizar',
          name: 'manutencao-visualizar',
          component: VisualizarManutencoesView,
        },
        // Sistema Routes
        {
          path: 'sistema/dashboard',
          name: 'sistema-dashboard',
          component: SistemaDashboardView,
        },
        {
          path: 'sistema/cadastrar',
          name: 'sistema-cadastrar',
          component: CadastroSistemasView,
        },
        {
          path: 'sistema/visualizar',
          name: 'sistema-visualizar',
          component: VisualizarSistemaView,
        },
        // Tecnico Routes
        {
          path: 'tecnico/dashboard',
          name: 'tecnico-dashboard',
          component: TecnicoDashboardView,
        },
        {
          path: 'tecnico/cadastrar',
          name: 'tecnico-cadastrar',
          component: CadastroTecnicosView,
        },
        {
          path: 'tecnico/visualizar',
          name: 'tecnico-visualizar',
          component: VisualizarTecnicoView,
        },
        {
          path: 'tecnico/tarefas',
          name: 'tecnico-tarefas',
          component: () => import('@/views/tecnico/VisualizarTarefasTecnicoView.vue'),
        },
        {
          path: 'tecnico/finalizar/:id',
          name: 'finalizar-tarefa',
          component: () => import('@/views/tecnico/FinalizarTarefaView.vue'),
        },
        {
          path: 'tecnico/tarefa/:id',
          name: 'tecnico-tarefa-detalhe',
          component: () => import('@/views/tecnico/DetalheTarefaView.vue'),
        },
      ],
    },
  ],
})

export default router
