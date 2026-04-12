<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100">
    <div class="bg-white shadow-lg rounded-lg w-full max-w-md p-8">
      <h2 class="text-2xl font-bold text-center mb-6">Cadastro de Clientes</h2>
      
      <form @submit.prevent="handleSubmit" class="space-y-4">
        <!-- Nome -->
        <div>
          <label class="block text-gray-700 font-medium mb-1">Nome</label>
          <input 
            v-model="cliente.nome" 
            type="text" 
            class="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Digite o nome completo"
            required
          />
        </div>

        <!-- Email -->
        <div>
          <label class="block text-gray-700 font-medium mb-1">Email</label>
          <input 
            v-model="cliente.email" 
            type="email" 
            class="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Digite o email"
            required
          />
        </div>

        <!-- Telefone -->
        <div>
          <label class="block text-gray-700 font-medium mb-1">Telefone</label>
          <input 
            v-model="cliente.telefone" 
            type="tel" 
            class="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="(xx) xxxxx-xxxx"
          />
        </div>

        <!-- Endereço -->
        <div>
          <label class="block text-gray-700 font-medium mb-1">Endereço</label>
          <textarea 
            v-model="cliente.endereco" 
            class="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Digite o endereço"
          ></textarea>
        </div>

        <!-- Botão -->
        <button 
          type="submit" 
          class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-500 transition"
        >
          Cadastrar
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const cliente = reactive({
  nome: '',
  email: '',
  telefone: '',
  endereco: ''
})

function handleSubmit() {
  console.log('Cliente cadastrado:', cliente)
  alert(`Cliente ${cliente.nome} cadastrado com sucesso!`)
}
</script>
