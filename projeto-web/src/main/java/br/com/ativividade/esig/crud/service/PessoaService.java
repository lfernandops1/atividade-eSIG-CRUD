package br.com.ativividade.esig.crud.service;


import br.com.ativividade.esig.crud.DAO.PessoaDAO;
import br.com.ativividade.esig.crud.DAO.UsuarioDAO;
import br.com.ativividade.esig.crud.model.Pessoa;

import java.sql.SQLException;

public class PessoaService {

    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;

    public PessoaService() {
        this.pessoaDAO = new PessoaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }


    public String cadastrar(Pessoa pessoa) throws SQLException {
        retirarMascaras(pessoa);
        return pessoaDAO.cadastrarPessoa(pessoa);
    }

    public String alterar(Pessoa pessoa) throws SQLException {
        retirarMascaras(pessoa);
        return pessoaDAO.atualizarPessoa(pessoa);
    }

    private void retirarMascaras(Pessoa pessoa) {
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[().\\s-]", ""));
        pessoa.setCep(pessoa.getCep().replaceAll("[.-]", ""));
    }
}
