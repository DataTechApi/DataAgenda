<template>
  <div class="login-page">
    <div class="login-card">
      <div class="login-header">
        <div class="logo-icon">
          <img src="@/assets/logoDataTech.jpeg" alt="Data Tech" class="logo-img" />
        </div>
        <p class="app-subtitle">Gestão de Manutenções</p>
      </div>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="field">
          <label for="email">E-mail</label>
          <InputText
            id="email"
            v-model="credentials.email"
            type="email"
            placeholder="seu@email.com"
            :disabled="loading"
            autocomplete="email"
            class="w-full"
          />
        </div>

        <div class="field">
          <label for="senha">Senha</label>
          <Password
            id="senha"
            v-model="credentials.senha"
            placeholder="••••••••"
            :feedback="false"
            :disabled="loading"
            toggle-mask
            input-class="w-full"
            class="w-full"
          />
        </div>

        <div v-if="erro" class="error-message">
          <i class="pi pi-exclamation-triangle"></i>
          {{ erro }}
        </div>

        <Button
          type="submit"
          label="Entrar"
          icon="pi pi-sign-in"
          class="login-btn w-full"
          :loading="loading"
          :disabled="loading"
        />
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import InputText from 'primevue/inputtext'
import Password from 'primevue/password'
import Button from 'primevue/button'

const router = useRouter()

const credentials = ref({ email: '', senha: '' })
const loading = ref(false)
const erro = ref('')

const MOCK_EMAIL = 'admin@dataagenda.com'
const MOCK_SENHA = '123456'

const handleLogin = () => {
  erro.value = ''
  loading.value = true

  setTimeout(() => {
    if (
      credentials.value.email === MOCK_EMAIL &&
      credentials.value.senha === MOCK_SENHA
    ) {
      sessionStorage.setItem('token', 'mock-token')
      router.push({ name: 'dashboard' })
    } else {
      erro.value = 'E-mail ou senha incorretos.'
    }
    loading.value = false
  }, 800)
}
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--bg-app);
  background-image:
    radial-gradient(ellipse at 20% 50%, rgba(59, 130, 246, 0.08) 0%, transparent 60%),
    radial-gradient(ellipse at 80% 20%, rgba(37, 99, 235, 0.06) 0%, transparent 50%);
}

.login-card {
  width: 100%;
  max-width: 420px;
  padding: 2.5rem 2rem;
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.4);
}

.login-header {
  text-align: center;
  margin-bottom: 2rem;
}

.logo-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
}

.logo-img {
  height: 72px;
  width: auto;
  border-radius: 12px;
  object-fit: contain;
}

.app-subtitle {
  font-size: 0.875rem;
  color: var(--text-muted);
  margin: 0;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.field label {
  font-size: 0.875rem;
  font-weight: 600;
  color: var(--text-label);
  letter-spacing: 0.3px;
}

.w-full {
  width: 100%;
}

:deep(.p-inputtext) {
  background: #0d1e30 !important;
  border: 1px solid var(--border-color) !important;
  color: var(--text-main) !important;
  border-radius: 8px !important;
  padding: 0.75rem 1rem !important;
  width: 100%;
  transition: border-color 0.2s, box-shadow 0.2s;
}

:deep(.p-inputtext:focus) {
  border-color: var(--primary-color) !important;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.15) !important;
  outline: none !important;
}

:deep(.p-inputtext::placeholder) {
  color: #4a5568 !important;
}

:deep(.p-password) {
  width: 100%;
}

:deep(.p-password-input) {
  width: 100% !important;
}

:deep(.p-password .p-password-toggle-mask-icon) {
  color: var(--text-muted) !important;
}

.error-message {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1rem;
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.3);
  border-radius: 8px;
  color: #fca5a5;
  font-size: 0.875rem;
}

.login-btn {
  margin-top: 0.25rem;
  padding: 0.75rem 1rem !important;
  border-radius: 8px !important;
  background: var(--primary-color) !important;
  border-color: var(--primary-color) !important;
  font-weight: 600 !important;
  font-size: 0.9375rem !important;
  transition: background 0.2s, box-shadow 0.2s !important;
}

.login-btn:hover:not(:disabled) {
  background: var(--primary-hover) !important;
  border-color: var(--primary-hover) !important;
  box-shadow: 0 4px 16px rgba(59, 130, 246, 0.4) !important;
}

.login-btn:disabled {
  opacity: 0.6;
}

.hint {
  margin-top: 1.5rem;
  text-align: center;
  font-size: 0.8rem;
  color: var(--text-muted);
}

.hint strong {
  color: var(--text-main);
}
</style>
