<template>
  <div class="registration-container">
    <div class="card registration-card">
      <h2 class="form-title">Cadastro de Manutenção</h2>
      <form @submit.prevent="salvarManutencao">
        <div class="p-fluid">

          <!-- Cliente -->
          <div class="form-row">
            <label for="cliente" class="form-label">Cliente</label>
            <div class="form-input">
              <AutoComplete 
                id="cliente" 
                v-model="selectedCliente" 
                :suggestions="filteredClientes" 
                @complete="buscarClientes" 
                optionLabel="nome"
                forceSelection
                placeholder="Digite para procurar..." 
                class="full-width" />
            </div>
          </div>

          <!-- Sistemas -->
          <div class="form-row">
            <label for="sistema" class="form-label">Sistemas</label>
            <div class="form-input">
              <Select 
                id="sistema" 
                v-model="manutencao.sistemaId" 
                :options="filteredSistemas" 
                optionLabel="nome" 
                optionValue="id" 
                placeholder="Selecione o sistema" 
                class="full-width"
                :disabled="!selectedCliente" />
            </div>
          </div>

          <!-- Técnicos -->
          <div class="form-row">
            <label for="tecnico" class="form-label">Técnicos</label>
            <div class="form-input">
              <Select 
                id="tecnico" 
                v-model="manutencao.tecnicoId" 
                :options="tecnicos" 
                optionLabel="nome" 
                optionValue="id" 
                placeholder="Selecione o técnico" 
                class="full-width" />
            </div>
          </div>

          <!-- Data -->
          <div class="form-row">
            <label for="data" class="form-label">Data</label>
            <div class="form-input">
              <DatePicker 
                id="data" 
                v-model="manutencao.dataAgendada" 
                dateFormat="dd/mm/yy"
                placeholder="dd/mm/aaaa"
                showIcon
                class="full-width" />
            </div>
          </div>

          <!-- Tipo de Manutenção -->
          <div class="form-row">
            <label for="tipo" class="form-label">Tipo</label>
            <div class="form-input">
              <Select 
                id="tipo" 
                v-model="manutencao.tipoManutencao" 
                :options="tipoOptions" 
                optionLabel="label" 
                optionValue="value" 
                placeholder="Selecione o tipo" 
                class="full-width" />
            </div>
          </div>

          <!-- Descrição -->
          <div class="form-row">
            <label for="descricao" class="form-label">Descrição</label>
            <div class="form-input">
              <Textarea 
                id="descricao" 
                v-model="manutencao.descricao" 
                rows="4" 
                autoResize 
                class="full-width textarea-custom" />
            </div>
          </div>

          <!-- Status -->
          <div class="form-row">
            <label for="status" class="form-label">Status</label>
            <div class="form-input short-dropdown">
              <Select 
                id="status" 
                v-model="manutencao.statusManutencao" 
                :options="statusOptions" 
                optionLabel="label" 
                optionValue="value" 
                placeholder="Selecione" />
            </div>
          </div>

          <!-- Botões -->
          <div class="botoes-container">
            <Button label="Salvar" icon="pi pi-check" type="submit" class="p-button-success" />
            <Button label="Limpar" icon="pi pi-refresh" type="button" class="p-button-secondary" @click="limparFormulario" />
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import Textarea from "primevue/textarea";
import Select from "primevue/select";
import AutoComplete from "primevue/autocomplete";
import Button from "primevue/button";
import DatePicker from "primevue/datepicker";

export default {
  name: "CadastroManutencao",
  components: { Textarea, Select, AutoComplete, Button, DatePicker },
  setup() {
    const URL = import.meta.env.VITE_API_URL;

    const manutencao = ref({
      descricao: "",
      tecnicoId: null,
      dataAgendada: null,
      tipoManutencao: "PREVENTIVA",
      statusManutencao: "PENDENTE",
      sistemaId: null,
    });

    const selectedCliente = ref(null);
    const allClientes = ref([]);
    const filteredClientes = ref([]);
    const allSistemas = ref([]);
    const filteredSistemas = ref([]);
    const tecnicos = ref([]);

    const tipoOptions = [
      { label: "Preventiva", value: "PREVENTIVA" },
      { label: "Emergencial", value: "EMERGENCIAL" },
    ];

    const statusOptions = [
      { label: "Pendente", value: "PENDENTE" },
      { label: "Executada", value: "EXECUTADA" },
    ];

    const carregarDados = async () => {
      try {
        const [resClientes, resSistemas, resTecnicos] = await Promise.all([
          axios.get(`${URL}/clientes/buscartodos`),
          axios.get(`${URL}/sistema/buscartodos`),
          axios.get(`${URL}/tecnico/buscartodos`)
        ]);
        allClientes.value = resClientes.data;
        allSistemas.value = resSistemas.data;
        tecnicos.value = resTecnicos.data;
      } catch (error) {
        console.error("Erro ao carregar dados do banco:", error);
      }
    };

    onMounted(carregarDados);

    const buscarClientes = (event) => {
      const query = event.query.toLowerCase();
      filteredClientes.value = allClientes.value.filter(c => 
        c.nome.toLowerCase().includes(query)
      );
    };

    // Filtra sistemas quando o cliente muda
    watch(selectedCliente, (newVal) => {
      manutencao.value.sistemaId = null;
      if (newVal && newVal.id) {
        filteredSistemas.value = allSistemas.value.filter(s => 
          s.cliente && s.cliente.id === newVal.id
        );
      } else {
        filteredSistemas.value = [];
      }
    });

    const salvarManutencao = async () => {
      try {
        // Formatar data para YYYY-MM-DD
        const dataFormatada = manutencao.value.dataAgendada 
          ? manutencao.value.dataAgendada.toISOString().split('T')[0]
          : null;

        const payload = {
          ...manutencao.value,
          dataAgendada: dataFormatada
        };

        const response = await axios.post(`${URL}/manutencoes`, payload);
        alert(response.data || "Manutenção cadastrada com sucesso!");
        limparFormulario();
      } catch (error) {
        console.error("Erro ao salvar manutenção:", error);
        alert("Erro ao cadastrar manutenção. Verifique os dados.");
      }
    };

    const limparFormulario = () => {
      manutencao.value = {
        descricao: "",
        tecnicoId: null,
        dataAgendada: null,
        tipoManutencao: "PREVENTIVA",
        statusManutencao: "PENDENTE",
        sistemaId: null,
      };
      selectedCliente.value = null;
    };

    return { 
      manutencao, 
      selectedCliente,
      salvarManutencao, 
      limparFormulario, 
      statusOptions, 
      tipoOptions,
      filteredClientes, 
      filteredSistemas, 
      tecnicos, 
      buscarClientes 
    };
  },
};
</script>

<style scoped>
.registration-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: 100vh;
  background-color: var(--bg-app);
  padding: 2rem;
}

.registration-card {
  width: 100%;
  max-width: 900px;
  background-color: var(--bg-card) !important;
  border: none;
  padding: 3rem;
  border-radius: 8px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.5);
}

.form-title {
  text-align: center;
  color: var(--primary-color);
  font-size: 1.8rem;
  font-weight: bold;
  margin-bottom: 3rem;
}

.form-row {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
}

.form-label {
  width: 150px;
  color: var(--text-muted);
  font-size: 1rem;
  text-align: left;
}

.form-input {
  flex: 1;
}

.short-dropdown {
  max-width: 200px;
}

.full-width {
  width: 100%;
}

:deep(.p-inputtext),
:deep(.p-select),
:deep(.p-autocomplete-input),
:deep(.p-datepicker-input),
:deep(.p-textarea) {
  background-color: var(--bg-input) !important;
  border: none !important;
  border-radius: 6px !important;
  color: var(--text-input) !important;
  padding: 0.75rem !important;
}

:deep(.p-select-label.p-placeholder) {
  color: #999 !important;
}

:deep(.p-multiselect), :deep(.p-select) {
  background: var(--bg-input) !important;
}

.textarea-custom {
  min-height: 120px;
}

.botoes-container {
  display: flex;
  justify-content: center;
  gap: 1.5rem;
  margin-top: 3rem;
}

.p-button-success {
  background-color: var(--status-success) !important;
  border: none !important;
  padding: 0.75rem 2.5rem !important;
}

.p-button-secondary {
  background-color: #424242 !important;
  border: none !important;
  padding: 0.75rem 2.5rem !important;
}

/* Ajustes para o PrimeVue 4 */
:deep(.p-autocomplete) {
  display: flex;
}

:deep(.p-autocomplete-overlay),
:deep(.p-autocomplete-panel) {
  background-color: var(--bg-card) !important;
  color: var(--text-main) !important;
  border: 1px solid var(--border-color) !important;
  box-shadow: 0 4px 12px rgba(0,0,0,0.5) !important;
}

:deep(.p-autocomplete-option) {
  color: var(--text-main) !important;
  padding: 0.75rem 1rem !important;
}

:deep(.p-autocomplete-option:hover),
:deep(.p-autocomplete-option.p-focus) {
  background-color: var(--border-color) !important;
  color: var(--text-main) !important;
}
</style>
