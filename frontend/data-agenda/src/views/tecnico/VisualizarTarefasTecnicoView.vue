<template>
  <div class="page">
    <h1 class="title">Tarefas em aberto</h1>

    <div v-if="tarefas.length === 0" class="empty">Nenhuma tarefa em aberto</div>

    <div v-for="tarefa in tarefas" :key="tarefa.id" class="card">
      <p><strong>Tipo:</strong> {{ tarefa.tipo || 'Não definido' }}</p>
      <p><strong> Data da tarefa:</strong> {{ tarefa.data }}</p>
      <p><strong>Cliente:</strong> {{ tarefa.cliente }}</p>
      <p><strong>Localidade:</strong> {{ tarefa.localidade }}</p>
      <p><strong>Sistema:</strong> {{ tarefa.sistema }}</p>
      <p><strong>Responsável dentro do cliente:</strong> {{ tarefa.responsavel }}</p>
      <p><strong>Contato:</strong> {{ tarefa.contato }}</p>
      <p><strong>Descrição:</strong> {{ tarefa.descricao }}</p>

      <div class="actions">
        <button class="btn open" @click="abrirTarefa(tarefa)">Abrir</button>

        <button class="btn finish" @click="finalizarTarefa(tarefa)">Finalizar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const tarefas = ref([
  {
    id: 1,
    tipo: 'preventiva',
    data: '25/04/2026',
    cliente: 'Empresa A',
    localidade: 'Mar',
    sistema: 'Monitoramento',
    responsavel: 'João',
    contato: '(12) 99999-0000',
    descricao: 'Executar tarefa preventiva',
  },
])

function abrirTarefa(tarefa) {
  router.push(`/tecnico/tarefa/${tarefa.id}`)
}

function finalizarTarefa(tarefa) {
  router.push(`/tecnico/finalizar/${tarefa.id}`)
}
</script>

<style scoped>
.page {
  color: #e2e8f0;
  padding: 30px;
}

.title {
  font-size: 22px;
  margin-bottom: 20px;
}

.card {
  background: #020617;
  border: 1px solid #1e293b;
  padding: 15px;
  border-radius: 10px;
  margin-bottom: 15px;
}

.actions {
  margin-top: 10px;
}

.btn {
  padding: 8px 12px;
  margin-right: 10px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.open {
  background: #3b82f6;
  color: white;
}

.finish {
  background: #22c55e;
  color: white;
}

.empty {
  color: #94a3b8;
}
</style>
