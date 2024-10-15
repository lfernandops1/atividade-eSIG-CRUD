package br.com.ativividade.esig.crud.shared.Constantes;

public interface Valores {

    interface Generico {
        String STRING_VAZIA = "";
    }

    interface Cargo {
        String ID = "id";
        String NOME = "nome";
    }

    interface Pessoa {
        String ID = "id";
        String NOME = "nome";
        String CIDADE = "cidade";
        String EMAIL = "email";
        String CEP = "cep";
        String ENDERECO = "endereco";
        String PAIS = "pais";
        String USUARIO = "usuario";
        String TELEFONE = "telefone";
        String DATA_NASCIMENTO = "data_nascimento";
        String NOME_CARGO = "nome_cargo";
    }

    interface Salario {
        String PESSOA_ID = "pessoa_id";
        String NOME_PESSOA = "nome_pessoa";
        String NOME_CARGO = "nome_cargo";
        String SALARIO = "salario";
        String SALARIO_BASE = "salario_base";
        String CREDITO = "CREDITO";
        String DEBITO = "DEBITO";
        String USUARIO_ID = "usuario_id";
        String USUARIO = "usuario";
        String SENHA = "senha";
        String NOME = "nome";
        String EMAIL = "email";
        String TELEFONE = "telefone";
        String DATA_NASCIMENTO = "data_nascimento";
        String CARGO_ID = "cargo_id";
        String CEP = "cep";
        String CIDADE = "cidade";
        String ENDERECO = "endereco";
        String PAIS = "pais";
        String USUARIO_PESSOA = "pessoa_usuario";
    }

    interface Vencimento {
        String ID = "id";
        String PESSOA_ID = "pessoa_id";
        String VALOR = "valor";
        String TIPO = "tipo";
    }

    interface Validator {
        String ID_PESSOA = "idPessoa";
    }
}
