<template>
  <div class="dashboard-container">
    <h1 class="page-title">Dashboard de Técnicos</h1>
    
    <div class="stats-grid">
      <div class="stat-card">
        <i class="pi pi-briefcase"></i>
        <div class="stat-info">
          <span class="stat-label">Técnicos Ativos</span>
          <span class="stat-value">12</span>
        </div>
      </div>
      <div class="stat-card accent">
        <i class="pi pi-star"></i>
        <div class="stat-info">
          <span class="stat-label">Avaliação Média</span>
          <span class="stat-value">4.9</span>
        </div>
      </div>
      <div class="stat-card warning">
        <i class="pi pi-user-minus"></i>
        <div class="stat-info">
          <span class="stat-label">Em Pausa/Férias</span>
          <span class="stat-value">1</span>
        </div>
      </div>
    </div>

    <div class="content-section">
      <h3>Equipe Técnica</h3>
      <p class="placeholder-text">Dados de produtividade e disponibilidade em tempo real...</p>
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
