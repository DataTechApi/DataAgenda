<template>
  <div class="card">
    <h2>Cadastro de Sistema</h2>
    <form @submit.prevent="salvarSistema">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="sistema.nome" required />
        </div>

        <!-- Número de Série -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="numeroSerie">Número de Série</label>
          <InputText id="numeroSerie" v-model="sistema.numeroSerie" required />
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
import Button from "primevue/button";

export default {
  name: "CadastroSistema",
  components: {
    InputText,
    Button,
  },
  setup() {
    const sistema = ref({
      nome: "",
      numeroSerie: "",
    });

    const salvarSistema = () => {
      console.log("Sistema cadastrado:", sistema.value);
      alert("Sistema cadastrado com sucesso!");
      limparFormulario();
    };

    const limparFormulario = () => {
      sistema.value = { nome: "", numeroSerie: "" };
    };

    return { sistema, salvarSistema, limparFormulario };
  },
};
</script>

<style scoped>
.card {
  max-width: 900px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f; /* fundo escuro */
  box-shadow: 0 4px 12px rgba(0,0,0,0.5);
  border-radius: 12px;
  color: #f5f5f5; /* texto claro para contraste */
}
h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50; /* destaque no título */
}
.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem; /* espaço entre linhas */
}
.horizontal-field label {
  width: 140px;
  font-weight: 600;
  color: #f5f5f5;
}
.horizontal-field input {
  flex: 1;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}
</style>
