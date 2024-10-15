package br.com.ativividade.esig.crud.service;

import br.com.ativividade.esig.crud.DAO.PessoaSalarioConsolidadoDAO;
import br.com.ativividade.esig.crud.model.PessoaSalarioConsolidado;

import java.sql.SQLException;
import java.util.List;

public class PessoaSalarioConsolidadoService {

    private PessoaSalarioConsolidadoDAO consolidadoDAO;

    public PessoaSalarioConsolidadoService() {
        this.consolidadoDAO = new PessoaSalarioConsolidadoDAO();
    }

    public void calcularSalarios() {
        consolidadoDAO.calcularSalarios();
    }

    public List<PessoaSalarioConsolidado> listar() {
        return consolidadoDAO.listarTudo();
    }

    public void calcularEInserirSalarios() throws SQLException {
        this.consolidadoDAO.calcularEInserirSalarios();

    }
}
