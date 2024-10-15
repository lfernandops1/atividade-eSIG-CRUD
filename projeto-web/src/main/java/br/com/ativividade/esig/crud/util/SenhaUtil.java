package br.com.ativividade.esig.crud.util;

import java.security.SecureRandom;

public class SenhaUtil {
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String gerarSenha(int tamanho) {
        StringBuilder senha = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            int index = RANDOM.nextInt(CHARSET.length());
            senha.append(CHARSET.charAt(index));
        }
        return senha.toString();
    }
}
