<template>
  <div class="card">
    <h2 class="page-title">Visualização de Clientes</h2>
    <DataTable 
      :value="clientes" 
      responsiveLayout="scroll" 
      class="p-datatable-custom" 
      paginator 
      :rows="10"
    >
      <Column field="nome" header="Nome" sortable></Column>
      <Column field="cnpj" header="CNPJ" sortable></Column>
      <Column field="localidade" header="Localidade"></Column>
      <Column field="nomeResponsavel" header="Responsável"></Column>
      <Column field="telefoneResponsavel" header="Telefone"></Column>
      
      <Column header="Ações">
        <template #body="slotProps">
          <div class="acoes">
            <Button 
              icon="pi pi-pencil" 
              class="p-button-rounded p-button-warning p-button-sm"
              @click="$router.push({name:'cliente-editar', params: { id: slotProps.data.id }})" 
            />
            <Button 
              icon="pi pi-trash" 
              class="p-button-rounded p-button-danger p-button-sm"
              @click="excluirCliente(slotProps.data)" 
            />
            <Button 
              icon="pi pi-file-export" 
              class="p-button-rounded p-button-success p-button-sm"
              @click="exportarManutencoesCliente(slotProps.data)"
              v-tooltip.top="'Exportar Manutenções'"
            />
          </div>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";
import pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from "pdfmake/build/vfs_fonts.js";
pdfMake.vfs = pdfFonts.default;

const URL = import.meta.env.VITE_API_URL;
const clientes = ref([]);

const carregarClientes = async () => {
  try {
    const response = await axios.get(`${URL}/clientes/buscartodos`);
    clientes.value = response.data;
  } catch (error) {
    console.error("Erro ao buscar clientes:", error);
  }
};

const formatarData = (valor) => {
  if (!valor) return "";
  const data = new Date(valor);
  const dia = String(data.getDate()).padStart(2, "0");
  const mes = String(data.getMonth() + 1).padStart(2, "0");
  const ano = data.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

const exportarManutencoesCliente = async (cliente) => {
  try {
    const response = await axios.get(`${URL}/manutencao/listar-cliente/${cliente.id}`);
    const manutencoes = response.data;

    if (manutencoes.length === 0) {
      alert(`O cliente ${cliente.nome} não possui manutenções.`);
      return;
    }

    const colunas = [
      { text: 'Sistema', style: 'tableHeader' },
      { text: 'Tipo', style: 'tableHeader' },
      { text: 'Status', style: 'tableHeader' },
      { text: 'Data Agendada', style: 'tableHeader' },
      { text: 'Técnico', style: 'tableHeader' }
    ];

    const linhas = manutencoes.map(m => {
      return [
        m.sistemaNome,
        m.tipoManutencao,
        m.statusManutencao,
        formatarData(m.dataAgendada),
        m.tecnicoNome
      ];
    });

    const docDefinition = {
      content: [
        { text: `Relatório de Manutenções`, style: 'header' },
        { text: `Cliente: ${cliente.nome}`, style: 'subheader' },
        {
          style: 'table',
          table: {
            headerRows: 1,
            widths: ['*', 'auto', 'auto', 'auto', 'auto'],
            body: [
              colunas,
              ...linhas
            ]
          }
        }
      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true,
          alignment: 'center',
          margin: [0, 0, 0, 5]
        },
        subheader: {
          fontSize: 14,
          bold: false,
          alignment: 'center',
          margin: [0, 0, 0, 10]
        },
        tableHeader: {
          bold: true,
          fontSize: 12,
          color: 'black'
        },
        table: {
          margin: [0, 5, 0, 15]
        }
      }
    };

    pdfMake.createPdf(docDefinition).download(`manutencoes_${cliente.nome.replace(/\s+/g, '_')}.pdf`);

  } catch (error) {
    console.error(`Erro ao exportar manutenções para o cliente ${cliente.nome}:`, error);
    alert(`Não foi possível gerar o relatório para o cliente ${cliente.nome}.`);
  }
};

const excluirCliente = async (cliente) => {
  if (confirm(`Deseja excluir o cliente ${cliente.nome}?`)) {
    try {
      await axios.delete(`${URL}/clientes/${cliente.id}`);
      clientes.value = clientes.value.filter(c => c.id !== cliente.id);
    } catch (error) {
      console.error("Erro ao excluir cliente:", error);
    }
  }
};

onMounted(carregarClientes);
</script>

<style scoped>
.card {
  max-width: 1000px;
  margin: 2rem auto;
  padding: 2rem;
  background: var(--bg-card);
  box-shadow: var(--shadow);
  border-radius: 12px;
  color: var(--text-main);
}

.page-title {
  text-align: center;
  margin-bottom: 2rem;
  color: var(--text-main);
  font-weight: bold;
}

:deep(.p-datatable-custom) {
  background: var(--bg-table) !important;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.p-datatable-custom .p-datatable-thead > tr > th) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  font-weight: bold;
  text-align: center;
  padding: 1rem;
}

:deep(.p-datatable-custom .p-datatable-tbody > tr > td) {
  background: var(--bg-table) !important;
  color: var(--text-table) !important;
  text-align: center;
  padding: 1rem;

}

:deep(.p-datatable-custom .p-datatable-tbody > tr:hover > td) {
  background: var(--bg-hover) !important;
}

:deep(.p-paginator) {
  background: var(--bg-table) !important;
  border: none !important;
  padding: 1rem;
}

.acoes {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
</style>
