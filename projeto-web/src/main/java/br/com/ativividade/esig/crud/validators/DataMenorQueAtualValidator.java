package br.com.ativividade.esig.crud.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;

@FacesValidator("DataMenorQueAtualValidator")
public class DataMenorQueAtualValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof LocalDate) {
            LocalDate data = (LocalDate) value;

            if (!isDataValid(data)) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "A data não pode ser maior que a atual.", "Erro de validação"));
            }
        } else {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de data inválido.", "Erro de validação"));
        }
    }

    private static boolean isDataValid(LocalDate data) {
        // Verifica se a data é menor ou igual à data atual
        return data.isBefore(LocalDate.now()) || data.isEqual(LocalDate.now());
    }
}