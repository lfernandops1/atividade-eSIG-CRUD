package br.com.ativividade.esig.crud.shared.Constantes;

public interface Queries {
    interface Pessoa {
        String INSERT_PESSOA = "INSERT INTO atividade.pessoa " +
                "(nome, cidade, email, cep, endereco, pais, usuario, telefone, data_nascimento, cargo_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        String UPDATE_PESSOA = "UPDATE atividade.pessoa SET " +
                "nome = ?, cidade = ?, email = ?, cep = ?, endereco = ?, pais = ?, " +
                "usuario = ?, telefone = ?, data_nascimento = ?, cargo_id = ? " +
                "WHERE id = ?;";

        String LISTAR_TODAS_PESSOAS =
                "SELECT " +
                        "p.id, " +
                        "p.nome, " +
                        "p.cidade, " +
                        "p.email, " +
                        "p.cep, " +
                        "p.endereco, " +
                        "p.pais, " +
                        "p.usuario, " +
                        "p.telefone, " +
                        "p.data_nascimento, " +
                        "p.cargo_id, " +
                        "c.nome AS nome_cargo " +
                        "FROM atividade.pessoa p " +
                        "JOIN atividade.cargo c ON p.cargo_id = c.id " +
                        "LIMIT 10;";

        String ATUALIZAR_PESSOA =
                "UPDATE atividade.pessoa " +
                        "SET nome = ?, cidade = ?, email = ?, cep = ?, endereco = ?, " +
                        "pais = ?, usuario = ?, telefone = ?, data_nascimento = ?, cargo_id = ? " +
                        "WHERE id = ?;";

        String QUERY_VERIFICAR_EMAIL_NAO_UTILIZADO = "SELECT COUNT(*) FROM atividade.pessoa WHERE email = ?";
        String QUERY_VERIFICAR_TELEFONE_NAO_UTILIZADO = "select count(*) from atividade.pessoa where telefone = ?;";
        String QUERY_VERIFICAR_USUARIO_NAO_UTILIZADO = "select count(*) from atividade.pessoa where usuario = ? ";
        String QUERY_VERIFICAR_TELEFONE_NAO_UTILIZADO_COM_ID = "SELECT COUNT(*) FROM atividade.pessoa WHERE telefone = ? AND id != ?";
        String QUERY_VERIFICAR_EMAIL_NAO_UTILIZADO_COM_ID = "SELECT COUNT(*) FROM atividade.pessoa WHERE email = ? AND id != ?";
        String QUERY_VERIFICAR_USUARIO_NAO_UTILIZADO_COM_ID = "SELECT COUNT(*) FROM atividade.pessoa WHERE usuario = ? AND id != ?";

    }

    interface Sessao {
        String QUERY_VERIFICAR_USUARIO =
                "select \n" +
                        "    u.id AS usuario_id, \n" +
                        "    u.usuario, \n" +
                        "    u.senha, \n" +
                        "    p.id AS pessoa_id, \n" +
                        "    p.nome, \n" +
                        "    p.cidade, \n" +
                        "    p.email, \n" +
                        "    p.cep, \n" +
                        "    p.endereco, \n" +
                        "    p.pais, \n" +
                        "    p.usuario AS pessoa_usuario, \n" +
                        "    p.telefone, \n" +
                        "    p.data_nascimento, \n" +
                        "    p.cargo_id \n" +
                        "from \n" +
                        "    atividade.usuario u \n" +
                        "join \n" +
                        "    atividade.pessoa p on p.id = u.id_pessoa \n" +
                        "where \n" +
                        "    u.usuario = ? \n" +
                        "    and u.senha = ?;\n";

        String QUERY_INSERIR_USUARIO = " INSERT INTO atividade.usuario (usuario, senha, id_pessoa) " +
                "        VALUES (?, ?, ?);";

        String QUERY_ATUALIZAR_USUARIO = "UPDATE atividade.usuario " +
                "SET usuario = ?, senha = ? " +
                "WHERE id_pessoa = ?;";
    }

    interface PessoaSalarioConsolidado {
        String QUERY_CALCULAR_SALARIO = "INSERT INTO atividade.pessoa_salario_consolidado (pessoa_id, nome_pessoa, nome_cargo, salario) " +
                "SELECT " +
                "    p.id AS pessoa_id, " +
                "    p.nome AS nome_pessoa, " +
                "    c.nome AS nome_cargo, " +
                "    SUM(CASE " +
                "        WHEN v.tipo = 'CREDITO' THEN v.valor " +
                "        WHEN v.tipo = 'DEBITO' THEN -v.valor " +
                "        ELSE 0 " +
                "    END) AS salario " +
                "FROM " +
                "    atividade.pessoa p " +
                "JOIN " +
                "    atividade.cargo c ON p.cargo_id = c.id " +
                "JOIN " +
                "    atividade.cargo_vencimentos cv ON c.id = cv.cargo_id " +
                "JOIN " +
                "    atividade.vencimentos v ON cv.vencimento_id = v.id " +
                "GROUP BY " +
                "    p.id, " +
                "    p.nome, " +
                "    c.nome;";

        String QUERY_LISTAR_TODOS_SALARIOS =
                "SELECT pessoa_id, nome_pessoa, nome_cargo, salario \n" +
                        "FROM atividade.pessoa_salario_consolidado \n" +
                        "ORDER BY nome_pessoa ASC, \n" +
                        "         CASE nome_cargo\n" +
                        "             WHEN 'Estagiario' THEN 1\n" +
                        "             WHEN 'Tecnico' THEN 2\n" +
                        "             WHEN 'Analista' THEN 3\n" +
                        "             WHEN 'Coordenador' THEN 4\n" +
                        "             WHEN 'Gerente' THEN 5\n" +
                        "             ELSE 6 -- Para cargos não listados\n" +
                        "         END;";

        String QUERY_DELETAR_REGISTROS = "DELETE FROM atividade.pessoa_salario_consolidado";

        String QUERY_REGISTRAR_HISTORICO = "INSERT INTO atividade.historico_consolidacao_salario (pessoa_id, nome_pessoa, nome_cargo, salario) " +
                "        SELECT " +
                "            pessoa_id, " +
                "            nome_pessoa, " +
                "            nome_cargo, " +
                "            salario " +
                "        FROM " +
                "            atividade.pessoa_salario_consolidado;";

        String CALCULAR_E_INSERIR_SALARIOS = "INSERT INTO atividade.pessoa_salario_consolidado " +
                "                    (pessoa_id, nome_pessoa, nome_cargo, salario) " +
                "                    VALUES (?, ?, ?, ?);";

        String OBTER_SALARIO_POR_CARGO = "SELECT salario_base FROM cargo_vencimentos WHERE cargo_id = ?";

    }

    interface Cargo {
        String LISTAR_CARGOS =
                "SELECT id, nome FROM atividade.cargo";

        String BUSCAR_CARGO_POR_ID =
                "SELECT * FROM atividade.cargo WHERE id = ?;";

        String OBTER_NOME_CARGO = "SELECT nome FROM cargo WHERE id = ?";

    }

    interface Vencimento {
        String BUSCAR_VENCIMENTO_POR_ID_PESSOA = "SELECT * FROM vencimentos WHERE pessoa_id = ?";
    }
}
