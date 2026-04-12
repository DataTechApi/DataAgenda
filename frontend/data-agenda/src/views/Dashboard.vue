
<template>

  
  <div>
    <div class="flex justify-between items-end mb-10">
      <div>
        <h2 class="text-3xl font-black text-[#002855] tracking-tight">Painel de Controle Operacional</h2>
        <p class="text-slate-500 font-medium">Monitoramento em tempo real da frota global de sistemas</p>
      </div>
      <div class="flex items-center space-x-3">
        <div class="relative">
          <input 
            v-model="searchQuery" 
            @input="handleSearch"
            type="text" 
            placeholder="Pesquisar (empresa, técnico, equipamento)..." 
            class="pl-10 pr-4 py-2.5 bg-white border border-slate-200 rounded-xl text-sm focus:outline-none focus:ring-2 focus:ring-[#00ADEE] w-80 transition-all"
          />
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 absolute left-3 top-3.5 text-slate-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
          </svg>
        </div>
        <button class="px-5 py-2.5 bg-white border border-slate-200 text-[#002855] font-bold rounded-xl shadow-sm hover:bg-slate-50 transition-all text-sm">
          Exportar
        </button>
        <button @click="refreshData" class="px-5 py-2.5 bg-[#00ADEE] text-white font-bold rounded-xl shadow-lg shadow-[#00ADEE]/20 hover:bg-[#0092c9] transition-all text-sm flex items-center space-x-2">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
          </svg>
          <span>Atualizar Dados</span>
        </button>
      </div>
    </div>
    
    <!-- Métricas Técnicas -->
    <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-10">
      <div class="bg-white p-7 rounded-2xl shadow-sm border border-slate-100 relative overflow-hidden group">
        <div class="absolute right-0 top-0 p-4 opacity-10 group-hover:scale-110 transition-transform">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-[#002855]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 3v2m6-2v2M9 19v2m6-2v2M5 9H3m2 6H3m18-6h-2m2 6h-2M7 19h10a2 2 0 002-2V7a2 2 0 00-2-2H7a2 2 0 00-2 2v10a2 2 0 002 2zM9 9h6v6H9V9z" />
          </svg>
        </div>
        <div class="text-slate-400 text-[10px] font-black uppercase tracking-[0.15em] mb-2">Total de Ativos</div>
        <div class="text-4xl font-black text-[#002855]">{{ assetStore.assets.length }}</div>
        <div class="mt-4 flex items-center text-xs font-bold text-green-500">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3 mr-1" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
          </svg>
          Sistemas Monitorados
        </div>
      </div>
      
      <div class="bg-white p-7 rounded-2xl shadow-sm border border-slate-100 relative overflow-hidden group">
        <div class="text-slate-400 text-[10px] font-black uppercase tracking-[0.15em] mb-2">Pendências de Manutenção</div>
        <div class="text-4xl font-black text-orange-500">{{ pendingOrders.length }}</div>
        <div class="mt-4 flex items-center text-xs font-bold text-orange-400">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3 mr-1" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
          </svg>
          Requer Planejamento
        </div>
      </div>

      <div class="bg-white p-7 rounded-2xl shadow-sm border border-slate-100 relative overflow-hidden group">
        <div class="text-slate-400 text-[10px] font-black uppercase tracking-[0.15em] mb-2">Sistemas em Alerta</div>
        <div class="text-4xl font-black text-red-600">{{ alertAssets.length }}</div>
        <div class="mt-4 flex items-center text-xs font-bold text-red-500">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3 mr-1" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M11.3 1.047a1 1 0 00-1.6 0l-8.618 12.311a1 1 0 00.82 1.574H18.1a1 1 0 00.82-1.574l-8.618-12.31zM10 5a1 1 0 011 1v3a1 1 0 11-2 0V6a1 1 0 011-1zm0 9a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd" />
          </svg>
          Ação Imediata
        </div>
      </div>

      <div class="bg-[#002855] p-7 rounded-2xl shadow-xl relative overflow-hidden group">
        <div class="text-[#00ADEE] text-[10px] font-black uppercase tracking-[0.15em] mb-2">% de Equipamentos Ativos</div>
        <div class="text-4xl font-black text-white">94.2%</div>
        <div class="mt-4 h-1.5 w-full bg-[#003D7A] rounded-full overflow-hidden">
          <div class="h-full bg-[#00ADEE] w-[94.2%]"></div>
        </div>
      </div>
    </div>

    <!-- Seção de Manutenção Crítica e GIS Placeholder -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
      <div class="lg:col-span-2 bg-white rounded-2xl shadow-sm border border-slate-100 flex flex-col">
        <div class="p-8 border-b border-slate-50 flex justify-between items-center">
          <h3 class="font-black text-[#002855] uppercase tracking-wider text-sm">Próximas Manutenções Críticas</h3>
          <router-link to="/maintenances" class="text-[#00ADEE] text-xs font-bold hover:underline">Ver todas</router-link>
        </div>
        <div class="overflow-x-auto">
          <table class="w-full text-left">
            <thead>
              <tr class="bg-slate-50 text-[10px] uppercase font-black text-slate-400 tracking-widest">
                <th class="px-8 py-4">Sistema</th>
                <th class="px-8 py-4">Tipo</th>
                <th class="px-8 py-4">Data Prevista</th>
                <th class="px-8 py-4">Técnico</th>
                <th class="px-8 py-4">Status</th>
              </tr>
            </thead>
            <tbody class="divide-y divide-slate-50">
              <tr 
                v-for="order in criticalOrders" 
                :key="order.id" 
                @click="openModal(order)"
                class="hover:bg-slate-50 transition-colors cursor-pointer"
              >
                <td class="px-8 py-5 text-sm font-bold text-[#002855]">{{ order.asset?.name }}</td>
                <td class="px-8 py-5">
                  <span class="px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-tighter" 
                    :class="order.type === 'CORRECTIVE' ? 'bg-red-100 text-red-600' : 'bg-blue-100 text-blue-600'">
                    {{ order.type === 'CORRECTIVE' ? 'Corretiva' : 'Preventiva' }}
                  </span>
                </td>
                <td class="px-8 py-5 text-sm text-slate-600 font-medium">{{ formatDate(order.scheduledDate) }}</td>
                <td class="px-8 py-5 text-sm text-slate-600 font-medium">{{ order.technicianName }}</td>
                <td class="px-8 py-5">
                   <div class="flex items-center space-x-2">
                     <div class="w-2 h-2 rounded-full bg-orange-400"></div>
                     <span class="text-xs font-bold text-slate-500 uppercase tracking-tighter">{{ order.status }}</span>
                   </div>
                </td>
              </tr>
              <tr v-if="criticalOrders.length === 0">
                <td colspan="5" class="px-8 py-20 text-center text-slate-400 font-bold uppercase tracking-widest text-xs">
                  Nenhum registro encontrado no servidor
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- GIS Placeholder (conforme imagem) -->
      <div class="bg-white rounded-2xl shadow-sm border border-slate-100 p-8 flex flex-col">
        <h3 class="font-black text-[#002855] uppercase tracking-wider text-sm mb-6 text-center italic">Monitoramento Geográfico (GIS)</h3>
        <div class="flex-1 bg-slate-50 rounded-2xl border-2 border-dashed border-slate-200 flex flex-col items-center justify-center p-10 text-center">
          <div class="w-20 h-20 bg-slate-100 rounded-full flex items-center justify-center mb-4">
             <svg xmlns="http://www.w3.org/2000/svg" class="h-10 w-10 text-slate-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
          </div>
          <p class="text-slate-400 font-bold text-sm uppercase tracking-widest">Mapeamento Global</p>
          <p class="text-slate-300 text-xs mt-2">Leaflet.js Engine Integrada</p>
        </div>
      </div>
    </div>

    <!-- Modal de Detalhes da Manutenção -->
    <div v-if="selectedOrder" class="fixed inset-0 bg-black/50 backdrop-blur-sm flex items-center justify-center z-50 p-4">
      <div class="bg-white rounded-3xl shadow-2xl max-w-2xl w-full overflow-hidden animate-in fade-in zoom-in duration-300">
        <div class="p-8 border-b border-slate-50 flex justify-between items-center bg-[#002855] text-white">
          <h3 class="font-black uppercase tracking-wider text-lg">Detalhes da Manutenção #{{ selectedOrder.id }}</h3>
          <button @click="closeModal" class="text-white/70 hover:text-white transition-colors">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        
        <div class="p-8 grid grid-cols-2 gap-6">
          <div>
            <p class="text-[10px] font-black uppercase text-slate-400 tracking-widest mb-1">Equipamento</p>
            <p class="text-[#002855] font-bold">{{ selectedOrder.asset?.name }}</p>
          </div>
          <div>
            <p class="text-[10px] font-black uppercase text-slate-400 tracking-widest mb-1">Empresa</p>
            <p class="text-[#002855] font-bold">{{ selectedOrder.asset?.company || 'N/A' }}</p>
          </div>
          <div>
            <p class="text-[10px] font-black uppercase text-slate-400 tracking-widest mb-1">Tipo</p>
            <span class="px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-tighter" 
              :class="selectedOrder.type === 'CORRECTIVE' ? 'bg-red-100 text-red-600' : 'bg-blue-100 text-blue-600'">
              {{ selectedOrder.type === 'CORRECTIVE' ? 'Corretiva' : 'Preventiva' }}
            </span>
          </div>
          <div>
            <p class="text-[10px] font-black uppercase text-slate-400 tracking-widest mb-1">Técnico Responsável</p>
            <p class="text-[#002855] font-bold">{{ selectedOrder.technicianName }}</p>
          </div>
          <div class="col-span-2">
            <p class="text-[10px] font-black uppercase text-slate-400 tracking-widest mb-1">Descrição</p>
            <p class="text-slate-600 text-sm leading-relaxed">{{ selectedOrder.description || 'Sem descrição detalhada disponível.' }}</p>
          </div>
        </div>

        <div class="p-8 bg-slate-50 flex justify-end space-x-3">
          <button @click="closeModal" class="px-6 py-3 bg-white border border-slate-200 text-[#002855] font-black rounded-xl hover:bg-slate-100 transition-all text-sm uppercase tracking-widest">
            Fechar
          </button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useAssetStore } from '../store/assetStore'
import { useMaintenanceStore } from '../store/maintenanceStore'
import TheHeader from '../components/TheHeader.vue'

// Pega os stores do Pinia
const assetStore = useAssetStore()
const maintenanceStore = useMaintenanceStore()

// Estados locais
const searchQuery = ref('')
const selectedOrder = ref(null)

// Computados
const pendingOrders = computed(() => 
  maintenanceStore.orders.filter(o => o.status === 'PLANNED')
)

const alertAssets = computed(() => 
  assetStore.assets.filter(a => a.status === 'MAINTENANCE_REQUIRED' || a.status === 'DOWN')
)

const criticalOrders = computed(() => 
  // Se tiver pesquisa, mostra os resultados da pesquisa, senão os 6 primeiros
  searchQuery.value.length > 0 ? maintenanceStore.orders : maintenanceStore.orders.slice(0, 6)
  
)

// Ações
const handleSearch = () => {
  if (searchQuery.value.length > 2 || searchQuery.value.length === 0) {
    maintenanceStore.searchOrders(searchQuery.value)
  }
}

const openModal = (order) => {
  selectedOrder.value = order
}

const closeModal = () => {
  selectedOrder.value = null
}

const refreshData = () => {
  assetStore.fetchAssets()
  maintenanceStore.fetchOrders()
}

// Formata a data bonitinho
const formatDate = (dateString) => {
  if (!dateString) return '-'
  return new Date(dateString).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  })
}

// Quando o componente carrega, ja busca os dados
onMounted(() => {
  refreshData()
})
</script>