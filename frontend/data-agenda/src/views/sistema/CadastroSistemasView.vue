<template>
  <div class="card">
    <h2>Cadastro de Sistema</h2>
    <form @submit.prevent="salvarSistema">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Tipo de Sistema -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="tipoSistema">Tipo de Sistema</label>
          <Dropdown 
            id="tipoSistema" 
            v-model="sistema.tipoSistema" 
            :options="tipoSistema" 
            optionLabel="label" 
            optionValue="value" 
            placeholder="Selecione o tipo" 
          />
        </div>

        <!-- Cliente -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="cliente">Cliente</label>
          <Dropdown 
            id="cliente" 
            v-model="sistema.clienteId" 
            :options="clientes" 
            optionLabel="nome" 
            optionValue="id" 
            placeholder="Selecione o cliente" 
          />
        </div>

        <!-- Técnico -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="tecnico">Técnico</label>
          <Dropdown 
            id="tecnico" 
            v-model="sistema.tecnicoId" 
            :options="tecnicos" 
            optionLabel="nome" 
            optionValue="id" 
            placeholder="Selecione o técnico" 
          />
        </div>

        <!-- Intervalo de Manutenção -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="intervaloManutencao">Intervalo de Manutenção (em dias)</label>
          <input 
            id="intervaloManutencao" 
            type="number" 
            v-model="sistema.intervaloManutencao" 
            placeholder="Ex: 30" 
            class="p-inputtext" 
          />
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="mensagem-erro">
          <i class="pi pi-exclamation-triangle"></i>
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Salvar" icon="pi pi-check" type="submit" class="p-button-success" :loading="loading" />
          <Button label="Limpar" icon="pi pi-refresh" type="button" class="p-button-secondary" @click="limparFormulario" />
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import Dropdown from "primevue/dropdown";
import Button from "primevue/button";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export default {
  name: "CadastroSistema",
  components: {
    Dropdown,
    Button,
  },
  setup() {
    const loading = ref(false);
    const erro = ref("");
    const URL = import.meta.env.VITE_API_URL;

    const sistema = ref({
      tipoSistema: "",
      clienteId: "",
      tecnicoId: "",          // novo campo
      intervaloManutencao: null
    });

    const tipoSistema = [
      { label: "Câmeras", value: "CAMERAS" },
      { label: "Balão", value: "BALAO" },
    ];

    const clientes = ref([]);
    const tecnicos = ref([]);

    const carregarClientes = async () => {
      try {
        const response = await api.get(`${URL}/clientes/buscartodos`);
        clientes.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar clientes:", error);
        erro.value = "Não foi possível carregar a lista de clientes.";
      }
    };

    const carregarTecnicos = async () => {
      try {
        const response = await api.get(`${URL}/tecnico/buscartodos`);
        tecnicos.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar técnicos:", error);
        erro.value = "Não foi possível carregar a lista de técnicos.";
      }
    };

    const limparFormulario = () => {
      sistema.value = { tipoSistema: "", clienteId: "", tecnicoId: "", intervaloManutencao: "" };
      erro.value = "";
    };

    const salvarSistema = async () => {
      erro.value = "";

      if (!sistema.value.tipoSistema) {
        erro.value = "Selecione o tipo de sistema.";
        return;
      }
      if (!sistema.value.clienteId) {
        erro.value = "Selecione um cliente.";
        return;
      }
      if (!sistema.value.tecnicoId) {
        erro.value = "Selecione um técnico.";
        return;
      }
      if (!sistema.value.intervaloManutencao || sistema.value.intervaloManutencao <= 0) {
        erro.value = "Informe um intervalo de manutenção válido.";
        return;
      }

      loading.value = true;
      try {
        const response = await api.post(`${URL}/sistema`, sistema.value);
        console.log("Resposta da API:", response.data);
        limparFormulario();
        alert("Sistema cadastrado com sucesso!");
      } catch (error) {
        console.error("Erro ao cadastrar sistema:", error);

        if (error.response) {
          erro.value = `Erro ${error.response.status}: ${error.response.data?.message || "Falha ao cadastrar sistema."}`;
        } else if (error.request) {
          erro.value = "Servidor não respondeu. Verifique se o backend está rodando.";
        } else {
          erro.value = "Erro inesperado. Tente novamente.";
        }
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      carregarClientes();
      carregarTecnicos();
    });

    return { sistema, salvarSistema, limparFormulario, tipoSistema, clientes, tecnicos, loading, erro };
  },
};
</script>

<style scoped>
.card {
  max-width: 900px;
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
.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}
.horizontal-field label {
  width: 180px;
  font-weight: 600;
  color: var(--text-main);
}
.horizontal-field input, .horizontal-field .p-dropdown {
  flex: 1;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}
.mensagem-erro {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  width: 100%;
  padding: 0.75rem 1rem;
  margin-bottom: 1rem;
  background-color: rgba(239, 68, 68, 0.1);
  color: var(--error-border);
  border: 1px solid rgba(239, 68, 68, 0.3);
  border-radius: 6px;
  font-size: 0.9rem;
}
</style>
