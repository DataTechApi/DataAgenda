  <template>
    <div class="layout">
      <!-- Header Superior -->
      <header class="header">
        <img src="../assets/logoDataTech.jpeg" alt="Logo" class="logo" />
        <span>DATA AGENDA</span>
      </header>

      <!-- Sidebar Lateral -->
      <aside class="sidebar">
        <ul>
          <!-- Dashboard Geral -->
          <li>
            <RouterLink to="/dashboard" class="router-link">
              <div class="menu-item">
                <div class="menu-label">
                  <i class="pi pi-home"></i>
                  <span>Dashboard Geral</span>
                </div>
              </div>
            </RouterLink>
          </li>

          <!-- Menu Clientes -->
          <li>
            <div class="menu-item">
              <RouterLink to="/dashboard/cliente/dashboard" class="router-link">
                <div class="menu-label">
                  <i class="pi pi-users"></i>
                  <span>Clientes</span>
                </div>
              </RouterLink>
              <i
                :class="openMenu === 'cliente' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'"
                class="arrow"
                @click="toggleMenu('cliente')"
              ></i>
            </div>
            <ul class="submenu" v-show="openMenu === 'cliente'">
              <li>
                <RouterLink to="/dashboard/cliente/cadastrar" class="router-link">
                  <i class="pi pi-plus"></i>
                  <span>Cadastrar</span>
                </RouterLink>
              </li>
              <li>
                <RouterLink to="/dashboard/cliente/visualizar" class="router-link">
                  <i class="pi pi-eye"></i>
                  <span>Visualizar</span>
                </RouterLink>
              </li>
            </ul>
          </li>

          <!-- Menu Manutenção -->
          <li>
            <div class="menu-item">
              <RouterLink to="/dashboard/manutencao/dashboard" class="router-link">
                <div class="menu-label">
                  <i class="pi pi-wrench"></i>
                  <span>Manutenção</span>
                </div>
              </RouterLink>
              <i
                :class="openMenu === 'manutencao' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'"
                class="arrow"
                @click="toggleMenu('manutencao')"
              ></i>
            </div>
            <ul class="submenu" v-show="openMenu === 'manutencao'">
              <li>
                <RouterLink to="/dashboard/manutencao/cadastrar" class="router-link">
                  <i class="pi pi-plus"></i>
                  <span>Agendar</span>
                </RouterLink>
              </li>
              <li>
                <RouterLink to="/dashboard/manutencao/visualizar" class="router-link">
                  <i class="pi pi-eye"></i>
                  <span>Visualizar</span>
                </RouterLink>
              </li>
            </ul>
          </li>

          <!-- Menu Sistemas -->
          <li>
            <div class="menu-item">
              <RouterLink to="/dashboard/sistema/dashboard" class="router-link">
                <div class="menu-label">
                  <i class="pi pi-desktop"></i>
                  <span>Sistemas</span>
                </div>
              </RouterLink>
              <i
                :class="openMenu === 'sistema' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'"
                class="arrow"
                @click="toggleMenu('sistema')"
              ></i>
            </div>
            <ul class="submenu" v-show="openMenu === 'sistema'">
              <li>
                <RouterLink to="/dashboard/sistema/cadastrar" class="router-link">
                  <i class="pi pi-plus"></i>
                  <span>Cadastrar</span>
                </RouterLink>
              </li>
              <li>
                <RouterLink to="/dashboard/sistema/visualizar" class="router-link">
                  <i class="pi pi-eye"></i>
                  <span>Visualizar</span>
                </RouterLink>
              </li>
            </ul>
          </li>

          <!-- Menu Técnico -->
          <li>
            <div class="menu-item">
              <RouterLink to="/dashboard/tecnico/dashboard" class="router-link">
                <div class="menu-label">
                  <i class="pi pi-user-edit"></i>
                  <span>Técnico</span>
                </div>
              </RouterLink>
              <i
                :class="openMenu === 'tecnico' ? 'pi pi-chevron-down' : 'pi pi-chevron-right'"
                class="arrow"
                @click="toggleMenu('tecnico')"
              ></i>
            </div>
            <ul class="submenu" v-show="openMenu === 'tecnico'">
              <li>
                <RouterLink to="/dashboard/tecnico/cadastrar" class="router-link">
                  <i class="pi pi-plus"></i>
                  <span>Cadastrar</span>
                </RouterLink>
              </li>
              <li>
                <RouterLink to="/dashboard/tecnico/visualizar" class="router-link">
                  <i class="pi pi-eye"></i>
                  <span>Visualizar</span>
                </RouterLink>
              </li>

            </ul>
          </li>
        </ul>
<!-- Botão Logout -->
      <button class="router-link logout-btn" @click="logout">
        <div class="menu-item">
          <div class="menu-label">
            <i class="pi pi-sign-out"></i>
            <span>Efetuar Logout</span>
          </div>
        </div>
      </button>

      <!-- Rodapé com usuário logado -->
      <div class="sidebar-footer">
        <i class="pi pi-user"></i>
        <span>{{ usuarioLogado }}</span>
      </div>
      </aside>

      <!-- Conteúdo Dinâmico -->
      <main class="content">
        <RouterView />
      </main>
    </div>
  </template>

  <script setup>
  import 'primeicons/primeicons.css'
  import { RouterLink, RouterView, useRouter } from 'vue-router'
  import { ref } from 'vue'

  const openMenu = ref(null)
  const router = useRouter()

  // Recupera somente o nome do usuário salvo no login
  let usuario = sessionStorage.getItem('usuario')
  let nomeUsuario = 'Usuário'

  if (usuario) {
    try {
      nomeUsuario = JSON.parse(usuario).nome || 'Usuário'
    } catch (e) {
      // Se não for JSON, usa direto
      nomeUsuario = usuario
    }
  }

  const usuarioLogado = ref(nomeUsuario)

  function toggleMenu(menu) {
    openMenu.value = openMenu.value === menu ? null : menu
  }

  function logout() {
    // Limpa sessionStorage e localStorage
    sessionStorage.clear()
    localStorage.clear()

    // Redireciona para login
    router.push('/login')
  }
  </script>


  <style scoped>
  .layout {
    display: grid;
    grid-template-columns: 250px 1fr;
    grid-template-rows: 80px 1fr;
    grid-template-areas:
      'header header'
      'sidebar content';
    height: 100vh;
    font-family: 'Segoe UI', sans-serif;
  }

  .header {
    grid-area: header;
    background: var(--bg-header);
    color: var(--text-main);
    display: flex;
    align-items: center;
    padding: 0 30px;
    font-size: 1.5rem;
    font-weight: bold;
    letter-spacing: 2px;
    border-bottom: 1px solid var(--border-color);
  }

  .logo {
    height: 80px;
    margin-right: 15px;
  }

  .sidebar {
    grid-area: sidebar;
    background: var(--bg-sidebar);
    color: var(--text-main);
    padding: 10px;
    overflow-y: auto;
  }
  .sidebar {
    grid-area: sidebar;
    background: var(--bg-sidebar);
    color: var(--text-main);
    padding: 10px;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
  }

  .sidebar ul {
    list-style: none;
    padding: 0;
    margin: 0;
    flex-grow: 1; /* ocupa o espaço disponível */
  }

  .logout-btn {
    width: 100%;
    background: none;
    border: none;
    cursor: pointer;
    text-align: left;
    margin-top: auto; /* empurra para o rodapé */
    padding: 12px;
  }

  .sidebar-footer {
    padding: 12px;
    border-top: 1px solid var(--border-color);
    display: flex;
    align-items: center;
    font-weight: bold;
  }

  .sidebar-footer i {
    margin-right: 8px;
  }


  .sidebar ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }

  .sidebar li {
    margin-bottom: 5px;
    border-radius: 8px;
    transition: background 0.3s;
  }

  .sidebar li:hover {
    background: var(--border-color);
  }

  .menu-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0;
  }

  .menu-label {
    display: flex;
    align-items: center;
    padding: 12px;
    width: 100%;
  }

  .sidebar i {
    margin-right: 10px;
    font-size: 1.2rem;
  }

  .arrow {
    padding: 12px;
    cursor: pointer;
    margin-left: auto;
  }

  .submenu {
    list-style: none;
    padding-left: 25px;
    margin-top: 5px;
  }

  .submenu li {
    padding: 0;
    margin-bottom: 4px;
    border-radius: 6px;
    transition: background 0.3s;
  }

  .content {
    grid-area: content;
    padding: 20px;
    background: var(--bg-app);
    overflow-y: auto;
  }

  .router-link {
    color: inherit;
    text-decoration: none;
    display: flex;
    align-items: center;
    width: 100%;
  }

  .submenu .router-link {
    padding: 8px 12px;
  }
  </style>
