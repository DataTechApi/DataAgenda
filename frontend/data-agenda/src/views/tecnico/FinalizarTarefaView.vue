<template>
  <div class="page">
    <h1>Finalizar Tarefa</h1>

    <textarea v-model="relato" placeholder="Descreva o que foi feito..." />

    <button class="btn" @click="salvar">Salvar Finalização</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const relato = ref('')

function salvar() {
  console.log('ID da tarefa:', route.params.id)
  console.log('Relato:', relato.value)

  // depois você envia para API
}
</script>

<style scoped>
.page {
  color: var(--text-main);
  padding: 30px;
}

textarea {
  width: 100%;
  height: 150px;
  margin: 20px 0;
  padding: 10px;
  background: var(--bg-input);
  color: var(--text-main);
  border: 1px solid var(--border-input);
  border-radius: 8px;
}

.btn {
  background: var(--status-success);
  padding: 10px;
  border: none;
  border-radius: 6px;
  color: white;
}
</style>
