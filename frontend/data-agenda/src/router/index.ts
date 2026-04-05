import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '../views/LoginView.vue';
import DashboardView from '../views/DashboardView.vue';
import TecnicosView from '../views/TecnicosView.vue';
import EmpresasView from '../views/EmpresasView.vue';
import TechFormView from '@/views/TechFormView.vue';
import SistemFormView from '@/views/SistemFormView.vue';
import EmpresaFormView from '@/views/EmpresaFormView.vue';
import ManutencaoFormView from '@/views/ManutencaoFormView.vue';

const routes = [
  { path: '/', component: LoginView },
  { path: '/dashboard', component: DashboardView },
  { path: '/tecnicos', component: TecnicosView },
  { path: '/empresas', component: EmpresasView },
  { path: '/tecnicos/novo', component: TechFormView },
  { path: '/empresas/novo', component: EmpresaFormView },
  { path: '/empresas/novo-sistema', component: SistemFormView },
  { path: '/dashboard/novo', component: ManutencaoFormView}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});


export default router;