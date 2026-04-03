<template>
  <div>
    <h2>Painel de Controle de Manutenções</h2>
    <div class="stats">
      <div class="stat"><span style="color:green">Sistemas Ativos</span><h1>2</h1></div>
      <div class="stat"><span style="color:red">Manutenções Críticas</span><h1>0</h1></div>
      <div class="stat"><span style="color:blue">Técnicos em Campo</span><h1>2</h1></div>
    </div>
    <table class="main-table">
      <thead>
        <tr>
          <th>Ativo (ID)</th>
          <th>Cliente/Contrato</th>
          <th>Localização</th>
          <th>Tipo</th>
          <th>Vencimento</th>
        </tr>
      </thead>
      <tbody>
        <tr><td>ATV001</td><td>Avibras</td><td>SJC, Brasil</td><td>Preventiva</td><td>15/10/2026</td></tr>
        <tr><td>ATV002</td><td>Saudi Aramco</td><td>Dhahran, AS</td><td>Preventiva</td><td>26/03/2026</td></tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.stats { display: flex; gap: 20px; margin-bottom: 30px; }
.stat { background: white; padding: 20px; border-radius: 12px; flex: 1; text-align: center; border: 1px solid #ddd; }
.main-table { width: 100%; background: white; border-collapse: collapse; border-radius: 10px; overflow: hidden; }
.main-table th { background: #5d6d7e; color: white; padding: 15px; text-align: left; }
.main-table td { padding: 15px; border-bottom: 1px solid #eee; }
</style>