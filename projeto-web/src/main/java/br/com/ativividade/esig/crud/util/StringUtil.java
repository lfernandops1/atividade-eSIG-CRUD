package br.com.ativividade.esig.crud.util;

public class StringUtil extends org.apache.commons.lang3.StringUtils {

    public static String retirarMascara(String valorComMascara) {
        return valorComMascara.replaceAll("\\D*", "");
    }

}
