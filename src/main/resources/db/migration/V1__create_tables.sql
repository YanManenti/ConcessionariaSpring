-- ======================================================
-- Flyway Migration: Estrutura Concessionária Atualizada
-- ======================================================


-- ---------------------------
-- Tabela de Entidade de Papéis (Role)
-- ---------------------------
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL,
                       salario DOUBLE PRECISION,
                       CONSTRAINT uk_roles_name UNIQUE (name)
);

-- =======================
-- Tabela de usuários
-- =======================
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       telefone VARCHAR(20),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       endereco VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       isActive BOOLEAN DEFAULT TRUE,

                       role_id BIGINT NOT NULL,

                       CONSTRAINT fk_user_roles FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =======================
-- Tabela de automóveis
-- =======================
CREATE TABLE automovel (
                           id BIGSERIAL PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           modelo VARCHAR(255) NOT NULL,
                           marca VARCHAR(255) NOT NULL,
                           ano INT NOT NULL,
                           cor VARCHAR(100),
                           placa VARCHAR(50) UNIQUE NOT NULL,
                           preco DOUBLE PRECISION NOT NULL,
                           disponivel BOOLEAN NOT NULL
);

-- =======================
-- Tabela de pedidos
-- =======================
CREATE TABLE pedido (
                        id BIGSERIAL PRIMARY KEY,
                        cliente_id BIGINT NOT NULL,
                        automovel_id BIGINT NOT NULL,
                        data_inicio TIMESTAMP,
                        CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES users(id),
                        CONSTRAINT fk_pedido_automovel FOREIGN KEY (automovel_id) REFERENCES automovel(id)
);

-- =======================
-- Tabela de compras
-- =======================
CREATE TABLE compra (
                        id BIGSERIAL PRIMARY KEY,
                        cliente_id BIGINT NOT NULL,
                        automovel_id BIGINT UNIQUE NOT NULL,
                        vendedor_id BIGINT NOT NULL,
                        data_inicio TIMESTAMP,
                        data_compra TIMESTAMP,
                        CONSTRAINT fk_compra_cliente FOREIGN KEY (cliente_id) REFERENCES users(id),
                        CONSTRAINT fk_compra_automovel FOREIGN KEY (automovel_id) REFERENCES automovel(id),
                        CONSTRAINT fk_compra_vendedor FOREIGN KEY (vendedor_id) REFERENCES users(id)
);