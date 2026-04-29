<template>
  <div class="card">
    <h2>Visualização de Manutenção</h2>
    <form @submit.prevent="salvarAtualizacao">
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
            v-model="manutencao.dataAtendimento" 
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
            v-model="manutencao.descricao" 
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
import { ref } from "vue";
import Textarea from "primevue/textarea";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import DatePicker from "primevue/datepicker";
import axios from "axios";

export default {
  name: "VisualizacaoManutencao",
  components: { Textarea, InputText, Button, DatePicker },
  setup() {
    const URL = import.meta.env.VITE_API_URL;

    // Exemplo de dados carregados
    const manutencao = ref({
      clienteNome: "Empresa XPTO",
      sistemaNome: "Sistema de Controle",
      tecnicoNome: "João Silva",
      tipoManutencao: "PREVENTIVA",
      statusManutencao: "PENDENTE",
      descricao: "",
      dataAtendimento: null,
    });

    const salvarAtualizacao = async () => {
      try {
        const payload = {
          descricao: manutencao.value.descricao,
          dataAtendimento: manutencao.value.dataAtendimento
            ? manutencao.value.dataAtendimento.toISOString().split("T")[0]
            : null,
        };
        const response = await axios.put(`${URL}/manutencoes/${manutencao.value.id}`, payload);
        alert(response.data || "Atualização salva com sucesso!");
      } catch (error) {
        console.error("Erro ao atualizar manutenção:", error);
        alert("Erro ao salvar alterações.");
      }
    };

    return { manutencao, salvarAtualizacao };
  },
};
</script>
