<template>
  <div class="card">
    <h2>Detalhes da Manutenção</h2>
    <div class="p-fluid p-formgrid p-grid">

      <!-- Cliente -->
      <div class="p-field p-col-12 horizontal-field full-width">
        <label for="cliente">Cliente</label>
        <InputText id="cliente" v-model="manutencao.clienteNome" :disabled="!editMode" class="full-width" />
      </div>

      <!-- Sistema + Técnico -->
      <div class="row-pair">
        <div class="p-field horizontal-field">
          <label for="sistema">Sistema</label>
          <InputText id="sistema" v-model="manutencao.sistemaNome" :disabled="!editMode" class="full-width" />
        </div>
        <div class="p-field horizontal-field">
          <label for="tecnico">Técnico</label>
          <InputText id="tecnico" v-model="manutencao.tecnicoNome" :disabled="!editMode" class="full-width" />
        </div>
      </div>

      <!-- Tipo + Status -->
      <div class="row-pair">
        <div class="p-field horizontal-field">
          <label for="tipo">Tipo</label>
          <InputText id="tipo" v-model="manutencao.tipoManutencao" :disabled="!editMode" class="full-width" />
        </div>
        <div class="p-field horizontal-field">
          <label for="status">Status</label>
          <InputText id="status" v-model="manutencao.statusManutencao" disabled class="full-width" />
        </div>
      </div>

      <!-- Data Agendada -->
      <div class="p-field horizontal-field">
        <label for="dataAgendada">Data Agendada</label>
        <InputText id="dataAgendada" v-model="manutencao.dataAgendada" :disabled="!editMode" class="full-width" />
      </div>

      <!-- Data Atendimento (sempre desabilitado) -->
      <div class="p-field horizontal-field">
        <label for="dataAtendimento">Data Atendimento</label>
        <DatePicker 
          id="dataAtendimento" 
          v-model="manutencao.dataRealizada" 
          dateFormat="dd/mm/yy"
          placeholder="dd/mm/aaaa"
          showIcon
          class="full-width"
          disabled />
      </div>

      <!-- Descrição Atendimento (sempre desabilitado) -->
      <div class="p-field p-col-12 horizontal-field full-width">
        <label for="descricaoAtendimento">Descrição Atendimento</label>
        <Textarea 
          id="descricaoAtendimento" 
          v-model="manutencao.descricaoAtendimento"
          rows="4" 
          autoResize 
          class="full-width textarea-custom"
          disabled />
      </div>

      <!-- Botões -->
      <div class="p-field p-col-12 botoes">
        <Button 
          label="Editar" 
          icon="pi pi-pencil" 
          class="p-button-warning" 
          :disabled="manutencao.statusManutencao.toLowerCase() !== 'pendente'"
          @click="habilitarEdicao" 
        />
        <Button 
          label="Salvar Alterações" 
          icon="pi pi-check" 
          class="p-button-success" 
          :disabled="!editMode"
          @click="salvarAlteracoes" 
        />
      </div>

    </div>
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
  name: "DetalhesManutencao",
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
      dataAgendada: "",
      dataRealizada: null,
      descricaoAtendimento: "",
    });

    const editMode = ref(false);

    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/manutencao/${route.params.id}`);
        manutencao.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar manutenção:", error);
      }
    });

    const habilitarEdicao = () => {
      if (manutencao.value.statusManutencao.toLowerCase() === "pendente") {
        editMode.value = true;
      } else {
        alert("Somente manutenções pendentes podem ser editadas.");
      }
    };

    const salvarAlteracoes = async () => {
      try {
        const payload = {
          clienteNome: manutencao.value.clienteNome,
          sistemaNome: manutencao.value.sistemaNome,
          tecnicoNome: manutencao.value.tecnicoNome,
          tipoManutencao: manutencao.value.tipoManutencao,
          dataAgendada: manutencao.value.dataAgendada,
        };

        const response = await axios.patch(
          `${URL}/manutencao/editar/${manutencao.value.id}`,
          payload
        );

        alert(response.data || "Alterações salvas com sucesso!");
        editMode.value = false;
        router.push("/manutencao");
      } catch (error) {
        console.error("Erro ao salvar alterações:", error);
        alert("Erro ao salvar alterações.");
      }
    };

    return { manutencao, editMode, habilitarEdicao, salvarAlteracoes };
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
