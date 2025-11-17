CREATE TABLE roles (
                      id BIGSERIAL PRIMARY KEY,
                      nome VARCHAR(255) NOT NULL,
                      salario DOUBLE PRECISION,
                      role VARCHAR(50) NOT NULL
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       cpf VARCHAR(20) NOT NULL,
                       telefone VARCHAR(20),
                       email VARCHAR(255) NOT NULL,
                       endereco VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       role_id BIGINT NOT NULL ,

                       CONSTRAINT fk_users_role
                           FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_type VARCHAR(50) NOT NULL,

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id) REFERENCES users(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_type)
);

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

CREATE TABLE pedido (
                        id BIGSERIAL PRIMARY KEY,

                        cliente_id BIGINT NOT NULL,
                        automovel_id BIGINT NOT NULL,

                        data_pedido TIMESTAMP,

                        CONSTRAINT fk_pedido_cliente
                            FOREIGN KEY (cliente_id) REFERENCES users(id),

                        CONSTRAINT fk_pedido_automovel
                            FOREIGN KEY (automovel_id) REFERENCES automovel(id)
);

CREATE TABLE compra (
                        id BIGSERIAL PRIMARY KEY,

                        cliente_id BIGINT NOT NULL,
                        automovel_id BIGINT UNIQUE NOT NULL,
                        vendedor_id BIGINT NOT NULL,

                        data_inicio TIMESTAMP,
                        data_compra TIMESTAMP,

                        CONSTRAINT fk_compra_cliente
                            FOREIGN KEY (cliente_id) REFERENCES users(id),

                        CONSTRAINT fk_compra_automovel
                            FOREIGN KEY (automovel_id) REFERENCES automovel(id),

                        CONSTRAINT fk_compra_vendedor
                            FOREIGN KEY (vendedor_id) REFERENCES users(id)
);