<template>
  <div class="card">
    <h2 class="page-title">Lista de Sistemas</h2>
    <DataTable :value="sistemas" responsiveLayout="scroll" class="p-datatable-custom" paginator :rows="10">
      <Column field="nome" header="Nome" sortable />
      <Column field="tipoSistema" header="Tipo de Sistema" sortable />
      <Column field="cliente.nome" header="Cliente" sortable />
    <Column field="dataCadastro" header="Data" sortable>
            <template #body="slotProps">
                {{ formatarData(slotProps.data.dataCadastro) }}
            </template>
        </Column>
<Column 
  field="dataAgendada" 
  header="Data Agendamento" 
  :body="(rowData) => formatarData(rowData.dataAgendada)" 
  sortable 
/>
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button
              icon="pi pi-pencil"
              class="p-button-rounded p-button-warning p-button-sm"
              @click="$router.push({name:'sistema-editar', params: { id: slotProps.data.id }})" 
            />
            <Button
              icon="pi pi-trash"
              class="p-button-rounded p-button-danger p-button-sm"
              @click="excluirSistema(slotProps.data)"
            />
          </div>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";

const URL = import.meta.env.VITE_API_URL;
const sistemas = ref([]);

const carregarSistemas = async () => {
  try {
    const response = await axios.get(`${URL}/sistema/buscartodos`);
    sistemas.value = response.data;
  } catch (error) {
    console.error("Erro ao buscar sistemas:", error);
  }
};

const editarSistema = (sistema) => {
  alert(`Editar sistema: ${sistema.nome}`);
};

const excluirSistema = (sistema) => {
  if (confirm(`Deseja excluir o sistema ${sistema.nome}?`)) {
    // Implementar exclusão real se necessário
    alert("Implementar exclusão");
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

onMounted(carregarSistemas);
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
  color:#2c3e50;
  font-weight: bold;
}

:deep(.p-datatable-custom) {
  background: var(--bg-table) !important;
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
  background: #0f0f0f!important;
}

:deep(.p-paginator) {
    background: #0f0f0f!important;
    border: none !important;
    padding: 1rem;
}

.acoes {
  display: flex;
  gap: 0.5rem;
  justify-content: center;
}
</style>
