package br.com.ativividade.esig.crud.DAO;

import br.com.ativividade.esig.crud.model.Cargo;
import br.com.ativividade.esig.crud.model.Pessoa;
import br.com.ativividade.esig.crud.model.Usuario;
import br.com.ativividade.esig.crud.shared.DTOs.AlterarUsuarioDTO;
import br.com.ativividade.esig.crud.shared.DTOs.CriarUsuarioDTO;
import br.com.ativividade.esig.crud.shared.DTOs.ParametrosLoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static br.com.ativividade.esig.crud.configuration.DatabaseConnection.getConnection;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.ERRO_ATUALIZAR_USUARIO;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.ERRO_BUSCAR_USUARIO;
import static br.com.ativividade.esig.crud.shared.Constantes.Queries.Sessao.*;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Pessoa.USUARIO;
import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Salario.*;

public class UsuarioDAO {

    private CargoDAO cargoDAO;

    public UsuarioDAO() {
        this.cargoDAO = new CargoDAO();
    }

    public Usuario verificarUsuario(ParametrosLoginDTO parametrosLoginDTO) {
        Usuario usuarioParaLogar = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QUERY_VERIFICAR_USUARIO)) {

            pstmt.setString(1, parametrosLoginDTO.getUsuario());
            pstmt.setString(2, parametrosLoginDTO.getSenha());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Integer id = rs.getInt(USUARIO_ID);
                String usuario = rs.getString(USUARIO);
                String senha = rs.getString(SENHA);

                Integer idPessoa = rs.getInt(PESSOA_ID);
                String nome = rs.getString(NOME);
                String email = rs.getString(EMAIL);
                String telefone = rs.getString(TELEFONE);
                LocalDate dataNascimento = rs.getDate(DATA_NASCIMENTO).toLocalDate();
                Integer cargoId = rs.getInt(CARGO_ID);
                String cep = rs.getString(CEP);
                String cidade = rs.getString(CIDADE);
                String endereco = rs.getString(ENDERECO);
                String pais = rs.getString(PAIS);
                String usuarioPessoa = rs.getString(USUARIO_PESSOA);

                Cargo cargo = this.cargoDAO.buscarCargoPorId(cargoId);

                Pessoa pessoa = new Pessoa(idPessoa,
                        nome, cidade,
                        email, cep,
                        endereco, pais,
                        usuarioPessoa, telefone,
                        dataNascimento, cargo);

                usuarioParaLogar = new Usuario(id, usuario, senha, pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioParaLogar;
    }

    public void criarUsuario(Connection conn, CriarUsuarioDTO criarUsuarioDTO) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_INSERIR_USUARIO)) {
            stmt.setString(1, criarUsuarioDTO.getUsuario());
            stmt.setString(2, criarUsuarioDTO.getSenha());
            stmt.setInt(3, criarUsuarioDTO.getIdPessoa());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException(ERRO_BUSCAR_USUARIO);
            }
            System.out.println("Usuário criado com sucesso: " + criarUsuarioDTO.getUsuario() +
                    " | Senha: " + criarUsuarioDTO.getSenha());
        }
    }

    public void atualizarUsuario(Connection conn, AlterarUsuarioDTO alterarUsuarioDTO) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_ATUALIZAR_USUARIO)) {
            stmt.setString(1, alterarUsuarioDTO.getUsuario());
            stmt.setString(2, alterarUsuarioDTO.getSenha());
            stmt.setInt(3, alterarUsuarioDTO.getIdPessoa());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException(ERRO_ATUALIZAR_USUARIO);
            }
            System.out.println("Usuário atualizado com sucesso: " + alterarUsuarioDTO.getUsuario());
        }
    }
}
