<template>
  <div ref="mapContainer" style="width: 100%; height: 400px;"></div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, watch } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import iconUrl from 'leaflet/dist/images/marker-icon.png';
import iconRetinaUrl from 'leaflet/dist/images/marker-icon-2x.png';
import shadowUrl from 'leaflet/dist/images/marker-shadow.png';

// ─── Corrige ícones quebrados no Vite/Webpack ────────────────────────────────
delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({ iconUrl, iconRetinaUrl, shadowUrl });

// ─── Props ───────────────────────────────────────────────────────────────────
const props = defineProps<{
  destination: { lat: number; lng: number };
}>();

// ─── Estado interno ──────────────────────────────────────────────────────────
const mapContainer = ref<HTMLDivElement | null>(null);
let map: L.Map | null = null;
let marker: L.Marker | null = null;

// ─── Inicializa o mapa ───────────────────────────────────────────────────────
const initMap = () => {
  if (!mapContainer.value || map) return;

  map = L.map(mapContainer.value).setView(
    [props.destination.lat, props.destination.lng],
    15
  );

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution:
      '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
  }).addTo(map);

  marker = L.marker([props.destination.lat, props.destination.lng]).addTo(map);
};

// ─── Atualiza posição do mapa e marker ───────────────────────────────────────
const updateMap = () => {
  if (!map || !marker) return;

  const newLatLng = L.latLng(props.destination.lat, props.destination.lng);
  map.setView(newLatLng, 15);
  marker.setLatLng(newLatLng);
};

// ─── Lifecycle ───────────────────────────────────────────────────────────────
onMounted(() => {
  // Pequeno delay para garantir que animações de dialog terminem antes do init
  setTimeout(initMap, 100);
});

onUnmounted(() => {
  map?.remove();
  map = null;
  marker = null;
});

// ─── Watcher ─────────────────────────────────────────────────────────────────
watch(
  () => ({ ...props.destination }),
  () => {
    if (map) {
      updateMap();
    } else {
      // Se o mapa ainda não foi iniciado, tenta inicializar
      initMap();
    }
  }
);
</script>

<style scoped>
/* Evita que o container colapse em alguns layouts flex/grid */
div {
  min-height: 400px;
}
</style>