<template>
  <header class="bg-gray-900 text-white shadow-md">
    <div class="container mx-auto flex justify-between items-center p-4">
      <!-- Logo -->
      <h1 class="text-2xl font-bold">Minha SPA</h1>

      <!-- Menu -->
      <nav class="relative">
        <button 
          @click="toggleMenu" 
          class="px-4 py-2 bg-gray-800 rounded hover:bg-gray-700 focus:outline-none"
        >
          Menu
        </button>

        <!-- Dropdown -->
        <ul 
          v-if="menuOpen" 
          class="absolute right-0 mt-2 w-48 bg-white text-gray-900 rounded shadow-lg"
        >
          <li class="px-4 py-2 hover:bg-gray-200 cursor-pointer">Home</li>
          <li class="px-4 py-2 hover:bg-gray-200 cursor-pointer">Sobre</li>
          <li class="px-4 py-2 hover:bg-gray-200 cursor-pointer">Serviços</li>
          <li class="px-4 py-2 hover:bg-gray-200 cursor-pointer">Contato</li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'

const menuOpen = ref(false)

function toggleMenu() {
  menuOpen.value = !menuOpen.value
}
</script>

<style>
/* Caso não use Tailwind, pode estilizar aqui */
</style>
