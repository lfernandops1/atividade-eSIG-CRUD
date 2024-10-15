package br.com.ativividade.esig.crud.shared.Constantes;

public interface Constantes {
    interface mensagems {
        String MENSAGEM_LOGIN_INVALIDO_TITULO = "Acesso Inválido.";
        String MENSAGEM_LOGIN_INVALIDO_DETALHES = "Email ou Senha Incorretos!";
        String MENSAGEM_EMAIL_INVALIDO_TITULO = "Email inválido!";
        String MENSAGEM_EMAIL_INVALIDO_DETALHES = "O email não está escrito corretamente.";
        String MENSAGEM_SUCESSO_EXCLUIR_PESSOA = "Usuario Excluir com Sucesso!";
        String MENSAGEM_ERRO_EXCLUIR_USUARIO = "Erro ao Excluir o Usuario";
        String MENSAGEM_SUCESSO_CADASTRAR_PESSOA = "Cadastro Realizado com sucesso";
        String MENSAGEM_SUCESSO_ATUALIZAR_PESSOA = "Atualização Realizada com sucesso";
        String MENSAGEM_ERRO_CADASTRAR_PESSOA = "Erro ao Cadastrar Usuario";
        String MENSAGEM_SUCESSO_ALTERAR_PESSOA = "Usuario alterado com sucesso";
        String MENSAGEM_ERRO_ALTERAR_PESSOA = "Erro ao alterar Usuario";
        String MENSAGEM_ERRO_LISTAR_USUARIOS = "Erro ao listar Usuarios";
        String ERRO_LISTAR_PESSOAS = "Erro ao listar pessoas";
        String NENHUM_ID_RETORNADO_AO_CADASTRAR = "Nenhum ID retornado ao cadastrar pessoa.";
        String CONTAGEM_EMAILS = "Contagem de emails encontrados: ";
        String ERRO_BUSCAR_USUARIO = "Erro: Nenhum usuário foi criado.";
        String ERRO_ATUALIZAR_USUARIO = "Erro: Nenhum usuário foi criado.";
    }

    interface outros {
        String USUARIO_DA_SESSAO = "user_session";
        String FORM = "form";
        String DLG = "dlg";
    }

    interface pagina {
        String PAGINA_PRINCPAL = "principal";
        String PAGINA_LOGIN = "login";
    }

}
