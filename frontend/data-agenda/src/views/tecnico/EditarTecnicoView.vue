<template>
  <div class="card">
    <h2>Edição de Usuário</h2>
    <form @submit.prevent="salvarAlteracoes">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome + E-mail -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="nome">Nome</label>
            <InputText id="nome" v-model="usuario.nome" :disabled="!editando" />
          </div>
          <div class="p-field horizontal-field">
            <label for="email">E-mail</label>
            <InputText id="email" v-model="usuario.email" type="email" :disabled="!editando" />
          </div>
        </div>

        <!-- Telefone + Senha -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="telefone">Telefone</label>
            <InputMask id="telefone" v-model="usuario.telefone" mask="(99) 99999-9999" :disabled="!editando" />
          </div>
          <div class="p-field horizontal-field">
            <label for="senha">Senha</label>
            <Password id="senha" v-model="usuario.senha" :feedback="false" :disabled="!editando" toggleMask />
          </div>
        </div>

        <!-- Role + Nível -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="role">Tipo de Usuário</label>
            <Dropdown 
              id="role" 
              v-model="usuario.role" 
              :options="tiposUsuario" 
              optionLabel="label" 
              optionValue="value" 
              :disabled="!editando" 
              placeholder="Selecione o tipo"
            />
          </div>
          <div class="p-field horizontal-field">
            <label for="nivel">Nível</label>
            <Dropdown 
              id="nivel" 
              v-model="usuario.nivel" 
              :options="niveis" 
              optionLabel="label" 
              optionValue="value" 
              :disabled="!editando" 
              placeholder="Selecione o nível"
            />
          </div>
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="mensagem-erro">
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button
            v-if="!editando"
            label="Editar"
            icon="pi pi-pencil"
            type="button"
            class="p-button-warning"
            @click="ativarEdicao"
          />
          <Button
            v-if="editando"
            label="Salvar Alterações"
            icon="pi pi-check"
            type="submit"
            class="p-button-success"
            :loading="loading"
            :disabled="loading"
          />
          <Button
            v-if="editando"
            label="Cancelar"
            icon="pi pi-times"
            type="button"
            class="p-button-secondary"
            @click="cancelarEdicao"
          />
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import InputText from "primevue/inputtext";
import InputMask from "primevue/inputmask";
import Password from "primevue/password";
import Button from "primevue/button";
import Dropdown from "primevue/dropdown";

export default {
  name: "EdicaoUsuario",
  components: { InputText, InputMask, Password, Button, Dropdown },
  setup() {
    const loading = ref(false);
    const erro = ref("");
    const editando = ref(false);
    const route = useRoute();
    const URL = import.meta.env.VITE_API_URL;

    const usuarioOriginal = ref({
      id: null,
      nome: "",
      email: "",
      telefone: "",
      senha: "",
      role: "",
      nivel: "",
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

    const usuario = ref({ ...usuarioOriginal.value });

    // Recupera dados do usuário ao montar
    onMounted(async () => {
      try {
        const response = await axios.get(`${URL}/tecnico/${route.params.id}`);
        usuario.value = response.data;
        usuarioOriginal.value = { ...response.data };
      } catch (error) {
        console.error("Erro ao carregar usuário:", error);
        erro.value = "Erro ao carregar dados do usuário.";
      }
    });

    const ativarEdicao = () => {
      editando.value = true;
    };

    const cancelarEdicao = () => {  
      editando.value = false;
      usuario.value = { ...usuarioOriginal.value };
    };

    const salvarAlteracoes = async () => {
      loading.value = true;
      erro.value = "";
      try {
        await axios.put(`${URL}/tecnico/editar/${usuario.value.id}`, usuario.value);
        alert("Alterações salvas com sucesso!");
        editando.value = false;
        usuarioOriginal.value = { ...usuario.value };
      } catch (error) {
        if (error.response && error.response.data) {
          erro.value = error.response.data.message || JSON.stringify(error.response.data);
        } else {
          erro.value = "Erro ao salvar alterações.";
        }
      } finally {
        loading.value = false;
      }
    };

    return { usuario, loading, erro, editando, ativarEdicao, cancelarEdicao, salvarAlteracoes, niveis, tiposUsuario };
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
  margin-bottom: 1.25rem;
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
}

.horizontal-field label {
  width: 140px;
  min-width: 140px;
  font-weight: 600;
}

.horizontal-field input,
.horizontal-field .p-inputmask,
.horizontal-field .p-password,
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
