package br.com.ativividade.esig.crud.util;

import br.com.ativividade.esig.crud.model.Usuario;

import javax.faces.context.FacesContext;
import java.util.Map;

import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.outros.USUARIO_DA_SESSAO;

public class SessaoUtil {
    public static void colocarVariavelNaSessao(String nomeValor, Object valor) {
        getSessionMap().put(nomeValor, valor);
    }

    public static void excluirVariavelDaSessao(String nomeValor) {
        getSessionMap().remove(nomeValor);
    }

    public static Object retornarVariavelDaSessao(String nomeValor) {
        return getSessionMap().get(nomeValor);
    }

    private static Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public static Usuario retornarUsuarioDaSessao() {
        return (Usuario) getSessionMap().get(USUARIO_DA_SESSAO);
    }
}
