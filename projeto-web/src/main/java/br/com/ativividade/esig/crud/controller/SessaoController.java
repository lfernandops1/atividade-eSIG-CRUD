package br.com.ativividade.esig.crud.controller;


import br.com.ativividade.esig.crud.model.Usuario;
import br.com.ativividade.esig.crud.service.UsuarioService;
import br.com.ativividade.esig.crud.shared.DTOs.ParametrosLoginDTO;
import br.com.ativividade.esig.crud.util.MensagemUtil;
import br.com.ativividade.esig.crud.util.PagesUtil;
import br.com.ativividade.esig.crud.util.SessaoUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.MENSAGEM_LOGIN_INVALIDO_DETALHES;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.MENSAGEM_LOGIN_INVALIDO_TITULO;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.outros.USUARIO_DA_SESSAO;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.pagina.PAGINA_LOGIN;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.pagina.PAGINA_PRINCPAL;
import static br.com.ativividade.esig.crud.util.SessaoUtil.colocarVariavelNaSessao;
import static br.com.ativividade.esig.crud.util.SessaoUtil.retornarUsuarioDaSessao;
import static br.com.ativividade.esig.crud.util.VerificadorUtil.estaNulo;
import static br.com.ativividade.esig.crud.util.VerificadorUtil.naoEstaNulo;

@SessionScoped
@ManagedBean(name = "sessaoMB")
public class SessaoController {
    private ParametrosLoginDTO parametrosLoginDTO;
    private Usuario usuarioSessao = retornarUsuarioDaSessao();
    private UsuarioService usuarioService;

    public SessaoController() {
        this.usuarioService = new UsuarioService();
        this.parametrosLoginDTO = new ParametrosLoginDTO();
    }

    public void verificarUsuario() {
        this.usuarioSessao = usuarioService.verificarUsuario(parametrosLoginDTO);
        if (naoEstaNulo(usuarioSessao)) {
            colocarVariavelNaSessao(USUARIO_DA_SESSAO, usuarioSessao);
            redirecionarPrincipal();
        } else {
            MensagemUtil.alerta(MENSAGEM_LOGIN_INVALIDO_TITULO, MENSAGEM_LOGIN_INVALIDO_DETALHES);
        }
    }

    public void sair() {
        SessaoUtil.excluirVariavelDaSessao("usuarioLogado");
        this.parametrosLoginDTO = new ParametrosLoginDTO();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            MensagemUtil.erro("Erro ao sair", e.getMessage());
        }
    }

    public String getNomeUsuario() {
        return usuarioSessao != null ? usuarioSessao.getPessoa().getNome() : "Usuário não logado";
    }

    public void controleSessao() {
        if (estaNulo(usuarioSessao)) redirecionarLogin();
    }

    public void redirecionarLogin() {
        PagesUtil.redirectPage(PAGINA_LOGIN);
    }

    public static void redirecionarPrincipal() {
        PagesUtil.redirectPage(PAGINA_PRINCPAL);
    }

    public void redirecionarParaAlteracao() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alteracaoPessoa.xhtml");
        } catch (IOException e) {
            MensagemUtil.erro("Erro ao ir para Alterar Dados da Pessoa", e.getMessage());
        }
    }

    public ParametrosLoginDTO getParametrosLoginDTO() {
        return parametrosLoginDTO;
    }

    public void setParametrosLoginDTO(ParametrosLoginDTO parametrosLoginDTO) {
        this.parametrosLoginDTO = parametrosLoginDTO;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }
}
