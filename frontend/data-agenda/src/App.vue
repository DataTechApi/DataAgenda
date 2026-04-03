<template>
  <div class="app-container">
    <nav v-if="$route.path !== '/'" class="navbar">
      <div class="logo">DataAgenda</div>
      <div class="menu">
        <router-link to="/dashboard">Painel de Controle</router-link>
        <router-link to="/tecnicos">Técnicos</router-link>
        <router-link to="/empresas">Empresas</router-link>
      </div>
      <button v-if="$route.path === '/tecnicos'" @click="$router.push('/tecnicos/novo')" class="btn-nav">Cadastrar Técnico</button>
    </nav>

    <main :class="{ 'content': $route.path !== '/' }">
      <router-view />
    </main>
  </div>
</template>

<style>
:root { --blue: #2b4c8c; --light-blue: #6a9bff; --bg: #f1f4f9; }
body { margin: 0; font-family: sans-serif; background: var(--bg); }
.navbar { display: flex; background: var(--blue); color: white; height: 60px; align-items: stretch; }
.logo { background: #3b5da3; padding: 0 20px; display: flex; align-items: center; font-weight: bold; }
.menu { display: flex; }
.menu a { color: white; text-decoration: none; padding: 0 20px; display: flex; align-items: center; }
.menu a.router-link-active { background: var(--light-blue); }
.btn-nav { margin-left: auto; margin-right: 20px; align-self: center; background: transparent; border: 1px solid white; color: white; padding: 5px 15px; border-radius: 4px; cursor: pointer; }
.content { padding: 30px; }
</style>