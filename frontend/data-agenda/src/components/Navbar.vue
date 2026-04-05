<!-- src/components/Navbar.vue -->
<template>
  <nav class="navbar">
    <div class="logo">
      <span class="black">Data</span><span class="blue">Agenda</span>
    </div>

    <ul class="menu">
      <li><router-link to="/dashboard">Painel de Controle</router-link></li>
      <li><router-link to="/tecnicos">Técnicos</router-link></li>
      <li><router-link to="/empresas">Empresas</router-link></li>
    </ul>

    <router-link to="/empresas/cadastro" class="btn">Cadastrar</router-link>
  </nav>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.navbar {
  background: #455a64;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 24px;
  border-radius: 8px;
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}
.logo .black { color: #fff; }
.logo .blue { color: #64b5f6; }

.menu {
  display: flex;
  gap: 20px;
  list-style: none;
  margin: 0;
  padding: 0;
}
.menu li a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}
.menu li a.router-link-exact-active {
  border-bottom: 2px solid #64b5f6;
}

.btn {
  background: #1976d2;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.btn:hover {
  background: #1565c0;
}
</style>