# Mechanics — Gestão de Oficina

Aplicação full stack para organizar clientes, veículos, ordens de serviço, peças, fornecedores e movimentações de estoque de uma oficina mecânica.

## Integrante

- **Nome:** Mateus Dayson de Oliveira

## Tecnologias

- **Frontend:** React, React Router, Axios, Styled Components e Vite
- **Backend:** Java 25, Spring Boot, Spring Security, Spring Data JPA e PostgreSQL
- **Documentação da API:** OpenAPI e Swagger UI

## Requisitos

- Java 25
- Node.js 20 ou superior e npm
- PostgreSQL em execução, com um banco chamado `mechanics_db`

## Executar localmente

### 1. Configurar o PostgreSQL e iniciar a API

Inicie o PostgreSQL local e crie o banco `mechanics_db`. Copie `backend/.env.example` para `backend/.env` e preencha as credenciais do banco e o segredo JWT. O Spring Boot carrega esse arquivo automaticamente ao iniciar pelo diretório do backend ou pela raiz do projeto. O `.env` não deve ser enviado ao Git.

Se preferir, também pode definir as variáveis no PowerShell:

```powershell
$env:DATABASE_URL = "jdbc:postgresql://localhost:5432/mechanics_db"
$env:DATABASE_USERNAME = "postgres"
$env:DATABASE_PASSWORD = "sua-senha-local"
$env:JWT_SECRET = "gere-um-segredo-aleatorio-com-pelo-menos-32-caracteres"
$env:FRONTEND_ORIGIN = "http://localhost:5173,http://127.0.0.1:5173"
```

Na pasta `backend`, execute:

```powershell
cd backend
./mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

### 2. Iniciar o frontend

Em outro terminal, configure a URL da API se ela não estiver em `http://localhost:8080`:

```powershell
cd frontend
$env:VITE_API_URL = "http://localhost:8080"
npm install
npm run dev
```

Abra o endereço indicado pelo Vite, normalmente `http://localhost:5173`.

### 3. Documentação interativa

Com a API ativa, acesse [Swagger UI](http://localhost:8080/swagger-ui.html). A especificação OpenAPI também fica em `http://localhost:8080/v3/api-docs`. Para testar rotas protegidas, faça login pela aplicação ou use o botão **Authorize** do Swagger e informe o JWT.

## Autenticação

Crie uma conta na tela de cadastro e entre com usuário e senha. As rotas de negócio usam JWT no cabeçalho `Authorization: Bearer <token>`. O token expira; faça login novamente quando isso acontecer.

## Módulos

- Clientes e veículos
- Ordens de serviço
- Estoque, fornecedores e movimentações

## Deploy e demonstração

- **Frontend publicado:** 
- **Backend publicado:** 
- **Repositório GitHub:** preencher após publicar o repositório
- **Vídeo de demonstração (Drive/YouTube):** pendente

Grave um vídeo de até 5–7 minutos mostrando cadastro, listagem, edição e exclusão de um registro. Inclua login e acesso a uma rota protegida para demonstrar o bônus de autenticação.

## Submissão

Crie um arquivo `.txt` ou `.md` com os links do repositório GitHub e do vídeo e envie-o pela plataforma da disciplina.
