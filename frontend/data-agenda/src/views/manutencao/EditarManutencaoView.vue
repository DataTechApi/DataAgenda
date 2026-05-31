<template>
  <div class="card">
    <h2>Detalhes da Manutenção</h2>
    <div class="p-fluid p-formgrid p-grid">

      <!-- Cliente -->
      <div class="p-field p-col-12 horizontal-field full-width">
        <label for="cliente">Cliente</label>
        <InputText id="cliente" v-model="manutencao.clienteNome" disabled class="full-width" />
      </div>

      <!-- Sistema + Técnico -->
      <div class="row-pair">
        <div class="p-field horizontal-field">
          <label for="sistema">Sistema</label>
          <InputText id="sistema" v-model="manutencao.sistemaNome" disabled class="full-width" />
        </div>
        <div class="p-field horizontal-field">
          <label for="tecnico">Técnico</label>
          <template v-if="editMode">
            <Dropdown 
              id="tecnico"
              v-model="manutencao.tecnicoNome"
              :options="tecnicos"
              optionLabel="nome"
              optionValue="nome"
              placeholder="Selecione um técnico"
              class="full-width editable-field"
            />
          </template>
          <template v-else>
            <InputText 
              id="tecnico" 
              v-model="manutencao.tecnicoNome" 
              disabled 
              class="full-width" 
            />
          </template>
        </div>
      </div>

      <!-- Tipo + Status -->
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

      <!-- Data Agendada -->
      <div class="p-field horizontal-field">
        <label for="dataAgendada">Data Agendada</label>
        <DatePicker 
          id="dataAgendada" 
          v-model="manutencao.dataAgendada"
          dateFormat="dd/mm/yy"
          placeholder="dd/mm/aaaa"
          :showIcon="editMode"
          :disabled="!editMode"
          :class="['full-width', editMode ? 'editable-field' : '']"
        />
      </div>

      <!-- Descrição Problema -->
      <div class="p-field p-col-12 horizontal-field full-width">
        <label for="descricao">Descrição Problema</label>
        <Textarea 
          id="descricao" 
          v-model="manutencao.descricao"
          rows="4" 
          autoResize 
          class="full-width textarea-custom"
          :disabled="!editMode" 
          :class="editMode ? 'editable-field' : ''"
        />
      </div>

      <!-- Data Atendimento (sempre desabilitado) -->
      <div class="p-field horizontal-field">
        <label for="dataAtendimento">Data Atendimento</label>
        <DatePicker 
          id="dataAtendimento" 
          v-model="manutencao.dataAtendimento"
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

      <!-- Mensagem de erro -->
      <div v-if="erro" class="mensagem-erro">
        {{ erro }}
      </div>

      <!-- Botões -->
      <div class="p-field p-col-12 botoes">
        <Button 
          v-if="!editMode"
          label="Editar" 
          icon="pi pi-pencil" 
          class="p-button-warning" 
          @click="habilitarEdicao" 
        />
        <div v-if="editMode" class="botoes-edit">
          <Button 
            label="Salvar Alterações" 
            icon="pi pi-check" 
            class="p-button-success" 
            @click="salvarAlteracoes" 
          />
          <Button 
            label="Cancelar" 
            icon="pi pi-times" 
            class="p-button-secondary" 
            @click="cancelarEdicao" 
          />
        </div>
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
import Dropdown from "primevue/dropdown";

export default {
  name: "DetalhesManutencao",
  components: { Textarea, InputText, Button, DatePicker, Dropdown },
  setup() {
    const URL = import.meta.env.VITE_API_URL;
    const route = useRoute();
    const router = useRouter();
    const erro = ref("");

    const manutencao = ref({
      id: null,
      clienteNome: "",
      sistemaNome: "",
      tecnicoNome: "",
      tipoManutencao: "",
      statusManutencao: "",
      descricao: "",
      dataAgendada: "",
      dataAtendimento: null,
      descricaoAtendimento: "",
    });

    const manutencaoOriginal = ref(null); // guarda cópia original
    const tecnicos = ref([]);
    const editMode = ref(false);

    onMounted(async () => {
  try {
    const response = await axios.get(`${URL}/manutencao/${route.params.id}`);
    
    // Ajusta a data agendada para o fuso local
    if (response.data.dataAgendada) {
      const data = new Date(response.data.dataAgendada);
      // Corrige para meia-noite local
      response.data.dataAgendada = new Date(data.getTime() + data.getTimezoneOffset() * 60000);
    }

    manutencao.value = response.data;
    manutencaoOriginal.value = { ...response.data };

    const respTecnicos = await axios.get(`${URL}/tecnico/buscartodos`);
    tecnicos.value = respTecnicos.data; 
  } catch (error) {
    console.error("Erro ao carregar manutenção ou técnicos:", error);
  }
});


  const habilitarEdicao = () => {
  erro.value = ""; // limpa qualquer erro anterior
  if (manutencao.value.statusManutencao.toLowerCase() === "pendente") {
    editMode.value = true;
  } else {
    alert("Somente manutenções pendentes podem ser editadas.");
  }
};

const salvarAlteracoes = async () => {
  try {
    const payload = {
      tecnicoNome: manutencao.value.tecnicoNome,
      descricao: manutencao.value.descricao,
      dataAgendada: manutencao.value.dataAgendada,
    };

    const response = await axios.patch(
      `${URL}/manutencao/editar/${manutencao.value.id}`,
      payload
    );

    alert(response.data || "Alterações salvas com sucesso!");
    editMode.value = false;
    manutencaoOriginal.value = { ...manutencao.value };
    erro.value = ""; // limpa erro após sucesso
    router.push(`/dashboard/manutencao/editar/${manutencao.value.id}`);
  } catch (error) {
    if (error.response && error.response.data) {
      erro.value = error.response.data.message || JSON.stringify(error.response.data);
    } else {
      erro.value = "Erro ao salvar alterações.";
    }
  }
};

const cancelarEdicao = () => {
  manutencao.value = { ...manutencaoOriginal.value };
  editMode.value = false;
  erro.value = ""; // limpa erro ao cancelar
};


    return { manutencao, tecnicos, editMode, erro, habilitarEdicao, salvarAlteracoes, cancelarEdicao };
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
.horizontal-field .p-textarea,
.horizontal-field .p-dropdown {
  flex: 1;
}

.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}

.botoes-edit {
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.textarea-custom {
  resize: vertical;
  min-height: 100px;
}

/* Destaque para campos editáveis */
.editable-field {
  border: 2px solid #007bff; /* azul */
  background-color: #e6ffed; /* verde claro */
  transition: 0.3s;
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
