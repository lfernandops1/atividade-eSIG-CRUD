package br.com.ativividade.esig.crud.validators;

import br.com.ativividade.esig.crud.DAO.PessoaDAO;
import br.com.ativividade.esig.crud.model.Usuario;
import br.com.ativividade.esig.crud.util.SessaoUtil;

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

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Inválido", "O email não pode ser nulo."));
        }

        String email = (String) value;

        System.out.println("Validando email: " + email);

        if (!estaNuloOuVazio(email)) {
            if (!isEmailValid(email)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Inválido", "Formato de email inválido."));
            }

            // Verifica se o email já está em uso
            if (new PessoaDAO().existsByEmail(email)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email em Uso", "Este email já está sendo utilizado."));
            }
        }
    }

    private static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}