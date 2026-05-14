<template>
  <div class="layout">
    <!-- Header -->
    <header class="header">
      <img src="../assets/logoDataTech.jpeg" alt="Logo" class="logo" />
      <h2>DataAgenda</h2>
    </header>

    <!-- Sidebar -->
    <aside class="sidebar">
      <ul>
        <li>
          <RouterLink to="/" class="router-link">
            <i class="pi pi-home"></i>
            <span>Painel de Controle</span>
          </RouterLink>
        </li>

        <!-- Gestão de Clientes -->
        <li @click="toggleMenu('cliente')">
          <div class="menu-item">
            <i class="pi pi-users"></i>
            <span>Gestão de Clientes</span>
            <i :class="openMenu === 'cliente' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'" class="arrow"></i>
          </div>
          <ul class="submenu" v-show="openMenu === 'cliente'">
            <li>
              <RouterLink to="/cliente/cadastrar" class="router-link">
                <i class="pi pi-plus"></i>
                <span>Cadastrar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/cliente/visualizar" class="router-link">
                <i class="pi pi-eye"></i>
                <span>Visualizar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/cliente/alterar" class="router-link">
                <i class="pi pi-pencil"></i>
                <span>Alterar</span>
              </RouterLink>
            </li>
          </ul>
        </li>

        <!-- Gestão de Manutenção -->
        <li @click="toggleMenu('manutencao')">
          <div class="menu-item">
            <i class="pi pi-wrench"></i>
            <span>Gestão de Manutenção</span>
            <i :class="openMenu === 'manutencao' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'" class="arrow"></i>
          </div>
          <ul class="submenu" v-show="openMenu === 'manutencao'">
            <li>
              <RouterLink to="/manutencao/cadastrar" class="router-link">
                <i class="pi pi-plus"></i>
                <span>Cadastrar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/manutencao/visualizar" class="router-link">
                <i class="pi pi-eye"></i>
                <span>Visualizar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/manutencao/alterar" class="router-link">
                <i class="pi pi-pencil"></i>
                <span>Alterar</span>
              </RouterLink>
            </li>
          </ul>
        </li>

        <!-- Gestão de Sistema -->
        <li @click="toggleMenu('sistema')">
          <div class="menu-item">
            <i class="pi pi-cog"></i>
            <span>Gestão de Sistema</span>
            <i :class="openMenu === 'sistema' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'" class="arrow"></i>
          </div>
          <ul class="submenu" v-show="openMenu === 'sistema'">
            <li>
              <RouterLink to="/sistema/cadastrar" class="router-link">
                <i class="pi pi-plus"></i>
                <span>Cadastrar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/sistema/visualizar" class="router-link">
                <i class="pi pi-eye"></i>
                <span>Visualizar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/sistema/alterar" class="router-link">
                <i class="pi pi-pencil"></i>
                <span>Alterar</span>
              </RouterLink>
            </li>
          </ul>
        </li>

        <!-- Gestão de Técnicos -->
        <li @click="toggleMenu('tecnico')">
          <div class="menu-item">
            <i class="pi pi-id-card"></i>
            <span>Gestão de Técnicos</span>
            <i :class="openMenu === 'tecnico' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'" class="arrow"></i>
          </div>
          <ul class="submenu" v-show="openMenu === 'tecnico'">
            <li>
              <RouterLink to="/tecnico/cadastrar" class="router-link">
                <i class="pi pi-plus"></i>
                <span>Cadastrar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/tecnico/visualizar" class="router-link">
                <i class="pi pi-eye"></i>
                <span>Visualizar</span>
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/tecnico/alterar" class="router-link">
                <i class="pi pi-pencil"></i>
                <span>Alterar</span>
              </RouterLink>
            </li>
          </ul>
        </li>
      </ul>
    </aside>

    <!-- Main Content -->
    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import 'primeicons/primeicons.css'
import { ref } from 'vue'

const openMenu = ref(null)

function toggleMenu(menu) {
  openMenu.value = openMenu.value === menu ? null : menu
}
</script>

<style scoped>
.layout {
  display: grid;
  grid-template-columns: 250px 1fr;
  grid-template-rows: 80px 1fr;
  grid-template-areas:
    "header header"
    "sidebar content";
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
  cursor: pointer;
  padding: 12px;
}

.sidebar i {
  margin-right: 10px;
  font-size: 1.2rem;
}

.arrow {
  margin-left: auto;
}

.submenu {
  list-style: none;
  padding-left: 25px;
  margin-top: 5px;
}

.submenu li {
  padding: 6px;
  margin-bottom: 4px;
  border-radius: 6px;
  transition: background 0.2s;
}

.submenu li:hover {
  background: var(--bg-hover);
}

.content {
  grid-area: content;
  padding: 20px;
  background: var(--bg-app);
}

.router-link {
  color: inherit;
  text-decoration: none;
}
</style>
