<template>
  <div ref="mapContainer" style="width: 100%; height: 400px;"></div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, watch } from 'vue';
import L from 'leaflet';

// Define props for the component
const props = defineProps<{
  destination: { lat: number; lng: number };
}>();

const mapContainer = ref<HTMLDivElement | null>(null);
let map: L.Map | null = null;
let marker: L.Marker | null = null;

const initMap = () => {
  if (mapContainer.value && !map) {
    // Initialize map centered on the destination
    map = L.map(mapContainer.value).setView([props.destination.lat, props.destination.lng], 15);

    // Add OpenStreetMap tile layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    // Add a marker at the destination
    marker = L.marker([props.destination.lat, props.destination.lng]).addTo(map);
  }
};

const updateMap = () => {
  if (map && marker) {
    const newLatLng = L.latLng(props.destination.lat, props.destination.lng);
    map.setView(newLatLng, 15);
    marker.setLatLng(newLatLng);
  }
};

onMounted(() => {
  // A slight delay to ensure the dialog animation is complete before initializing the map
  setTimeout(() => {
    initMap();
  }, 100);
});

onUnmounted(() => {
  if (map) {
    map.remove();
  }
});

// Watch for changes in destination prop and update the map
watch(() => props.destination, () => {
  if (map) {
    updateMap();
  }
}, { deep: true });

</script>

<style scoped>
/* Scoped styles for the map container if needed */
</style>
