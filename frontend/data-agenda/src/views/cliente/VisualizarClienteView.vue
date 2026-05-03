<template>
  <div class="card">
    <h2 class="page-title">Visualização de Clientes</h2>
    <DataTable 
      :value="clientes" 
      responsiveLayout="scroll" 
      class="p-datatable-custom" 
      paginator 
      :rows="10"
    >
      <Column field="nome" header="Nome" sortable></Column>
      <Column field="cnpj" header="CNPJ" sortable></Column>
      <Column field="localidade" header="Localidade"></Column>
      <Column field="nomeResponsavel" header="Responsável"></Column>
      <Column field="telefoneResponsavel" header="Telefone"></Column>
      
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button 
              icon="pi pi-pencil" 
              class="p-button-rounded p-button-warning p-button-sm"
              @click="$router.push({name:'cliente-editar', params: { id: slotProps.data.id }})" 
            />
            <Button 
              icon="pi pi-trash" 
              class="p-button-rounded p-button-danger p-button-sm"
              @click="excluirCliente(slotProps.data)" 
            />
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
const clientes = ref([]);

const carregarClientes = async () => {
  try {
    const response = await axios.get(`${URL}/clientes/buscartodos`);
    clientes.value = response.data;
  } catch (error) {
    console.error("Erro ao buscar clientes:", error);
  }
};

const editarCliente = (cliente) => {
  alert(`Editar cliente: ${cliente.nome}`);
};

const excluirCliente = async (cliente) => {
  if (confirm(`Deseja excluir o cliente ${cliente.nome}?`)) {
    try {
      await axios.delete(`${URL}/clientes/${cliente.id}`);
      clientes.value = clientes.value.filter(c => c.id !== cliente.id);
    } catch (error) {
      console.error("Erro ao excluir cliente:", error);
    }
  }
};

onMounted(carregarClientes);
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f; /* igual ao cadastro */
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  border-radius: 12px;
}

.page-title {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50; /* igual ao cadastro */
  font-weight: bold;
}

:deep(.p-datatable-custom) {
  background: #ffffff !important;
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

.acoes {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
</style>
