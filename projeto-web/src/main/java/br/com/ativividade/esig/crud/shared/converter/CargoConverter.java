package br.com.ativividade.esig.crud.shared.converter;

import br.com.ativividade.esig.crud.model.Cargo;
import br.com.ativividade.esig.crud.service.CargoService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter("cargoConverter")
public class CargoConverter implements Converter {

    private CargoService cargoService;

    public CargoConverter() {
        this.cargoService = new CargoService();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Cargo cargo = (Cargo) value;

        // Retorna o ID do cargo como string
        return cargo.getId() != null ? cargo.getId().toString() : "";
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }


        try {
            Integer id = Integer.valueOf(value);
            List<Cargo> cargosDisponiveis = cargoService.obterCargos();
            return cargosDisponiveis.stream()
                    .filter(cargo -> cargo.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas na conversão do cargo.",
                    "O ID do cargo deve ser um número válido.");
            throw new ConverterException(facesMessage);
        }
    }
}
