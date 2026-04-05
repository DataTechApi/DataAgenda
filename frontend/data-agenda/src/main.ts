// src/main.ts
import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

/// import PrimeVue from 'primevue/config'
/// import "primevue/themes/saga-blue.css"
/// import "primevue/resources/primevue.min.css"
/// import "primeicons/primeicons.css"

const app = createApp(App)

app.use(router)
// app.use(PrimeVue)

app.mount('#app')