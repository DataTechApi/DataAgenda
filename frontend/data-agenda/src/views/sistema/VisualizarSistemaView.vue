<template>
  <div class="card">
    <h2>Lista de Sistemas</h2>
    <DataTable :value="sistemas" responsiveLayout="scroll" class="tabela-sistemas">
      <Column field="nome" header="Nome" />
      <Column field="tipoSistema" header="Tipo de Sistema" />
      <Column field="status" header="Status" />
      <Column field="dataCadastro" header="Data de Cadastro" />

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
import { ref } from "vue";
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
    const sistemas = ref([
      {
        nome: "Sistema de Controle",
        numeroSerie: "ABC123456",
        status: "Ativo",
        dataCadastro: "14/04/2026",
      },
      {
        nome: "Sistema de Monitoramento",
        numeroSerie: "XYZ987654",
        status: "Inativo",
        dataCadastro: "10/04/2026",
      },
    ]);

    const editarSistema = (sistema) => {
      alert(`Editar sistema: ${sistema.nome}`);
    };

    const excluirSistema = (sistema) => {
      alert(`Excluir sistema: ${sistema.nome}`);
    };

    return { sistemas, editarSistema, excluirSistema };
  },
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
