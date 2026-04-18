<template>
  <div class="dashboard-container">
    <h1 class="page-title">Dashboard de Clientes</h1>
    
    <div class="stats-grid">
      <div class="stat-card">
        <i class="pi pi-users"></i>
        <div class="stat-info">
          <span class="stat-label">Total de Clientes</span>
          <span class="stat-value">{{ totalClientes }}</span>
        </div>
      </div>
      <div class="stat-card accent">
        <i class="pi pi-user-plus"></i>
        <div class="stat-info">
          <span class="stat-label">Novos este mês</span>
          <span class="stat-value">+12%</span>
        </div>
      </div>
      <div class="stat-card warning">
        <i class="pi pi-exclamation-circle"></i>
        <div class="stat-info">
          <span class="stat-label">Pendentes</span>
          <span class="stat-value">5</span>
        </div>
      </div>
    </div>

    <div class="content-section">
      <h3>Clientes Recentes</h3>
      <p class="placeholder-text">A lista de clientes será carregada aqui...</p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  name: "DashboardClientes",
  setup() {
    const totalClientes = ref(0);
    const URL=  import.meta.env.VITE_API_URL;

    const carregarTotalClientes = async () => {
      try {
        // Se você tiver um endpoint dedicado, por exemplo /clientes/count:
        const response = await axios.get(`${URL}/clientes/contarclientes`);
        totalClientes.value = response.data;

        // Caso não exista esse endpoint, você pode buscar todos e contar:
        // const response = await axios.get("http://localhost:8080/clientes");
        // totalClientes.value = response.data.length;
      } catch (error) {
        console.error("Erro ao buscar total de clientes:", error);
      }
    };

    onMounted(() => {
     carregarTotalClientes();
    });

    return { totalClientes };
  },
};
</script>

<style scoped>
.dashboard-container {
  color: #ffffff;
}

.page-title {
  font-size: 2rem;
  margin-bottom: 25px;
  color: #ffffff;
  font-weight: 300;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.stat-card {
  background: #041628;
  padding: 25px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  border: 1px solid #1c2b3a;
  transition: transform 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-card i {
  font-size: 2.5rem;
  margin-right: 20px;
  color: #3b82f6;
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-label {
  font-size: 0.9rem;
  color: #94a3b8;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.stat-value {
  font-size: 1.8rem;
  font-weight: bold;
}

.stat-card.accent i { color: #10b981; }
.stat-card.warning i { color: #f59e0b; }

.content-section {
  background: #041628;
  padding: 30px;
  border-radius: 12px;
  border: 1px solid #1c2b3a;
  min-height: 300px;
}

.placeholder-text {
  color: #64748b;
  font-style: italic;
}
</style>
