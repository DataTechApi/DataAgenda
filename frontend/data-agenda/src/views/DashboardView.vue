<template>
  <div class="dashboard">
    <h2 class="dashboard-title">Dashboard</h2>
    <div class="cards">
      <div class="card">
        <i class="pi pi-users"></i>
        <h3>Clientes</h3>
        <p>Gerencie todos os clientes cadastrados.</p>
        <RouterLink to="/cliente" class="card-link">Ver detalhes</RouterLink>
      </div>

      <div class="card">
        <i class="pi pi-cog"></i>
        <h3>Sistemas</h3>
        <p>Controle e configure os sistemas ativos.</p>
        <RouterLink to="/sistema" class="card-link">Ver detalhes</RouterLink>
      </div>

      <div class="card">
        <i class="pi pi-wrench"></i>
        <h3>Manutenções</h3>
        <p>Acompanhe e organize as manutenções.</p>
        <RouterLink to="/manutencao" class="card-link">Ver detalhes</RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import 'primeicons/primeicons.css'
</script>

<style scoped>
.dashboard {
  padding: 20px;
  color: #fff;
}

.dashboard-title {
  font-size: 2rem;
  margin-bottom: 20px;
  font-weight: bold;
  letter-spacing: 1px;
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.card {
  background: #2E2E40;
  padding: 20px;
  border-radius: 12px;
  text-align: center;
  transition: transform 0.3s, background 0.3s;
}

.card:hover {
  transform: translateY(-5px);
  background: #3a3a50;
}

.card i {
  font-size: 2rem;
  margin-bottom: 10px;
  color: #00bcd4;
}

.card h3 {
  margin: 10px 0;
  font-size: 1.5rem;
}

.card p {
  font-size: 0.9rem;
  color: #ccc;
  margin-bottom: 15px;
}

.card-link {
  display: inline-block;
  padding: 8px 15px;
  background: #657375;
  color: #fff;
  border-radius: 6px;
  text-decoration: none;
  transition: background 0.3s;
}

.card-link:hover {
  background: #748586;
}
</style>
