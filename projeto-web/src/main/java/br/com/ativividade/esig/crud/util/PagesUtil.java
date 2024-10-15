package br.com.ativividade.esig.crud.util;

import org.primefaces.context.RequestContext;

import javax.faces.context.FacesContext;
import java.io.IOException;

import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.outros.*;

public class PagesUtil {
    public static void redirectPage(String pagina){
        try{
            FacesContext context  = FacesContext.getCurrentInstance();
            String url = context.getExternalContext().getRequestContextPath();
            context.getExternalContext().redirect(url+"/pages/"+pagina+".faces");
        }catch (IOException e){
            MensagemUtil.erro("Erro ao acessar a pagina: "+pagina, e.getMessage());
        }
    }

    public static void fecharDialog(String dialog) {
        getRequestContext().execute("PF('" + dialog + "').hide();");
    }

    public static void atualizarComponente(String componente){
        getRequestContext().update(componente);
    }

    public static void abrirDialogAtualizado(String nomeDialog){
        atualizarComponente(FORM+nomeDialog);
        abrirDialog(DLG+nomeDialog);
    }

    public static void abrirDialog(String dialog) {
        getRequestContext().execute("PF('" + dialog + "').show();");
    }

    private static RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }
}
