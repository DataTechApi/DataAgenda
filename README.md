
# API 4º Semestre BD  
# DATAGENDA

## Grupo DataTech

| [Desafio](#-desafio) | [Solução](#-solução) | [Backlog do Produto](#-Backlog-do-Produto) | [DoR](#-dor---definition-of-ready) | [DoD](#-dod---definition-of-done) | [Cronograma de Sprints](#-cronograma-de-sprints) | [Tecnologias](#-tecnologias) | [Manual de Instalação](#-manual-de-instalação) | [Equipe](#-equipe) |
|--------|--------|--------|--------|--------|--------|--------|--------|--------|

**Status do Projeto:** Em Desenvolvimento 🚧  

**Documentação da API:** Swagger  

**Repositório:** GitHub  

---

# 🏅 Desafio

O problema apresentado foi a falta de um programa que gerencia a alocação, visualização e extração de dados sobre as diversas manutenções correntivas e preventivas dos sistemas dos clientes que compôem o ecosistema da Altave

O desafio consiste em desenvolver uma **aplicação web para gerenciamento de manutenções preventivas em sistemas corporativos**.

A aplicação deve permitir:

- Visualizar **manutenções pendentes**
- **Agendar chamados** de manutenção
- **Alocar técnicos responsáveis**
- Gerenciar o **status dos chamados**
- Gerar **relatórios de acompanhamento**

Além disso, todas as informações devem ser armazenadas em um **banco de dados relacional**, garantindo controle, rastreabilidade e histórico das manutenções realizadas.

---

# 🏅 Solução

A solução proposta é o **DataAgenda**, uma plataforma desenvolvida pela equipe **DataTech**, composta por alunos do **4º semestre do curso de Banco de Dados**.

O sistema permite:

- Cadastro e gerenciamento de **ativos e sistemas**
- **Agendamento de manutenções preventivas**
- Controle de **chamados de manutenção**
- **Notificação de técnicos**
- Visualização de **relatórios e histórico de manutenção**

O objetivo é **facilitar a visualização e gerenciamento das manutenções preventivas** dentro da empresa **Altave**, proporcionando melhor organização das atividades técnicas.

---

## 📋 Backlog do Produto

| Rank | Prioridade | User Story | Story Points | Sprint | Requisito | Status |
|------|-----------|-----------|--------------|--------|-----------|--------|
| 1 | Alta | Como **gestor**, quero agendar uma manutenção preventiva para um ativo, para evitar falhas e manutenções corretivas | 5 | 2 | R02 | ✅ |
| 2 | Alta | Como **gestor**, quero abrir chamados de manutenção corretiva quando ocorrer um problema, para registrar e acompanhar a resolução | 5 | 2 | R02 | ✅ |
| 3 | Alta | Como **gestor**, quero atribuir um técnico responsável ao chamado, para garantir que a manutenção seja executada | 3 | 2 | R02 | ✅ |
| 4 | Alta | Como **técnico**, quero visualizar os chamados atribuídos a mim, para gerenciar minhas atividades | 3 | 2 | R03 | ✅ |
| 5 | Alta | Como **técnico**, quero alterar o status do chamado, para refletir o andamento da manutenção | 3 | 2 | R03 | ⬜ |
| 6 | Alta | Como **gestor**, quero visualizar o histórico de manutenções de um ativo, para análise e tomada de decisão | 3 | 3 | R04 | ⬜ |
| 7 | Alta | Como **administrador**, quero acessar um menu com todas as telas do sistema, para navegar entre as funcionalidades | 3 | 1 | R01 | ⬜ |
| 8 | Alta | Como **administrador**, quero visualizar a lista de ativos cadastrados, para acompanhar status e acessar detalhes de cada ativo | 3 | 1 | R01 | ⬜ |
| 9 | Alta | Como **administrador**, quero editar ou remover ativos cadastrados, para corrigir dados incorretos ou excluir ativos inativos | 5 | 2 | R01 | ⬜ |
| 10 | Média | Como **gestor**, quero visualizar um painel com todos os chamados e seus status, para monitorar os atendimentos| 5 | 3 | R04 | ⬜ |
| 11 | Média | Como **técnico**, quero registrar observações da manutenção realizada, para registrar o atedimento e ter um histórico | 2 | 2 | R03 | ⬜ |
| 12 | Média | Como **gestor**, quero gerar relatórios de manutenções realizadas, para controle e auditoria | 5 | 3 | R04 | ⬜ |
| 13 | Baixa | Como **gestor**, quero visualizar estatísticas de manutenções, para identificar padrões e melhorias | 3 | 3 | R04 | ⬜ |
| 14 | Baixa | Como **administrador**, quero gerenciar permissões de usuários, para controlar acessos ao sistema | 5 | 3 | R05 | ⬜ |

---

# 📋 Requisitos do Sistema

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

## 🟣 R04 – Relatórios e Monitoramento

| Item | Descrição |
|------|----------|
| **Código** | R04 |
| **Nome** | Relatórios e Monitoramento |
| **Descrição** | Permitir a visualização de dados, histórico e métricas de manutenção para apoio à tomada de decisão |
| **Funcionalidades** | - Visualizar histórico de manutenções por ativo <br> - Visualizar painel de chamados e status <br> - Gerar relatórios de manutenções <br> - Visualizar estatísticas e indicadores |

---

## 🔴 R05 – Gestão de Usuários e Permissões

| Item | Descrição |
|------|----------|
| **Código** | R05 |
| **Nome** | Gestão de Usuários e Permissões |
| **Descrição** | Permitir o controle de acesso ao sistema por meio de perfis e permissões de usuários |
| **Funcionalidades** | - Gerenciar usuários <br> - Definir perfis de acesso (administrador, gestor, técnico) <br> - Controlar permissões de funcionalidades |

---

# 🏃 DoR - Definition of Ready

Uma **User Story está pronta para desenvolvimento** quando:

- O objetivo está claramente descrito
- Os critérios de aceitação estão definidos
- As dependências estão identificadas
- As tarefas técnicas estão quebradas e estimadas
- Existe ambiente ou dados de teste disponíveis

---

# 🏆 DoD - Definition of Done

Uma **User Story é considerada concluída** quando:

- O código foi desenvolvido conforme os critérios
- Testes foram realizados com sucesso
- Documentação foi atualizada
- O Product Owner aprovou a entrega
- A funcionalidade está pronta para produção

---

# 📅 Cronograma de Sprints

| Sprint | Período | Principais Entregas |
|------|------|------|
| Sprint 1 | 08/09 – 28/09 | Estrutura do banco, backend inicial(Entidades e Endpoints), FrontEnd definido|
| Sprint 2 | 06/10 – 26/10 | Sistema de alertas, gestão de chamados, segurança, implementação BackEnd->FrontEnd |
| Sprint 3 | 03/11 – 23/11 | Relatórios, documentação, correções e deploy |

## Backlog das Sprints

| Sprint | Link |
|------|------|
| Sprint1 | https://github.com/orgs/DataTechApi/projects/11 |
| Sprint2 | https://github.com/orgs/DataTechApi/projects/13 |
| Sprint3 | https://github.com/orgs/DataTechApi/projects/13 |

---

# 💻 Tecnologias

| Categoria | Tecnologias |
|-----------|-------------|
| **Back-end** | Java 21 • Spring Boot • JPA • Maven |
| **Front-end** | Vue |
| **Banco de Dados** | OracleDB |
| **Documentação da API** | Swagger |
| **Controle de Versão** | Git • GitHub |
| **IDE** | IntelliJ IDEA • VS Code |
| **Testes de API** | Postman |
| **Ambiente de Desenvolvimento** | DevContainer |

---

# 📖 Manual de Instalação

## 🛠 Pré-requisitos

- Java 21+
- Maven
- Docker
- Git

---

## 1️⃣ Clonar o Repositório

```bash
git clone <link-do-repositorio>
cd dataagenda

```

---

## 🌿 Estratégia de Branch

| **Branch** | **Função** |
|-------------|------------|
| `main` | Contém o código **estável** e **pronto para produção**. Nenhum commit direto é feito nela — apenas merges revisados. |
| `sprint1`, `sprint2`, `sprint3` | Cada branch representa uma **entrega de sprint**, com features específicas desenvolvidas nesse ciclo. |
| **Pull Request** | Utilizado para **unir branches** (ex: `sprint2` → `main`), garantindo **revisão de código** e **controle de versão seguro** antes do merge. |

## 🧩 Padrão de Commits

Adotamos o padrão **[Conventional Commits]** para manter um histórico **organizado**, **padronizado** e **de fácil leitura**.  
Esse padrão define prefixos que descrevem o tipo de alteração realizada no projeto.  

### Exemplos de commits válidos:
- `feat: criação da tela de login`
- `fix: correção no endpoint de autenticação`
- `docs: atualização do README`
- `refactor: melhoria na estrutura do componente`

### Estrutura do Commit

A estrutura básica de um commit segue o padrão:

<tipo> (escopo opcional): <descrição breve>

### Exemplos de estruturas:

- `feat(login): adicionar validação de credenciais`
- `fix(api): corrigir erro de resposta no endpoint de usuários`
- `docs(readme): atualizar seção de instalação`

### Tipos mais usados:
| Tipo | Descrição |
|------|------------|
| `feat` | Nova funcionalidade |
| `fix` | Correção de bug |
| `docs` | Alterações em documentação |
| `style` | Mudanças visuais ou de formatação (sem alterar comportamento) |
| `refactor` | Refatoração de código sem alterar funcionalidade |
| `test` | Adição ou ajuste de testes |

---

# Videos 

## Video Sprint1 

[Assistir vídeo](VideoSprint1.mp4)

---


## 👥 Equipe

| Nome | Função |
|-----|-------|
| Jackson Tadeu | Scrum Master |
| Cauê Gandini | Product Owner |
| Davi Gramacho | Desenvolvedor |
| Abimael Santos | Desenvolvedor |
