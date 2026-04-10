import { defineStore } from 'pinia'
import axios from 'axios'

export const useAssetStore = defineStore('asset', {
  state: () => ({
    assets: [],
    loading: false
  }),
  actions: {
    async fetchAssets() {
      this.loading = true
      try {
        const response = await axios.get('/api/assets')
        this.assets = response.data
      } catch (error) {
        console.error('Error fetching assets:', error)
      } finally {
        this.loading = false
      }
    }
  }
})
