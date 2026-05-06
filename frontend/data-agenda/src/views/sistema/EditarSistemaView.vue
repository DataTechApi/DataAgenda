<template>
  <div class="card">
    <h2>Detalhes do Sistema</h2>
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

      <!-- Datas: Última Manutenção + Cadastro -->
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

    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import InputText from "primevue/inputtext";
import DatePicker from "primevue/datepicker";

export default {
  name: "DetalhesSistema",
  components: { InputText, DatePicker },
  setup() {
    const URL = import.meta.env.VITE_API_URL;
    const route = useRoute();

    const sistema = ref({
      id: null,
      nome: "",
      tipoSistema: "",
      dataProximaManutencao: null,
      dataCadastro: null,
      cliente: {
        id: null,
        nome: "",
        localidade: "",
      },
    });

    // Carregar sistema do banco ao montar
    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/sistema/${route.params.id}`);
        sistema.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar sistema:", error);
        alert("Erro ao carregar dados do sistema.");
      }
    });

    return { sistema };
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
  gap: 1.5rem;            /* Espaço horizontal entre os campos */
  width: 100%;
  margin-bottom: 1.25rem; /* Espaço vertical entre linhas */
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
  gap: 1rem;              /* Espaço entre label e input */
  margin-bottom: 1.25rem; /* Espaço vertical entre cada campo */
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
</style>
