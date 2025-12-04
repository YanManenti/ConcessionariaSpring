# ConcessionariaSpring API

API REST para gerenciamento de uma concessionária de veículos, desenvolvida como trabalho acadêmico. O sistema permite o gerenciamento de usuários, autenticação segura via token, cadastro de automóveis e realização de pedidos e vendas.

## Tecnologias Utilizadas

* Java 21
* Spring Boot 3
* Spring Security (Autenticação e Autorização via Token JWT)
* Spring Data JPA
* Maven
* Lombok
* Banco de Dados: H2 ou MySQL (Configurável)

## Funcionalidades

* Autenticação e Segurança: Registro de usuários, login com geração de Token JWT e controle de acesso baseado em permissões (ADMIN, VENDEDOR, CLIENTE).
* Gestão de Automóveis: Cadastro, listagem e atualização de veículos. Controle automático de disponibilidade (veículos vendidos tornam-se indisponíveis).
* Transações:
    * Pedidos: Clientes podem registrar intenção de compra.
    * Vendas: Vendedores registram a venda efetiva, vinculando cliente e veículo.

## Como Rodar o Projeto

### Pré-requisitos
Certifique-se de ter o Java JDK 17 (ou superior) instalado e o Git configurado. O Maven está incluído no projeto via wrapper.

### Passo a Passo

1. Clone o repositório:
   git clone https://github.com/YanManenti/ConcessionariaSpring.git
   cd ConcessionariaSpring

2. Configure o Banco de Dados:
   Abra o arquivo src/main/resources/application.properties. Verifique as configurações de URL, usuário e senha caso esteja utilizando um banco MySQL externo. Se estiver usando H2 (em memória), nenhuma configuração extra é necessária.

3. Compile e execute o projeto:
   No Linux/macOS:
   ./mvnw clean install
   ./mvnw spring-boot:run

   No Windows:
   .\mvnw.cmd clean install
   .\mvnw.cmd spring-boot:run

4. O sistema estará acessível em: http://localhost:8080

## Documentação da API (Endpoints)

Abaixo estão os exemplos de requisição para as principais rotas.

### Autenticação

#### Registrar Novo Usuário
Rota: POST /auth/register
Corpo da Requisição (JSON):
{
"name": "Nome do Usuario",
"email": "usuario@email.com",
"password": "senha123",
"role": "ADMIN"
}
Nota: As roles disponíveis geralmente são ADMIN, VENDEDOR e CLIENTE.

#### Login
Rota: POST /auth/login
Corpo da Requisição (JSON):
{
"email": "usuario@email.com",
"password": "senha123"
}
Resposta: Retorna um token de acesso. Este token deve ser enviado no cabeçalho das requisições protegidas (Authorization: Bearer <token>).

### Transações

#### Realizar Venda
Requer perfil de Vendedor ou Admin.
Rota: POST /api/transacao/users/vendedores/venda
Cabeçalho: Authorization: Bearer <seu_token_jwt>
Corpo da Requisição (JSON):
{
"clienteId": 1,
"automovelId": 5
}

#### Criar Pedido
Requer perfil de Cliente.
Rota: POST /api/pedidos
Cabeçalho: Authorization: Bearer <seu_token_jwt>
Corpo da Requisição (JSON):
{
"automovelId": 3
}

## Contribuidores

* Yan Manenti
* Cristopher Olis
* Misael Mendes
* Micael Mendes