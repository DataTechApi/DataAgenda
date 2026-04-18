<template>
  <div class="card">
    <h2>Cadastro de Cliente</h2>
    <form @submit.prevent="salvarCliente">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="cliente.nome" required placeholder="Nome do Cliente" />
        </div>

        <!-- CNPJ + Localidade -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="cnpj">CNPJ</label>
            <InputMask id="cnpj" v-model="cliente.cnpj" mask="99.999.999/9999-99" placeholder="00.000.000/0000-00" required />
          </div>
          <div class="p-field horizontal-field">
            <label for="localidade">Localidade</label>
            <InputText id="localidade" v-model="cliente.localidade" placeholder="Cidade/Estado" />
          </div>
        </div>

        <!-- Responsável + E-mail do Responsável -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="responsavel">Responsável</label>
            <InputText id="responsavel" v-model="cliente.responsavel" placeholder="Nome do Responsável" />
          </div>
          <div class="p-field horizontal-field">
            <label for="email">E-mail</label>
            <InputText id="email" v-model="cliente.email" type="email" placeholder="email@empresa.com" />
          </div>
        </div>

        <!-- Telefone + E-mail do Responsável (linha separada para telefone e email) -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="telefone">Telefone</label>
            <InputMask id="telefone" v-model="cliente.telefone" mask="(99) 99999-9999" placeholder="(xx) xxxxx-xxxx" />
          </div>
          <div class="p-field horizontal-field">
            <!-- Coluna vazia para manter alinhamento, ou adicionar campo extra -->
            <label for="dataInicio">Início do Contrato</label>
            <InputText id="dataInicio" v-model="cliente.dataInicio" type="date" />
          </div>
        </div>

        <!-- Duração do Contrato + Tempo de Contrato -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="duracao">Duração (meses)</label>
            <InputText id="duracao" v-model="cliente.duracao" type="number" min="1" placeholder="Ex: 12" />
          </div>
          
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
      email: "",
      telefone: "",
      dataInicio: "",
      duracao: "",
      tempoContrato: "",
    });

    const salvarCliente = () => {
      console.log("Cliente cadastrado:", cliente.value);
      alert("Cliente cadastrado com sucesso!");
      limparFormulario();
    };

    const limparFormulario = () => {
      cliente.value = {
        nome: "",
        cnpj: "",
        localidade: "",
        responsavel: "",
        email: "",
        telefone: "",
        dataInicio: "",
        duracao: "",
        tempoContrato: "",
      };
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
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 12px;
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}

/* Par de campos lado a lado */
.row-pair {
  display: flex;
  gap: 1.5rem;
  width: 100%;
  margin-bottom: 0;
}

.row-pair .p-field {
  flex: 1;
}

/* Campo de largura total (Nome) */
.full-width {
  width: 100%;
}

.horizontal-field {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.horizontal-field label {
  width: 140px;
  min-width: 140px;
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