# API 4º Semestre BD

# DataTech - DataAgenda

# Documentação - Sprint 3

> Status da Sprint: 🟨 Em progresso

---

# 🎯 Sprint Goal

Realizar a estabilização do sistema através da correção de bugs e ajustes de regras de negócio, além da implementação de funcionalidades estratégicas de visualização, planejamento e deploy da aplicação.

---

# 🏅 Desafio <a id="desafio"></a>

Implementar correções de bugs e ajustes de lógica de negócio, desenvolver funcionalidades estratégicas de visualização (dashboard, mapas e agenda), concluir o deploy da aplicação e disponibilizar um manual de uso para o cliente.

---

# 📋 Product Backlog - Sprint 3 <a id="us"></a>

| Rank | Prioridade | User Story | Story Points | Sprint | Requisito | Dependência | Valor de Negócio | Status |
|------|-----------|-----------|--------------|--------|-----------|-------------|------------------|--------|
| 1 | Alta | Como **gestor**, quero visualizar o histórico de manutenções de um ativo, para análise e tomada de decisão | 3 | 3 | R04 | R02 | Facilita auditoria e análise operacional | 🟨 Doing |
| 2 | Média | Como **gestor**, quero visualizar um painel com todos os chamados e seus status, para monitorar os atendimentos | 5 | 3 | R04 | R02 | Melhora acompanhamento operacional | 🟨 Doing |
| 3 | Média | Como **gestor**, quero gerar relatórios de manutenções realizadas, para controle e auditoria | 5 | 3 | R04 | US01 | Auxilia auditoria e documentação | 🟨 Doing |
| 4 | Baixa | Como **gestor**, quero visualizar estatísticas de manutenções, para identificar padrões e melhorias | 3 | 3 | R04 | US01 | Apoia tomada de decisão estratégica | 🟨 Doing |
| 5 | Baixa | Como **administrador**, quero gerenciar permissões de usuários, para controlar acessos ao sistema | 5 | 3 | R05 | Login/Auth | Reforça segurança do sistema | 🟨 Doing |
| 6 | Baixa | Como **usuário**, quero visualizar mapas dentro da aplicação, para localizar melhor chamados e sistemas | 5 | 3 | R04 | R02 | Facilita localização de atendimentos | ⬜ To Do |
| 7 | Baixa | Como **usuário**, quero visualizar um painel de agenda, para ter maior noção do planejamento dos chamados | 5 | 3 | R04 | R02 | Melhora organização das atividades | ⬜ To Do |

---

# 📚 Épicos

| Épico | Descrição |
|---|---|
| Gestão Operacional | Controle de chamados, técnicos e manutenções |
| Visualização Estratégica | Dashboards, estatísticas, relatórios e mapas |
| Planejamento | Agenda e organização de atendimentos |
| Segurança e Controle | Gestão de permissões e acessos |
| Estabilização do Sistema | Correções de bugs e melhorias gerais |

---

# 📋 Critérios de Story Points

| Story Points | Complexidade |
|---|---|
| 1 | Muito simples |
| 3 | Simples |
| 5 | Média |
| 8 | Complexa |
| 13 | Muito complexa |

---

# 📋 Requisitos Funcionais <a id="re"></a>

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

---

## 🟣 R04 – Visualização e Relatórios

| Item | Descrição |
|------|----------|
| **Código** | R04 |
| **Nome** | Painéis e Relatórios |
| **Descrição** | Permitir visualização estratégica das informações do sistema |
| **Funcionalidades** | - Dashboard de chamados <br> - Relatórios de manutenção <br> - Agenda operacional <br> - Estatísticas <br> - Mapas |

---

## 🔴 R05 – Controle de Acesso

| Item | Descrição |
|------|----------|
| **Código** | R05 |
| **Nome** | Gestão de Usuários e Permissões |
| **Descrição** | Permitir gerenciamento de acessos e permissões dos usuários |
| **Funcionalidades** | - Controle de perfis <br> - Restrições de acesso <br> - Permissões por usuário |

---

# 🔒 Requisitos Não Funcionais

## RNF01 – Segurança

- Controle de permissões por perfil
- Proteção de rotas autenticadas

---

## RNF02 – Performance

- Consultas otimizadas para dashboard e relatórios
- Sistema preparado para múltiplos acessos simultâneos

---

## RNF03 – Disponibilidade

- Sistema acessível via navegador web
- Compatível com navegadores modernos
- Deploy em ambiente online

---

## RNF04 – Usabilidade

- Navegação intuitiva
- Feedback visual para ações do usuário

---

# 📋 Exemplo de Refinamento de User Story

## US01 – Histórico de Manutenções

### História

Como **gestor**,  
quero visualizar o histórico de manutenções de um ativo,  
para análise e tomada de decisão.

### Critérios de Aceitação

- O sistema deve permitir pesquisar ativos por nome ou código
- O histórico deve exibir:
  - Data da manutenção
  - Técnico responsável
  - Tipo da manutenção
  - Status do chamado
- Os registros devem ser ordenados do mais recente para o mais antigo
- Apenas usuários autenticados com permissão podem visualizar

### Cenário de Teste

**Dado** que exista um ativo cadastrado com manutenções registradas  
**Quando** o gestor acessar o histórico do ativo  
**Então** o sistema deve listar corretamente todas as manutenções relacionadas

---

# 🏅 DoR - Definition of Ready <a id="dor"></a>

| Critério | Descrição |
| :---: | --- |
| Clareza na Descrição | A User Story está escrita no formato “Como [persona], quero [ação] para que [objetivo]” |
| Critérios de Aceitação Definidos | A história possui critérios objetivos para validação |
| Cenários de Teste Especificados | A história possui ao menos um cenário estruturado |
| Independente | Pode ser desenvolvida sem bloqueios da mesma sprint |
| Escopo Técnico Validado | Backend/frontend e integrações estão definidos |
| Validação com PO e Equipe | História refinada e validada com o time |
| Alinhamento com Arquitetura Atual | Compatível com a estrutura já implementada |

---

# 🏅 DoD - Definition of Done <a id="dod"></a>

| Critério | Descrição |
| :---: | --- |
| Critérios de Aceitação atendidos | Todos os critérios foram implementados |
| Cenários de Teste aprovados | Testes realizados e validados |
| Dados persistidos corretamente | Informações salvas corretamente no banco |
| Feedback visual implementado | Mensagens e feedbacks claros ao usuário |
| Fluxo seguro e controlado | Navegação sem falhas ou inconsistências |
| Código revisado | Code Review realizado |
| Integrado ao restante do sistema | Funcionalidade validada no fluxo completo |
| Validação final com o PO | Aprovação do Product Owner |
| Pronta para Deploy | Sem pendências críticas |

---

# 🚀 Melhorias Gerais Planejadas para Sprint 3

- Correção de bugs identificados nas sprints anteriores
- Refatoração de endpoints críticos
- Padronização de respostas da API
- Melhorias na experiência do usuário
- Criação de dashboards interativos
- Implementação de visualização geográfica
- Implementação de agenda operacional
- Otimização de consultas SQL
- Preparação do ambiente de produção
- Criação do manual de uso do sistema
- Melhorias de segurança e permissões
- Testes integrados entre frontend e backend

---

# 👥 Equipe

| Nome | Função |
|-----|-------|
| Jackson Tadeu | Scrum Master |
| Cauê Gandini | Product Owner |
| Davi Gramacho | Desenvolvedor |
| Abimael Santos | Desenvolvedor |
| Cleber Kirch | Desenvolvedor |

---

# 🛠 Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java Spring Boot | Backend |
| Vue | Frontend |
| OracleDB | Banco de Dados |
| Docker | Containerização |
| Swagger | Documentação da API |
| Git/GitHub | Versionamento |

---

# 📌 Status Gerais da Sprint

| Status | Significado |
|---|---|
| ⬜ To Do | Não iniciado |
| 🟨 Doing | Em desenvolvimento |
| 🟦 Review | Em revisão/testes |
| 🟩 Done | Concluído |
| 🟥 Blocked | Bloqueado |

---
