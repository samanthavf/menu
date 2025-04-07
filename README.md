# 🍽️ Sistema de Cardápio Interativo

Aplicação web para gerenciamento de pedidos em restaurantes, onde os clientes podem navegar pelo cardápio, adicionar pratos ao carrinho, editar pedidos e finalizar a compra com emissão de nota fiscal vinculada ao número da mesa.

---

## 🧾 Funcionalidades

- 📋 Visualização do cardápio com listagem de pratos disponíveis
- ➕ Adição de múltiplos pratos ao carrinho com quantidade personalizada
- 🛒 Visualização do carrinho com resumo dos itens escolhidos
- ➖ Remoção de pratos ou ajuste de quantidades no carrinho
- 💰 Finalização do pedido com geração automática da **nota fiscal**
- 🪑 Identificação do pedido por **número da mesa**
- 🗃️ Persistência dos dados no banco PostgreSQL

---

## 🧑‍🍳 Tecnologias Utilizadas

### 🔹 Frontend

- [Angular](https://angular.io/) — SPA moderna e responsiva
- HTML + CSS + TypeScript
- Consumo de APIs REST via `HttpClient`

### 🔹 Backend

- [Java](https://www.java.com/) com [Spring Boot](https://spring.io/projects/spring-boot)
- Spring Web, Spring Data JPA
- Geração de nota fiscal (lógica interna do backend)

### 🔹 Banco de Dados

- [PostgreSQL](https://www.postgresql.org/) — Armazenamento de pratos, pedidos e mesas

---

## 🗂️ Estrutura de Dados

### 📦 Entidades principais

- `Prato` → nome, descrição, preço, categoria
- `Carrinho` → lista de itens, total, mesa vinculada
- `ItemCarrinho` → prato, quantidade, subtotal
- `NotaFiscal` → número, valor total, mesa, itens

---

## 🚀 Como Executar o Projeto

### 🔧 Backend (Java Spring Boot)

1. Acesse a pasta do backend
```bash
cd backend
```
2. Configure o application.properties com suas credenciais do PostgreSQL

3. Execute o projeto
```bash
./mvnw spring-boot:run
```

### 🌐 Frontend (Angular)
1. Acesse a pasta do frontend
```bash
cd frontend
```
2. Instale as dependências
```bash
npm install
```
3. Rode a aplicação
```bash
ng serve
```
