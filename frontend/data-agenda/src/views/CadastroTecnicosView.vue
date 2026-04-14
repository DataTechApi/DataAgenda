<template>
  <div class="card">
    <h2>Cadastro de Técnico</h2>
    <form @submit.prevent="salvarTecnico">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="tecnico.nome" required />
        </div>

        <!-- Telefone -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="telefone">Telefone</label>
          <InputMask id="telefone" v-model="tecnico.telefone" mask="(99) 99999-9999" placeholder="(xx) xxxxx-xxxx" />
        </div>

        <!-- Nível -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nivel">Nível</label>
          <Dropdown id="nivel" v-model="tecnico.nivel" :options="niveis" optionLabel="label" optionValue="value" placeholder="Selecione o nível" />
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
import InputMask from "primevue/inputmask";
import Dropdown from "primevue/dropdown";
import Button from "primevue/button";

export default {
  name: "CadastroTecnico",
  components: {
    InputText,
    InputMask,
    Dropdown,
    Button,
  },
  setup() {
    const tecnico = ref({
      nome: "",
      telefone: "",
      nivel: null,
    });

    const niveis = [
      { label: "Júnior", value: "junior" },
      { label: "Pleno", value: "pleno" },
      { label: "Sênior", value: "senior" },
    ];

    const salvarTecnico = () => {
      console.log("Técnico cadastrado:", tecnico.value);
      alert("Técnico cadastrado com sucesso!");
      limparFormulario();
    };

    const limparFormulario = () => {
      tecnico.value = { nome: "", telefone: "", nivel: null };
    };

    return { tecnico, niveis, salvarTecnico, limparFormulario };
  },
};
</script>

<style scoped>
.card {
  max-width: 900px;
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
  margin-bottom: 1.5rem; /* espaço entre linhas */
}
.horizontal-field label {
  width: 120px;
  font-weight: 600;
}
.horizontal-field input,
.horizontal-field .p-inputmask,
.horizontal-field .p-dropdown {
  flex: 1;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}
</style>
