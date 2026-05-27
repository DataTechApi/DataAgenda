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

        <!-- Email e Senha na mesma linha -->
        <div class="p-field p-col-12 linha-dupla">

          <!-- Email -->
          <div class="horizontal-field">
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

          <!-- Senha -->
          <div class="horizontal-field">
            <label for="senha">Senha</label>
            <InputText
              id="senha"
              v-model="tecnico.senha"
              type="password"
              placeholder="Digite sua senha"
              required
              minlength="6"
              :disabled="carregando"
            />
          </div>

        </div>

        <!-- Nível e Tipo de Usuário na mesma linha -->
        <div class="p-field p-col-12 linha-dupla">
          
          <!-- Nível -->
          <div class="horizontal-field">
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

          <!-- Tipo de Usuário -->
          <div class="horizontal-field">
            <label for="tipoUsuario">Tipo de Usuário</label>
            <Dropdown
              id="tipoUsuario"
              v-model="tecnico.role"
              :options="tiposUsuario"
              optionLabel="label"
              optionValue="value"
              placeholder="Selecione o tipo"
              :disabled="carregando"
            />
          </div>

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
      senha: "",
      nivel: null,
      role: null, // novo campo
    });

    const niveis = [
      { label: "Júnior", value: "JUNIOR" },
      { label: "Pleno",  value: "PLENO"  },
      { label: "Sênior", value: "SENIOR" },
    ];

    const tiposUsuario = [
      { label: "Administrador", value: "ADMIN" },
      { label: "Técnico", value: "TECNICO" },
    ];

    const carregando = ref(false);
    const erro = ref(null);
    const URL = import.meta.env.VITE_API_URL;

    const salvarTecnico = async () => {
      erro.value = null;
      carregando.value = true;

      if (!tecnico.value.senha || tecnico.value.senha.length < 6) {
        erro.value = "A senha deve ter pelo menos 6 caracteres.";
        carregando.value = false;
        return;
      }

      try {
        const response = await axios.post(`${URL}/tecnico`, tecnico.value);
        console.log("Técnico cadastrado:", response.data);
        alert("Técnico cadastrado com sucesso!");
        limparFormulario();
      } catch (error) {
        if (error.response && error.response.data) {
          erro.value = error.response.data.message || JSON.stringify(error.response.data);
        } else {
          erro.value = "Erro ao salvar alterações.";
        }
      } finally {
        carregando.value = false;
      }
    };

    const limparFormulario = () => {
      tecnico.value = { nome: "", telefone: "", email: "", senha: "", nivel: null, tipoUsuario: null };
      erro.value = null;
    };

    return { tecnico, niveis, tiposUsuario, carregando, erro, salvarTecnico, limparFormulario };
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
  width: 120px;
  font-weight: 600;
}
.horizontal-field input,
.horizontal-field .p-inputmask,
.horizontal-field .p-dropdown {
  flex: 1;
}
.linha-dupla {
  display: flex;
  gap: 1.5rem;
}
.linha-dupla .horizontal-field {
  flex: 1;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
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
