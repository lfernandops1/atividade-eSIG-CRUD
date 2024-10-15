package br.com.ativividade.esig.crud.service;

import br.com.ativividade.esig.crud.DAO.UsuarioDAO;
import br.com.ativividade.esig.crud.model.Usuario;
import br.com.ativividade.esig.crud.shared.DTOs.ParametrosLoginDTO;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario verificarUsuario(ParametrosLoginDTO parametrosLoginDTO) {
        return usuarioDAO.verificarUsuario(parametrosLoginDTO);
    }
}
