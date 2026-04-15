<template>
  <div class="card">
    <h2>Cadastro de Manutenção</h2>
    <form @submit.prevent="salvarManutencao">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Cliente (100% largura, igual aos outros) -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="cliente">Cliente</label>
          <AutoComplete 
            id="cliente" 
            v-model="manutencao.cliente" 
            :suggestions="clientes" 
            @complete="buscarClientes" 
            field="nome" 
            placeholder="Digite para procurar..." 
            class="full-width" />
        </div>

        <!-- Sistemas (múltiplos) -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="sistemas">Sistemas</label>
          <MultiSelect 
            id="sistemas" 
            v-model="manutencao.sistemas" 
            :options="sistemas" 
            optionLabel="nome" 
            optionValue="id" 
            display="chip"
            placeholder="Selecione os sistemas" 
            class="full-width" />
        </div>

        <!-- Técnicos (múltiplos) -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="tecnicos">Técnicos</label>
          <MultiSelect 
            id="tecnicos" 
            v-model="manutencao.tecnicos" 
            :options="tecnicos" 
            optionLabel="nome" 
            optionValue="id" 
            display="chip"
            placeholder="Selecione os técnicos" 
            class="full-width" />
        </div>

        <!-- Data -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="data">Data</label>
          <InputText id="data" type="date" v-model="manutencao.data" required />
        </div>

        <!-- Descrição -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="descricao">Descrição</label>
          <Textarea id="descricao" v-model="manutencao.descricao" rows="4" autoResize />
        </div>

        <!-- Status -->
        <div class="p-field p-col-12 horizontal-field">
          <label for="status">Status</label>
          <Dropdown id="status" v-model="manutencao.status" :options="statusOptions" optionLabel="label" optionValue="value" placeholder="Selecione" />
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
import { ref } from "vue";
import InputText from "primevue/inputtext";
import Textarea from "primevue/textarea";
import Dropdown from "primevue/dropdown";
import AutoComplete from "primevue/autocomplete";
import MultiSelect from "primevue/multiselect";
import Button from "primevue/button";

export default {
  name: "CadastroManutencao",
  components: { InputText, Textarea, Dropdown, AutoComplete, MultiSelect, Button },
  setup() {
    const manutencao = ref({
      cliente: null,
      sistemas: [],
      tecnicos: [],
      data: "",
      descricao: "",
      status: "",
    });

    const clientes = ref([]);
    const sistemas = ref([
      { id: 1, nome: "Sistema X" },
      { id: 2, nome: "Sistema Y" },
      { id: 3, nome: "Sistema Z" },
    ]);
    const tecnicos = ref([
      { id: 1, nome: "João" },
      { id: 2, nome: "Maria" },
      { id: 3, nome: "Carlos" },
    ]);

    const statusOptions = [
      { label: "Pendente", value: "pendente" },
      { label: "Em andamento", value: "em_andamento" },
      { label: "Concluída", value: "concluida" },
    ];

    const buscarClientes = (event) => {
      const query = event.query.toLowerCase();
      clientes.value = [{ id: 1, nome: "Cliente A" }, { id: 2, nome: "Cliente B" }]
        .filter(c => c.nome.toLowerCase().includes(query));
    };

    const salvarManutencao = () => {
      console.log("Manutenção cadastrada:", manutencao.value);
      alert("Manutenção cadastrada com sucesso!");
      limparFormulario();
    };

    const limparFormulario = () => {
      manutencao.value = {
        cliente: null,
        sistemas: [],
        tecnicos: [],
        data: "",
        descricao: "",
        status: "",
      };
    };

    return { manutencao, salvarManutencao, limparFormulario, statusOptions, clientes, sistemas, tecnicos, buscarClientes };
  },
};
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  border-radius: 12px;
}
h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}
.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}
.horizontal-field label {
  width: 120px;
  font-weight: 600;
}
.horizontal-field input,
.horizontal-field textarea,
.horizontal-field .p-dropdown,
.horizontal-field .p-autocomplete,
.horizontal-field .p-multiselect {
  flex: 1;
}
.full-width {
  width: 100%;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}
</style>
