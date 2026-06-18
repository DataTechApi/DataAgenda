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

        <!-- Email e Senha -->
        <div class="p-field p-col-12 linha-dupla">
          <div class="horizontal-field">
            <label for="email">Email</label>
            <InputText id="email" v-model="tecnico.email" type="email" required :disabled="carregando" />
          </div>
          <div class="horizontal-field">
            <label for="senha">Senha</label>
            <InputText id="senha" v-model="tecnico.senha" type="password" required minlength="6" :disabled="carregando" />
          </div>
        </div>

        <!-- Nível e Tipo de Usuário -->
        <div class="p-field p-col-12 linha-dupla">
          <div class="horizontal-field">
            <label for="nivel">Nível</label>
            <Dropdown id="nivel" v-model="tecnico.nivel" :options="niveis" optionLabel="label" optionValue="value" placeholder="Selecione o nível" :disabled="carregando" />
          </div>
          <div class="horizontal-field">
            <label for="tipoUsuario">Tipo de Usuário</label>
            <Dropdown id="tipoUsuario" v-model="tecnico.role" :options="tiposUsuario" optionLabel="label" optionValue="value" placeholder="Selecione o tipo" :disabled="carregando" />
          </div>
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button label="Salvar" icon="pi pi-check" type="submit" class="p-button-success" :loading="carregando" :disabled="carregando" />
          <Button label="Limpar" icon="pi pi-refresh" type="button" class="p-button-secondary" :disabled="carregando" @click="limparFormulario" />
        </div>
      </div>
    </form>

    <!-- Modal reutilizável -->
    <ModalSucesso ref="modalRef" />
  </div>
</template>

<script>
import { ref } from "vue";
import axios from '@/services/api' 
import InputText from "primevue/inputtext";
import InputMask from "primevue/inputmask";
import Dropdown from "primevue/dropdown";
import Button from "primevue/button";
import ModalSucesso from "@/components/ModalSucesso.vue";

export default {
  name: "CadastroTecnico",
  components: { InputText, InputMask, Dropdown, Button, ModalSucesso },
  setup() {
    const tecnico = ref({
      nome: "",
      telefone: "",
      email: "",
      senha: "",
      nivel: null,
      role: null,
    });

    const niveis = [
      { label: "Júnior", value: "JUNIOR" },
      { label: "Pleno", value: "PLENO" },
      { label: "Sênior", value: "SENIOR" },
    ];

    const tiposUsuario = [
      { label: "Administrador", value: "ADMIN" },
      { label: "Técnico", value: "TECNICO" },
    ];

    const carregando = ref(false);
    const URL = import.meta.env.VITE_API_URL;
    const modalRef = ref(null);

    const salvarTecnico = async () => {
      carregando.value = true;

      if (!tecnico.value.senha || tecnico.value.senha.length < 6) {
        modalRef.value.abrir("A senha deve ter pelo menos 6 caracteres.", { tipo: "erro" });
        carregando.value = false;
        return;
      }

      try {
        await axios.post(`${URL}/tecnico`, tecnico.value);

        modalRef.value.abrir("Técnico cadastrado com sucesso!", {
          tipo: "sucesso",
          onConfirm: () => limparFormulario(),
        });
      } catch (error) {
        console.error("Erro ao cadastrar técnico:", error);

        let msg = "";
        if (error.response && error.response.data) {
          msg = error.response.data.message || "Erro ao cadastrar técnico.";
        } else {
          msg = "Erro inesperado ao cadastrar técnico.";
        }

        modalRef.value.abrir(msg, { tipo: "erro" });
      } finally {
        carregando.value = false;
      }
    };

    const limparFormulario = () => {
      tecnico.value = { nome: "", telefone: "", email: "", senha: "", nivel: null, role: null };
    };

    return { tecnico, niveis, tiposUsuario, carregando, salvarTecnico, limparFormulario, modalRef };
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
</style>
