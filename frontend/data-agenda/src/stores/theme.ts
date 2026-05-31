import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useThemeStore = defineStore('theme', () => {
  const isDark = ref(localStorage.getItem('theme') !== 'light')

  function apply() {
    const html = document.documentElement
    if (isDark.value) {
      html.classList.remove('light-theme')
      html.classList.add('app-dark')
    } else {
      html.classList.add('light-theme')
      html.classList.remove('app-dark')
    }
  }

  function toggle() {
    isDark.value = !isDark.value
    localStorage.setItem('theme', isDark.value ? 'dark' : 'light')
    apply()
  }

  return { isDark, toggle, apply }
})
