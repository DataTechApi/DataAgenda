<template>
  <div>
    <h2 class="title">Técnicos</h2>
    <div class="stats-row">
      <div class="stat-card">
        <span style="color: #27ae60">Técnicos Cadastrados</span>
        <h2 style="color: #27ae60">4</h2>
      </div>
      <div class="stat-card">
        <span style="color: #3498db">Técnicos em Campo</span>
        <h2 style="color: #3498db">2</h2>
      </div>
    </div>

    <div class="table-container">
      <table class="tech-table">
        <thead>
          <tr>
            <th>Ativo (ID)</th>
            <th>Técnico</th>
            <th>Telefone</th>
            <th>Tarefas</th>
            <th>Disponível?</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="t in tecnicos" :key="t.id">
            <td>{{ t.id }}</td>
            <td>{{ t.nome }}</td>
            <td>{{ t.tel }}</td>
            <td>{{ t.tarefa }}</td>
            <td>{{ t.disp }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
const tecnicos = [
  { id: 'TEC001', nome: 'Carlos Eduardo', tel: '+55 12 98829-1234', tarefa: 'Vistoria Preventiva', disp: 'Sim' },
  { id: 'TEC002', nome: 'Vanderley Senna', tel: '+55 48 98729-1737', tarefa: 'Identificar problema', disp: 'Não' },
  { id: 'TEC003', nome: 'Rogerio Yamaha', tel: '+55 22 98889-1284', tarefa: 'Atualizações de software', disp: 'Não' },
  { id: 'TEC004', nome: 'Pablo Pasqualini', tel: '+55 17 99899-1934', tarefa: 'Manutenção de hardware', disp: 'Sim' },
];
</script>

<style scoped>
.title { color: #333; }
.stats-row { display: flex; gap: 20px; margin-bottom: 35px; max-width: 600px; }
.stat-card { background: white; padding: 20px; border-radius: 12px; flex: 1; text-align: center; border: 1px solid #eee; }
.tech-table th { background: #0066ff; color: white; }
/* ... reuso estilos do dashboard ... */
.table-container { background: white; border-radius: 12px; overflow: hidden; border: 1px solid #eee; }
table { width: 100%; border-collapse: collapse; }
th { padding: 15px; text-align: left; }
td { padding: 15px; border-bottom: 1px solid #f1f1f1; }
tr:nth-child(even) { background: #eee; }
</style>