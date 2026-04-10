<template>
  <div class="h-full w-full">
    <div id="map" class="h-full w-full rounded-lg z-0"></div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css' // Importa o CSS do Leaflet aqui
import { useAssetStore } from '../store/assetStore'

// Cria um ref para o mapa do Leaflet
const map = ref(null)
const assetStore = useAssetStore()

// Função para iniciar ou atualizar o mapa
const initMap = () => {
  // Se o mapa já existe, remove ele pra recriar
  if (map.value) {
    map.value.remove()
  }

  // Inicializa o mapa com uma view padrão (Brasil central)
  map.value = L.map('map').setView([-15.7801, -47.9292], 3)

  // Adiciona a camada de tiles do OpenStreetMap
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors' // Atribuição é importante
  }).addTo(map.value)

  // Adiciona os marcadores (pinos) dos ativos no mapa
  assetStore.assets.forEach(a => {
    // Só adiciona se tiver lat/lon
    if (a.latitude && a.longitude) {
      L.marker([a.latitude, a.longitude])
        .addTo(map.value)
        .bindPopup(`<b>${a.name}</b><br>${a.location}<br>Status: ${a.status}`) // Info no popup
    }
  })
}

// Observa mudanças nos ativos e atualiza o mapa
watch(() => assetStore.assets, () => {
  initMap()
}, { deep: true }) // Observa a fundo o array de ativos

onMounted(() => {
  // Inicializa o mapa quando o componente é montado
  initMap()
})
</script>

<style scoped>
/* Estilos básicos para o mapa */
#map {
  height: 100%;
  width: 100%;
}

/* Isso aqui ajuda a evitar problemas de fonte/ícone no Leaflet */
.leaflet-container {
  font-family: inherit;
  font-size: initial;
}
</style>
