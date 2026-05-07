# API 4º Semestre BD

# DataTech - DataAgenda

# Documentação - Sprint 3

> Status da Sprint: Em progresso

## 🏅 Desafio <a id="desafio"></a>

Implementar correções de Bugs e lógicas, inserir mais telas de visualização (Mapas e agenda), realizar o Deploy e manual de uso cliente.

## 📋 User Stories <a id="us"></a>

| Rank | Prioridade | User Story | Story Points | Sprint | Requisito | Status |
|------|-----------|-----------|--------------|--------|-----------|--------|
| 1 | Alta | Como **gestor**, quero visualizar o histórico de manutenções de um ativo, para análise e tomada de decisão | 3 | 3 | R04 | ⬜ |
| 2 | Média | Como **gestor**, quero visualizar um painel com todos os chamados e seus status, para monitorar os atendimentos| 5 | 3 | R04 | ⬜ |
| 3 | Média | Como **gestor**, quero gerar relatórios de manutenções realizadas, para controle e auditoria | 5 | 3 | R04 | ⬜ |
| 4 | Baixa | Como **gestor**, quero visualizar estatísticas de manutenções, para identificar padrões e melhorias | 3 | 3 | R04 | ⬜ |
| 5 | Baixa | Como **administrador**, quero gerenciar permissões de usuários, para controlar acessos ao sistema | 5 | 3 | R05 | ⬜ |
| 6 | Baixa | Como **Usuário**, quero visualizar mapas dentro da aplicação, para localizar melhor chamados/sistemas | 5 | 3 | R04 | ⬜ |
| 7 | Baixa | Como **Usuário**, quero visualizar um painel de agenda, para ter maior noção do planejamento dos chamados | 5 | 3 | R04 | ⬜ |

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
