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
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 12px;
}

.page-title {
  text-align: center;
  margin-bottom: 0.5rem;
  color:#2c3e50;
  font-weight: bold;
}

.subtitle {
  text-align: center;
  color: #aaaaaa;
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
  color: #aaaaaa;
}

.search-input {
  width: 100%;
  padding-left: 2.5rem !important;
  background: #1a1a1a !important;
  border: 1px solid #333333 !important;
  color: #ffffff !important;
  border-radius: 6px !important;
}

.search-input::placeholder {
  color: #888888 !important;
}

:deep(.p-datatable-custom) {
  background: #0f0f0f !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: #0f0f0f !important;
  color: #ffffff !important;
  font-weight: bold;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr > td) {
  background: #0f0f0f !important;
  color: #ffffff !important;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: #0f0f0f !important;
}

:deep(.p-paginator) {
  background: #0f0f0f !important;
  border: none !important;
  padding: 1rem;
}

:deep(.p-paginator .p-paginator-element) {
  color: #ffffff !important;
}

.status-badge {
  padding: 0.25rem 0.6rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  color: #fff;
}

.status-badge.preventiva  { background: #27ae60; }
.status-badge.emergencial { background: #e74c3c; }
.status-badge.pendente    { background: #f39c12; }
.status-badge.executada   { background: #2980b9; }

.placeholder-text {
  color: #aaaaaa;
  font-style: italic;
  text-align: center;
  padding: 20px;
}
</style>