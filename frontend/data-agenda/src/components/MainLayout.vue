<template>
  <div class="flex h-screen bg-[#F8FAFC]">
    <!-- Sidebar ALTAVE -->
    <aside class="w-72 bg-[#002855] text-white flex flex-col shadow-2xl z-20">
      <div class="p-8 flex items-center space-x-3 border-b border-[#003D7A]">
        <div class="w-10 h-10 bg-[#00ADEE] rounded-lg flex items-center justify-center font-black text-white shadow-lg">
          A
        </div>
        <div class="flex flex-col">
          <span class="text-xl font-black tracking-tighter uppercase leading-none">Anarquia</span>
          <span class="text-[10px] text-[#00ADEE] font-bold uppercase tracking-widest mt-1">Altave Intelligence</span>
        </div>
      </div>
      
      <nav class="flex-1 p-6 space-y-2">
        <p class="text-[10px] text-slate-400 font-bold uppercase tracking-widest mb-4 px-4">Menu Principal</p>
        
        <router-link to="/" class="flex items-center space-x-3 p-4 rounded-xl hover:bg-[#003D7A] transition-all group" active-class="bg-[#00ADEE] shadow-lg shadow-[#00ADEE]/20">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z" />
          </svg>
          <span class="font-bold">Painel de Controle</span>
        </router-link>
        
        <router-link to="/assets" class="flex items-center space-x-3 p-4 rounded-xl hover:bg-[#003D7A] transition-all group" active-class="bg-[#00ADEE] shadow-lg shadow-[#00ADEE]/20">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" />
          </svg>
          <span class="font-bold">Gestão de Ativos</span>
        </router-link>
        
        <router-link to="/maintenances" class="flex items-center space-x-3 p-4 rounded-xl hover:bg-[#003D7A] transition-all group" active-class="bg-[#00ADEE] shadow-lg shadow-[#00ADEE]/20">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012-2" />
          </svg>
          <span class="font-bold">Ordens de Manutenção</span>
        </router-link>
      </nav>

      <div class="p-8 bg-[#001D3D] border-t border-[#003D7A]">
        <div class="flex items-center space-x-3 text-xs">
          <div class="w-2 h-2 bg-green-400 rounded-full animate-pulse shadow-[0_0_8px_#4ade80]"></div>
          <span class="text-slate-400 font-bold uppercase tracking-widest">Sistema Ativo</span>
        </div>
      </div>
    </aside>

    <!-- Content Area -->
    <main class="flex-1 overflow-auto flex flex-col">
      <header class="bg-white h-24 shadow-sm px-10 flex justify-between items-center z-10 border-b border-slate-200">
        <div>
          <h1 class="text-[10px] font-black text-slate-400 uppercase tracking-[0.2em] mb-1">Central de Operações</h1>
          <p class="text-2xl font-black text-[#002855]">Interface de Planejamento</p>
        </div>
        
        <div class="flex items-center space-x-8">
          <div class="text-right border-r pr-8 border-slate-200">
            <p class="text-sm font-black text-[#002855]">Administrador Altave</p>
            <p class="text-xs text-[#00ADEE] font-bold uppercase tracking-tighter">Equipe Técnica</p>
          </div>
          <div class="relative group cursor-pointer">
            <div class="w-14 h-14 rounded-2xl bg-gradient-to-br from-[#00ADEE] to-[#002855] text-white flex items-center justify-center font-black text-xl shadow-xl transform transition-transform group-hover:scale-105">
              AD
            </div>
            <div class="absolute -bottom-1 -right-1 w-4 h-4 bg-green-500 border-2 border-white rounded-full"></div>
          </div>
        </div>
      </header>

      <div class="p-10 flex-1">
        <slot></slot>
      </div>
    </main>
  </div>
</template>

<script setup>
</script>
