<template>
  <div class="card">
    <h2>Visualização de Clientes</h2>
    <DataTable :value="clientes" responsiveLayout="scroll" class="p-datatable-custom">
      <Column field="nome" header="Nome" sortable></Column>
      <Column field="cnpj" header="CNPJ" sortable></Column>
      <Column field="localidade" header="Localidade"></Column>
      <Column field="responsavel" header="Responsável"></Column>
      <Column field="telefone" header="Telefone"></Column>
      
      <!-- Ações -->
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning p-button-sm"
              @click="editarCliente(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-danger p-button-sm"
              @click="excluirCliente(slotProps.data)" />
          </div>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script>
import { ref } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";

export default {
  name: "VisualizacaoClientes",
  components: {
    DataTable,
    Column,
    Button,
  },
  setup() {
    const clientes = ref([
      {
        nome: "Empresa Alpha",
        cnpj: "12.345.678/0001-99",
        localidade: "São Paulo/SP",
        responsavel: "Maria Silva",
        telefone: "(11) 98765-4321",
      },
      {
        nome: "Beta Solutions",
        cnpj: "98.765.432/0001-11",
        localidade: "Rio de Janeiro/RJ",
        responsavel: "João Souza",
        telefone: "(21) 91234-5678",
      },
    ]);

    const editarCliente = (cliente) => {
      alert(`Editar cliente: ${cliente.nome}`);
    };

    const excluirCliente = (cliente) => {
      clientes.value = clientes.value.filter(c => c !== cliente);
      alert(`Cliente ${cliente.nome} excluído!`);
    };

    return { clientes, editarCliente, excluirCliente };
  },
};
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  border-radius: 12px;
}
h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}
.p-datatable-custom {
  background: #1c1c1c;
  border-radius: 12px;
  overflow: hidden;
}
.p-datatable-custom .p-datatable-thead > tr > th {
  background: #2c3e50;
  color: #fff;
  font-weight: bold;
  text-align: center;
}
.p-datatable-custom .p-datatable-tbody > tr > td {
  color: #e0e0e0;
  text-align: center;
}
.acoes {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
</style>
