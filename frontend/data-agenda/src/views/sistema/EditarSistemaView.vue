<template>
  <div class="card">
    <h2>Edição de Sistema</h2>
    <form @submit.prevent="salvarAlteracoes">
      <div class="p-fluid p-formgrid p-grid">

        <!-- ID -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="id">ID</label>
          <InputText id="id" v-model="sistema.id" :disabled="!editando" type="number" />
        </div>

        <!-- Nome do Cliente -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="nomeCliente">Nome do Cliente</label>
          <InputText id="nomeCliente" v-model="sistema.nomeCliente" :disabled="!editando" />
        </div>

        <!-- Nome do Sistema -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="nome">Nome do Sistema</label>
          <InputText id="nome" v-model="sistema.nome" :disabled="!editando" />
        </div>

        <!-- Tipo de Sistema -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="tipoSistema">Tipo de Sistema</label>
          <InputText id="tipoSistema" v-model="sistema.tipoSistema" :disabled="!editando" />
        </div>

        <!-- Data Próxima Manutenção -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="dataProximaManutencao">Próxima Manutenção</label>
          <InputText id="dataProximaManutencao" v-model="sistema.dataProximaManutencao" type="date" :disabled="!editando" />
        </div>

        <!-- Disponibilidade -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="isDisponivel">Disponível</label>
          <InputText id="isDisponivel" v-model="sistema.isDisponivel" :disabled="!editando" />
        </div>

        <!-- Data Cadastro -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="dataCadastro">Data de Cadastro</label>
          <InputText id="dataCadastro" v-model="sistema.dataCadastro" type="date" :disabled="!editando" />
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="mensagem-erro">
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button
            v-if="!editando"
            label="Editar"
            icon="pi pi-pencil"
            type="button"
            class="p-button-warning"
            @click="ativarEdicao"
          />
          <Button
            v-if="editando"
            label="Salvar Alterações"
            icon="pi pi-check"
            type="submit"
            class="p-button-success"
            :loading="loading"
            :disabled="loading"
          />
          <Button
            v-if="editando"
            label="Cancelar"
            icon="pi pi-times"
            type="button"
            class="p-button-secondary"
            @click="cancelarEdicao"
          />
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import InputText from "primevue/inputtext";
import Button from "primevue/button";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: { "Content-Type": "application/json" },
});

export default {
  name: "EdicaoSistema",
  components: { InputText, Button },
  setup() {
    const loading = ref(false);
    const erro = ref("");
    const editando = ref(false);

    // Dados originais de exemplo
    const sistemaOriginal = {
      id: 0,
      nome: "string",
      tipoSistema: "BALAO",
      dataProximaManutencao: "2026-05-03",
      isDisponivel: true,
      dataCadastro: "2026-05-03",
      nomeCliente: "Cliente Exemplo",
    };

    const sistema = ref({ ...sistemaOriginal });

    const ativarEdicao = () => {
      editando.value = true;
    };

    const cancelarEdicao = () => {
      editando.value = false;
      sistema.value = { ...sistemaOriginal };
    };

    const salvarAlteracoes = async () => {
      loading.value = true;
      erro.value = "";
      try {
        const response = await api.put(`/sistemas/${sistema.value.id}`, sistema.value);
        alert("Alterações salvas com sucesso!");
        editando.value = false;
      } catch (error) {
        erro.value = "Erro ao salvar alterações.";
      } finally {
        loading.value = false;
      }
    };

    return { sistema, loading, erro, editando, ativarEdicao, cancelarEdicao, salvarAlteracoes };
  },
};
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 12px;
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}

.full-width {
  width: 100%;
}

.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.horizontal-field label {
  width: 180px;
  min-width: 180px;
  font-weight: 600;
}

.horizontal-field input {
  flex: 1;
}

.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}

.mensagem-erro {
  width: 100%;
  padding: 0.75rem 1rem;
  margin-bottom: 1rem;
  background-color: #fee2e2;
  color: #991b1b;
  border: 1px solid #fca5a5;
  border-radius: 6px;
  font-size: 0.9rem;
}
</style>
