<template>
 
  <div class="bg-white rounded-2xl shadow-sm border border-slate-100 flex flex-col">
    <div class="p-8 border-b border-slate-50 flex justify-between items-center">
      <div>
        <h2 class="text-2xl font-black text-[#002855]">Gestão de Ativos</h2>
        <p class="text-slate-500 text-sm font-medium">Inventário de hardware global da frota Altave</p>
      </div>
      <button class="px-6 py-3 bg-[#002855] text-white font-bold rounded-xl shadow-lg hover:bg-[#001d3d] transition-all flex items-center space-x-2">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z" clip-rule="evenodd" />
        </svg>
        <span>Novo Ativo</span>
      </button>
    </div>
    
    <div class="overflow-x-auto">
      <table class="w-full text-left">
        <thead>
          <tr class="bg-slate-50 text-[10px] uppercase font-black text-slate-400 tracking-widest">
            <th class="px-8 py-5">Nome do Sistema</th>
            <th class="px-8 py-5">Tipo</th>
            <th class="px-8 py-5">Localização</th>
            <th class="px-8 py-5">Horas de Uso</th>
            <th class="px-8 py-5">Status</th>
            <th class="px-8 py-5 text-right">Ações</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-slate-50">
          <tr v-for="asset in assetStore.assets" :key="asset.id" class="hover:bg-slate-50 transition-colors">
            <td class="px-8 py-5">
              <div class="flex flex-col">
                <span class="text-sm font-bold text-[#002855]">{{ asset.name }}</span>
                <span class="text-[10px] text-slate-400 font-bold uppercase tracking-tighter">{{ asset.serialNumber }}</span>
              </div>
            </td>
            <td class="px-8 py-5 text-sm text-slate-600 font-medium">{{ asset.type }}</td>
            <td class="px-8 py-5 text-sm text-slate-600 font-medium">
              <div class="flex items-center space-x-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-[#00ADEE]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                <span>{{ asset.location }}</span>
              </div>
            </td>
            <td class="px-8 py-5 text-sm font-bold text-slate-600">{{ asset.hoursOfUse }}h</td>
            <td class="px-8 py-5">
              <span class="px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-tighter"
                :class="{
                  'bg-green-100 text-green-600': asset.status === 'ACTIVE',
                  'bg-orange-100 text-orange-600': asset.status === 'MAINTENANCE_REQUIRED',
                  'bg-red-100 text-red-600': asset.status === 'DOWN'
                }">
                {{ asset.status === 'ACTIVE' ? 'Ativo' : asset.status === 'DOWN' ? 'Inativo' : 'Manutenção Requerida' }}
              </span>
            </td>
            <td class="px-8 py-5 text-right">
              <button class="text-slate-400 hover:text-[#00ADEE] transition-colors">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                  <path d="M13.586 3.586a2 2 0 112.828 2.828l-.707.707-2.828-2.828.707-.707zM11.36 6.036l-9.435 9.435a.5.5 0 00-.126.285l-1.208 4.83a.5.5 0 00.588.588l4.83-1.208a.5.5 0 00.285-.126l9.435-9.435-2.828-2.828z" />
                </svg>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useAssetStore } from '../store/assetStore'
import MenuLateral from '../components/MenuLateral.vue'

const assetStore = useAssetStore()

onMounted(() => {
  assetStore.fetchAssets()
})
</script>
