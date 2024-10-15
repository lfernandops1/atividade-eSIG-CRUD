package br.com.ativividade.esig.crud.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.*;
import static br.com.ativividade.esig.crud.util.VerificadorUtil.estaNuloOuVazio;

@FacesValidator ("EmailValidatorAlterar")
public class EmailValidatorAlterar implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        if (!estaNuloOuVazio(email)) {
            if (!isEmailValid(email)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, MENSAGEM_EMAIL_INVALIDO_TITULO, MENSAGEM_EMAIL_INVALIDO_DETALHES));
            }
        }
    }

    private static boolean isEmailValid(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}