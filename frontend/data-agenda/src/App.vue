<script setup lang="ts">
import { RouterView } from 'vue-router';
</script>

<template>
  <RouterView />
</template>

<style>
@import "./assets/custom-theme.css";

body {
  margin: 0;
  padding: 0;
  background-color: var(--bg-app);
  color: var(--text-main);
}
</style>
