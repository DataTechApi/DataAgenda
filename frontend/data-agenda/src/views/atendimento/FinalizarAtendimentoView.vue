<template>
  <div class="card">
    <h2>Visualização de Manutenção</h2>
    <form @submit.prevent="finalizarAtendimento">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Cliente (somente leitura) -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="cliente">Cliente</label>
          <InputText id="cliente" v-model="manutencao.clienteNome" disabled class="full-width" />
        </div>

        <!-- Sistemas + Técnicos (somente leitura) -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="sistema">Sistema</label>
            <InputText id="sistema" v-model="manutencao.sistemaNome" disabled class="full-width" />
          </div>
          <div class="p-field horizontal-field">
            <label for="tecnico">Técnico</label>
            <InputText id="tecnico" v-model="manutencao.tecnicoNome" disabled class="full-width" />
          </div>
        </div>

        <!-- Tipo + Status (somente leitura) -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="tipo">Tipo</label>
            <InputText id="tipo" v-model="manutencao.tipoManutencao" disabled class="full-width" />
          </div>
          <div class="p-field horizontal-field">
            <label for="status">Status</label>
            <InputText id="status" v-model="manutencao.statusManutencao" disabled class="full-width" />
          </div>
        </div>

        <!-- Data do atendimento (editável) -->
        <div class="p-field horizontal-field">
          <label for="dataAtendimento">Data Atendimento</label>
          <DatePicker 
            id="dataAtendimento" 
            v-model="manutencao.dataRealizada" 
            dateFormat="dd/mm/yy"
            placeholder="dd/mm/aaaa"
            showIcon
            class="full-width" />
        </div>

        <!-- Descrição (editável) -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="descricao">Descrição do Serviço</label>
          <Textarea 
            id="descricao" 
            v-model="manutencao.descricaoAtendimento"
            rows="4" 
            autoResize 
            class="full-width textarea-custom" />
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Salvar Alterações" icon="pi pi-check" type="submit" class="p-button-success" />
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import Textarea from "primevue/textarea";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import DatePicker from "primevue/datepicker";

export default {
  name: "VisualizacaoManutencao",
  components: { Textarea, InputText, Button, DatePicker },
  setup() {
    const URL = import.meta.env.VITE_API_URL;
    const route = useRoute();
    const router = useRouter();

    const manutencao = ref({
      id: null,
      clienteNome: "",
      sistemaNome: "",
      tecnicoNome: "",
      tipoManutencao: "",
      statusManutencao: "",
      descricao: "",
      dataAtendimento: null,
      descricaoAtendimento: "",
      dataRealizada: null,
    });

    // Carregar dados da manutenção pelo ID vindo da rota
    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/manutencao/${route.params.id}`);
        manutencao.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar manutenção:", error);
      }
    });

    const finalizarAtendimento = async () => {
      try {
        const payload = {
          descricaoAtendimento: manutencao.value.descricaoAtendimento,
          dataRealizada: manutencao.value.dataRealizada
            ? new Date(manutencao.value.dataRealizada).toISOString().split("T")[0]
            : null,
        };

        const response = await axios.patch(
          `${URL}/manutencao/finalizar-atendimento/${manutencao.value.id}`,
          payload
        );

        alert(response.data || "Atendimento finalizado com sucesso!");

        // Redireciona para a página Visualizar Tarefa
        router.push("/atendimento");
      } catch (error) {
        console.error("Erro ao finalizar atendimento:", error);
        alert("Erro ao salvar alterações.");
      }
    };

    return { manutencao, finalizarAtendimento };
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

.textarea-custom {
  resize: vertical;
  min-height: 100px;
}
</style>
