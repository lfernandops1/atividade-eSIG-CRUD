package br.com.ativividade.esig.crud.DAO;

import br.com.ativividade.esig.crud.model.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.ativividade.esig.crud.configuration.DatabaseConnection.getConnection;
import static br.com.ativividade.esig.crud.shared.Constantes.Queries.Cargo.*;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Cargo.ID;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Cargo.NOME;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Generico.STRING_VAZIA;

public class CargoDAO {

    public CargoDAO() {

    }

    public List<Cargo> listarCargos() {
        List<Cargo> cargos = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(LISTAR_CARGOS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt(ID);
                String nome = rs.getString(NOME);
                cargos.add(new Cargo(id, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    public Cargo buscarCargoPorId(Integer cargoId) {
        Cargo cargo = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(BUSCAR_CARGO_POR_ID)) {
            pstmt.setInt(1, cargoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt(ID);
                String nome = rs.getString(NOME);
                cargo = new Cargo(id, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargo;
    }

    public String obterNomeCargo(int cargoId) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement(OBTER_NOME_CARGO)) {
            stmt.setInt(1, cargoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(NOME);
                }
            }
        }
        return STRING_VAZIA;
    }
}
