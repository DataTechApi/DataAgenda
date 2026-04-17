<template>
  <div class="dashboard-container">
    <h1 class="page-title">Dashboard de Sistemas</h1>
    
    <div class="stats-grid">
      <div class="stat-card">
        <i class="pi pi-desktop"></i>
        <div class="stat-info">
          <span class="stat-label">Sistemas Ativos</span>
          <span class="stat-value">42</span>
        </div>
      </div>
      <div class="stat-card accent">
        <i class="pi pi-shield"></i>
        <div class="stat-info">
          <span class="stat-label">Monitorados</span>
          <span class="stat-value">100%</span>
        </div>
      </div>
      <div class="stat-card warning">
        <i class="pi pi-database"></i>
        <div class="stat-info">
          <span class="stat-label">Alertas de Backup</span>
          <span class="stat-value">3</span>
        </div>
      </div>
    </div>

    <div class="content-section">
      <h3>Inventário de Sistemas</h3>
      <p class="placeholder-text">Listagem de ativos e licenças em sincronização...</p>
    </div>
  </div>
</template>

<style scoped>
.dashboard-container {
  color: #ffffff;
}

.page-title {
  font-size: 2rem;
  margin-bottom: 25px;
  color: #ffffff;
  font-weight: 300;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.stat-card {
  background: #041628;
  padding: 25px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  border: 1px solid #1c2b3a;
  transition: transform 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-card i {
  font-size: 2.5rem;
  margin-right: 20px;
  color: #3b82f6;
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-label {
  font-size: 0.9rem;
  color: #94a3b8;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.stat-value {
  font-size: 1.8rem;
  font-weight: bold;
}

.stat-card.accent i { color: #10b981; }
.stat-card.warning i { color: #f59e0b; }

.content-section {
  background: #041628;
  padding: 30px;
  border-radius: 12px;
  border: 1px solid #1c2b3a;
  min-height: 300px;
}

.placeholder-text {
  color: #64748b;
  font-style: italic;
}
</style>
