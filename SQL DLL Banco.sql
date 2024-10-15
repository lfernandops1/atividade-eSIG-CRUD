CREATE TABLE atividade.cargo
(
    id   serial4     NOT NULL,
    nome varchar(50) NOT NULL,
    CONSTRAINT cargo_pkey PRIMARY KEY (id)
);

CREATE TABLE atividade.cargo_vencimentos
(
    id            serial4 NOT NULL,
    cargo_id      int4    NOT NULL,
    vencimento_id int4    NOT NULL,
    CONSTRAINT cargo_vencimentos_pkey PRIMARY KEY (id)
);


-- atividade.cargo_vencimentos foreign keys

ALTER TABLE atividade.cargo_vencimentos
    ADD CONSTRAINT fk_cargo FOREIGN KEY (cargo_id) REFERENCES atividade.cargo (id);
ALTER TABLE atividade.cargo_vencimentos
    ADD CONSTRAINT fk_cargo_venc FOREIGN KEY (cargo_id) REFERENCES atividade.cargo (id);
ALTER TABLE atividade.cargo_vencimentos
    ADD CONSTRAINT fk_venc FOREIGN KEY (vencimento_id) REFERENCES atividade.vencimentos (id);


CREATE TABLE atividade.pessoa
(
    "ID"              int4 NOT NULL,
    "Nome"            varchar(255) NULL,
    "Cidade"          varchar(255) NULL,
    "Email"           varchar(255) NULL,
    "CEP"             varchar(255) NULL,
    "Enderco"         varchar(255) NULL,
    "Pais"            varchar(255) NULL,
    "Usuario"         varchar(255) NULL,
    "Telefone"        varchar(255) NULL,
    "Data_Nascimento" varchar(255) NULL,
    "Cargo_ID"        int4 NULL,
    CONSTRAINT pk_id PRIMARY KEY ("ID")
);


-- atividade.pessoa foreign keys

ALTER TABLE atividade.pessoa
    ADD CONSTRAINT fk_cargo FOREIGN KEY ("Cargo_ID") REFERENCES atividade.cargo (id);

CREATE TABLE atividade.pessoa_salario_consolidado
(
    pessoa_id   int4 NULL,
    nome_pessoa varchar(100) NULL,
    nome_cargo  varchar(50) NULL,
    salario     numeric(10, 2) NULL,
    id          serial4 NOT NULL,
    CONSTRAINT pessoa_salario_consolidado_pkey PRIMARY KEY (id)
);


-- atividade.pessoa_salario_consolidado foreign keys

ALTER TABLE atividade.pessoa_salario_consolidado
    ADD CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES atividade.pessoa ("ID");


CREATE TABLE atividade.vencimentos
(
    id        serial4        NOT NULL,
    descricao varchar(100)   NOT NULL,
    valor     numeric(10, 2) NOT NULL,
    tipo      varchar(50)    NOT NULL,
    CONSTRAINT vencimentos_pkey PRIMARY KEY (id)
);