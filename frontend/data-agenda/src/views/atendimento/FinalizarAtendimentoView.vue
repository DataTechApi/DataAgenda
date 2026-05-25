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

        <!-- Data Agendamento -->
        <div class="p-field horizontal-field">
          <label for="dataAgendamento">Data Agendamento</label>
          <DatePicker 
            id="dataAgendamento" 
            v-model="manutencao.dataAgendada" 
            dateFormat="dd/mm/yy"
            placeholder="dd/mm/aaaa"
            showIcon
            disabled
            class="full-width" />
        </div>

        <!-- Descrição do Serviço (somente leitura) -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="descricao">Descrição do Serviço</label>
          <Textarea 
            id="descricao" 
            v-model="manutencao.descricao"
            rows="4" 
            autoResize 
            disabled
            class="full-width textarea-custom" />
        </div>

        <!-- Data do atendimento (editável) -->
        <div class="p-field horizontal-field">
          <label for="dataAtendimento">Data Atendimento</label>
          <DatePicker 
            id="dataAtendimento" 
            v-model="manutencao.dataAtendimento" 
            dateFormat="dd/mm/yy"
            placeholder="dd/mm/aaaa"
            showIcon
            class="full-width" />
        </div>

        <!-- Descrição (editável) -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="descricaoAtendimento">Descrição do Serviço</label>
          <Textarea 
            id="descricaoAtendimento" 
            v-model="manutencao.descricaoAtendimento"
            rows="4" 
            autoResize 
            maxlength="500"
            class="full-width textarea-custom" />
          <!-- Contador de caracteres -->
          <small class="contador">
            {{ contadorDescricao }}/500 caracteres
          </small>
        </div>

        <!-- Mensagens -->
        <div v-if="erro" class="erro-mensagem">
          {{ erro }}
        </div>
        <div v-if="sucesso" class="sucesso-mensagem">
          {{ sucesso }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Finalizar Atendimento" icon="pi pi-check" type="submit" class="p-button-success" />
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted, computed } from "vue";
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
      dataAgendada: null,
    });

    const erro = ref("");
    const sucesso = ref("");

    // Computed para contador de caracteres
    const contadorDescricao = computed(() => 
      manutencao.value.descricaoAtendimento?.length || 0
    );

    // Carregar dados da manutenção pelo ID vindo da rota
    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/manutencao/${route.params.id}`);
        manutencao.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar manutenção:", error);
        erro.value = "Erro ao carregar manutenção.";
      }
    });

    const finalizarAtendimento = async () => {
      erro.value = "";
      sucesso.value = "";
      try {
        const payload = {
          descricaoAtendimento: manutencao.value.descricaoAtendimento,
          dataAtendimento: manutencao.value.dataAtendimento
            ? new Date(manutencao.value.dataAtendimento).toISOString().split("T")[0]
            : null,
        };

        await axios.patch(
          `${URL}/manutencao/finalizar-atendimento/${manutencao.value.id}`,
          payload
        );

        alert("Atendimento finalizado com sucesso!");
        setTimeout(() => router.push("/atendimento"), 2000);
      } catch (error) {
        if (error.response && error.response.data) {
          erro.value = error.response.data.message || JSON.stringify(error.response.data);
        } else {
          erro.value = "Erro ao salvar alterações.";
        }
      }
    };

    return { manutencao, finalizarAtendimento, erro, sucesso, contadorDescricao };
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

.contador {
  display: block;
  text-align: right;
  font-size: 0.85rem;
  color: var(--text-secondary, #666);
  margin-top: 0.3rem;
  color:white
}

.erro-mensagem {
  color: rgb(243, 240, 240);
  font-weight: bold;
  margin-top: 1rem;
  text-align: center;
}

.sucesso-mensagem {
  color: green;
  font-weight: bold;
  margin-top: 1rem;
  text-align: center;
}
</style>
