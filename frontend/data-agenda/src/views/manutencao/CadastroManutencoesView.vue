<template>
  <div class="card">
    <h2>Cadastro de Manutenção</h2>
    <form @submit.prevent="salvarManutencao">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Cliente -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="cliente">Cliente</label>
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

        <!-- Sistemas + Técnicos -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="sistema">Sistemas</label>
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
          <div class="p-field horizontal-field">
            <label for="tecnico">Técnicos</label>
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

        <!-- Data + Tipo -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="data">Data</label>
            <DatePicker 
              id="data" 
              v-model="manutencao.dataAgendada" 
              dateFormat="dd/mm/yy"
              placeholder="dd/mm/aaaa"
              showIcon
              class="full-width" />
          </div>
          <div class="p-field horizontal-field">
            <label for="tipo">Tipo</label>
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
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="descricao">Descrição</label>
          <Textarea 
            id="descricao" 
            v-model="manutencao.descricao" 
            rows="4" 
            autoResize 
            class="full-width textarea-custom" />
        </div>

        <!-- Status -->
        <div class="p-field horizontal-field short-dropdown">
          <label for="status">Status</label>
          <Select 
            id="status" 
            v-model="manutencao.statusManutencao" 
            :options="statusOptions" 
            optionLabel="label" 
            optionValue="value" 
            placeholder="Selecione" />
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Salvar" icon="pi pi-check" type="submit" class="p-button-success" />
          <Button label="Limpar" icon="pi pi-refresh" type="button" class="p-button-secondary" @click="limparFormulario" />
        </div>

      </div>
    </form>
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
        const dataFormatada = manutencao.value.dataAgendada
          ? manutencao.value.dataAgendada.toISOString().split('T')[0]
          : null;

        const payload = { ...manutencao.value, dataAgendada: dataFormatada };
        const response = await axios.post(`${URL}/manutencao`, payload);
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

.row-pair {
  display: flex;
  gap: 1.5rem;
  width: 100%;
  margin-bottom: 0;
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
  margin-bottom: 1.5rem;
}

.horizontal-field label {
  width: 140px;
  min-width: 140px;
  font-weight: 600;
}

.horizontal-field input,
.horizontal-field .p-inputmask,
.horizontal-field .p-dropdown,
.horizontal-field .p-autocomplete,
.horizontal-field .p-calendar,
.horizontal-field .p-textarea {
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

/* Ajuste para textarea */
.textarea-custom {
  resize: vertical;
  min-height: 100px;
}

/* Dropdown mais curto (ex: status) */
.short-dropdown {
  max-width: 300px;
}
</style>
