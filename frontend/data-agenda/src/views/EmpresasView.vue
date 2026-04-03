<template>
  <div class="empresas-container">
    <h2 class="title">Empresas</h2>

    <div class="stats-row">
      <div class="stat-card">
        <span class="label-green">Empresas Cadastradas</span>
        <h2 class="value-green">4</h2>
      </div>
      <div class="stat-card">
        <span class="label-blue">Sistemas Ativos</span>
        <h2 class="value-blue">4</h2>
      </div>
    </div>

    <div class="tables-grid">
      
      <div class="table-wrapper">
        <table class="table-purple">
          <thead>
            <tr>
              <th>Ativo (ID)</th>
              <th>Empresa</th>
              <th>Frequência manutenção</th>
              <th>País</th>
              <th>Início contrato</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="emp in empresas" :key="emp.id">
              <td>{{ emp.id }}</td>
              <td>{{ emp.nome }}</td>
              <td>{{ emp.freq }}</td>
              <td>{{ emp.pais }}</td>
              <td>{{ emp.inicio }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="table-wrapper-small">
        <table class="table-purple">
          <thead>
            <tr>
              <th>ID Sistema</th>
              <th>Localidade</th>
              <th>Nº Serie</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="sis in sistemas" :key="sis.id">
              <td>{{ sis.id }}</td>
              <td>{{ sis.local }}</td>
              <td>{{ sis.serie }}</td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</template>

<script setup>
const empresas = [
  { id: 'EMP 011', nome: 'Embraer', freq: '3 meses', pais: 'Brasil', inicio: '10/10/2023' },
  { id: 'EMP 030', nome: 'Latam', freq: '4 meses', pais: 'EUA', inicio: '23/11/2024' },
  { id: 'EMP 55', nome: 'Shell', freq: '1 ano', pais: 'UAE', inicio: '23/01/2024' },
  { id: 'EMP 022', nome: 'Consul', freq: '6 meses', pais: 'Brasil', inicio: '09/01/2024' },
];

const sistemas = [
  { id: 'SIS001', local: 'Mar', serie: '62260' },
  { id: 'SIS002', local: 'Terra', serie: '6520' },
  { id: 'SIS003', local: 'Terra', serie: '6462' },
  { id: 'SIS004', local: 'Mar', serie: '6325' },
];
</script>

<style scoped>
.title { color: #333; margin-bottom: 20px; }

.stats-row { 
  display: flex; 
  gap: 30px; 
  margin-bottom: 40px;
  justify-content: center;
}

.stat-card { 
  background: white; 
  padding: 20px; 
  border-radius: 15px; 
  width: 250px; 
  text-align: center; 
  border: 1px solid #eee;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.label-green { color: #27ae60; font-weight: bold; font-size: 0.8rem; }
.value-green { color: #27ae60; font-size: 2.5rem; margin: 5px 0; }
.label-blue { color: #3498db; font-weight: bold; font-size: 0.8rem; }
.value-blue { color: #3498db; font-size: 2.5rem; margin: 5px 0; }

.tables-grid {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.table-wrapper { flex: 2; }
.table-wrapper-small { flex: 1; }

table { 
  width: 100%; 
  border-collapse: separate; 
  border-spacing: 0; 
  background: white; 
  border-radius: 12px; 
  overflow: hidden;
  border: 1px solid #ddd;
}

.table-purple th { 
  background: #6200ee; /* Roxo do protótipo */
  color: white; 
  padding: 12px; 
  text-align: left;
  font-size: 0.85rem;
}

td { 
  padding: 12px; 
  border-bottom: 1px solid #eee; 
  font-size: 0.85rem;
  color: #444;
  text-align: center;
}

th { text-align: center !important; }

tr:nth-child(even) { background: #f2f2f2; }
</style>