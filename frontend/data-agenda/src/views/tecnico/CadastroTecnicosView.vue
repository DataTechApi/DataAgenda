<template>
  <div class="card">
    <h2>Cadastro de Técnico</h2>
    <form @submit.prevent="salvarTecnico">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="tecnico.nome" required :disabled="carregando" />
        </div>

        <!-- Telefone -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="telefone">Telefone</label>
          <InputMask
            id="telefone"
            v-model="tecnico.telefone"
            mask="(99) 99999-9999"
            placeholder="(xx) xxxxx-xxxx"
            :disabled="carregando"
          />
        </div>

        <!-- Email -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="email">Email</label>
          <InputText
            id="email"
            v-model="tecnico.email"
            type="email"
            placeholder="exemplo@email.com"
            required
            :disabled="carregando"
          />
        </div>

        <!-- Nível -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nivel">Nível</label>
          <Dropdown
            id="nivel"
            v-model="tecnico.nivel"
            :options="niveis"
            optionLabel="label"
            optionValue="value"
            placeholder="Selecione o nível"
            :disabled="carregando"
          />
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="p-field p-col-12 mensagem-erro">
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button
            label="Salvar"
            icon="pi pi-check"
            type="submit"
            class="p-button-success"
            :loading="carregando"
            :disabled="carregando"
          />
          <Button
            label="Limpar"
            icon="pi pi-refresh"
            type="button"
            class="p-button-secondary"
            :disabled="carregando"
            @click="limparFormulario"
          />
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import InputText from "primevue/inputtext";
import InputMask from "primevue/inputmask";
import Dropdown from "primevue/dropdown";
import Button from "primevue/button";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080/api",
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
  },
});

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
      email: "",
      nivel: null,
    });

    const niveis = [
      { label: "Júnior", value: "JUNIOR" },
      { label: "Pleno",  value: "PLENO"  },
      { label: "Sênior", value: "SENIOR" },
    ];

    const carregando = ref(false);
    const erro = ref(null);
    const URL = import.meta.env.VITE_API_URL;

    const salvarTecnico = async () => {
      erro.value = null;
      carregando.value = true;

      try {
        const response= await api.post(`${URL}/tecnico`, tecnico.value);
        console.log("Técnico cadastrado:", response.data);
        alert("Técnico cadastrado com sucesso!");
        limparFormulario();
      } catch (err) {
        console.error("Erro ao cadastrar técnico:", err);
        erro.value =
          err.response?.data?.mensagem ||
          "Erro ao cadastrar técnico. Tente novamente.";
      } finally {
        carregando.value = false;
      }
    };

    const limparFormulario = () => {
      tecnico.value = { nome: "", telefone: "", email: "", nivel: null };
      erro.value = null;
    };

    return { tecnico, niveis, carregando, erro, salvarTecnico, limparFormulario };
  },
};
</script>

<style scoped>
.card {
  max-width: 900px;
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
.mensagem-erro {
  color: #e74c3c;
  font-size: 0.875rem;
  margin-bottom: 0.5rem;
}
</style>