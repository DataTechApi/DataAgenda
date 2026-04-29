<template>
  <div class="layout">
    <!-- Header Superior -->
    <header class="header">
      <img src="/src/assets/logoDataTech.jpeg" alt="Logo" class="logo" />
      <span>DATA AGENDA</span>
    </header>

    <!-- Sidebar Lateral -->
    <aside class="sidebar">
      <ul>
        <!-- Menu Finalizar Atendimento -->
        <li>
          <RouterLink to="/atendimento/finalizar" class="router-link">
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
import { ref, onMounted } from 'vue'


const router = useRouter()

// Recupera somente o nome do usuário salvo no login
let usuario = sessionStorage.getItem('usuario')
let nomeUsuario = 'Usuário'

if (usuario) {
  try {
    nomeUsuario = JSON.parse(usuario).nome || 'Usuário'
  } catch (e) {
    // Se não for JSON, usa direto
    nomeUsuario = usuario
  }
}

const usuarioLogado = ref(nomeUsuario)


function logout() {
  sessionStorage.clear()
  localStorage.clear()
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

.header {
  grid-area: header;
  background: var(--bg-header);
  color: var(--text-main);
  display: flex;
  align-items: center;
  padding: 0 30px;
  font-size: 1.5rem;
  font-weight: bold;
  letter-spacing: 2px;
  border-bottom: 1px solid var(--border-color);
}

.logo {
  height: 80px;
  margin-right: 15px;
}

.sidebar {
  grid-area: sidebar;
  background: var(--bg-sidebar);
  color: var(--text-main);
  padding: 10px;
  display: flex;
  flex-direction: column;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar li {
  margin-bottom: 5px;
  border-radius: 8px;
  transition: background 0.3s;
}

.sidebar li:hover {
  background: var(--border-color);
}

.menu-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0;
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
  margin-bottom: 10px;
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
</style>
