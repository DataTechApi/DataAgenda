<template>
  <div class="login-screen">
    <div class="card">
      <h1>DataAgenda</h1>
      <div class="tabs">
        <button class="active">Entrar</button>
        <button>Cadastrar</button>
      </div>
      <div class="input-group">
        <label>E-mail ou Usuário</label>
        <input type="text" />
      </div>
      <div class="input-group">
        <label>Senha</label>
        <input type="password" />
      </div>
      <button class="btn-main" @click="$router.push('/dashboard')">Acessar</button>
      <p class="forgot">Esqueci minha senha</p>
    </div>
  </div>
</template>

<style scoped>
.login-screen { height: 100vh; display: flex; align-items: center; justify-content: center; }
.card { background: white; padding: 40px; border-radius: 15px; width: 350px; text-align: center; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
.tabs { display: flex; border: 1px solid #ddd; border-radius: 8px; margin: 20px 0; overflow: hidden; }
.tabs button { flex: 1; padding: 10px; border: none; background: white; cursor: pointer; }
.tabs .active { background: #2b4c8c; color: white; }
.input-group { text-align: left; margin-bottom: 15px; }
.input-group label { font-size: 12px; color: #888; }
.input-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 8px; box-sizing: border-box; }
.btn-main { width: 100%; padding: 12px; background: #2b4c8c; color: white; border: none; border-radius: 8px; font-weight: bold; cursor: pointer; }
.forgot { font-size: 12px; color: #2b4c8c; margin-top: 15px; cursor: pointer; }
</style>