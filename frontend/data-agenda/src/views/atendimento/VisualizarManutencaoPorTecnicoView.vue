<template>
  <div class="card">
    <div class="header">
      <h2 class="page-title">Visualizar Manutenções</h2>
      <Button 
        label="Exportar PDF" 
        icon="pi pi-file-pdf" 
        class="p-button-secondary p-button-sm"
        @click="exportarPDF" 
        :disabled="manutencoes.length === 0"
      />
    </div>

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
import { ref, onMounted, computed } from 'vue'
import axios from '@/services/api'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Button from 'primevue/button'
import ButtonGroup from 'primevue/buttongroup'
import Dialog from 'primevue/dialog'
import { FilterMatchMode } from '@primevue/core/api'
import Card from 'primevue/card'
import MapView from '@/components/MapView.vue'
import pdfMake from "pdfmake/build/pdfmake"
import * as pdfFonts from "pdfmake/build/vfs_fonts.js"
pdfMake.vfs = pdfFonts.default

// Estado principal
const modoDeExibicao = ref('tabela')
const URL = import.meta.env.VITE_API_URL
const manutencoes = ref([])
const filters = ref({
  global: { value: null, matchMode: FilterMatchMode.CONTAINS }
})
const manutencaoSelecionada = ref(null)
const mapaDialogoVisivel = ref(false)
const manutencaoDialogoVisivel = ref(false)

// --- AUTENTICAÇÃO COM JWT ---
const token = sessionStorage.getItem('token')

function decodeToken(token) {
  try {
    const payload = token.split('.')[1]
    return JSON.parse(atob(payload))
  } catch (e) {
    console.error('Erro ao decodificar token:', e)
    return null
  }
}

let tecnicoId = ref(null)
if (token) {
  const decoded = decodeToken(token)
  tecnicoId.value = decoded?.id || null
}

// --- LÓGICA DO CALENDÁRIO ---
const dataAtual = ref(new Date())
const diasDaSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb']

const manutencoesPorData = computed(() => {
  return manutencoes.value.reduce((acc, m) => {
    const data = new Date(m.dataAgendada).toISOString().split('T')[0]
    if (!acc[data]) acc[data] = []
    acc[data].push(m)
    return acc
  }, {})
})

const nomeDoMesAtual = computed(() =>
  dataAtual.value.toLocaleString('pt-BR', { month: 'long' })
)
const anoAtual = computed(() => dataAtual.value.getFullYear())

function mesAnterior() {
  dataAtual.value = new Date(dataAtual.value.setMonth(dataAtual.value.getMonth() - 1))
}
function proximoMes() {
  dataAtual.value = new Date(dataAtual.value.setMonth(dataAtual.value.getMonth() + 1))
}

function obterCorDoStatus(status) {
  if (!status) return '#cccccc'
  switch (status.toLowerCase()) {
    case 'preventiva': return '#27ae60'
    case 'emergencial': return '#e74c3c'
    case 'pendente': return '#f39c12'
    case 'executada': return '#2980b9'
    default: return '#cccccc'
  }
}

const abrirDialogoManutencao = (manutencao) => {
  manutencaoSelecionada.value = manutencao
  manutencaoDialogoVisivel.value = true
}

// --- FUNÇÕES GERAIS ---
function formatarData(valor) {
  if (!valor) return ""
  const data = new Date(valor)
  const dia = String(data.getDate()).padStart(2, "0")
  const mes = String(data.getMonth() + 1).padStart(2, "0")
  const ano = data.getFullYear()
  return `${dia}/${mes}/${ano}`
}

const exportarPDF = () => {
  if (manutencoes.value.length === 0) return
  const tecnicoNome = manutencoes.value[0].tecnicoNome

  const colunas = [
    { text: 'Cliente', style: 'tableHeader' },
    { text: 'Sistema', style: 'tableHeader' },
    { text: 'Tipo', style: 'tableHeader' },
    { text: 'Status', style: 'tableHeader' },
    { text: 'Data Agendada', style: 'tableHeader' }
  ]

  const linhas = manutencoes.value.map(m => [
    m.clienteNome,
    m.sistemaNome,
    m.tipoManutencao,
    m.statusManutencao,
    formatarData(m.dataAgendada)
  ])

  const docDefinition = {
    content: [
      { text: 'Relatório de Manutenções', style: 'header' },
      { text: `Técnico: ${tecnicoNome}`, style: 'subheader' },
      {
        style: 'table',
        table: {
          headerRows: 1,
          widths: ['*', '*', 'auto', 'auto', 'auto'],
          body: [colunas, ...linhas]
        }
      }
    ],
    styles: {
      header: { fontSize: 18, bold: true, alignment: 'center', margin: [0, 0, 0, 5] },
      subheader: { fontSize: 14, alignment: 'center', margin: [0, 0, 0, 10] },
      tableHeader: { bold: true, fontSize: 12, color: 'black' },
      table: { margin: [0, 5, 0, 15] }
    }
  }

  pdfMake.createPdf(docDefinition).download(`manutencoes_${tecnicoNome.replace(/\s+/g, '_')}.pdf`)
}

const geocodificarCidade = async (manutencao) => {
  const local = `${manutencao.clienteLocalidade}, Brasil`
  try {
    const response = await fetch(
      `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(local)}&limit=1`
    )
    const data = await response.json()
    if (data.length > 0) {
      return { latitude: parseFloat(data[0].lat), longitude: parseFloat(data[0].lon) }
    }
  } catch (error) {
    console.error('Erro ao geocodificar:', error)
  }
  return null
}

const carregarManutencoes = async () => {
  try {
    const response = await axios.get(`${URL}/atendimento/listar-tecnico/${tecnicoId.value}`)
    manutencoes.value = response.data
  } catch (error) {
    console.error("Erro ao carregar manutenções:", error)
  }
}

const mostrarMapa = async (manutencao) => {
  if (manutencao.clienteLatitude && manutencao.clienteLongitude) {
    manutencaoSelecionada.value = manutencao
    mapaDialogoVisivel.value = true
    return
  }
  manutencao.loadingMap = true
  const coords = await geocodificarCidade(manutencao)
  manutencao.loadingMap = false
  if (coords) {
    manutencao.clienteLatitude = coords.latitude
    manutencao.clienteLongitude = coords.longitude
    manutencaoSelecionada.value = manutencao
    mapaDialogoVisivel.value = true
  } else {
    alert('Não foi possível encontrar a localização do cliente.')
  }
}

// Executa ao montar
onMounted(carregarManutencoes)
</script>


<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.view-switcher {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}
.card {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  background: var(--bg-card);
  box-shadow: var(--shadow);
  border-radius: 12px;
  color: var(--text-main);
}

.page-title {
  text-align: center;
  margin-bottom: 0.5rem;
  color: var(--text-main);
  font-weight: bold;
}

.subtitle {
  text-align: center;
  color: var(--text-muted);
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
  color: var(--text-muted);
}

.search-input {
  width: 100%;
  padding-left: 2.5rem !important;
  background: var(--bg-input) !important;
  border: 1px solid var(--border-input) !important;
  color: var(--text-table) !important;
  border-radius: 6px !important;
}

.search-input::placeholder {
  color: var(--text-muted) !important;
}

:deep(.p-datatable-custom) {
  background: var(--bg-table) !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  font-weight: bold;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr > td) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: var(--bg-hover) !important;
}

:deep(.p-paginator) {
  background: var(--bg-table) !important;
  border: none !important;
  padding: 1rem;
}

:deep(.p-paginator .p-paginator-element) {
  color: var(--text-table) !important;
}

.status-badge {
  padding: 0.25rem 0.6rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  color: #fff;
}

.status-badge.preventiva  { background: var(--status-success); }
.status-badge.emergencial { background: var(--status-danger); }
.status-badge.pendente    { background: var(--status-warning); }
.status-badge.executada   { background: var(--status-info); }

.placeholder-text {
  color: var(--text-muted);
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
  background: var(--bg-input);
  border: 1px solid var(--border-input);
  color: var(--text-main);
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
  color: var(--text-muted);
}

.card-actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}


.calendar-view {
  color: var(--text-main);
}

.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.calendar-header h2 {
  color: var(--text-main);
}

.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 5px;
}

.weekday {
  text-align: center;
  font-weight: bold;
  color: var(--text-muted);
}

.calendar-day {
  border: 1px solid var(--border-input);
  border-radius: 4px;
  min-height: 100px;
  padding: 5px;
}

.day-number {
  font-weight: bold;
}

.not-current-month {
  color: var(--text-muted);
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
  color: var(--text-table);
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