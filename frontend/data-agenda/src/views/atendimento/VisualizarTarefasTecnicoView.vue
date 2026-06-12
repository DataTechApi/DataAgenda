<template>
  <div class="layout" :class="{ 'sidebar-open': sidebarAberta }">
    <!-- Header Superior -->
    <header class="header">
      <img src="/src/assets/logoDataTech.jpeg" alt="Logo" class="logo" />
      <span>DATA AGENDA</span>

      <!-- Botão Hamburger só no mobile -->
      <button
        class="hamburger"
        v-if="isMobile"
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

      <!-- Usuário + Logout só no mobile -->
      <div class="mobile-user" v-if="isMobile">
        <span>{{ usuarioLogado }}</span>
        <button class="logout-btn" @click="logout">
          <i class="pi pi-sign-out"></i>
        </button>
      </div>
    </header>

    <!-- Sidebar Lateral -->
    <aside class="sidebar" v-if="!isMobile || sidebarAberta">
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

      <!-- Rodapé com logout e usuário (só desktop) -->
      <div class="sidebar-footer" v-if="!isMobile">
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
import { ref, onMounted, onBeforeUnmount } from 'vue'
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
const isMobile = ref(window.innerWidth <= 768)

function logout() {
  sessionStorage.clear()
  router.push('/login')
}

function handleResize() {
  isMobile.value = window.innerWidth <= 768
  if (!isMobile.value) sidebarAberta.value = false
}

onMounted(() => {
  window.addEventListener('resize', handleResize)
})

onBeforeUnmount(() => {
  window.removeEventListener('resize', handleResize)
})
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
  background: transparent;
  border: none;
  color: #ffffff;
  cursor: pointer;
  font-size: 1.5rem;
  margin-left: auto;
}

.hamburger {
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
  position: fixed;
  top: 80px;
  left: 0;
  width: 250px;
  height: calc(100% - 80px);
  z-index: 1000;

  /* escondido por padrão no desktop */
  transform: translateX(-230px); /* deixa 20px visível */
}

.sidebar:hover {
  transform: translateX(0); /* aparece ao passar o mouse */
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar li {
  margin-bottom: 5px;
  border-radius: 8px;
  transition: background 0.2s;
}

.sidebar li:hover {
  background: var(--bg-hover);
}

.menu-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.menu-label {
  display: flex;
  align-items: center;
  padding: 12px;
  width: 100%;
}

.sidebar i {
  margin-right: 10px;
  font-size: 1.2rem;
}

/* ---- Content ---- */
.content {
  grid-area: content;
  padding: 20px;
  background: var(--bg-app);
  overflow-y: auto;
}

.router-link {
  color: inherit;
  text-decoration: none;
  display: flex;
  align-items: center;
  width: 100%;
}

.logout-btn {
  background: none;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  color: inherit;
  font-weight: bold;
}

.sidebar-footer {
  margin-top: auto;
  padding: 12px;
  border-top: 1px solid var(--border-color);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: var(--text-main);
}

.user-info {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.user-info i {
  margin-right: 8px;
}

/* ---- Mobile ---- */
@media (max-width: 768px) {
  .layout {
    grid-template-columns: 1fr;
    grid-template-areas:
      'header'
      'content';
  }

  .sidebar {
    transform: translateX(-100%); /* escondido totalmente no mobile */
  }

  .layout.sidebar-open .sidebar {
    transform: translateX(0); /* aparece com botão hamburger */
  }

  .mobile-user {
    display: flex;
    align-items: center;
    margin-left: 15px;
    color: #fff;
    font-weight: bold;
  }

  .mobile-user .logout-btn {
    margin-left: 10px;
    background: none;
    border: none;
    color: inherit;
    cursor: pointer;
  }
}
</style>
