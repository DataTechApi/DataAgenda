<template>
  <div class="card">
    <h2>Cadastro de Cliente</h2>
    <form @submit.prevent="salvarCliente">
      <div class="p-fluid p-formgrid p-grid">
        
        <!-- Nome -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="cliente.nome" required placeholder="Home do Cliente"/>
        </div>

        <!-- CNPJ -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="cnpj">CNPJ</label>
          <InputMask id="cnpj" v-model="cliente.cnpj" mask="99.999.999/9999-99" placeholder="00.000.000/0000-00" required />
        </div>

        <!-- Localidade -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="localidade">Localidade</label>
          <InputText id="localidade" v-model="cliente.localidade" placeholder="Cidade/Estado" />
        </div>

        <!-- Responsável -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="responsavel">Responsável</label>
          <InputText id="responsavel" v-model="cliente.responsavel" placeholder="Nome do Responsável"/>
        </div>

        <!-- Telefone -->
        <div class="p-field p-col-12 p-md-6 horizontal-field">
          <label for="telefone">Telefone</label>
          <InputMask id="telefone" v-model="cliente.telefone" mask="(99) 99999-9999" placeholder="(xx) xxxxx-xxxx" />
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
import Button from "primevue/button";

export default {
  name: "CadastroCliente",
  components: {
    InputText,
    InputMask,
    Button,
  },
  setup() {
    const cliente = ref({
      nome: "",
      cnpj: "",
      localidade: "",
      responsavel: "",
      telefone: "",
    });

    const salvarCliente = () => {
      console.log("Cliente cadastrado:", cliente.value);
      alert("Cliente cadastrado com sucesso!");
      limparFormulario();
    };

    const limparFormulario = () => {
      cliente.value = { nome: "", cnpj: "", localidade: "", responsavel: "", telefone: "" };
    };

    return { cliente, salvarCliente, limparFormulario };
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
  margin-bottom: 1.5rem; /* espaço entre linhas */
}
.horizontal-field label {
  width: 120px; /* largura fixa para alinhar */
  font-weight: 600;
}
.horizontal-field input,
.horizontal-field .p-inputmask {
  flex: 1;
}
.botoes {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 2rem;
}
</style>
