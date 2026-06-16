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

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Salvar" icon="pi pi-check" type="submit" class="p-button-success" :loading="loading" />
          <Button label="Limpar" icon="pi pi-refresh" type="button" class="p-button-secondary" @click="limparFormulario" />
        </div>
      </div>
    </form>

    <!-- Modal reutilizável -->
    <ModalSucesso ref="modalRef" />
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import Dropdown from "primevue/dropdown";
import Button from "primevue/button";
import ModalSucesso from "@/components/ModalSucesso.vue";

export default {
  name: "CadastroSistema",
  components: { Dropdown, Button, ModalSucesso },
  setup() {
    const loading = ref(false);
    const URL = import.meta.env.VITE_API_URL;
    const modalRef = ref(null);

    const sistema = ref({
      tipoSistema: "",
      clienteId: "",
      tecnicoId: "",
      intervaloManutencao: null,
    });

    const tipoSistema = [
      { label: "Câmeras", value: "CAMERAS" },
      { label: "Balão", value: "BALAO" },
    ];

    const clientes = ref([]);
    const tecnicos = ref([]);

    const carregarClientes = async () => {
      try {
        const response = await axios.get(`${URL}/clientes/buscartodos`);
        clientes.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar clientes:", error);
        modalRef.value.abrir("Não foi possível carregar a lista de clientes.", { tipo: "erro" });
      }
    };

    const carregarTecnicos = async () => {
      try {
        const response = await axios.get(`${URL}/tecnico/buscartodos`);
        tecnicos.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar técnicos:", error);
        modalRef.value.abrir("Não foi possível carregar a lista de técnicos.", { tipo: "erro" });
      }
    };

    const limparFormulario = () => {
      sistema.value = { tipoSistema: "", clienteId: "", tecnicoId: "", intervaloManutencao: "" };
    };

    const salvarSistema = async () => {
      if (!sistema.value.tipoSistema) {
        return modalRef.value.abrir("Selecione o tipo de sistema.", { tipo: "erro" });
      }
      if (!sistema.value.clienteId) {
        return modalRef.value.abrir("Selecione um cliente.", { tipo: "erro" });
      }
      if (!sistema.value.tecnicoId) {
        return modalRef.value.abrir("Selecione um técnico.", { tipo: "erro" });
      }
      if (!sistema.value.intervaloManutencao || sistema.value.intervaloManutencao <= 0) {
        return modalRef.value.abrir("Informe um intervalo de manutenção válido.", { tipo: "erro" });
      }

      loading.value = true;
      try {
        await axios.post(`${URL}/sistema`, sistema.value);

        modalRef.value.abrir("Sistema cadastrado com sucesso!", {
          tipo: "sucesso",
          onConfirm: () => limparFormulario(),
        });
      } catch (error) {
        console.error("Erro ao cadastrar sistema:", error);

        let msg = "";
        if (error.response) {
          msg = `Erro ${error.response.status}: ${error.response.data?.message || "Falha ao cadastrar sistema."}`;
        } else if (error.request) {
          msg = "Servidor não respondeu. Verifique se o backend está rodando.";
        } else {
          msg = "Erro inesperado. Tente novamente.";
        }

        modalRef.value.abrir(msg, { tipo: "erro" });
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      carregarClientes();
      carregarTecnicos();
    });

    return { sistema, salvarSistema, limparFormulario, tipoSistema, clientes, tecnicos, loading, modalRef };
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
</style>
