<template>
  <Dialog
    v-model:visible="visivel"
    modal
    :closable="false"
    :style="{ width: '25rem' }"
  >
    <template #header>
      <div class="modal-header" :class="tipo">
        <i :class="icone"></i>
        <span>{{ titulo }}</span>
      </div>
    </template>

    <p class="modal-message">{{ mensagem }}</p>

    <template #footer>
      <Button label="OK" @click="fechar" autofocus />
    </template>
  </Dialog>
</template>

<script setup>
import { ref, computed } from "vue";
import Dialog from "primevue/dialog";
import Button from "primevue/button";

const visivel = ref(false);
const mensagem = ref("");
const tipo = ref("sucesso"); // 'sucesso' | 'erro' | 'info'
let callback = null;

const titulo = computed(() => {
  if (tipo.value === "erro") return "Erro";
  if (tipo.value === "info") return "Aviso";
  return "Sucesso";
});

const icone = computed(() => {
  if (tipo.value === "erro") return "pi pi-times-circle";
  if (tipo.value === "info") return "pi pi-info-circle";
  return "pi pi-check-circle";
});

// onConfirm: função opcional executada ao clicar em OK
function abrir(msg, opcoes = {}) {
  mensagem.value = msg;
  tipo.value = opcoes.tipo || "sucesso";
  callback = opcoes.onConfirm || null;
  visivel.value = true;
}

function fechar() {
  visivel.value = false;
  if (callback) callback();
}

defineExpose({ abrir });
</script>

<style scoped>
.modal-header {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-weight: 600;
  font-size: 1.1rem;
}

.modal-header.sucesso i {
  color: #27ae60;
  font-size: 1.4rem;
}

.modal-header.erro i {
  color: #e74c3c;
  font-size: 1.4rem;
}

.modal-header.info i {
  color: #3498db;
  font-size: 1.4rem;
}

.modal-message {
  margin: 0;
  padding: 0.5rem 0;
  color: var(--text-secondary, #aaaaaa);
}

:deep(.p-dialog) {
  background: var(--bg-card, #1a1a1a);
  border: 1px solid #333333;
}

:deep(.p-dialog-header) {
  background: var(--bg-card, #1a1a1a);
  color: var(--text-main, #ffffff);
  border-bottom: 1px solid #333333;
}

:deep(.p-dialog-content) {
  background: var(--bg-card, #1a1a1a);
}

:deep(.p-dialog-footer) {
  background: var(--bg-card, #1a1a1a);
  border-top: 1px solid #333333;
}
</style>