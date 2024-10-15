package br.com.ativividade.esig.crud.validators;

import br.com.ativividade.esig.crud.DAO.PessoaDAO;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.ativividade.esig.crud.shared.Constantes.Valores.Validator.ID_PESSOA;

@FacesValidator("EmailValidatorAlterar")
public class EmailValidatorAlterar implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "E-mail Inválido", "O e-mail não pode ser nulo."
            ));
        }

        String email = (String) value;

        if (!estaNuloOuVazio(email)) {
            if (!isEmailValid(email)) {
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "E-mail Inválido", "Formato de e-mail inválido."
                ));
            }

            Integer idAtual = (Integer) component.getAttributes().get(ID_PESSOA);
            if (new PessoaDAO().verificarEmailComId(email, idAtual)) {
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "E-mail em Uso",
                        "Este e-mail já está sendo utilizado por outra pessoa."
                ));
            }
        }
    }

    private static boolean isEmailValid(String email) {
        String expression = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean estaNuloOuVazio(String value) {
        return value == null || value.trim().isEmpty();
    }
}