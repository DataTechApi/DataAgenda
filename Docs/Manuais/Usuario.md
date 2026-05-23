# DATAGENDA

## Sistema de Gestão de Manutenções

### MANUAL DO USUÁRIO

```
DataTech | Sprint 3 | 2025
```

## 1. Introdução

Bem-vindo ao DataAgenda, a plataforma desenvolvida pela equipe DataTech para gerenciar
manutenções preventivas e corretivas de sistemas corporativos. Este manual descreve
todas as funcionalidades disponíveis no sistema e orienta o usuário em cada passo de sua
utilização.

#### 1.1 Objetivo do Sistema

O DataAgenda permite:

- Cadastrar e gerenciar clientes, sistemas e técnicos
- Agendar manutenções preventivas e emergenciais
- Atribuir técnicos responsáveis a cada chamado
- Acompanhar o status de cada manutenção em tempo real
- Visualizar um painel gerencial (Dashboard) com indicadores e gráficos
- Finalizar atendimentos registrando observações técnicas

#### 1.2 Perfis de Usuário

O sistema possui dois perfis de acesso com permissões distintas:

```
Perfil Permissões
```
```
Administrador (ADMIN) Acesso total: dashboard, clientes, sistemas, técnicos, manutenções e
cadastros.
```
```
Técnico (TECNICO) Visualiza apenas os chamados atribuídos a ele e pode finalizar
atendimentos.
```

## 2. Acesso ao Sistema

#### 2.1 Login

Para acessar o DataAgenda, abra o navegador e acesse a URL do sistema. A tela de login
será exibida.

Preencha os campos:

- E-mail: endereço de e-mail cadastrado no sistema
- Senha: senha definida no cadastro

Clique em Entrar. O sistema redirecionará automaticamente conforme o perfil do usuário:

- ADMIN → Dashboard Gerencial
- TECNICO → Lista de chamados atribuídos

```
⚠ Atenção: Caso o e-mail ou a senha estejam incorretos, o sistema exibirá uma mensagem de
erro. Verifique as credenciais e tente novamente.
💡 Observação: A tela de login possui um botão no canto superior direito para alternar entre o
modo claro e o modo escuro.
```
#### 2.2 Modo Claro / Modo Escuro

O DataAgenda suporta dois temas visuais. O botão de alternância fica no canto superior
direito da tela de login (ícone de sol ou lua). A preferência é mantida durante toda a sessão.


## 3. Dashboard Gerencial

Disponível apenas para usuários com perfil ADMIN. O Dashboard é a tela inicial após o
login e apresenta uma visão consolidada do sistema.

#### 3.1 Cartões de Indicadores (KPIs)

Quatro cartões no topo da tela exibem os principais números do sistema:

```
Indicador Descrição
```
```
Total de Clientes Quantidade total de clientes cadastrados no sistema.
```
```
Sistemas Ativos Quantidade de sistemas registrados.
```
```
Pendentes Manutenções com status PENDENTE.
```
```
Executadas Manutenções com status EXECUTADA.
```
#### 3.2 Gráficos

Dois gráficos são exibidos na seção central do dashboard:

- Sistemas por Tipo (rosca): mostra a distribuição dos sistemas cadastrados por
    categoria.
- Status das Manutenções (pizza): mostra a proporção entre manutenções pendentes
    e executadas.

#### 3.3 Próximas Manutenções Agendadas

Uma tabela na parte inferior lista as próximas manutenções agendadas, com data, cliente,
sistema, tipo e descrição. O botão Ver Todas redireciona para a tela completa de
manutenções.


## 4. Gerenciamento de Clientes

A seção de Clientes permite cadastrar e gerenciar as empresas que contratam os serviços
de manutenção. Disponível para o perfil ADMIN.

#### 4.1 Listar Clientes

No menu lateral, acesse Clientes > Visualizar. A tela exibe todos os clientes cadastrados
com seus dados principais.

#### 4.2 Cadastrar Cliente

Acesse Clientes > Cadastrar e preencha o formulário com os seguintes campos:

```
Campo Tipo Descrição
```
```
Nome Texto Razão social ou nome do
cliente.
```
```
CNPJ Texto CNPJ do cliente.
```
```
Nome do Responsável Texto Nome do contato principal.
```
```
Telefone do Responsável Texto Número de contato.
```
```
E-mail do Responsável Texto E-mail do contato.
```
```
Duração do Contrato Número Duração em meses.
```
```
Localidade Texto Endereço ou cidade do cliente.
```
```
Data de Início do Contrato Data Data de início da vigência.
```
Após preencher todos os campos, clique em Cadastrar. Uma mensagem de confirmação
será exibida.

#### 4.3 Editar Cliente

Na listagem de clientes, localize o cliente desejado e clique em Editar. Altere os campos
necessários e confirme.

#### 4.4 Excluir Cliente

Na listagem, clique na opção de excluir ao lado do cliente. Confirme a ação na janela de
diálogo.

```
⚠ Atenção: A exclusão de um cliente é irreversível. Certifique-se de que não há sistemas ou
manutenções vinculados antes de excluir.
```

## 5. Gerenciamento de Sistemas

Sistemas representam os ativos tecnológicos dos clientes que precisam de manutenção
periódica.

#### 5.1 Listar Sistemas

Acesse Sistemas > Visualizar para ver todos os sistemas cadastrados, seus tipos, clientes
vinculados e intervalo de manutenção.

#### 5.2 Cadastrar Sistema

Acesse Sistemas > Cadastrar e preencha:

```
Campo Tipo Descrição
```
```
Cliente Seleção Cliente ao qual o sistema
pertence.
```
```
Técnico Responsável Seleção Técnico que gerencia o
sistema.
```
```
Tipo de Sistema Seleção Categoria do sistema (ex.:
CFTV, Alarme).
```
```
Intervalo de Manutenção Número Frequência de manutenção em
dias.
```
#### 5.3 Editar Sistema

Acesse a listagem de sistemas, localize o registro desejado e clique em Editar. Atualize os
campos e salve.


## 6. Gerenciamento de Técnicos

A seção de Técnicos permite gerenciar os profissionais responsáveis pelos atendimentos.
Disponível para o perfil ADMIN.

#### 6.1 Listar Técnicos

Acesse Técnicos > Visualizar. A lista mostra todos os técnicos com nome, e-mail, telefone e
nível.

#### 6.2 Cadastrar Técnico

Acesse Técnicos > Cadastrar e preencha:

```
Campo Opções / Tipo Descrição
```
```
Nome Texto Nome completo do técnico.
```
```
E-mail Texto Endereço de e-mail para login.
```
```
Telefone Texto Número de contato.
```
```
Nível JUNIOR / PLENO / SENIOR Nível de experiência do
técnico.
```
```
Senha Texto Senha de acesso ao sistema.
```
```
Perfil (Role) TECNICO / ADMIN Define as permissões do
usuário.
```
```
💡 Observação: Atribua o perfil ADMIN com cautela, pois ele concede acesso total ao sistema.
```
#### 6.3 Editar Técnico

Na listagem, clique em Editar ao lado do técnico desejado, atualize os dados e confirme.


## 7. Gerenciamento de Manutenções

A seção de Manutenções é o núcleo do DataAgenda. Aqui são registrados e gerenciados
todos os chamados de manutenção preventiva e emergencial.

#### 7.1 Tipos de Manutenção

```
Tipo Descrição
```
```
PREVENTIVA Manutenção planejada com antecedência para evitar falhas.
```
```
EMERGENCIAL Chamado aberto após a ocorrência de um problema.
```
#### 7.2 Status de Manutenção

```
Status Significado
```
```
PENDENTE Manutenção agendada, ainda não realizada.
```
```
EXECUTADA Manutenção concluída e registrada pelo técnico.
```
#### 7.3 Listar Manutenções

Acesse Manutenções > Visualizar. A tabela lista todos os chamados com data agendada,
sistema, técnico, tipo e status. Clique em qualquer linha para ver mais detalhes.

#### 7.4 Cadastrar Manutenção (ADMIN)

Acesse Manutenções > Cadastrar e preencha:

```
Campo Opções / Tipo Descrição
```
```
Descrição Texto Detalhamento do problema ou
da tarefa.
```
```
Técnico Seleção Técnico responsável pelo
atendimento.
```
```
Data Agendada Data Data prevista para a
manutenção.
```
```
Tipo PREVENTIVA /
EMERGENCIAL
```
```
Categoria do chamado.
```
```
Status PENDENTE / EXECUTADA Estado inicial (geralmente
PENDENTE).
```
```
Sistema Seleção Sistema que receberá a
manutenção.
```

Clique em Cadastrar para salvar o chamado. O técnico selecionado passará a ver o
chamado em sua lista.

#### 7.5 Visualizar Manutenções por Técnico

Um administrador pode acessar a lista de chamados de um técnico específico em
Atendimentos > Ver por Técnico. Selecione o técnico desejado para filtrar os chamados.


## 8. Atendimentos (Perfil Técnico)

Usuários com perfil TECNICO são redirecionados automaticamente para a tela de
atendimentos após o login.

#### 8.1 Visualizar Chamados Atribuídos

A tela exibe todos os chamados de manutenção atribuídos ao técnico logado, incluindo data
agendada, sistema, cliente, tipo e status.

#### 8.2 Detalhar um Chamado

Clique sobre um chamado para ver todos os seus detalhes: sistema, cliente, localidade,
descrição completa e status atual.

#### 8.3 Finalizar Atendimento

Para registrar a conclusão de uma manutenção:

- Acesse o chamado desejado e clique em Finalizar Atendimento.
- Preencha a Data de Atendimento (data em que o serviço foi realizado).
- Preencha a Descrição do Atendimento com as observações técnicas sobre o serviço
    executado.
- Clique em Confirmar. O status do chamado será alterado para EXECUTADA.

```
💡 Observação: Após finalizar, o chamado não poderá ser reaberto pelo técnico. Em caso de
erro, contate um administrador.
```

## 9. Navegação e Menu

O sistema utiliza um menu lateral (sidebar) visível após o login. Os itens disponíveis variam
conforme o perfil do usuário.

```
Seção do Menu Perfil Destino
```
```
Dashboard ADMIN Painel gerencial com KPIs e
gráficos.
```
```
Clientes ADMIN Cadastrar, listar e editar
clientes.
```
```
Sistemas ADMIN Cadastrar, listar e editar
sistemas.
```
```
Técnicos ADMIN Cadastrar, listar e editar
técnicos.
```
```
Manutenções ADMIN Cadastrar e visualizar
manutenções.
```
```
Atendimentos TECNICO Lista de chamados do técnico
logado.
```

## 10. Dicas e Boas Práticas

#### 10.1 Fluxo Recomendado para Administradores

Para iniciar o uso do sistema, siga esta ordem:

- 1. Cadastre os técnicos que realizarão os atendimentos.
- 2. Cadastre os clientes atendidos pela empresa.
- 3. Cadastre os sistemas (ativos) de cada cliente, associando um técnico
    responsável.
- 4. Agende manutenções preventivas para cada sistema conforme o intervalo
    configurado.
- 5. Acompanhe o andamento pelo Dashboard e pela lista de manutenções.

#### 10.2 Fluxo Recomendado para Técnicos

- 1. Faça login com seu e-mail e senha.
- 2. Visualize os chamados atribuídos a você na tela de atendimentos.
- 3. Acesse o detalhe de cada chamado para conferir o sistema, cliente e descrição.
- 4. Ao concluir o serviço, finalize o atendimento registrando a data e as observações.

#### 10.3 Segurança

- Nunca compartilhe sua senha com outros usuários.
- Realize logout ao terminar de usar o sistema em computadores compartilhados.
- Comunique ao administrador imediatamente em caso de acesso não autorizado.

