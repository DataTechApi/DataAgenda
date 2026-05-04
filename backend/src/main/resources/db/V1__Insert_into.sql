-- V2__Insert_tables.sql

--------------------------------------------------
-- TECNICO
--------------------------------------------------
INSERT INTO tecnico (nome, email, telefone, senha, is_ativo, nivel, role)
VALUES ('João Silva', 'joao.silva@datatech.com', '11999999999', 'senha123', 1, 'PLENO', 'TECNICO');

INSERT INTO tecnico (nome, email, telefone, senha, is_ativo, nivel, role)
VALUES ('admin', 'admin@admin', '11988888888', 'senha123', 1, 'SENIOR', 'ADMIN');

--------------------------------------------------
-- CLIENTE
--------------------------------------------------
INSERT INTO cliente (nome, cnpj, nome_responsavel, telefone_responsavel, email_responsavel, duracao_contrato, is_ativo, localidade)
VALUES ('Petrobras', '12345678000199', 'Carlos Oliveira', '11977777777', 'carlos.oliveira@petrobras.com', 12, 1, 'São Paulo');

INSERT INTO cliente (nome, cnpj, nome_responsavel, telefone_responsavel, email_responsavel, duracao_contrato, is_ativo, localidade)
VALUES ('Vale', '98765432000188', 'Marina Costa', '11966666666', 'marina.costa@vale.com', 24, 1, 'Rio de Janeiro');

--------------------------------------------------
-- SISTEMA
--------------------------------------------------
-- cliente_id = 1 (Petrobras)
INSERT INTO sistema (nome, tipo_sistema, data_proxima_manutencao, DATA_CADASTRO, is_disponivel, cliente_id)
VALUES ('Petrobras - SP', 'CAMERAS', DATE '2024-12-01', DATE '2024-01-01', 1, 1);

-- cliente_id = 2 (Vale)
INSERT INTO sistema (nome, tipo_sistema, data_proxima_manutencao, DATA_CADASTRO, is_disponivel, cliente_id)
VALUES ('Vale - RJ', 'BALAO', DATE '2024-11-15', DATE '2024-01-01', 1, 2);

--------------------------------------------------
-- MANUTENCAO
--------------------------------------------------
-- tecnico_id = 1 | sistema_id = 1
INSERT INTO manutencao (descricao, tecnico_id, data_agendada, tipo_manutencao, status_manutencao, sistema_id)
VALUES ('Manutenção preventiva das câmeras', 1, DATE '2024-12-01', 'PREVENTIVA', 'PENDENTE', 1);

-- tecnico_id = 2 | sistema_id = 2
INSERT INTO manutencao (descricao, tecnico_id, data_agendada, tipo_manutencao, status_manutencao, sistema_id)
VALUES ('Verificação emergencial do balão', 2, DATE '2024-11-10', 'EMERGENCIAL', 'PENDENTE', 2);

COMMIT;
