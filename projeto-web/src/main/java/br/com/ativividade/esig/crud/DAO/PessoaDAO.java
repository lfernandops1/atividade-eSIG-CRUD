package br.com.ativividade.esig.crud.DAO;


import br.com.ativividade.esig.crud.model.Cargo;
import br.com.ativividade.esig.crud.model.Pessoa;
import br.com.ativividade.esig.crud.shared.DTOs.AlterarUsuarioDTO;
import br.com.ativividade.esig.crud.shared.DTOs.CriarUsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.ativividade.esig.crud.configuration.DatabaseConnection.getConnection;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.*;
import static br.com.ativividade.esig.crud.shared.Constantes.Queries.Pessoa.*;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Pessoa.*;
import static br.com.ativividade.esig.crud.util.SenhaUtil.gerarSenha;


public class PessoaDAO {
    UsuarioDAO usuarioDAO;

    public PessoaDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(LISTAR_TODAS_PESSOAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt(ID));
                pessoa.setNome(rs.getString(NOME));
                pessoa.setCidade(rs.getString(CIDADE));
                pessoa.setEmail(rs.getString(EMAIL));
                pessoa.setCep(rs.getString(CEP));
                pessoa.setEndereco(rs.getString(ENDERECO));
                pessoa.setPais(rs.getString(PAIS));
                pessoa.setUsuario(rs.getString(USUARIO));
                pessoa.setTelefone(rs.getString(TELEFONE));
                pessoa.setDataNascimento(rs.getDate(DATA_NASCIMENTO).toLocalDate());

                Cargo cargo = new Cargo();
                cargo.setNome(rs.getString(NOME_CARGO));
                pessoa.setCargo(cargo);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.err.println(ERRO_LISTAR_PESSOAS + e.getMessage());
            e.printStackTrace();
        }
        return pessoas;
    }

    public String cadastrarPessoa(Pessoa pessoa) throws SQLException {
        Connection conn = null;
        String senhaGerada = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            int idPessoa = cadastrarPessoa(conn, INSERT_PESSOA, pessoa);
            pessoa.setId(idPessoa);

            CriarUsuarioDTO criarUsuarioDTO = new CriarUsuarioDTO();
            criarUsuarioDTO.setUsuario(pessoa.getUsuario());
            criarUsuarioDTO.setIdPessoa(idPessoa);

            senhaGerada = gerarSenha(10);
            criarUsuarioDTO.setSenha(senhaGerada);

            usuarioDAO.criarUsuario(conn, criarUsuarioDTO);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return senhaGerada;
    }

    private int cadastrarPessoa(Connection conn, String query, Pessoa pessoa) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCidade());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getCep());
            stmt.setString(5, pessoa.getEndereco());
            stmt.setString(6, pessoa.getPais());
            stmt.setString(7, pessoa.getUsuario());
            stmt.setString(8, pessoa.getTelefone());
            stmt.setDate(9, java.sql.Date.valueOf(pessoa.getDataNascimento()));
            stmt.setInt(10, pessoa.getCargo().getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(ID);
                } else {
                    throw new SQLException(NENHUM_ID_RETORNADO_AO_CADASTRAR);
                }
            }
        }
    }

    public String atualizarPessoa(Pessoa pessoa) throws SQLException {
        Connection conn = null;
        String senhaGerada = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            atualizarPessoa(conn, UPDATE_PESSOA, pessoa);

            AlterarUsuarioDTO alterarUsuarioDTO = new AlterarUsuarioDTO();
            alterarUsuarioDTO.setUsuario(pessoa.getUsuario());
            alterarUsuarioDTO.setIdPessoa(pessoa.getId());

            senhaGerada = gerarSenha(10);
            alterarUsuarioDTO.setSenha(senhaGerada);

            usuarioDAO.atualizarUsuario(conn, alterarUsuarioDTO);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return senhaGerada;
    }

    private void atualizarPessoa(Connection conn, String query, Pessoa pessoa) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCidade());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getCep());
            stmt.setString(5, pessoa.getEndereco());
            stmt.setString(6, pessoa.getPais());
            stmt.setString(7, pessoa.getUsuario());
            stmt.setString(8, pessoa.getTelefone());
            stmt.setDate(9, java.sql.Date.valueOf(pessoa.getDataNascimento()));
            stmt.setInt(10, pessoa.getCargo().getId());
            stmt.setInt(11, pessoa.getId());
            stmt.executeUpdate();
        }
    }

    public boolean existsByEmail(String email) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_VERIFICAR_EMAIL_NAO_UTILIZADO)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println(CONTAGEM_EMAILS + count);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByTelefone(String telefone) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_VERIFICAR_TELEFONE_NAO_UTILIZADO)) {

            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByUsuario(String usuario) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_VERIFICAR_USUARIO_NAO_UTILIZADO)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



