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

        <!-- CNPJ + Cidade -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="cnpj">CNPJ</label>
            <InputMask id="cnpj" v-model="cliente.cnpj" mask="99.999.999/9999-99" placeholder="00.000.000/0000-00" required />
          </div>
          <div class="p-field horizontal-field">
            <label for="cidade">Cidade</label>
            <InputText id="cidade" v-model="cliente.cidade" placeholder="Ex: São Leopoldo - RS" />
          </div>
        </div>

        <!-- Endereço Completo -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="localidade">Endereço Completo</label>
          <InputText id="localidade" v-model="cliente.localidade" placeholder="Rua, Número, Bairro, Cidade, Estado" />
        </div>

        <!-- Responsável + E-mail -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="responsavel">Responsável</label>
            <InputText id="responsavel" v-model="cliente.nomeResponsavel" placeholder="Nome do Responsável" />
          </div>
          <div class="p-field horizontal-field">
            <label for="email">E-mail</label>
            <InputText id="email" v-model="cliente.emailResponsavel" type="email" placeholder="email@empresa.com" />
          </div>
        </div>

        <!-- Telefone + Início do Contrato -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="telefone">Telefone</label>
            <InputMask id="telefone" v-model="cliente.telefoneResponsavel" mask="(99) 99999-9999" placeholder="(xx) xxxxx-xxxx" />
          </div>
          <div class="p-field horizontal-field">
            <label for="dataInicio">Início do Contrato</label>
            <InputText id="dataInicio" v-model="cliente.dataInicioContrato" type="date" />
          </div>
        </div>

        <!-- Duração do Contrato -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="duracao">Duração (meses)</label>
            <InputText id="duracao" v-model="cliente.duracaoContrato" type="number" min="1" placeholder="Ex: 12" />
          </div>
        </div>

        <!-- Mensagem de erro -->
        <div v-if="erro" class="mensagem-erro">
          {{ erro }}
        </div>

        <!-- Botões -->
        <div class="p-field p-col-12 botoes">
          <Button
            label="Salvar"
            icon="pi pi-check"
            type="submit"
            class="p-button-success"
            :loading="loading"
            :disabled="loading"
          />
          <Button
            label="Limpar"
            icon="pi pi-refresh"
            type="button"
            class="p-button-secondary"
            :disabled="loading"
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
import Button from "primevue/button";

// Instância do axios com baseURL configurada
const api = axios.create({
  URL: import.meta.env.VITE_API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export default {
  name: "CadastroCliente",
  components: {
    InputText,
    InputMask,
    Button,
  },
  setup() {
    const loading = ref(false);
    const erro = ref("");
    const URL = import.meta.env.VITE_API_URL;

    const cliente = ref({
      nome: "",
      cnpj: "",
      cidade: "",
      localidade: "",
      nomeResponsavel: "",
      emailResponsavel: "",
      telefoneResponsavel: "",
      dataInicioContrato: "",
      duracaoContrato: "",
    });

    const salvarCliente = async () => {
      loading.value = true;
      erro.value = "";

      try {
        const response = await api.post(`${URL}/clientes`, cliente.value);
        console.log("Resposta da API:", response.data);
        alert("Cliente cadastrado com sucesso!");
        limparFormulario();
      } catch (error) {
        console.error("Erro ao cadastrar cliente:", error);

        if (error.response) {
          // Erro retornado pelo servidor (4xx, 5xx)
          erro.value = `Erro ${error.response.status}: ${error.response.data?.message || "Falha ao cadastrar cliente."}`;
        } else if (error.request) {
          // Sem resposta do servidor
          erro.value = "Servidor não respondeu. Verifique se o backend está rodando.";
        } else {
          erro.value = "Erro inesperado. Tente novamente.";
        }
      } finally {
        loading.value = false;
      }
    };

    const limparFormulario = () => {
      cliente.value = {
        nome: "",
        cnpj: "",
        cidade: "",
        localidade: "",
        nomeResponsavel: "",
        emailResponsavel: "",
        telefoneResponsavel: "",
        dataInicioContrato: "",
        duracaoContrato: "",
      };
    };

    return { cliente, loading, erro, salvarCliente, limparFormulario };
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

.row-pair {
  display: flex;
  gap: 1.5rem;
  width: 100%;
  margin-bottom: 0;
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

.mensagem-erro {
  width: 100%;
  padding: 0.75rem 1rem;
  margin-bottom: 1rem;
  background-color: #fee2e2;
  color: #991b1b;
  border: 1px solid #fca5a5;
  border-radius: 6px;
  font-size: 0.9rem;
}
</style>