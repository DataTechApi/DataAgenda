# Guia de instalação do projeto

## Descrição

Este repositório contém a aplicação desenvolvida para o 4º semestre do curso de Análise e Desenvolvimento de Sistemas da FATEC São José dos Campos - Prof. Jessen Vidal.

## Pré-requisitos

Git instalado na máquina

Versão 17 do Java e npm instalado na máquina

Docker Desktop (Para windows)

## Clonando o Repositório

Para clonar o repositório, execute o seguinte comando:
```bash
git clone https://github.com/DataTechApi/DataAgenda.git
```

## Inicializando a Aplicação

#### - Incialize o Docker

#### Cheque se o npm e a extensão do maven estão instalados

Após clonar o repositório, siga os passos abaixo para iniciar a aplicação:

Instale as dependências necessárias para cada submódulo:

``` bash

# Instale as dependências(FrontEnd) dentro da pasta data-agenda
npm install
npm i

```

Logo após:

```bash
# Acessando aplicação BackEnd
# Entre no repositório
cd /backend

# Configurando a Docker 
Caso estiver em uma ide como o vscode, suba a docker via devContainer
Ou suba manualmente o compose

# Execução 
mvn spring-boot:run

# Acessando aplicação Frontend
Em outro Terminal, Servidor Frontend:

```bash
# Entre no repositório
cd /frontend/data-agenda

# Inicie o servidor Frontend
npm run dev
```
