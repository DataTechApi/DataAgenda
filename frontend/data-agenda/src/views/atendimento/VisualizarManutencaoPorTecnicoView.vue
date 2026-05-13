<template>
  <div class="card">
    <h2 class="page-title">Visualizar Manutenções</h2>

    <!-- Botões para alternar entre os modos de visualização -->
    <div class="view-switcher">
      <ButtonGroup>
        <Button label="Tabela" @click="modoDeExibicao = 'tabela'" :class="{ 'p-button-outlined': modoDeExibicao !== 'tabela' }" />
        <Button label="Cards" @click="modoDeExibicao = 'card'" :class="{ 'p-button-outlined': modoDeExibicao !== 'card' }" />
        <Button label="Calendário" @click="modoDeExibicao = 'calendario'" :class="{ 'p-button-outlined': modoDeExibicao !== 'calendario' }" />
      </ButtonGroup>
    </div>
    
    <!-- Visualização em Tabela -->
    <div v-if="modoDeExibicao === 'tabela'" class="table-container">
      <DataTable 
        :value="manutencoes" 
        responsiveLayout="scroll" 
        class="p-datatable-custom"
        paginator 
        :rows="10"
        v-model:filters="filters"
        filterDisplay="menu"
        :globalFilterFields="['descricao', 'tecnico.nome', 'sistema.nome', 'sistema.cliente.nome']"
      >
        <Column field="clienteNome" header="Cliente" sortable></Column>
        <Column field="sistemaNome" header="Sistema" sortable></Column>
        
        <Column field="tipoManutencao" header="Tipo" sortable>
          <template #body="slotProps">
            <span :class="'status-badge ' + (slotProps.data.tipoManutencao || '').toLowerCase()">
              {{ slotProps.data.tipoManutencao }}
            </span>
          </template>
        </Column>

        <Column field="statusManutencao" header="Status" sortable>
          <template #body="slotProps">
            <span :class="'status-badge ' + (slotProps.data.statusManutencao || '').toLowerCase()">
              {{ slotProps.data.statusManutencao }}
            </span>
          </template>
        </Column>

        <Column field="dataAgendada" header="Data" sortable>
          <template #body="slotProps">
            {{ formatarData(slotProps.data.dataAgendada) }}
          </template>
        </Column>

        <Column header="Ações">
          <template #body="slotProps">
            <Button 
              icon="pi pi-eye" 
              class="p-button-rounded p-button-info p-button-sm mr-2" 
              @click="$router.push({name:'atendimento-finalizar', params: { id: slotProps.data.id }})" 
            />
            <Button 
              icon="pi pi-map" 
              class="p-button-rounded p-button-success p-button-sm"
              :loading="slotProps.data.loadingMap"
              @click="mostrarMapa(slotProps.data)"
            />
          </template>
        </Column>

        <template #empty>
          <p class="placeholder-text">Nenhuma manutenção encontrada.</p>
        </template>
      </DataTable>
    </div>

    <!-- Visualização em Cards -->
    <div v-if="modoDeExibicao === 'card'" class="card-grid">
      <Card v-for="manutencao in manutencoesOrdenadas" :key="manutencao.id" class="maintenance-card">
        <template #title>{{ manutencao.clienteNome }}</template>
        <template #subtitle>{{ manutencao.sistemaNome }}</template>
        <template #content>
          <div class="card-content">
            <div class="info-row">
              <span class="info-label">Tipo:</span>
              <span :class="'status-badge ' + (manutencao.tipoManutencao || '').toLowerCase()">{{ manutencao.tipoManutencao }}</span>
            </div>
            <div class="info-row">
              <span class="info-label">Status:</span>
              <span :class="'status-badge ' + (manutencao.statusManutencao || '').toLowerCase()">{{ manutencao.statusManutencao }}</span>
            </div>
            <div class="info-row">
              <span class="info-label">Data:</span>
              <span>{{ formatarData(manutencao.dataAgendada) }}</span>
            </div>
          </div>
        </template>
        <template #footer>
          <div class="card-actions">
            <Button icon="pi pi-eye" class="p-button-rounded p-button-info p-button-sm" @click="$router.push({name:'atendimento-finalizar', params: { id: manutencao.id }})" />
            <Button icon="pi pi-map" class="p-button-rounded p-button-success p-button-sm" :loading="manutencao.loadingMap" @click="mostrarMapa(manutencao)" />
          </div>
        </template>
      </Card>
    </div>

    <!-- Visualização em Calendário -->
    <div v-if="modoDeExibicao === 'calendario'" class="calendar-view">
      <div class="calendar-header">
        <Button icon="pi pi-chevron-left" @click="mesAnterior" class="p-button-text" />
        <h2>{{ nomeDoMesAtual }} {{ anoAtual }}</h2>
        <Button icon="pi pi-chevron-right" @click="proximoMes" class="p-button-text" />
      </div>
      <div class="calendar-grid">
        <div v-for="dia in diasDaSemana" :key="dia" class="weekday">{{ dia }}</div>
        <div v-for="dia in diasDoCalendario" :key="dia.date" class="calendar-day" :class="{ 'not-current-month': !dia.isCurrentMonth }">
          <div class="day-number">{{ dia.day }}</div>
          <div class="maintenance-entries">
            <div 
              v-for="manutencao in dia.manutencoes" 
              :key="manutencao.id" 
              class="maintenance-entry" 
              :style="{ backgroundColor: obterCorDoStatus(manutencao.statusManutencao) }"
              @click="abrirDialogoManutencao(manutencao)"
            >
              {{ manutencao.clienteNome }}
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Dialog para exibir o mapa -->
  <Dialog 
    v-model:visible="mapaDialogoVisivel" 
    modal 
    header="Localização do Cliente" 
    :style="{ width: '75vw' }" 
    :breakpoints="{ '1199px': '75vw', '575px': '90vw' }"
  >
    <MapView 
      v-if="manutencaoSelecionada"
      :destination="{ lat: manutencaoSelecionada.clienteLatitude, lng: manutencaoSelecionada.clienteLongitude }"
    />
  </Dialog>

  <!-- Dialog para exibir detalhes da manutenção no calendário -->
  <Dialog 
    v-model:visible="manutencaoDialogoVisivel"
    modal 
    header="Detalhes da Manutenção" 
    :style="{ width: '50vw' }" 
    :breakpoints="{ '1199px': '50vw', '575px': '90vw' }"
  >
    <div v-if="manutencaoSelecionada" class="maintenance-details">
      <h3>{{ manutencaoSelecionada.clienteNome }}</h3>
      <p><strong>Sistema:</strong> {{ manutencaoSelecionada.sistemaNome }}</p>
      <p><strong>Tipo:</strong> <span :class="'status-badge ' + (manutencaoSelecionada.tipoManutencao || '').toLowerCase()">{{ manutencaoSelecionada.tipoManutencao }}</span></p>
      <p><strong>Status:</strong> <span :class="'status-badge ' + (manutencaoSelecionada.statusManutencao || '').toLowerCase()">{{ manutencaoSelecionada.statusManutencao }}</span></p>
      <p><strong>Data:</strong> {{ formatarData(manutencaoSelecionada.dataAgendada) }}</p>
      <div class="dialog-actions">
        <Button label="Ver Detalhes" icon="pi pi-eye" class="p-button-info" @click="$router.push({name:'atendimento-finalizar', params: { id: manutencaoSelecionada.id }})" />
        <Button label="Ver Mapa" icon="pi pi-map" class="p-button-success" @click="mostrarMapa(manutencaoSelecionada)" />
      </div>
    </div>
  </Dialog>
</template>

<script setup>
// Importações de bibliotecas e componentes
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Button from 'primevue/button';
import ButtonGroup from 'primevue/buttongroup';
import Dialog from 'primevue/dialog';
import { FilterMatchMode } from '@primevue/core/api';
import Card from 'primevue/card';
import MapView from '@/components/MapView.vue';

// Estado para controlar qual visualização está ativa: 'tabela', 'card', ou 'calendario'
const modoDeExibicao = ref('tabela');
const URL = import.meta.env.VITE_API_URL;
const manutencoes = ref([]); // Array para armazenar as manutenções buscadas da API

// Lógica para ordenar as manutenções por data, usada na visualização de cards
const manutencoesOrdenadas = computed(() => {
  // Cria uma cópia do array para não modificar o original
  return [...manutencoes.value].sort((a, b) => {
    const dateA = new Date(a.dataAgendada);
    const dateB = new Date(b.dataAgendada);
    // Ordena da data mais antiga para a mais nova
    return dateA.getTime() - dateB.getTime();
  });
});
const filters = ref({
  global: { value: null, matchMode: FilterMatchMode.CONTAINS }
});

// Estado para controlar os dialogs (modais)
const manutencaoSelecionada = ref(null); // Guarda a manutenção que foi clicada
const mapaDialogoVisivel = ref(false); // Controla a visibilidade do dialog do mapa
const manutencaoDialogoVisivel = ref(false); // Controla a visibilidade do dialog de detalhes

// Recupera o ID do técnico logado
let usuario = sessionStorage.getItem('usuario');
let tecnico = JSON.parse(usuario).id;
const tecnicoId = ref(tecnico);

// --- LÓGICA DO CALENDÁRIO ---

// Guarda a data atual para saber qual mês exibir
const dataAtual = ref(new Date()); 
// Nomes dos dias da semana para o cabeçalho do calendário
const diasDaSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];

// Agrupa as manutenções por data para fácil acesso
const manutencoesPorData = computed(() => {
  return manutencoes.value.reduce((acc, manutencao) => {
    const data = new Date(manutencao.dataAgendada).toISOString().split('T')[0];
    if (!acc[data]) {
      acc[data] = [];
    }
    acc[data].push(manutencao);
    return acc;
  }, {});
});

// Obtém o nome do mês atual para o título do calendário
const nomeDoMesAtual = computed(() => {
  return dataAtual.value.toLocaleString('pt-BR', { month: 'long' });
});

// Obtém o ano atual para o título do calendário
const anoAtual = computed(() => {
  return dataAtual.value.getFullYear();
});

// Gera os dias que serão exibidos no grid do calendário
const diasDoCalendario = computed(() => {
  const ano = dataAtual.value.getFullYear();
  const mes = dataAtual.value.getMonth();
  const primeiroDiaDoMes = new Date(ano, mes, 1);
  const ultimoDiaDoMes = new Date(ano, mes + 1, 0);
  const diasNoMes = ultimoDiaDoMes.getDate();
  const diaDaSemanaInicial = primeiroDiaDoMes.getDay();

  const dias = [];

  // Adiciona os últimos dias do mês anterior para preencher o início do calendário
  const ultimoDiaDoMesAnterior = new Date(ano, mes, 0).getDate();
  for (let i = diaDaSemanaInicial; i > 0; i--) {
    const dia = ultimoDiaDoMesAnterior - i + 1;
    const data = new Date(ano, mes - 1, dia);
    dias.push({
      day: dia,
      date: data.toISOString().split('T')[0],
      isCurrentMonth: false,
      manutencoes: manutencoesPorData.value[data.toISOString().split('T')[0]] || []
    });
  }

  // Adiciona os dias do mês atual
  for (let i = 1; i <= diasNoMes; i++) {
    const data = new Date(ano, mes, i);
    dias.push({
      day: i,
      date: data.toISOString().split('T')[0],
      isCurrentMonth: true,
      manutencoes: manutencoesPorData.value[data.toISOString().split('T')[0]] || []
    });
  }

  // Adiciona os primeiros dias do próximo mês para preencher o final do calendário
  const diaDaSemanaFinal = ultimoDiaDoMes.getDay();
  for (let i = 1; i < 7 - diaDaSemanaFinal; i++) {
    const data = new Date(ano, mes + 1, i);
    dias.push({
      day: i,
      date: data.toISOString().split('T')[0],
      isCurrentMonth: false,
      maintenances: manutencoesPorData.value[data.toISOString().split('T')[0]] || []
    });
  }

  return dias;
});

// Funções para navegar entre os meses
function mesAnterior() {
  dataAtual.value = new Date(dataAtual.value.setMonth(dataAtual.value.getMonth() - 1));
}
function proximoMes() {
  dataAtual.value = new Date(dataAtual.value.setMonth(dataAtual.value.getMonth() + 1));
}

// Retorna uma cor baseada no status da manutenção
function obterCorDoStatus(status) {
  if (!status) return '#cccccc'; // Cor padrão
  switch (status.toLowerCase()) {
    case 'preventiva': return '#27ae60';
    case 'emergencial': return '#e74c3c';
    case 'pendente': return '#f39c12';
    case 'executada': return '#2980b9';
    default: return '#cccccc';
  }
}

// Abre o dialog de detalhes da manutenção
const abrirDialogoManutencao = (manutencao) => {
  manutencaoSelecionada.value = manutencao;
  manutencaoDialogoVisivel.value = true;
};

// --- FUNÇÕES GERAIS ---

// Busca as coordenadas de uma cidade usando a API Nominatim
const geocodificarCidade = async (manutencao) => {
  const local = `${manutencao.clienteLocalidade}, Brasil`;

  try {
    const response = await fetch(
      `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(local)}&limit=1`
    );
    const data = await response.json();

    if (data.length > 0) {
      return {
        latitude: parseFloat(data[0].lat),
        longitude: parseFloat(data[0].lon)
      };
    }
  } catch (error) {
    console.error('Erro ao geocodificar:', error);
  }

  return null;
};

// Carrega as manutenções do técnico a partir da API
const carregarManutencoes = async () => {
  try {
    const response = await axios.get(`${URL}/manutencao/listar-tecnico/${tecnicoId.value}`);
    manutencoes.value = response.data;
  } catch (error) {
    console.error("Erro ao carregar manutenções:", error);
  }
};

// Exibe o mapa com a localização do cliente
const mostrarMapa = async (manutencao) => {
  // Se já temos as coordenadas, apenas abre o mapa
  if (manutencao.clienteLatitude && manutencao.clienteLongitude) {
    manutencaoSelecionada.value = manutencao;
    mapaDialogoVisivel.value = true;
    return;
  }

  // Senão, busca as coordenadas primeiro
  manutencao.loadingMap = true;
  const coords = await geocodificarCidade(manutencao);
  manutencao.loadingMap = false;

  if (coords) {
    manutencao.clienteLatitude = coords.latitude;
    manutencao.clienteLongitude = coords.longitude;
    manutencaoSelecionada.value = manutencao;
    mapaDialogoVisivel.value = true;
  } else {
    alert('Não foi possível encontrar a localização do cliente.');
  }
};

// Formata uma data para o formato dd/mm/yyyy
function formatarData(valor) {
  if (!valor) return "";
  const data = new Date(valor);
  const dia = String(data.getDate()).padStart(2, "0");
  const mes = String(data.getMonth() + 1).padStart(2, "0");
  const ano = data.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

// Executa ao montar o componente
onMounted(carregarManutencoes);
</script>

<style scoped>
.view-switcher {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}
.card {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  background: #0f0f0f;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 12px;
}

.page-title {
  text-align: center;
  margin-bottom: 0.5rem;
  color: #2c3e50;
  font-weight: bold;
}

.subtitle {
  text-align: center;
  color: #aaaaaa;
  margin-bottom: 2rem;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
  gap: 1rem;
}

.search-container {
  flex: 1;
  position: relative;
}

.search-container i {
  position: absolute;
  left: 1rem;
  top: 50%;
  transform: translateY(-50%);
  color: #aaaaaa;
}

.search-input {
  width: 100%;
  padding-left: 2.5rem !important;
  background: #1a1a1a !important;
  border: 1px solid #333333 !important;
  color: #ffffff !important;
  border-radius: 6px !important;
}

.search-input::placeholder {
  color: #888888 !important;
}

:deep(.p-datatable-custom) {
  background: #0f0f0f !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: #0f0f0f !important;
  color: #ffffff !important;
  font-weight: bold;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr > td) {
  background: #0f0f0f !important;
  color: #ffffff !important;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: #0f0f0f !important;
}

:deep(.p-paginator) {
  background: #0f0f0f !important;
  border: none !important;
  padding: 1rem;
}

:deep(.p-paginator .p-paginator-element) {
  color: #ffffff !important;
}

.status-badge {
  padding: 0.25rem 0.6rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  color: #fff;
}

.status-badge.preventiva  { background: #27ae60; }
.status-badge.emergencial { background: #e74c3c; }
.status-badge.pendente    { background: #f39c12; }
.status-badge.executada   { background: #2980b9; }

.placeholder-text {
  color: #aaaaaa;
  font-style: italic;
  text-align: center;
  padding: 20px;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1.5rem;
}

.maintenance-card {
  background: #1a1a1a;
  border: 1px solid #333333;
  color: #ffffff;
  border-radius: 8px;
}

.card-content {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.info-label {
  font-weight: bold;
  color: #aaaaaa;
}

.card-actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}


.calendar-view {
  color: #ffffff;
}

.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.calendar-header h2 {
  color: #ffffff;
}

.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 5px;
}

.weekday {
  text-align: center;
  font-weight: bold;
  color: #aaaaaa;
}

.calendar-day {
  border: 1px solid #333333;
  border-radius: 4px;
  min-height: 100px;
  padding: 5px;
}

.day-number {
  font-weight: bold;
}

.not-current-month {
  color: #555555;
}

.maintenance-entries {
  display: flex;
  flex-direction: column;
  gap: 3px;
  margin-top: 5px;
}

.maintenance-entry {
  padding: 2px 4px;
  border-radius: 4px;
  color: #ffffff;
  font-size: 0.75rem;
  cursor: pointer;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.maintenance-details p {
  margin: 0.5rem 0;
}

.dialog-actions {
  margin-top: 1.5rem;
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}
</style>