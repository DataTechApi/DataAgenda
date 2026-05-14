<template>
  <div class="card">
    <h2 class="page-title">Visualizar Manutenções</h2>
    
    
    <div class="table-container">
      <DataTable 
        :value="manutencoes" 
        responsiveLayout="scroll" 
        class="p-datatable-custom"
        paginator 
        :rows="10"
        v-model:filters="filters"
        filterDisplay="menu"
        :globalFilterFields="['descricao', 'tecnico.nome', 'sistema.nome', 'sistema.cliente.nome']"
      >
        <template #header>
            <div class="table-header">
                <span class="p-input-icon-left search-container">
                    <i class="pi pi-search"></i>
                    <InputText v-model="filters['global'].value" placeholder="Pesquisar..." class="search-input" />
                </span>
                <Button label="Agendar Nova" icon="pi pi-plus" class="p-button-success p-button-sm" @click="$router.push('/manutencao/cadastrar')" />
            </div>
        </template>

        <Column field="dataAgendada" header="Data" sortable>
            <template #body="slotProps">
                {{ formatDate(slotProps.data.dataAgendada) }}
            </template>
        </Column>
        
        <Column field="clienteNome" header="Cliente" sortable></Column>
        <Column field="sistemaNome" header="Sistema" sortable></Column>
        <Column field="tecnicoNome" header="Técnico" sortable></Column>
        
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
        <Column header="Ações">
  <template #body="slotProps">
    <Button 
      label="" 
      icon="pi pi-eye" 
      class="p-button-rounded p-button-info p-button-sm" 
      @click="$router.push({name:'manutencao-editar', params: { id: slotProps.data.id }})"
    />
  </template>
</Column>

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
    const response = await axios.get(`${URL}/manutencao/listar-todas`);
    manutencoes.value = response.data;
  } catch (error) {
    console.error("Erro ao carregar manutenções:", error);
  }
};

const formatDate = (value) => {
    if (!value) return "N/A";
    if (Array.isArray(value)) {
        return `${String(value[2]).padStart(2, '0')}/${String(value[1]).padStart(2, '0')}/${value[0]}`;
    }
    if (typeof value === 'string' && value.includes('-')) {
        const [year, month, day] = value.split('-');
        return `${day}/${month}/${year}`;
    }
    return value;
};

onMounted(carregarManutencoes);
</script>

<style scoped>
.card {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  background: var(--bg-card);
  box-shadow: var(--shadow);
  border-radius: 12px;
  color: var(--text-main);
}

.page-title {
  text-align: center;
  margin-bottom: 0.5rem;
  color: var(--text-main);
  font-weight: bold;
}

.subtitle {
  text-align: center;
  color: var(--text-muted);
  margin-bottom: 2rem;
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
  background: var(--bg-input) !important;
  border: 1px solid var(--border-input) !important;
  color: var(--text-table) !important;
  border-radius: 6px !important;
}

.search-input::placeholder {
  color: var(--text-muted) !important;
}

:deep(.p-datatable-custom) {
  background: var(--bg-table) !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  font-weight: bold;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr > td) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: var(--bg-hover) !important;
}

:deep(.p-paginator) {
  background: var(--bg-table) !important;
  border: none !important;
  padding: 1rem;
}

:deep(.p-paginator .p-paginator-element) {
  color: var(--text-table) !important;
}

.status-badge {
  padding: 0.25rem 0.6rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  color: #fff;
}

.status-badge.preventiva  { background: var(--status-success); }
.status-badge.emergencial { background: var(--status-danger); }
.status-badge.pendente    { background: var(--status-warning); }
.status-badge.executada   { background: var(--status-info); }

.placeholder-text {
  color: var(--text-muted);
  font-style: italic;
  text-align: center;
  padding: 20px;
}
</style>