# API 4º Semestre BD

# DataTech - DataAgenda

# Documentação - Sprint 2

> Status da Sprint: Finalizado

## 🏅 Desafio <a id="desafio"></a>

Implementar funcionalidades que proporcionem o máximo de funcionalidades e usabilidades para o cliente, com um foco na parte operacional, como: Abertura de chamados, agendamento de manutenções, alteração do status de um chamado, para isso teremos que fazer a conexão do BackEnd com o FrontEnd, de modo que os dois fiquem coesos.

## 📋 User Stories <a id="us"></a>

| Rank | Prioridade | User Story | Story Points | Sprint | Requisito | Status |
|------|-----------|-----------|--------------|--------|-----------|--------|
| 1 | Alta | Como **gestor**, quero agendar uma manutenção preventiva para um ativo, para evitar falhas e manutenções corretivas | 5 | 2 | R02 | ✅ |
| 2 | Alta | Como **gestor**, quero abrir chamados de manutenção corretiva quando ocorrer um problema, para registrar e acompanhar a resolução | 5 | 2 | R02 | ✅ |
| 3 | Alta | Como **gestor**, quero atribuir um técnico responsável ao chamado, para garantir que a manutenção seja executada | 3 | 2 | R02 | ✅ |
| 4 | Alta | Como **técnico**, quero visualizar os chamados atribuídos a mim, para gerenciar minhas atividades | 3 | 2 | R03 | ✅ |
| 5 | Alta | Como **técnico**, quero alterar o status do chamado, para refletir o andamento da manutenção | 3 | 2 | R03 | ✅ |

# 🔗 Relação entre Product Backlog e Sprint Backlog

## 📋 Rastreabilidade entre User Stories e Tarefas Técnicas

| US | User Story | Task | Descrição Técnica | Responsável | Status |
|----|------------|------|-------------------|-------------|--------|
| US01 | Agendar manutenção preventiva | #46 | Implementação inicial Script SQL | Cauê | ✅ Done |
| US01 | Agendar manutenção preventiva | #47 | Endpoint buscar manutenção por ID | Jackson | ✅ Done |
| US01 | Agendar manutenção preventiva | #50 | Página de visualização de manutenção administrador | Jackson | ✅ Done |
| US02 | Abrir chamados corretivos | #46 | Implementação inicial Script SQL | Cauê | ✅ Done |
| US02 | Abrir chamados corretivos | #47 | Endpoint buscar manutenção por ID | Jackson | ✅ Done |
| US02 | Abrir chamados corretivos | #49 | Endpoint finalizar atendimento | Jackson | ✅ Done |
| US02 | Abrir chamados corretivos | #50 | Página de visualização manutenção administrador | Jackson | ✅ Done |
| US03 | Atribuir técnico ao chamado | #42 | Página de acesso a técnicos para finalizar manutenção | Jackson | ✅ Done |
| US03 | Atribuir técnico ao chamado | #48 | Retorno do técnico via DTOResponse | Jackson | ✅ Done |
| US03 | Atribuir técnico ao chamado | #50 | Tela administrador manutenção | Jackson | ✅ Done |
| US04 | Técnico visualizar chamados atribuídos | #31 | Tela de visualização do técnico | Abimael | ✅ Done |
| US04 | Técnico visualizar chamados atribuídos | #37 | Estilização da tela dos chamados | Abimael/Cleber | ✅ Done |
| US04 | Técnico visualizar chamados atribuídos | #44 | Tela de visualização de manutenção por técnico | Jackson | ✅ Done |
| US04 | Técnico visualizar chamados atribuídos | #45 | Endpoint visualizar manutenções por técnico | Jackson | ✅ Done |
| US05 | Técnico alterar status do chamado | #42 | Página acesso técnico para finalizar manutenção | Jackson | ✅ Done |
| US05 | Técnico alterar status do chamado | #49 | Endpoint finalizar atendimento | Jackson | ✅ Done |
| US05 | Técnico alterar status do chamado | #44 | Tela manutenção por técnico | Jackson | ✅ Done |

---

# ⚙️ Funcionalidades de Integração (impactam várias US)

| Task | Descrição | Categoria | Status |
|------|------------|------------|--------|
| #24 | Atualização frontend | Frontend | ✅ |
| #27 | Atualização documentação | Documentação | ✅ |
| #28 | Comunicação FrontEnd ↔ BackEnd | Integração/API | ✅ |
| #51 | Refatoração do caminho dashboard manutenção | Backend | ✅ |

---


# 📚 Classificação Técnica das Tasks da Sprint

| Categoria | Tasks |
|-----------|-------|
| Frontend | #24 #30 #31 #37 |
| Backend | #33 #34 #39 #40 #41 #45 #47 #48 #49 #51 |
| Banco de Dados | #46 |
| Integração/API | #28 #35 |
| Documentação | #27 |
| Autenticação | #30 #32 #33 #34 #35 |
| Visualização | #31 #37 #44 #50 |

---

# 📌 Critérios de Priorização da Sprint

As prioridades foram definidas considerando:

- Implementação do fluxo principal de manutenção
- Integração entre FrontEnd e BackEnd
- Operação do técnico no sistema
- Controle de autenticação
- Persistência correta dos dados
- Disponibilização do fluxo completo gestor → técnico → manutenção

---

# 📊 Progresso Final da Sprint

| Tipo | Quantidade |
|------|------------|
| Tasks Totais | 23 |
| Concluídas | 23 |
| Em progresso | 0 |
| Pendentes | 0 |
| Percentual concluído | 100% |

---

# 🏁 Resultado da Sprint

Sprint concluída com entrega das funcionalidades previstas:

✅ Abertura de chamados  
✅ Visualização por técnico  
✅ Alteração de status  
✅ Login  
✅ Comunicação Front ↔ Back  
✅ Fluxo operacional completo  
✅ Persistência em banco  
✅ Interface administrativa  
✅ Integração completa do sistema

## 📋 Requisitos <a id="re"></a>

## 🔵 R01 – Gestão de Ativos

| Item | Descrição |
|------|----------|
| **Código** | R01 |
| **Nome** | Gestão de Ativos |
| **Descrição** | Permitir o cadastro e gerenciamento dos ativos que passarão por manutenção |
| **Funcionalidades** | - Cadastrar ativos <br> - Listar ativos <br> - Editar ativos <br> - Associar ativos a manutenções |

---

## 🟠 R02 – Gestão de Manutenção (Chamados)

| Item | Descrição |
|------|----------|
| **Código** | R02 |
| **Nome** | Gestão de Manutenção |
| **Descrição** | Permitir o controle completo de chamados de manutenção preventiva e corretiva |
| **Funcionalidades** | - Agendar manutenção preventiva <br> - Abrir chamado corretivo <br> - Atribuir técnico ao chamado <br> - Visualizar detalhes do chamado |

---

## 🟢 R03 – Operações do Técnico

| Item | Descrição |
|------|----------|
| **Código** | R03 |
| **Nome** | Operações do Técnico |
| **Descrição** | Permitir que o técnico visualize e atualize suas atividades de manutenção |
| **Funcionalidades** | - Visualizar chamados atribuídos <br> - Atualizar status do chamado <br> - Acompanhar histórico de atividades |



## 🏅 DoR - Definition of Ready <a id="dor"></a>

|              Critério              | Descrição                                                                                          |
| :--------------------------------: | -------------------------------------------------------------------------------------------------- |
|        Clareza na Descrição        | A User Story está escrita no formato “Como [persona], quero [ação] para que [objetivo]”            |
|  Critérios de Aceitação Definidos  | A história possui critérios objetivos que indicam o que é necessário para considerá-la concluída.  |
|  Cenários de Teste Especificados   | A história tem pelo menos 1 cenário de teste estruturado (Dado, Quando, Então).                    |
|            Independente            | A história pode ser implementada sem depender de outra tarefa da mesma Sprint.                     |
|      Escopo Técnico Validado       | Há clareza se a história envolve frontend, backend ou ambos — com pontos de integração definidos.  |
|     Validação com PO e equipe      | A história foi discutida em refinamento ou planning e validada com o time técnico.                 |
| Alinhamento com arquitetura atual  | A funcionalidade proposta está coerente com o funcionamento já entregue na Sprint 1.               |

## 🏅 DoD - Definition of Done <a id="dod"></a>

|                 Critério                 | Descrição                                                                                                        |
| :--------------------------------------: | ---------------------------------------------------------------------------------------------------------------- |
|     Critérios de Aceitação atendidos     | Todos os critérios definidos na US foram implementados e validados com sucesso.                                  |
| Cenários de Teste executados e aprovados | Todos os cenários descritos foram validados manualmente ou por teste automatizado.                               |
|      Dados persistidos corretamente      | Informações sensíveis como prompt, resposta, avaliação ou contexto vetorizado foram salvos conforme esperado.    |
|       Feedback visual implementado       | Funcionalidades como pop-ups, mensagens de erro ou interface estão claras e acessíveis ao usuário.               |
|        Fluxo seguro e controlado         | Não há caminhos quebrados nem submissões incoerentes no fluxo de avaliação ou navegação.                         |
|      Código revisado (Code Review)       | A US passou por revisão entre pares antes da integração.                                                         |
|  Integrado com o restante da aplicação   | A funcionalidade foi testada junto com o fluxo completo do sistema (Ex: Envio → Resposta → Avaliação → Escolha). |
|         Validação final com o PO         | O PO testou e confirmou que a funcionalidade atende ao esperado.                                                 |
|            Pronta para Deploy            | A funcionalidade pode ser entregue ao ambiente de produção/testes finais sem pendências.                         |


## 👥 Equipe

| Nome | Função |
|-----|-------|
| Jackson Tadeu | Scrum Master |
| Cauê Gandini | Product Owner |
| Davi Gramacho | Desenvolvedor |
| Abimael Santos | Desenvolvedor |
| Cleber Kirch | Desenvolvedor |
