package br.com.ativividade.esig.crud.DAO;

import br.com.ativividade.esig.crud.model.Pessoa;
import br.com.ativividade.esig.crud.model.PessoaSalarioConsolidado;
import br.com.ativividade.esig.crud.model.Vencimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.ativividade.esig.crud.configuration.DatabaseConnection.getConnection;
import static br.com.ativividade.esig.crud.shared.Constantes.Queries.PessoaSalarioConsolidado.*;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Salario.*;

public class PessoaSalarioConsolidadoDAO {

    private PessoaDAO pessoaDAO;
    private VencimentoDAO vencimentoDAO;
    private CargoDAO cargoDAO;

    public PessoaSalarioConsolidadoDAO() {
        this.pessoaDAO = new PessoaDAO();
        this.vencimentoDAO = new VencimentoDAO();
        this.cargoDAO = new CargoDAO();
    }

    public void calcularSalarios() {

        try (Connection conn = getConnection();
             PreparedStatement historicoStmt = conn.prepareStatement(QUERY_REGISTRAR_HISTORICO);
             PreparedStatement deleteStmt = conn.prepareStatement(QUERY_DELETAR_REGISTROS);
             PreparedStatement insertStmt = conn.prepareStatement(QUERY_CALCULAR_SALARIO)) {

            historicoStmt.executeUpdate();
            deleteStmt.executeUpdate();

            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PessoaSalarioConsolidado> listarTudo() {
        List<PessoaSalarioConsolidado> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QUERY_LISTAR_TODOS_SALARIOS);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PessoaSalarioConsolidado pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
                pessoaSalarioConsolidado.getPessoa().setId(rs.getInt(PESSOA_ID));
                pessoaSalarioConsolidado.getPessoa().setNome(rs.getString(NOME_PESSOA));
                pessoaSalarioConsolidado.getCargo().setNome(rs.getString(NOME_CARGO));
                pessoaSalarioConsolidado.setSalario(rs.getDouble(SALARIO));
                lista.add(pessoaSalarioConsolidado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void calcularEInserirSalarios() throws SQLException {
        List<Pessoa> pessoas = pessoaDAO.listarPessoas();

        try (PreparedStatement stmt = getConnection().prepareStatement(CALCULAR_E_INSERIR_SALARIOS)) {
            for (Pessoa pessoa : pessoas) {
                double salarioBase = obterSalarioBasePorCargo(pessoa.getCargo().getId());
                double salarioFinal = calcularSalarioFinal(pessoa.getId(), salarioBase);

                stmt.setInt(1, pessoa.getId());
                stmt.setString(2, pessoa.getNome());
                stmt.setString(3, cargoDAO.obterNomeCargo(pessoa.getCargo().getId()));
                stmt.setDouble(4, salarioFinal);
                stmt.executeUpdate();
            }
        }
    }

    private double obterSalarioBasePorCargo(int cargoId) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement(OBTER_SALARIO_POR_CARGO)) {
            stmt.setInt(1, cargoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(SALARIO_BASE);
                }
            }
        }
        return 0.0;
    }

    private double calcularSalarioFinal(int pessoaId, double salarioBase) throws SQLException {
        List<Vencimento> vencimentos = vencimentoDAO.buscarPorPessoa(pessoaId);
        double salarioFinal = salarioBase;

        for (Vencimento vencimento : vencimentos) {
            if (vencimento.getTipo().equalsIgnoreCase(CREDITO)) {
                salarioFinal += vencimento.getValor();
            } else if (vencimento.getTipo().equalsIgnoreCase(DEBITO)) {
                salarioFinal -= vencimento.getValor();
            }
        }
        return salarioFinal;
    }

}
