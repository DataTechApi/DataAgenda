<template>
  <div class="card">
    <h2>Lista de Sistemas</h2>
    <DataTable :value="sistemas" responsiveLayout="scroll" class="tabela-sistemas">
      <Column field="nome" header="Nome" />
      <Column field="tipoSistema" header="Tipo de Sistema" />
      <Column field="cliente.nome" header="Cliente" />
      <Column field="dataCadastro" header="Data de Cadastro" :body="formatarData" />

      <!-- Coluna de Ações usando template -->
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button
              icon="pi pi-pencil"
              class="p-button-rounded p-button-warning p-button-sm"
              @click="editarSistema(slotProps.data)"
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

<script>
import axios from "axios";
import { ref, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";


export default {
  name: "ListaSistemas",
  components: {
    DataTable,
    Column,
    Button,
  },
  setup() {
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
    alert(`Excluir sistema: ${sistema.nome}`);
  };

  const formatarData = (rowData) => {
    if (!rowData.dataCadastro) return "";
    const data = new Date(rowData.dataCadastro);
    const dia = String(data.getDate()).padStart(2, "0");
    const mes = String(data.getMonth() + 1).padStart(2, "0");
    const ano = data.getFullYear();
    return `${dia}${mes}${ano}`;
  };

  onMounted(() => {
    carregarSistemas();
  });

  return { sistemas, editarSistema, excluirSistema, carregarSistemas, formatarData };
}

};
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0,0,0,0.5);
  border-radius: 12px;
  color: #f5f5f5;
}
h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}
.tabela-sistemas {
  background: #1c1c1c;
  border-radius: 8px;
  overflow: hidden;
}
:deep(.p-datatable-thead > tr > th) {
  background: #2c2c2c;
  color: #00ffcc;
  font-weight: 600;
}
:deep(.p-datatable-tbody > tr > td) {
  background: #1c1c1c;
  color: #f5f5f5;
}
.acoes {
  display: flex;
  gap: 0.5rem;
  justify-content: center;
}
</style>
