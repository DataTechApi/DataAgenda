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
:root { 
  --blue: #2b4c8c; 
  --light-blue: #6a9bff; 
  --bg: #f1f4f9; 
}

body { 
  margin: 0; 
  font-family: 'Segoe UI', Roboto, sans-serif; /* Fontes mais modernas */
  background: var(--bg); 
}

.navbar { 
  display: flex; 
  background: var(--blue); 
  color: white; 
  height: 60px; 
  align-items: stretch;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Dá profundidade ao topo */
}

.logo { 
  background: #3b5da3; 
  padding: 0 25px; 
  display: flex; 
  align-items: center; 
  font-weight: bold; 
  letter-spacing: 1px;
}

.menu { display: flex; }
.menu a { 
  color: white; 
  text-decoration: none; 
  padding: 0 20px; 
  display: flex; 
  align-items: center; 
  transition: background 0.2s;
}

.menu a:hover {
  background: rgba(255,255,255,0.1);
}

.menu a.router-link-active { 
  background: var(--light-blue); 
}

.btn-nav { 
  margin-left: auto; 
  margin-right: 20px; 
  align-self: center; 
  background: transparent; 
  border: 1px solid rgba(255,255,255,0.5); 
  color: white; 
  padding: 6px 18px; 
  border-radius: 4px; 
  cursor: pointer; 
  transition: all 0.2s;
}

.btn-nav:hover {
  background: white;
  color: var(--blue);
}

.content { 
  padding: 40px;       
  width: 98%;           
  max-width: 1900px;    
  margin: 0 auto;       
  box-sizing: border-box;
}


</style>