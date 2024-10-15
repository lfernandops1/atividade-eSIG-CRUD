package br.com.ativividade.esig.crud.service;

import br.com.ativividade.esig.crud.DAO.CargoDAO;
import br.com.ativividade.esig.crud.model.Cargo;

import java.util.List;

public class CargoService {
    private CargoDAO cargoDAO;

    public CargoService() {
        this.cargoDAO = new CargoDAO();
    }

    public List<Cargo> obterCargos() {
        return cargoDAO.listarCargos();
    }
}
