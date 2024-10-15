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

import static br.com.ativividade.esig.crud.util.StringUtil.retirarMascara;
import static br.com.ativividade.esig.crud.util.VerificadorUtil.estaNuloOuVazio;

@FacesValidator("TelefoneValidator")
public class TelefoneValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone Inválido", "O telefone não pode ser nulo."));
        }

        String telefone = (String) value;
        String telefoneSemMascara = retirarMascara(telefone);
        System.out.println(telefoneSemMascara);

        if (!estaNuloOuVazio(telefone)) {
            if (!isTelefoneValid(telefoneSemMascara)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone Inválido", "Formato de telefone inválido."));
            }

            if (new PessoaDAO().existsByTelefone(retirarMascara(telefoneSemMascara))) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone em Uso", "Este telefone já está sendo utilizado."));
            }
        }
    }

    private static boolean isTelefoneValid(String telefone) {
        String expression = "^\\(?(\\d{2})\\)?\\s?(\\d)\\s?(\\d{4})-?(\\d{4})$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }
}