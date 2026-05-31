<template>
  <div class="dashboard-container">
    <h1 class="page-title">Dashboard de Técnicos</h1>
    
    <div class="stats-grid">
      <div class="stat-card">
        <i class="pi pi-briefcase"></i>
        <div class="stat-info">
          <span class="stat-label">Técnicos Ativos</span>
          <span class="stat-value">{{ totalTecnicosAtivos }}</span>
        </div>
      </div>
      <div class="stat-card accent">
        <i class="pi pi-star"></i>
        <div class="stat-info">
          <span class="stat-label">Avaliação Média</span>
          <span class="stat-value">4.9</span>
        </div>
      </div>
      <div class="stat-card warning">
        <i class="pi pi-user-minus"></i>
        <div class="stat-info">
          <span class="stat-label">Em Pausa/Férias</span>
          <span class="stat-value">1</span>
        </div>
      </div>
    </div>

    <div class="content-section">
      <h3>Equipe Técnica</h3>
      <p class="placeholder-text">Dados de produtividade e disponibilidade em tempo real...</p>
    </div>
  </div>
</template>
<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  name: "DashboardTecnicos",
  setup() {
    const totalTecnicosAtivos= ref(0);
    const URL=  import.meta.env.VITE_API_URL;

    const carregarTotalTecnicos = async () => {
      try {
       
        const response = await axios.get(`${URL}/tecnico/contartecnicosativos`);
        totalTecnicosAtivos.value = response.data;

      } catch (error) {
        console.error("Erro ao buscar total de técnicos ativos:", error);
      }
    };

    onMounted(() => {
     carregarTotalTecnicos();
    });

    return { totalTecnicosAtivos };
  },
};
</script>

<style scoped>
.dashboard-container {
  color: var(--text-main);
}

.page-title {
  font-size: 2rem;
  margin-bottom: 25px;
  color: var(--text-main);
  font-weight: 300;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.stat-card {
  background: var(--bg-card);
  padding: 25px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  border: 1px solid var(--border-color);
  transition: transform 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-card i {
  font-size: 2.5rem;
  margin-right: 20px;
  color: var(--primary-color);
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-label {
  font-size: 0.9rem;
  color: var(--text-muted);
  text-transform: uppercase;
  letter-spacing: 1px;
}

.stat-value {
  font-size: 1.8rem;
  font-weight: bold;
}

.stat-card.accent i { color: var(--status-success); }
.stat-card.warning i { color: var(--status-warning); }

.content-section {
  background: var(--bg-card);
  padding: 30px;
  border-radius: 12px;
  border: 1px solid var(--border-color);
  min-height: 300px;
}

.placeholder-text {
  color: var(--text-muted);
  font-style: italic;
}
</style>
