package br.com.ativividade.esig.crud.DAO;

import br.com.ativividade.esig.crud.model.Vencimento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.ativividade.esig.crud.configuration.DatabaseConnection.getConnection;
import static br.com.ativividade.esig.crud.shared.Constantes.Queries.Vencimento.BUSCAR_VENCIMENTO_POR_ID_PESSOA;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Vencimento.*;

public class VencimentoDAO {

    public VencimentoDAO() {
    }

    public List<Vencimento> buscarPorPessoa(int pessoaId) throws SQLException {
        List<Vencimento> vencimentos = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(BUSCAR_VENCIMENTO_POR_ID_PESSOA)) {
            stmt.setInt(1, pessoaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vencimento vencimento = new Vencimento();
                    vencimento.setId(rs.getInt(ID));
                    vencimento.getPessoaId().setId(rs.getInt(PESSOA_ID));
                    vencimento.setValor(rs.getDouble(VALOR));
                    vencimento.setTipo(rs.getString(TIPO));
                    vencimentos.add(vencimento);
                }
            }
        }
        return vencimentos;
    }
}
