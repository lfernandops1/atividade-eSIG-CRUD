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

import static br.com.ativividade.esig.crud.util.VerificadorUtil.estaNuloOuVazio;

@FacesValidator("UsuarioValidator")
public class UsuarioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário Inválido", "O nome de usuário não pode ser nulo."));
        }

        String usuario = (String) value;

        if (!estaNuloOuVazio(usuario)) {
            if (!isUsuarioValid(usuario)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário Inválido", "O nome de usuário deve conter apenas letras e números."));
            }

            if (new PessoaDAO().existsByUsuario(usuario)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário em Uso", "Este nome de usuário já está em uso."));
            }
        }
    }

    private static boolean isUsuarioValid(String usuario) {
        String expression = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }
}