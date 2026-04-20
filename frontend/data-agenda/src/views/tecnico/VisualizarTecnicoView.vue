<template>
  <div class="card">
    <h2>Visualização de Técnicos</h2>
    <DataTable :value="tecnicos" responsiveLayout="scroll" class="p-datatable-custom">
      <Column field="nome" header="Nome" sortable></Column>
      <Column field="email" header="Email" sortable></Column>
      <Column field="telefone" header="Telefone"></Column>
      <Column field="nivel" header="Nível"></Column>
      
      
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
import { ref, onMounted } from "vue";
import axios from "axios";
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
    const tecnicos = ref([]);

    const URL = import.meta.env.VITE_API_URL;

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
      try {
        await axios.delete(`${URL}/tecnico/${tecnico.id}`);
        tecnicos.value = tecnicos.value.filter(t => t.id !== tecnico.id);
        alert(`Técnico ${tecnico.nome} excluído!`);
      } catch (error) {
        console.error("Erro ao excluir técnico:", error);
      }
    };

    onMounted(() => {
      carregarTecnicos  ();
    });

    return { tecnicos, editarTecnico, excluirTecnico };
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
