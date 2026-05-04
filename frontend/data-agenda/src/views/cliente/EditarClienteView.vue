<template>
  <div class="card">
    <h2>Edição de Cliente</h2>
    <form @submit.prevent="salvarAlteracoes">
      <div class="p-fluid p-formgrid p-grid">

        <!-- Nome -->
        <div class="p-field p-col-12 horizontal-field full-width">
          <label for="nome">Nome</label>
          <InputText id="nome" v-model="cliente.nome" :disabled="!editando" />
        </div>

        <!-- CNPJ + Localidade -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="cnpj">CNPJ</label>
            <InputMask id="cnpj" v-model="cliente.cnpj" mask="99.999.999/9999-99" :disabled="!editando" />
          </div>
          <div class="p-field horizontal-field">
            <label for="localidade">Localidade</label>
            <InputText id="localidade" v-model="cliente.localidade" :disabled="!editando" />
          </div>
        </div>

        <!-- Responsável + E-mail -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="responsavel">Responsável</label>
            <InputText id="responsavel" v-model="cliente.nomeResponsavel" :disabled="!editando" />
          </div>
          <div class="p-field horizontal-field">
            <label for="email">E-mail</label>
            <InputText id="email" v-model="cliente.emailResponsavel" type="email" :disabled="!editando" />
          </div>
        </div>

        <!-- Telefone + Início do Contrato -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="telefone">Telefone</label>
            <InputMask id="telefone" v-model="cliente.telefoneResponsavel" mask="(99) 99999-9999" :disabled="!editando" />
          </div>
          <div class="p-field horizontal-field">
            <label for="dataInicio">Início do Contrato</label>
            <InputText id="dataInicio" v-model="cliente.dataInicioContrato" type="date" :disabled="!editando" />
          </div>
        </div>

        <!-- Duração do Contrato -->
        <div class="row-pair">
          <div class="p-field horizontal-field">
            <label for="duracao">Duração (meses)</label>
            <InputText id="duracao" v-model="cliente.duracaoContrato" type="number" min="1" :disabled="!editando" />
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
import Button from "primevue/button";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: { "Content-Type": "application/json" },
});

export default {
  name: "EdicaoCliente",
  components: { InputText, InputMask, Button },
  setup() {
    const loading = ref(false);
    const erro = ref("");
    const editando = ref(false);
    const route = useRoute();
    const URL = import.meta.env.VITE_API_URL;

    const cliente = ref({
      nome: "",
      cnpj: "",
      localidade: "",
      nomeResponsavel: "",
      emailResponsavel: "",
      telefoneResponsavel: "",
      dataInicioContrato: null,
      duracaoContrato: "",
      duracaoContrato: "",
    });


    // Recupera dados do cliente ao montar o componente
    onMounted(async () => {
      try {
        const response = await api.get(`/clientes/${route.params.id}`);
        cliente.value = response.data;
       
      } catch (error) {
        console.error("Erro ao carregar cliente:", error);
        erro.value = "Erro ao carregar dados do cliente.";
      }
    });

    const ativarEdicao = () => {
      editando.value = true;
    };

    const cancelarEdicao = () => {
      editando.value = false;
      cliente.value = { ...clienteOriginal.value }; // restaura dados originais
    };

    const salvarAlteracoes = async () => {
      loading.value = true;
      erro.value = "";
      try {
        await api.put(`/clientes/${cliente.value.id}`, cliente.value);
        alert("Alterações salvas com sucesso!");
        editando.value = false;
        clienteOriginal.value = { ...cliente.value };
      } catch (error) {
        erro.value = "Erro ao salvar alterações.";
      } finally {
        loading.value = false;
      }
    };

    return { cliente, loading, erro, editando, ativarEdicao, cancelarEdicao, salvarAlteracoes };
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
