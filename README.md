# API 4º Semestre BD  
# DATAGENDA

<img src="assets/cabecalho.png">

## DataTech

| [Desafio](#-desafio) | [Solução](#-solução) | [Backlog do Produto](#-backlog-do-produto) | [DoR](#-dor---definition-of-ready) | [DoD](#-dod---definition-of-done) | [Cronograma de Sprints](#-cronograma-de-sprints) | [Tecnologias](#-tecnologias) | [Manual de Instalação](#-manual-de-instalação) | [Equipe](#-equipe) |
|--------|--------|--------|--------|--------|--------|--------|--------|--------|

**Status do Projeto:** Em Desenvolvimento 🚧  

**Documentação da API:** Swagger  

**Repositório:** GitHub  

---

# 🏅 Desafio

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

# 📋 Backlog do Produto

| Rank | Prioridade | User Story | Sprint | Status |
|----|----|----|----|----|
| 1 | Alta | Como técnico, quero visualizar a lista de ativos cadastrados para identificar onde realizar manutenção | 2 | Pendente |
| 2 | Alta | Como gestor, quero agendar manutenções preventivas para cada ativo | 2 | Pendente |
| 3 | Alta | Como técnico, quero receber notificações de chamados agendados | 2 | Pendente |
| 4 | Alta | Como técnico, quero registrar o status do chamado | 3 | Pendente |
| 5 | Alta | Como gestor, quero visualizar relatórios de chamados concluídos | 3 | Pendente |
| 6 | Alta | Como administrador, quero controlar permissões de acesso | 1 | Pendente |
| 7 | Média | Como gestor, quero uma interface intuitiva para visualizar rapidamente o status dos ativos | 2 | Pendente |

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
| Sprint 1 | 08/09 – 28/09 | Setup do ambiente, criação do banco de dados, lógica inicial |
| Sprint 2 | 06/10 – 26/10 | Sistema de alertas, gestão de chamados, segurança |
| Sprint 3 | 03/11 – 23/11 | Usabilidade, documentação, correções e deploy |

## Backlog das Sprints

| Sprint | Link |
|------|------|
| Sprint1 | https://github.com/orgs/DataTechApi/projects/11 |
| Sprint2 | https://github.com/orgs/DataTechApi/projects/12 |
| Sprint3 | https://github.com/orgs/DataTechApi/projects/13 |

---

# 💻 Tecnologias

| Categoria | Tecnologias |
|-----------|-------------|
| **Back-end** | Java 21 • Spring Boot • JPA • Maven |
| **Front-end** | Thymeleaf |
| **Banco de Dados** | PostgreSQL |
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
- PostgreSQL
- Git

---

## 1️⃣ Clonar o Repositório

```bash
git clone <link-do-repositorio>
cd dataagenda

```

---

## 👥 Equipe

| Nome | Função |
|-----|-------|
| Jackson Tadeu | Scru Master |
| Cauê Gandini | Product Owner |
| Davi Gramacho | Desenvolvedor |
| Abimael Santos | Desenvolvedor |



