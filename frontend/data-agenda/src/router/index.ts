import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layouts/MainLayout.vue'
import LoginView from '../views/LoginView.vue'

// Dashboards
import DashboardView from '../views/DashboardView.vue'
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
import VisualizarTarefasTecnicoView from '../views/atendimento/VisualizarTarefasTecnicoView.vue'
import VisualizarManutencaoPorTecnicoView from '../views/atendimento/VisualizarManutencaoPorTecnicoView.vue'
import FinalizarAtendimentoView from '../views/atendimento/FinalizarAtendimentoView.vue'
import EditarManutencaoView from '../views/manutencao/EditarManutencaoView.vue'
import EditarClienteView from '../views/cliente/EditarClienteView.vue'
import EditarSistemaView from '../views/sistema/EditarSistemaView.vue'
import EditarTecnicoView from '../views/tecnico/EditarTecnicoView.vue'

// Função para decodificar JWT
function decodeToken(token) {
  try {
    const payload = token.split('.')[1]
    return JSON.parse(atob(payload))
  } catch {
    return null
  }
}

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/atendimento',
      component: VisualizarTarefasTecnicoView,
      meta: { requiresAuth: true, roles: ['ROLE_TECNICO'] },
      children:[
        {
          path: '',
          name: 'atendimento-visualizar',
          component: VisualizarManutencaoPorTecnicoView,
          meta: { roles: ['ROLE_TECNICO'] }
        },
        {
          path: '/atendimento/:id',
          name: 'atendimento-finalizar',
          component: FinalizarAtendimentoView,
          props: true,
          meta: { roles: ['ROLE_TECNICO'] }
        },
      ],
    },
    {
      path: '/',
      redirect: 'login',
    },
    {
      path: '/dashboard',
      component: MainLayout,
      meta: { requiresAuth: true, roles: ['ROLE_ADMIN'] },
      children: [
        {
          path: '',
          name: 'dashboard',
          component: DashboardView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        // Cliente Routes
        {
          path: 'cliente/dashboard',
          name: 'cliente-dashboard',
          component: ClienteDashboardView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'cliente/cadastrar',
          name: 'cliente-cadastrar',
          component: CadastroClientesView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'cliente/editar/:id',
          name: 'cliente-editar',
          component: EditarClienteView,
          props:true,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'cliente/visualizar',
          name: 'cliente-visualizar',
          component: VisualizarClienteView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        // Manutencao Routes
        {
          path: 'manutencao/dashboard',
          name: 'manutencao-dashboard',
          component: ManutencaoDashboardView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'manutencao/cadastrar',
          name: 'manutencao-cadastrar',
          component: CadastroManutencoesView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'manutencao/editar/:id',
          name: 'manutencao-editar',
          component: EditarManutencaoView,
          props: true,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'manutencao/visualizar',
          name: 'manutencao-visualizar',
          component: VisualizarManutencoesView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        // Sistema Routes
        {
          path: 'sistema/dashboard',
          name: 'sistema-dashboard',
          component: SistemaDashboardView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'sistema/cadastrar',
          name: 'sistema-cadastrar',
          component: CadastroSistemasView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'sistema/editar/:id',
          name: 'sistema-editar',
          component: EditarSistemaView,
          props:true,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'sistema/visualizar',
          name: 'sistema-visualizar',
          component: VisualizarSistemaView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        // Tecnico Routes
        {
          path: 'tecnico/dashboard',
          name: 'tecnico-dashboard',
          component: TecnicoDashboardView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'tecnico/cadastrar',
          name: 'tecnico-cadastrar',
          component: CadastroTecnicosView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'tecnico/editar/:id',
          name: 'tecnico-editar',
          component: EditarTecnicoView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'tecnico/visualizar',
          name: 'tecnico-visualizar',
          component: VisualizarTecnicoView,
          meta: { roles: ['ROLE_ADMIN'] }
        },
        {
          path: 'tecnico/tarefa/:id',
          name: 'tecnico-tarefa-detalhe',
          component: () => import('../views/tecnico/DetalheTarefaView.vue'),
          meta: { roles: ['ROLE_ADMIN'] }
        },
      ],
    },
  ],
})

// Guarda de rota
router.beforeEach((to, _from, next) => {
  const token = sessionStorage.getItem('token')
  const isAuthenticated = !!token
  const decoded = token ? decodeToken(token) : null
  const role = decoded?.role

  if (to.meta?.requiresAuth && !isAuthenticated) {
    return next({ name: 'login' })
  }
const roles = to.meta && to.meta.roles ? to.meta.roles : []
if (roles.length > 0 && !roles.includes(role)) {
  return next({ name: 'login' })
}


  if (to.name === 'login' && isAuthenticated) {
    return next({ name: 'dashboard' })
  }

  next()
})

export default router
