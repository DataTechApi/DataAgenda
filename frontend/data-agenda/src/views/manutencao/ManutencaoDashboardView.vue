<template>
  <div class="dashboard-container">
    <h1 class="page-title">Dashboard de Manutenção</h1>
    
    <div class="stats-grid">
      <div class="stat-card">
        <i class="pi pi-calendar"></i>
        <div class="stat-info">
          <span class="stat-label">Agendamentos Hoje</span>
          <span class="stat-value">{{ metrics.agendadasHoje }}</span>
        </div>
      </div>
      <div class="stat-card accent">
        <i class="pi pi-check-circle"></i>
        <div class="stat-info">
          <span class="stat-label">Concluídas (Mês)</span>
          <span class="stat-value">{{ metrics.concluidasMes }}</span>
        </div>
      </div>
      <div class="stat-card critical">
        <i class="pi pi-clock"></i>
        <div class="stat-info">
          <span class="stat-label">Atrasadas</span>
          <span class="stat-value">{{ metrics.atrasadas }}</span>
        </div>
      </div>
    </div>

    <div class="content-section">
      <h3>Próximas Manutenções</h3>
      
      <DataTable :value="proximasManutencoes" stripedRows responsiveLayout="stack" class="p-datatable-sm custom-table">
        <Column field="dataAgendada" header="Data" :body="formatDate"></Column>
        <Column field="clienteNome" header="Cliente"></Column>
        <Column field="sistemaNome" header="Sistema"></Column>
        <Column field="tipo" header="Tipo">
            <template #body="slotProps">
                <span :class="'status-badge ' + slotProps.data.tipo.toLowerCase()">
                    {{ slotProps.data.tipo }}
                </span>
            </template>
        </Column>
        <Column field="descricao" header="Descrição"></Column>
        <template #empty>
            <p class="placeholder-text">Nenhuma manutenção pendente encontrada.</p>
        </template>
      </DataTable>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';

const URL = import.meta.env.VITE_API_URL;
const metrics = ref({
  agendadasHoje: 0,
  concluidasMes: 0,
  atrasadas: 0
});
const proximasManutencoes = ref([]);

const carregarDashboard = async () => {
  try {
    const response = await axios.get(`${URL}/api/dashboard`);
    const data = response.data;
    
    metrics.value = {
      agendadasHoje: data.agendadasHoje || 0,
      concluidasMes: data.concluidasMes || 0,
      atrasadas: data.atrasadas || 0
    };
    
    proximasManutencoes.value = data.proximasManutencoes || [];
  } catch (error) {
    console.error("Erro ao carregar dashboard de manutenção:", error);
  }
};

const formatDate = (rowData) => {
    if (!rowData.dataAgendada) return "N/A";
    const [year, month, day] = rowData.dataAgendada.split('-');
    return `${day}/${month}/${year}`;
};

onMounted(carregarDashboard);
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
  color: var(--status-info);
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
.stat-card.critical i { color: var(--status-danger); }

.content-section {
  background: var(--bg-card);
  padding: 30px;
  border-radius: 12px;
  border: 1px solid var(--border-color);
  min-height: 300px;
}

.content-section h3 {
  margin-bottom: 20px;
  color: var(--primary-color);
}

.placeholder-text {
  color: var(--text-muted);
  font-style: italic;
  text-align: center;
  padding: 20px;
}

:deep(.custom-table) {
    background: transparent !important;
}

:deep(.p-datatable-thead > tr > th) {
    background: var(--bg-header) !important;
    color: var(--primary-text) !important;
    border-bottom: 1px solid var(--border-color) !important;
}

:deep(.p-datatable-tbody > tr) {
    background: var(--bg-card) !important;
    color: var(--text-main) !important;
}

:deep(.p-datatable-tbody > tr > td) {
    border-bottom: 1px solid var(--border-color) !important;
}

.status-badge {
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    font-size: 0.75rem;
    font-weight: bold;
    text-transform: uppercase;
}

.status-badge.preventiva {
    background: var(--status-success);
    color: #ffffff;
}

.status-badge.emergencial {
    background: var(--status-danger);
    color: #ffffff;
}
</style>
