# Estrutura das Branches

```
main: Branch principal e estável.
sprint/<nome-da-sprint>: Para novas funcionalidades.
```

## Prazo para Último Commit e PR

- **Domingo às 18h** da semana estabelecida para a speintm, o código final deve estar pronto para ser submetido à branch `main` (Produção).

## Processo de Code Review

- Todo código deve passar por revisão de pelo menos 1 (um) developer antes de ser aceito o Pull Request na branch `main`.
- **Responsabilidade:** O dono da task é responsável por testar e corrigir problemas no seu código. Pull Requests que “quebrarem” a API terão como responsáveis tanto o dono da task quanto quem aprovou.

### Checklist obrigatório antes de abrir um PR:

- [ ] Código foi revisado
- [ ] Testes foram executados
- [ ] Documentação foi atualizada

## Aprovação

- Qualquer Dev pode aprovar um PR, mas é necessário que siga as responsabilidades.
