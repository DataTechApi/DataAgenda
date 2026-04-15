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
         
        <li>
          
          <RouterLink to="/cliente" class="router-link">
            <i class="pi pi-users"></i>
            <span>Gestão de Clientes</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/manutencao" class="router-link">
          <i class="pi pi-wrench"></i>
          <span>Gestão de Manutenção</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/sistema" class="router-link">
            <i class="pi pi-cog"></i>
            <span>Gestão de Sistema</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/tecnico" class="router-link">
            <i class="pi pi-id-card"></i>
            <span>Gestão de Técnicos</span>
          </RouterLink>
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
import { RouterLink, RouterView } from 'vue-router'
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
  background: #000;
  color: white;
  display: flex;
  align-items: center;
  padding: 0 30px;
  font-size: 1.5rem; /* Ajustado para h2 */
  font-weight: bold;
  letter-spacing: 2px;
}

.logo {
  height: 80px;
  margin-right: 15px;
}

.sidebar {
  grid-area: sidebar;
  background: #000;
  color: #fff;
  padding: 10px;
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar li {
  display: flex;
  align-items: center;
  padding: 12px;
  margin-bottom: 10px;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s;
}

.sidebar li:hover {
  background: #2E2E40;
}

.sidebar i {
  margin-right: 10px;
  font-size: 1.2rem;
}

.content {
  grid-area: content;
  padding: 20px;
  background: #1d1c1c;
}
</style>
