<template>
  <div class="card">
    <h2 class="page-title">Visualização de Técnicos</h2>
    <DataTable :value="tecnicos" responsiveLayout="scroll" class="p-datatable-custom" paginator :rows="10">
      <Column field="nome" header="Nome" sortable></Column>
      <Column field="email" header="Email" sortable></Column>
      <Column field="telefone" header="Telefone"></Column>
      <Column field="nivel" header="Nível"></Column>
      
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning p-button-sm"
              @click="$router.push({name:'tecnico-editar', params: { id: slotProps.data.id }})" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-danger p-button-sm"
              @click="$router.push({name:'tecnico-editar', params: { id: slotProps.data.id }})" />
          </div>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";

const URL = import.meta.env.VITE_API_URL;
const tecnicos = ref([]);

const carregarTecnicos = async () => {
  try {
    const response = await axios.get(`${URL}/tecnico/buscartodos`);
    tecnicos.value = response.data;
  } catch (error) {
    console.error("Erro ao buscar tecnicos:", error);
  }
};

const editarTecnico = (tecnico) => {
  alert(`Editar técnico: ${tecnico.nome}`);
};

const excluirTecnico = async (tecnico) => {
  if (confirm(`Deseja excluir o técnico ${tecnico.nome}?`)) {
    try {
      await axios.delete(`${URL}/tecnico/${tecnico.id}`);
      tecnicos.value = tecnicos.value.filter(t => t.id !== tecnico.id);
    } catch (error) {
      console.error("Erro ao excluir técnico:", error);
    }
  }
};

onMounted(carregarTecnicos);
</script>

<style scoped>
.card {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  border-radius: 12px;
}

.page-title {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
  font-weight: bold;
}

:deep(.p-datatable-custom) {
  background: var(--bg-table) !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: #0f0f0f!important;
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
  ;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: #0f0f0f !important;
}

:deep(.p-paginator) {
    background: #0f0f0f!important;
    border: none !important;
    padding: 1rem;
}

.acoes {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
</style>
