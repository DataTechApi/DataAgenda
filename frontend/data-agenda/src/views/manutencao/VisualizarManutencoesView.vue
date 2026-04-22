<template>
  <div class="view-container">
    <div class="card header-card">
      <h2 class="page-title">Visualizar Manutenções</h2>
      <p class="subtitle">Lista completa de agendamentos e manutenções realizadas.</p>
    </div>

    <div class="card table-card">
      <DataTable 
        :value="manutencoes" 
        stripedRows 
        paginator 
        :rows="10" 
        responsiveLayout="scroll" 
        class="p-datatable-sm custom-table"
        v-model:filters="filters"
        filterDisplay="menu"
      >
        <template #header>
            <div class="table-header">
                <span class="p-input-icon-left search-container">
                    <i class="pi pi-search"></i>
                    <InputText v-model="filters['global'].value" placeholder="Pesquisar..." class="search-input" />
                </span>
                <Button label="Agendar Nova" icon="pi pi-plus" class="p-button-primary" @click="$router.push('/manutencao/cadastrar')" />
            </div>
        </template>

        <Column field="dataAgendada" header="Data Agendada" sortable :body="formatDate"></Column>
        <Column field="sistema.cliente.nome" header="Cliente" sortable></Column>
        <Column field="sistema.nome" header="Sistema" sortable></Column>
        <Column field="tecnico.nome" header="Técnico" sortable></Column>
        <Column field="tipoManutencao" header="Tipo" sortable>
            <template #body="slotProps">
                <span :class="'status-badge ' + (slotProps.data.tipoManutencao || '').toLowerCase()">
                    {{ slotProps.data.tipoManutencao }}
                </span>
            </template>
        </Column>
        <Column field="statusManutencao" header="Status" sortable>
            <template #body="slotProps">
                <span :class="'status-badge ' + (slotProps.data.statusManutencao || '').toLowerCase()">
                    {{ slotProps.data.statusManutencao }}
                </span>
            </template>
        </Column>
        <Column field="descricao" header="Descrição"></Column>

        <template #empty>
            <p class="placeholder-text">Nenhuma manutenção encontrada.</p>
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
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import { FilterMatchMode } from '@primevue/core/api';

const URL = import.meta.env.VITE_API_URL;
const manutencoes = ref([]);
const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS }
});

const carregarManutencoes = async () => {
  try {
    const response = await axios.get(`${URL}/manutencoes`);
    manutencoes.value = response.data;
  } catch (error) {
    console.error("Erro ao carregar manutenções:", error);
  }
};

const formatDate = (rowData) => {
    if (!rowData.dataAgendada) return "N/A";
    const [year, month, day] = rowData.dataAgendada.split('-');
    return `${day}/${month}/${year}`;
};

onMounted(carregarManutencoes);
</script>

<style scoped>
.view-container {
  padding: 1rem;
  color: var(--text-main);
}

.card {
  background: var(--bg-card);
  border-radius: 12px;
  border: 1px solid var(--border-color);
  padding: 2rem;
  margin-bottom: 2rem;
}

.page-title {
  margin: 0;
  color: var(--primary-color);
  font-size: 1.8rem;
}

.subtitle {
  color: var(--text-muted);
  margin-top: 0.5rem;
}

.table-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1rem;
    gap: 1rem;
}

.search-container {
    flex: 1;
    position: relative;
}

.search-container i {
    position: absolute;
    left: 1rem;
    top: 50%;
    transform: translateY(-50%);
    color: var(--text-muted);
}

.search-input {
    width: 100%;
    padding-left: 2.5rem !important;
    background: var(--bg-header) !important;
    border: 1px solid var(--border-color) !important;
    color: var(--text-main) !important;
}

:deep(.custom-table) {
    background: transparent !important;
}

:deep(.p-datatable-header) {
    background: transparent !important;
    border: none !important;
    padding: 0 !important;
    margin-bottom: 1.5rem;
}

:deep(.p-datatable-thead > tr > th) {
    background: var(--bg-header) !important;
    color: var(--text-muted) !important;
    border-bottom: 1px solid var(--border-color) !important;
    padding: 1rem !important;
}

:deep(.p-datatable-tbody > tr) {
    background: var(--bg-card) !important;
    color: var(--text-main) !important;
}

:deep(.p-datatable-tbody > tr > td) {
    border-bottom: 1px solid var(--border-color) !important;
    padding: 1rem !important;
}

.status-badge {
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    font-size: 0.75rem;
    font-weight: bold;
    text-transform: uppercase;
}

.status-badge.preventiva { background: var(--status-success); color: #fff; }
.status-badge.emergencial { background: var(--status-danger); color: #fff; }
.status-badge.pendente { background: var(--status-warning); color: #fff; }
.status-badge.executada { background: var(--status-info); color: #fff; }

.placeholder-text {
  color: var(--text-muted);
  font-style: italic;
  text-align: center;
  padding: 20px;
}
</style>
