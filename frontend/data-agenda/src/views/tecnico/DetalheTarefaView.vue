<template>
  <div class="page">
    <!-- BOTÃO VOLTAR -->
    <div class="top-bar">
      <button class="btn-back" @click="voltar">← Voltar</button>
    </div>

    <h1 class="title">Detalhes da Tarefa</h1>

    <div class="card">
      <div class="grid">
        <div class="item">
          <span class="label">Tipo</span>
          <span class="value">{{ tarefa.tipo }}</span>
        </div>

        <div class="item">
          <span class="label">Data da tarefa</span>
          <span class="value">{{ tarefa.data }}</span>
        </div>

        <div class="item">
          <span class="label">Cliente</span>
          <span class="value">{{ tarefa.cliente }}</span>
        </div>

        <div class="item">
          <span class="label">Localidade</span>
          <span class="value">{{ tarefa.localidade }}</span>
        </div>

        <div class="item">
          <span class="label">Sistema</span>
          <span class="value">{{ tarefa.sistema }}</span>
        </div>

        <div class="item">
          <span class="label">Responsável</span>
          <span class="value">{{ tarefa.responsavel }}</span>
        </div>

        <div class="item">
          <span class="label">Contato</span>
          <span class="value">{{ tarefa.contato }}</span>
        </div>
      </div>

      <div class="description">
        <span class="label">Descrição</span>
        <p class="desc-text">{{ tarefa.descricao }}</p>
      </div>

      <div class="actions">
        <button class="btn-finish" @click="finalizarTarefa">Finalizar Tarefa</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const tarefa = ref({
  id: route.params.id,
  tipo: 'preventiva',
  data: '25/04/2026',
  cliente: 'Empresa A',
  localidade: 'Mar',
  sistema: 'Monitoramento',
  responsavel: 'João',
  contato: '(12) 99999-0000',
  descricao: 'Executar tarefa preventiva',
})

function voltar() {
  router.push('/tecnico/tarefas')
}

function finalizarTarefa() {
  router.push(`/tecnico/finalizar/${tarefa.value.id}`)
}
</script>

<style scoped>
.page {
  color: var(--text-main);
  padding: 30px;
}

.title {
  margin-bottom: 20px;
  font-size: 22px;
}

.top-bar {
  margin-bottom: 10px;
}

.btn-back {
  background: var(--bg-sidebar);
  color: var(--text-main);
  border: 1px solid var(--border-color);
  padding: 6px 12px;
  border-radius: 6px;
  cursor: pointer;
}

.btn-back:hover {
  background: var(--border-color);
}

.card {
  background: var(--bg-input);
  border: 1px solid var(--border-input);
  padding: 25px;
  border-radius: 12px;
}

.grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
  margin-bottom: 20px;
}

.item {
  display: flex;
  flex-direction: column;
}

.label {
  font-size: 12px;
  color: var(--text-muted);
}

.value {
  font-size: 14px;
  font-weight: 500;
}

.description {
  margin-top: 10px;
}

.desc-text {
  margin-top: 5px;
  background: var(--bg-input);
  border: 1px solid var(--border-input);
  padding: 10px;
  border-radius: 8px;
}

.actions {
  margin-top: 25px;
  display: flex;
  justify-content: flex-end;
}

.btn-finish {
  background: var(--status-success);
  color: white;
  padding: 10px 18px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
}

.btn-finish:hover {
  background: var(--status-success);
}
</style>
