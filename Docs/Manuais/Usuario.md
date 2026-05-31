# 🛠️ DataAgenda
### Sistema de Gestão de Manutenções

> **DataTech • Sprint 3 • 2025**  
> Plataforma para gerenciamento de manutenções preventivas e corretivas.

---

# 📌 1. Introdução

Bem-vindo ao **DataAgenda**, sistema desenvolvido para gerenciamento de clientes, sistemas, técnicos e manutenções corporativas.

## Objetivos do sistema

O DataAgenda permite:

✔ Gerenciar clientes, sistemas e técnicos  
✔ Agendar manutenções preventivas e emergenciais  
✔ Atribuir responsáveis aos chamados  
✔ Monitorar status em tempo real  
✔ Visualizar indicadores no dashboard  
✔ Finalizar atendimentos com registro técnico

---

# 👤 2. Perfis de Usuário

| Perfil | Permissões |
|--------|-------------|
| **ADMIN** | Controle total do sistema |
| **TECNICO** | Visualização e conclusão de chamados atribuídos |

---

# 🔐 3. Acesso ao Sistema

## Login

Informe:

- **E-mail**
- **Senha**

Após autenticação:

```txt
ADMIN   → Dashboard
TECNICO → Atendimentos
```

> ⚠ Credenciais incorretas exibem mensagem de erro.

---

## Tema Escuro / Claro

O sistema possui alternância de tema através do ícone ☀️/🌙 disponível no canto superior direito.

A preferência permanece durante toda a sessão.

---

# 📊 4. Dashboard (ADMIN)

Tela inicial do administrador.

## Indicadores principais

| KPI | Descrição |
|-----|------------|
| Clientes | Total cadastrado |
| Sistemas | Sistemas ativos |
| Pendentes | Manutenções pendentes |
| Executadas | Manutenções concluídas |

## Gráficos

- Sistemas por tipo
- Status das manutenções

## Próximas manutenções

Tabela com:

- Cliente
- Sistema
- Data
- Tipo
- Descrição

---

# 🏢 5. Clientes

Menu:

```txt
Clientes > Visualizar
Clientes > Cadastrar
```

## Cadastro

Preencha:

- Nome
- CNPJ
- Responsável
- Telefone
- E-mail
- Duração do contrato
- Localidade
- Data inicial

Após concluir:

**Cadastrar → Confirmação exibida**

---

## Editar

Selecione:

```txt
Cliente → Editar → Salvar
```

---

## Excluir

> ⚠ Exclusão é irreversível.

Verifique vínculos com sistemas ou manutenções antes.

---

# 💻 6. Sistemas

Representam ativos monitorados.

## Cadastro

Campos:

| Campo | Exemplo |
|-------|----------|
| Cliente | Empresa vinculada |
| Técnico | Responsável |
| Tipo | CFTV, Alarme... |
| Intervalo | Dias |

---

# 👷 7. Técnicos

## Cadastro

Informações:

- Nome
- E-mail
- Telefone
- Nível
- Senha
- Perfil

Níveis disponíveis:

```txt
JUNIOR
PLENO
SENIOR
```

Perfis:

```txt
ADMIN
TECNICO
```

> 💡 Conceda ADMIN apenas quando necessário.

---

# 🔧 8. Manutenções

Centro principal do sistema.

## Tipos

| Tipo | Função |
|------|---------|
| PREVENTIVA | Evita falhas |
| EMERGENCIAL | Corrige falhas ocorridas |

---

## Status

| Status | Significado |
|---------|-------------|
| PENDENTE | Agendada |
| EXECUTADA | Finalizada |

---

## Cadastro

Preencher:

- Descrição
- Técnico
- Data
- Tipo
- Status
- Sistema

Após salvar:

```txt
Chamado atribuído ao técnico selecionado
```

---

# 📋 9. Atendimentos (TECNICO)

## Visualização

O técnico acessa apenas chamados atribuídos.

Informações:

- Sistema
- Cliente
- Tipo
- Data
- Status

---

## Finalizar atendimento

Fluxo:

```txt
Chamado
   ↓
Finalizar Atendimento
   ↓
Data realizada
   ↓
Observações técnicas
   ↓
Confirmar
```

Resultado:

```txt
Status → EXECUTADA
```

> ⚠ Chamados concluídos não podem ser reabertos pelo técnico.

---

# 🧭 10. Navegação

Menu disponível conforme perfil.

| Menu | Perfil |
|------|---------|
| Dashboard | ADMIN |
| Clientes | ADMIN |
| Sistemas | ADMIN |
| Técnicos | ADMIN |
| Manutenções | ADMIN |
| Atendimentos | TECNICO |

---

# ✅ 11. Fluxo Recomendado

## Administrador

1. Cadastrar técnicos  
2. Cadastrar clientes  
3. Cadastrar sistemas  
4. Agendar manutenções  
5. Monitorar dashboard

---

## Técnico

1. Login  
2. Visualizar chamados  
3. Conferir detalhes  
4. Finalizar atendimento

---

# 🔒 12. Segurança

- Não compartilhe senhas
- Faça logout após uso
- Informe acessos indevidos ao administrador

---

<div align="center">

**DataAgenda — Manual do Usuário**  
DataTech • Sprint 3 • 2025

</div>
