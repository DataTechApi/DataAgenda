<template>
 
  <div class="bg-white rounded-2xl shadow-sm border border-slate-100 flex flex-col">
    <div class="p-8 border-b border-slate-50 flex justify-between items-center">
      <div>
        <h2 class="text-2xl font-black text-[#002855]">Ordens de Manutenção</h2>
        <p class="text-slate-500 text-sm font-medium">Controle de intervenções preventivas e corretivas</p>
      </div>
      <button class="px-6 py-3 bg-[#00ADEE] text-white font-bold rounded-xl shadow-lg hover:bg-[#0092c9] transition-all flex items-center space-x-2">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-11a1 1 0 10-2 0v2H7a1 1 0 100 2h2v2a1 1 0 102 0v-2h2a1 1 0 100-2h-2V7z" clip-rule="evenodd" />
        </svg>
        <span>Nova O.M.</span>
      </button>
    </div>
    

    <div class="overflow-x-auto">
      <table class="w-full text-left">
        <thead>
          <tr class="bg-slate-50 text-[10px] uppercase font-black text-slate-400 tracking-widest">
            <th class="px-8 py-5">Ativo</th>
            <th class="px-8 py-5">Tipo</th>
            <th class="px-8 py-5">Descrição</th>
            <th class="px-8 py-5">Agendado para</th>
            <th class="px-8 py-5">Técnico</th>
            <th class="px-8 py-5">Status</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-slate-50">
          <tr v-for="order in maintenanceStore.orders" :key="order.id" class="hover:bg-slate-50 transition-colors">
            <td class="px-8 py-5">
              <span class="text-sm font-bold text-[#002855]">{{ order.asset?.name || 'Sistema Desconhecido' }}</span>
            </td>
            <td class="px-8 py-5">
              <span class="px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-tighter" 
                :class="order.type === 'CORRECTIVE' ? 'bg-red-100 text-red-600' : 'bg-blue-100 text-blue-600'">
                {{ order.type === 'CORRECTIVE' ? 'Corretiva' : 'Preventiva' }}
              </span>
            </td>
            <td class="px-8 py-5 text-sm text-slate-600 font-medium max-w-xs truncate">{{ order.description }}</td>
            <td class="px-8 py-5 text-sm text-slate-600 font-bold italic">{{ formatDate(order.scheduledDate) }}</td>
            <td class="px-8 py-5 text-sm text-slate-600 font-medium">{{ order.technicianName }}</td>
            <td class="px-8 py-5">
              <div class="flex items-center space-x-2">
                <div class="w-2 h-2 rounded-full shadow-sm"
                  :class="{
                    'bg-orange-400': order.status === 'PLANNED',
                    'bg-blue-400': order.status === 'IN_PROGRESS',
                    'bg-green-500': order.status === 'COMPLETED'
                  }"></div>
                <span class="text-xs font-bold text-slate-500 uppercase tracking-tighter">{{ translateStatus(order.status) }}</span>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useMaintenanceStore } from '../store/maintenanceStore'
import TheHeader from '../components/TheHeader.vue'

const maintenanceStore = useMaintenanceStore()

const formatDate = (dateString) => {
  if (!dateString) return '-'
  return new Date(dateString).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}

const translateStatus = (status) => {
  const map = {
    'PLANNED': 'Planejado',
    'IN_PROGRESS': 'Em Execução',
    'COMPLETED': 'Concluído',
    'CANCELLED': 'Cancelado'
  }
  return map[status] || status
}

onMounted(() => {
  maintenanceStore.fetchOrders()
})
</script>
