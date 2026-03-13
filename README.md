<img src = "assets/cabecalho.png">

# API 4ºBD
# DATAGENDA

A equipe **DataTech** é composta por estudantes do 4º Semestre do curso de **Banco de Dados**, comprometidos com o desenvolvimento de uma plataforma semelhante a um **Agendador de manutenção pendentes**, onde é possível visualizar chamados pendentes de manutenção, alocar técnicos e visualizar relatórios.  
O objetivo é **facilitar a vizualização e gerenciamento das manutenções preventivas** dentro da empresa **Altave**.

---

## 📍 Índice do projeto 
<center>

| [Requisitos](#requisitos) | [Tecnologias Utilizadas](#tecnologias-utilizadas) | [Estrutura do Projeto](#estrutura-do-projeto) | [Backlog](#backlog-do-produto) | [Cronograma](#cronograma-de-sprints) | [Como Executar o Projeto](#como-executar-o-projeto) |

</center>

---

## 📋 Requisitos

### Requisitos Funcionais
- Implementar Aplicação usando Linguagem de Programação Java Web para BackEnd;
- Implementar Aplicação usando conceitos de Orientação a Objetos;
- Utilizar IDE’s na implementação de aplicação em Java;
- Aplicar técnicas de depuração e análise de logs;
- Implementar web services REST;
- Implementar clientes para consumir web services;
- Implementar projeto de Banco de Dados Relacional em termos de seus principais objetos (Schema, Tabelas, Views, Índices);
- Implementar consultas SQL com junções, subconsultas e agrupamentos;
- Implementar manutenção de dados utilizando DML.

### Requisitos Não Funcionais
- Manual de Instalação;
- Manual do Usuário;
- Front minimalista e responsivo;
- Modelo de banco de dados relacional normalizado até a 3FN;
- Documentação da API com Swagger;
- Perfis como: **Gestor, Cliente, Técnico**;
- Banco de dados com **mínimo de 100 registros de sistemas**

---

## 🧠 Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|-------------|
| **Back-end** | Java 21 • Spring Boot • JPA • Maven |
| **Front-end** | Thymeleaf |
| **Banco de Dados** | Postgres |
| **Documentação de API** | Swagger (OpenAPI) |
| **Controle de Versão** | Git • GitHub |
| **IDE’s** | IntelliJ IDEA • VS Code |
| **Outros** | Postman (testes de endpoints) | DevContainer |

---

## 📁 Estrutura do Projeto

O projeto **DataAgenda** segue uma arquitetura **Java Spring Boot + Angular**, organizada de forma modular para facilitar manutenção, escalabilidade e trabalho em equipe.

Abaixo está a estrutura real do projeto:

| **Pasta / Arquivo** | **Descrição** |
|----------------------|---------------|
| `controller/` | Contém os endpoints da API REST. |
| `service/` | Implementa a lógica de negócio do sistema. |
| `repository/` | Camada de persistência, com interfaces JPA para o MySQL. |
| `entity/` | Define as entidades do banco de dados. |
| `config/` | Contém configurações globais (Swagger, etc). |
| `resources/` | Inclui arquivos de configuração, como `application.properties`. |
| `frontend/` | Projeto Angular responsável pela interface do usuário. |
| `dataskilldbmodelo.mwb` | Modelo conceitual/lógico do banco de dados. |
| `pom.xml` | Arquivo Maven responsável por dependências e build. |

---

## 🧭 Cronograma de Sprints

| Sprint | Período | Principais Entregas | Status |
|---------|----------|---------------------|---------|
| **Sprint 1** | 08/09 a 28/09 | Setup de ambiente, Criação BD, lista de sistemas, lógica de recorrência |  Em andamento |
| **Sprint 2** | 06/10 a 26/10 | Sistema de alerta, lembretes automáticos, Gestão de chamados, segurança e acesso, relatórios |  Pendente |
| **Sprint 3** | 03/11 a 23/11 | Usabilidade e experiência, documentações, Correção de Bugs, Deploy para produção | Pendente |

---

## ⚙️ Como Executar o Projeto

### 🔧 Pré-requisitos
- Java 21+
- MySQL 8+
- Maven instalado

### 🗃️ Banco de Dados
1. Crie um banco de dados chamado `dataskilldb`.
2. Configure o arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/dataskilldb
   spring.datasource.username=root
   spring.datasource.password=senha


## 📊 Backlog do Produto

| #   | Épico                                | Prioridade | User Story                                                                                                   | Sprint | Status    |
|-----|--------------------------------------|------------|--------------------------------------------------------------------------------------------------------------|--------|-----------|
| 1   | Épico 1: Cadastro e Gestão de Ativos | Alta       | História 1: Como técnico, quero visualizar a lista de ativos cadastrados para identificar onde realizar manutenção. | 2      | Pendente  |
| 2   | Épico 2: Agendamento de Chamados     | Alta       | História 2: Como gestor, quero agendar manutenções preventivas para cada ativo, definindo data e periodicidade. | 2      | Pendente  |
| 3   | Épico 3: Notificação de Chamados     | Alta       | História 3: Como técnico, quero receber notificações de chamados agendados para não perder prazos.          | 2      | Pendente  |
| 4   | Épico 4: Gestão de Chamados          | Alta       | História 4: Como técnico, quero registrar o status do chamado (pendente, em andamento, concluído) para manter controle. | 3      | Pendente  |
| 5   | Épico 5: Visualização de Chamados          | Alta       | História 5: Como gestor, quero visualizar relatórios de chamados concluídos para avaliar eficiência da equipe. | 3      | Pendente  |
| 6   | Épico 6: Segurança e Acesso          | Alta       | História 6: Como administrador, quero controlar permissões de acesso para garantir que apenas usuários autorizados possam agendar ou editar chamados. | 1      | Pendente  |
| 7   | Épico 7: Usabilidade e Experiência   | Média      | História 7: Como gestor, quero uma interface intuitiva para visualizar rapidamente o status dos ativos.     | 2      | Pendente  |

## Backlog das Sprints 

| **Sprint** | **Link** |
|-------------|------------|
| `Sprint1` | https://github.com/orgs/DataTechApi/projects/11 |
| `Sprint2` | https://github.com/orgs/DataTechApi/projects/12 |
| `Sprint3` | https://github.com/orgs/DataTechApi/projects/13 |

🧩 Definition of Ready (DoR) e ✅ Definition of Done (DoD)
📌 Definition of Ready (DoR)

## 📌 **Definition of Ready (DoR)**
Uma *User Story* está **pronta para desenvolvimento** quando:

- ✅ O objetivo está claramente descrito e compreendido por todos.  
- ✅ Os critérios de aceitação estão definidos e validados pelo Product Owner.  
- ✅ As dependências estão identificadas e resolvidas (APIs, banco, telas anteriores).   
- ✅ As tarefas técnicas estão quebradas e estimadas.  
- ✅ Há dados de teste ou ambiente pronto para validação.

---

## 🚀 **Definition of Done (DoD)**
Uma *User Story* é considerada **concluída** quando:

- 🧠 Todo o código foi desenvolvido conforme os critérios de aceitação.  
- 🔍 Foram realizados **testes** com sucesso.  
- 📘 A documentação técnica e/ou manual de usuário foi atualizada.  
- 🔒 Os dados estão tratados e armazenados de forma segura.  
- 💬 O Product Owner revisou e aprovou a entrega.  
- 🚀 A funcionalidade está pronta para ser liberada em produção.

📋 Aplicação do DoR e DoD nas User Stories

| # | User Story (Resumo) | 🧩 DoR Específico | ✅ DoD Específico |
|:-:|----------------------|------------------|------------------|
| **1** | Visualização de sistemas e chamados | Mockups prontos e estrutura do banco definida | sistemas listados e minimamente interativos |
| **2** | Agendamento de Chamados | Sistemas cadastrados e front minimamente integrado | Possibilidade de agendamento de chamados para cada sistema |
| **3** | Notificação de Chamados | Chamados devidamente interativos e agendados | Técnico receber com clareza a notificação de chamados agendados |
| **4** | Gestão de Chamados | Sistema de chamados bem definidos logicamente e no FrontEnd | Possibilidade do técnico conseguir manipular status do chamado |
| **5** | Visualização de Chamados | Lógica de agendamento e status do chamado definidos | Gestor conseguir visualizar plenamente Logs de chamados e sistemas |
| **6** | Segurança e Acesso | Lógicas de gestão de chamados e cadastros devidamente implementados | Controles de acesso a técnicos estiverem alinhados |
| **7** | Usabilidade e Experiência | Protótipo de tela criado | Interface intuitiva e validade pelo usuário |


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



<img src = "assets/rodape.png">
