<template>
  <div class="card">
    <h2>Detalhes do Sistema</h2>
    <form @submit.prevent="salvarAlteracoes">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome do Sistema + Tipo de Sistema -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="nomeSistema">Nome do Sistema</label>
            <InputText id="nomeSistema" v-model="sistema.nome" disabled class="full-width" />
          </div>
          <div class="p-field horizontal-field">
            <label for="tipoSistema">Tipo de Sistema</label>
            <InputText id="tipoSistema" v-model="sistema.tipoSistema" disabled class="full-width" />
          </div>
        </div>

        <!-- Cliente -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="cliente">Cliente</label>
          <InputText id="cliente" v-model="sistema.cliente.nome" disabled class="full-width" />
        </div>

        <!-- Localidade -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="localidade">Localidade</label>
          <InputText id="localidade" v-model="sistema.cliente.localidade" disabled class="full-width" />
        </div>

        <!-- Datas: Próxima Manutenção + Cadastro -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="dataUltimaManutencao">Próxima Manutenção</label>
            <DatePicker 
              id="dataUltimaManutencao" 
              v-model="sistema.dataProximaManutencao"
              dateFormat="dd/mm/yy"
              placeholder="dd/mm/aaaa"
              showIcon
              class="full-width"
              disabled
            />
          </div>
          <div class="p-field horizontal-field">
            <label for="dataCadastro">Data de Cadastro</label>
            <DatePicker 
              id="dataCadastro" 
              v-model="sistema.dataCadastro"
              dateFormat="dd/mm/yy"
              placeholder="dd/mm/aaaa"
              showIcon
              class="full-width"
              disabled
            />
          </div>
        </div>

        <!-- Intervalo de Manutenção -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="intervaloManutencao">Intervalo de Manutenção (dias)</label>
          <InputText 
            id="intervaloManutencao" 
            v-model="sistema.intervaloManutencao" 
            :disabled="!editando" 
            class="full-width" 
          />
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="mensagem-erro">
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button
            v-if="!editando"
            label="Editar Intervalo"
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
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import InputText from "primevue/inputtext";
import DatePicker from "primevue/datepicker";
import Button from "primevue/button";

export default {
  name: "DetalhesSistema",
  components: { InputText, DatePicker, Button },
  setup() {
    const URL = import.meta.env.VITE_API_URL;
    const route = useRoute();

    const sistemaOriginal = ref(null);
    const sistema = ref({
      id: null,
      nome: "",
      tipoSistema: "",
      dataProximaManutencao: null,
      dataCadastro: null,
      intervaloManutencao: null,
      cliente: {
        id: null,
        nome: "",
        localidade: "",
      },
    });

    const editando = ref(false);
    const loading = ref(false);
    const erro = ref("");

    // Carregar sistema do banco ao montar
    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/sistema/${route.params.id}`);
        sistema.value = response.data;
        sistemaOriginal.value = { ...response.data };
      } catch (error) {
        console.error("Erro ao carregar sistema:", error);
        erro.value = error.response?.data?.message || "Erro ao carregar dados do sistema.";
      }
    });

    const ativarEdicao = () => {
      editando.value = true;
    };

    const cancelarEdicao = () => {
      editando.value = false;
      sistema.value = { ...sistemaOriginal.value };
      erro.value = "";
    };

    const salvarAlteracoes = async () => {
      loading.value = true;
      erro.value = "";
      try {
        await axios.patch(`${URL}/sistema/editar/${sistema.value.id}`, {
          intervaloManutencao: sistema.value.intervaloManutencao,
        });
        alert("Alterações salvas com sucesso!");
        editando.value = false;
        sistemaOriginal.value = { ...sistema.value };
      } catch (error) {
        console.error("Erro ao salvar alterações:", error);
        erro.value = error.response?.data?.message || "Erro ao salvar alterações.";
      } finally {
        loading.value = false;
      }
    };

    return { sistema, editando, loading, erro, ativarEdicao, cancelarEdicao, salvarAlteracoes };
  },
};
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: var(--bg-card);
  box-shadow: var(--shadow);
  border-radius: 12px;
  color: var(--text-main);
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: var(--text-main);
}

.row-pair {
  display: flex;
  gap: 1.5rem;
  width: 100%;
  margin-bottom: 1.25rem;
}

.row-pair .p-field {
  flex: 1;
}

.full-width {
  width: 100%;
}

.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.25rem;
}

.horizontal-field label {
  width: 160px;
  min-width: 160px;
  font-weight: 600;
}

.horizontal-field input,
.horizontal-field .p-calendar {
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
  background-color: var(--error-bg);
  color: var(--error-text);
  border: 1px solid var(--error-border);
  border-radius: 6px;
  font-size: 0.9rem;
}
</style>
