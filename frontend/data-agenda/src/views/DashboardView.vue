<template>
  <div class="dashboard-container">
    <h2 class="dashboard-title">Dashboard Gerencial</h2>

    <!-- KPIs Top Cards -->
    <div class="grid kpi-section">
      <div class="col-12 md:col-6 lg:col-3">
        <div class="kpi-card">
          <div class="kpi-content">
            <span class="kpi-label">Total Clientes</span>
            <span class="kpi-value kpi-blue">{{ metrics.totalClientes }}</span>
          </div>
          <div class="kpi-icon kpi-icon-blue">
            <i class="pi pi-users kpi-blue"></i>
          </div>
        </div>
      </div>
      <div class="col-12 md:col-6 lg:col-3">
        <div class="kpi-card">
          <div class="kpi-content">
            <span class="kpi-label">Sistemas Ativos</span>
            <span class="kpi-value kpi-green">{{ metrics.totalSistemas }}</span>
          </div>
          <div class="kpi-icon kpi-icon-green">
            <i class="pi pi-desktop kpi-green"></i>
          </div>
        </div>
      </div>
      <div class="col-12 md:col-6 lg:col-3">
        <div class="kpi-card">
          <div class="kpi-content">
            <span class="kpi-label">Pendentes</span>
            <span class="kpi-value kpi-orange">{{ metrics.manutencoesPendentes }}</span>
          </div>
          <div class="kpi-icon kpi-icon-orange">
            <i class="pi pi-clock kpi-orange"></i>
          </div>
        </div>
      </div>
      <div class="col-12 md:col-6 lg:col-3">
        <div class="kpi-card">
          <div class="kpi-content">
            <span class="kpi-label">Executadas</span>
            <span class="kpi-value kpi-purple">{{ metrics.manutencoesExecutadas }}</span>
          </div>
          <div class="kpi-icon kpi-icon-purple">
            <i class="pi pi-check-circle kpi-purple"></i>
          </div>
        </div>
      </div>
    </div>

    <!-- Charts Section -->
    <div class="grid chart-section">
      <div class="col-12 lg:col-6">
        <div class="chart-card">
          <h3>Sistemas por Tipo</h3>
          <Chart type="doughnut" :data="sistemasChartData" :options="chartOptions" :key="themeStore.isDark ? 'dark' : 'light'" class="chart-container" />
        </div>
      </div>
      <div class="col-12 lg:col-6">
        <div class="chart-card">
          <h3>Status das Manutenções</h3>
          <Chart type="pie" :data="statusChartData" :options="chartOptions" :key="(themeStore.isDark ? 'dark' : 'light') + '-pie'" class="chart-container" />
        </div>
      </div>
    </div>

    <!-- Next Maintenances Table -->
    <div class="table-section mt-4">
      <div class="table-card">
        <div class="flex justify-content-between align-items-center mb-3">
          <h3>Próximas Manutenções Agendadas</h3>
          <Button label="Ver Todas" icon="pi pi-external-link" text @click="$router.push('/dashboard/manutencao/visualizar')" />
        </div>
        <DataTable :value="metrics.proximasManutencoes" responsiveLayout="stack" breakpoint="960px" class="p-datatable-sm">
          <Column field="dataAgendada" header="Data">
            <template #body="slotProps">
              {{ formatDate(slotProps.data.dataAgendada) }}
            </template>
          </Column>
          <Column field="clienteNome" header="Cliente"></Column>
          <Column field="sistemaNome" header="Sistema"></Column>
          <Column field="tipo" header="Tipo">
            <template #body="slotProps">
              <Badge :value="slotProps.data.tipo" :severity="getTipoSeverity(slotProps.data.tipo)" />
            </template>
          </Column>
          <Column field="descricao" header="Descrição"></Column>
        </DataTable>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import Chart from 'primevue/chart'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Badge from 'primevue/badge'
import Button from 'primevue/button'
import { useThemeStore } from '@/stores/theme'

const themeStore = useThemeStore()

const metrics = ref({
  totalClientes: 0,
  totalSistemas: 0,
  manutencoesPendentes: 0,
  manutencoesExecutadas: 0,
  sistemasPorTipo: {},
  proximasManutencoes: []
})

const loading = ref(true)

const fetchDashboardData = async () => {
  try {
    const URL = import.meta.env.VITE_API_URL;
    const response = await axios.get(`${URL}/api/dashboard`)
    metrics.value = response.data
  } catch (error) {
    console.error('Erro ao buscar dados do dashboard:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  
    fetchDashboardData()
 
})

const sistemasChartData = computed(() => {
  const labels = Object.keys(metrics.value.sistemasPorTipo)
  const data = Object.values(metrics.value.sistemasPorTipo)
  
  return {
    labels: labels,
    datasets: [
      {
        data: data,
        backgroundColor: ['#42A5F5', '#66BB6A', '#FFA726', '#26C6DA'],
        hoverBackgroundColor: ['#64B5F6', '#81C784', '#FFB74D', '#4DD0E1']
      }
    ]
  }
})

const statusChartData = computed(() => {
  return {
    labels: ['Pendentes', 'Executadas'],
    datasets: [
      {
        data: [metrics.value.manutencoesPendentes, metrics.value.manutencoesExecutadas],
        backgroundColor: ['#FFA726', '#AB47BC'],
        hoverBackgroundColor: ['#FFB74D', '#BA68C8']
      }
    ]
  }
})

const chartOptions = computed(() => ({
  plugins: {
    legend: {
      labels: {
        color: themeStore.isDark ? '#ebedef' : '#334155'
      }
    }
  }
}))

const formatDate = (dateStr) => {
  if (!dateStr || dateStr === 'N/A') return 'N/A'
  const date = new Date(dateStr)
  return date.toLocaleDateString('pt-BR')
}

const getTipoSeverity = (tipo) => {
  switch (tipo) {
    case 'EMERGENCIAL': return 'danger'
    case 'PREVENTIVA': return 'info'
    default: return 'secondary'
  }
}
</script>

<style scoped>
.dashboard-container {
  padding: 2rem;
  background-color: var(--bg-app);
  min-height: 100vh;
  color: var(--text-main);
}

.dashboard-title {
  font-size: 2.25rem;
  font-weight: 700;
  margin-bottom: 2rem;
  color: var(--primary-color);
}

/* KPI Cards Layout */
.grid {
  display: flex;
  flex-wrap: wrap;
  margin-right: -1rem;
  margin-left: -1rem;
}

.col-12 {
  flex: 0 0 100%;
  padding: 1rem;
}

@media (min-width: 768px) {
  .md\:col-6 {
    flex: 0 0 50%;
  }
}

@media (min-width: 1024px) {
  .lg\:col-3 {
    flex: 0 0 25%;
  }
  .lg\:col-6 {
    flex: 0 0 50%;
  }
}

.kpi-card {
  background: var(--bg-card);
  border-radius: 12px;
  padding: 1.5rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 4px 20px 0 rgba(0,0,0,.14);
  transition: transform 0.3s ease;
  border: 1px solid var(--border-color);
}

.kpi-card:hover {
  transform: translateY(-5px);
}

.kpi-content {
  display: flex;
  flex-direction: column;
}

.kpi-label {
  color: var(--text-muted);
  font-size: 0.875rem;
  font-weight: 600;
  text-transform: uppercase;
}

.kpi-value {
  font-size: 1.75rem;
  font-weight: 700;
  margin-top: 0.5rem;
}

.kpi-icon {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  flex-shrink: 0;
}

.kpi-blue   { color: var(--status-info); }
.kpi-green  { color: var(--status-success); }
.kpi-orange { color: var(--status-warning); }
.kpi-purple { color: #a855f7; }

.kpi-icon-blue   { background: rgba(59, 130, 246, 0.15); }
.kpi-icon-green  { background: rgba(16, 185, 129, 0.15); }
.kpi-icon-orange { background: rgba(245, 158, 11, 0.15); }
.kpi-icon-purple { background: rgba(168, 85, 247, 0.15); }

/* Charts */
.chart-card {
  background: var(--bg-card);
  border-radius: 12px;
  padding: 1.5rem;
  height: 100%;
  border: 1px solid var(--border-color);
}

.chart-card h3 {
  margin-bottom: 1.5rem;
  font-size: 1.25rem;
  color: var(--text-muted);
}

.chart-container {
  max-height: 300px;
}

/* Table */
.table-card {
  background: var(--bg-card);
  border-radius: 12px;
  padding: 1.5rem;
  border: 1px solid var(--border-color);
}

.table-card h3 {
  color: var(--text-muted);
  font-size: 1.25rem;
}

:deep(.p-datatable) {
  background: transparent;
}

:deep(.p-datatable .p-datatable-thead > tr > th) {
  background: var(--bg-header);
  color: var(--primary-text);
  border-color: var(--border-color);
}

:deep(.p-datatable .p-datatable-tbody > tr) {
  background: transparent;
  color: var(--text-main);
  border-color: var(--border-color);
}

:deep(.p-datatable .p-datatable-tbody > tr:hover) {
  background: var(--bg-hover);
}

:deep(.p-button.p-button-text) {
  color: var(--primary-color);
}
</style>
