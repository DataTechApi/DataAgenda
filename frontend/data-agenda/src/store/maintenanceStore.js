import { defineStore } from 'pinia'
import axios from 'axios'

export const useMaintenanceStore = defineStore('maintenance', {
  state: () => ({
    orders: [],
    loading: false
  }),
  actions: {
    async fetchOrders() {
      this.loading = true
      try {
        const response = await axios.get('/api/maintenances')
        this.orders = response.data
      } catch (error) {
        console.error('Error fetching orders:', error)
      } finally {
        this.loading = false
      }
    },
    async searchOrders(query) {
      this.loading = true
      try {
        const response = await axios.get(`/api/maintenances/search?q=${query}`)
        this.orders = response.data
      } catch (error) {
        console.error('Error searching orders:', error)
      } finally {
        this.loading = false
      }
    }
  }
})
