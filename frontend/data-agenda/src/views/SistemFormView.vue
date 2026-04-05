<template>
  <div class="form-page">
    <button class="btn-back" @click="$router.push('/sistemas')">
      <span class="arrow">←</span>
    </button>

    <div class="form-card">
      <h2 class="form-title">Cadastro de Sistema</h2>

      <div class="form-body">
        <div class="input-wrapper">
          <label>Numero de série</label>
          <input type="text" placeholder="Digite o número de série..." />
        </div>

        <div class="input-wrapper">
          <label>Onde o sistema se encontra?</label>
          <select>
            <option value="" disabled selected>Selecione...</option>
            <option value="terra">Terra</option>
            <option value="mar">Mar</option>
            <option value="ar">Ar</option>
          </select>
        </div>

        <div class="input-wrapper">
          <label>Empresa responsável</label>
          <select>
            <option value="" disabled selected>Selecione...</option>
            <option value="empresa1">Embraer</option>
            <option value="empresa2">Latam</option>
            <option value="empresa3">Shell</option>
            <option value="empresa4">Consul</option>
          </select>
        </div>

        <button class="btn-submit" @click="handleSave">Cadastrar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const handleSave = () => {
  alert('Sistema cadastrado com sucesso! (Simulação)');
  router.push('/empresas');
};
</script>

<style scoped>
.form-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
}

.btn-back {
  align-self: flex-start;
  background: #e0e0e0;
  border: none;
  width: 35px;
  height: 35px;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  transition: background 0.2s;
}

.btn-back:hover {
  background: #d0d0d0;
}

.form-card {
  background: white;
  width: 100%;
  max-width: 500px;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
  border: 1px solid #eee;
}

.form-title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 1.5rem;
}

.form-body {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.input-wrapper {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.input-wrapper label {
  font-size: 0.75rem;
  color: #999;
  font-weight: 600;
}

.input-wrapper input, 
.input-wrapper select {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 10px;
  font-size: 0.9rem;
  box-sizing: border-box;
  outline: none;
  transition: border-color 0.2s;
}

.input-wrapper input:focus {
  border-color: #2b4c8c;
}

.btn-submit {
  background: #2b4c8c;
  color: white;
  border: none;
  padding: 14px;
  border-radius: 10px;
  font-weight: bold;
  cursor: pointer;
  margin-top: 10px;
  transition: opacity 0.2s;
}

.btn-submit:hover {
  opacity: 0.9;
}
</style>