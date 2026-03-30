<template>
  <div class="card">
    <h2 class="title">Cadastro de Cliente</h2>
    <form @submit.prevent="salvarCliente">
      <div class="p-fluid p-formgrid p-grid">
        
        <!-- Nome -->
        <div class="p-field p-col-12 p-md-6">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="cliente.nome" placeholder="Digite o nome completo" />
        </div>

        <!-- Email -->
        <div class="p-field p-col-12 p-md-6">
          <label for="email">Email</label>
          <InputText id="email" v-model="cliente.email" placeholder="exemplo@email.com" />
        </div>

        <!-- Telefone -->
        <div class="p-field p-col-12 p-md-6">
          <label for="telefone">Telefone</label>
          <InputMask id="telefone" mask="(99) 99999-9999" v-model="cliente.telefone" placeholder="(xx) xxxxx-xxxx" />
        </div>

        <!-- Data de Nascimento -->
        <div class="p-field p-col-12 p-md-6">
          <label for="nascimento">Data de Nascimento</label>
          <Calendar id="nascimento" v-model="cliente.nascimento" dateFormat="dd/mm/yy" showIcon />
        </div>

        <!-- Endereço -->
        <div class="p-field p-col-12">
          <label for="endereco">Endereço</label>
          <InputTextarea id="endereco" v-model="cliente.endereco" rows="3" placeholder="Rua, número, bairro, cidade" />
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 p-text-center">
          <Button label="Salvar" icon="pi pi-check" class="p-button-success" type="submit" />
          <Button label="Cancelar" icon="pi pi-times" class="p-button-secondary p-ml-2" @click="resetarFormulario" />
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import InputText from "primevue/inputtext";
import InputMask from "primevue/inputmask";
import Calendar from "primevue/calendar";
import InputTextarea from "primevue/inputtextarea";
import Button from "primevue/button";

export default {
  name: "CadastroCliente",
  components: {
    InputText,
    InputMask,
    Calendar,
    InputTextarea,
    Button
  },
  data() {
    return {
      cliente: {
        nome: "",
        email: "",
        telefone: "",
        nascimento: null,
        endereco: ""
      }
    };
  },
  methods: {
    salvarCliente() {
      if (!this.cliente.nome || !this.cliente.email) {
        alert("Nome e Email são obrigatórios!");
        return;
      }
      console.log("Cliente salvo:", this.cliente);
      // Aqui você pode integrar com API ou backend
      alert("Cliente cadastrado com sucesso!");
      this.resetarFormulario();
    },
    resetarFormulario() {
      this.cliente = {
        nome: "",
        email: "",
        telefone: "",
        nascimento: null,
        endereco: ""
      };
    }
  }
};
</script>

<style scoped>
.card {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  background: #fff;
}

.title {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #3f51b5;
  font-weight: bold;
}

.p-field label {
  font-weight: 600;
  margin-bottom: 0.5rem;
  display: block;
}

.p-button {
  min-width: 120px;
}
</style>
