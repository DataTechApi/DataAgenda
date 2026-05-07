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
        

        
        <Column field="clienteNome" header="Cliente" sortable></Column>
        <Column field="sistemaNome" header="Sistema" sortable></Column>
       
        
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
        <Column field="dataAgendada" header="Data" sortable>
            <template #body="slotProps">
                {{ formatarData(slotProps.data.dataAgendada) }}
            </template>
        </Column>
        

        <!-- Nova coluna de ações -->
        <Column header="Ações">
          <template #body="slotProps">
            <Button 
              icon="pi pi-eye" 
              class="p-button-rounded p-button-info p-button-sm mr-2" 
              @click="$router.push({name:'atendimento-finalizar', params: { id: slotProps.data.id }})" 
            />
            <Button 
              icon="pi pi-map" 
              class="p-button-rounded p-button-success p-button-sm" 
              @click="showMap(slotProps.data)"
              :disabled="!slotProps.data.clienteLatitude || !slotProps.data.clienteLongitude"
            />
          </template>
        </Column>

        <template #empty>
            <p class="placeholder-text">Nenhuma manutenção encontrada.</p>
        </template>
      </DataTable>
    </div>
  </div>

  <Dialog v-model:visible="mapDialogVisible" modal header="Localização do Cliente" :style="{ width: '75vw' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
    <MapView 
      v-if="selectedMaintenance"
      :destination="{ lat: selectedMaintenance.clienteLatitude, lng: selectedMaintenance.clienteLongitude }"
    />
  </Dialog>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import { FilterMatchMode } from '@primevue/core/api';

import MapView from '@/components/MapView.vue';

const URL = import.meta.env.VITE_API_URL;
const manutencoes = ref([]);
const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS }
});

const selectedMaintenance = ref(null);
const mapDialogVisible = ref(false);

let usuario = sessionStorage.getItem('usuario');
let tecnico = JSON.parse(usuario).id;
const tecnicoId = ref(tecnico);

const carregarManutencoes = async () => {
  try {
    const response = await axios.get(`${URL}/manutencao/listar-tecnico/${tecnicoId.value}`);
    manutencoes.value = response.data;
  } catch (error) {
    console.error("Erro ao carregar manutenções:", error);
  }
};

const showMap = (maintenance) => {
  if (maintenance.clienteLatitude !== null && maintenance.clienteLongitude !== null) {
    selectedMaintenance.value = maintenance;
    mapDialogVisible.value = true;
  } else {
    console.warn("Sir, client coordinates are not available for this maintenance.");
  }
};

function formatarData(valor) {
  if (!valor) return "";
  const data = new Date(valor);
  const dia = String(data.getDate()).padStart(2, "0");
  const mes = String(data.getMonth() + 1).padStart(2, "0");
  const ano = data.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

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
