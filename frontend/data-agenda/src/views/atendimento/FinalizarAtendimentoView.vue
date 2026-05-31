<template>
  <div class="layout" :class="{ 'sidebar-open': sidebarAberta }">
    <!-- Header Superior -->
    <header class="header">
      <img src="/src/assets/logoDataTech.jpeg" alt="Logo" class="logo" />
      <span>DATA AGENDA</span>

      <!-- Botão Hamburger -->
      <button
        class="hamburger"
        @click="sidebarAberta = !sidebarAberta"
        title="Menu"
      >
        <i class="pi pi-bars"></i>
      </button>

      <!-- Botão de tema -->
      <button
        class="theme-toggle"
        @click="themeStore.toggle()"
        :title="themeStore.isDark ? 'Mudar para modo claro' : 'Mudar para modo escuro'"
      >
        <i :class="themeStore.isDark ? 'pi pi-sun' : 'pi pi-moon'"></i>
      </button>
    </header>

    <!-- Sidebar Lateral -->
    <aside class="sidebar" v-if="sidebarAberta">
      <ul>
        <li>
          <RouterLink to="/atendimento" class="router-link">
            <div class="menu-item">
              <div class="menu-label">
                <i class="pi pi-check-circle"></i>
                <span>Visualizar Manutenções</span>
              </div>
            </div>
          </RouterLink>
        </li>
      </ul>

      <!-- Rodapé com logout e usuário -->
      <div class="sidebar-footer">
        <button class="logout-btn" @click="logout">
          <i class="pi pi-sign-out"></i>
          <span>Efetuar Logout</span>
        </button>
        <div class="user-info">
          <i class="pi pi-user"></i>
          <span>{{ usuarioLogado }}</span>
        </div>
      </div>
    </aside>

    <!-- Conteúdo Dinâmico -->
    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import 'primeicons/primeicons.css'
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { ref } from 'vue'
import { useThemeStore } from '../../stores/theme'

const themeStore = useThemeStore()
const router = useRouter()

let usuario = sessionStorage.getItem('usuario')
let nomeUsuario = 'Usuário'

if (usuario) {
  try {
    nomeUsuario = JSON.parse(usuario).nome || 'Usuário'
  } catch (e) {
    nomeUsuario = usuario
  }
}

const usuarioLogado = ref(nomeUsuario)
const sidebarAberta = ref(false)

function logout() {
  sessionStorage.clear()
  router.push('/login')
}
</script>

<style scoped>
.layout {
  display: grid;
  grid-template-columns: 250px 1fr;
  grid-template-rows: 80px 1fr;
  grid-template-areas:
    'header header'
    'sidebar content';
  height: 100vh;
  font-family: 'Segoe UI', sans-serif;
}

/* ---- Header ---- */
.header {
  grid-area: header;
  background: var(--bg-header);
  color: var(--text-main);
  display: flex;
  align-items: center;
  padding: 0 15px;
  font-size: 1.5rem;
  font-weight: bold;
  letter-spacing: 2px;
  border-bottom: 1px solid var(--border-color);
}

.logo {
  height: 60px;
  margin-right: 10px;
}

.theme-toggle,
.hamburger {
  margin-left: auto;
  background: transparent;
  border: none;
  color: #ffffff;
  cursor: pointer;
  font-size: 1.5rem;
}

.hamburger {
  margin-left: auto;
  margin-right: 10px;
}

/* ---- Sidebar ---- */
.sidebar {
  grid-area: sidebar;
  background: var(--bg-sidebar);
  color: var(--text-main);
  padding: 10px;
  display: flex;
  flex-direction: column;
  transition: transform 0.3s ease;
}

/* ---- Responsividade ---- */
@media (max-width: 768px) {
  .layout {
    grid-template-columns: 1fr;
    grid-template-areas:
      'header'
      'content';
  }

  .sidebar {
    position: fixed;
    top: 80px;
    left: 0;
    width: 220px;
    height: calc(100% - 80px);
    transform: translateX(-100%);
    z-index: 1000;
  }

  .layout.sidebar-open .sidebar {
    transform: translateX(0);
  }
}
</style>
