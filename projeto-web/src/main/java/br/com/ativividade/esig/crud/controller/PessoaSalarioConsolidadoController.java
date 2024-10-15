package br.com.ativividade.esig.crud.controller;

import br.com.ativividade.esig.crud.model.PessoaSalarioConsolidado;
import br.com.ativividade.esig.crud.service.PessoaSalarioConsolidadoService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "consolidacaoMB")
@ViewScoped
public class PessoaSalarioConsolidadoController implements Serializable {
    private List<PessoaSalarioConsolidado> pessoas;
    private PessoaSalarioConsolidadoService service;

    public PessoaSalarioConsolidadoController() {
        this.service = new PessoaSalarioConsolidadoService();
    }

    public void listarSalarios() {
        pessoas = service.listar();
    }

    public void recalcularSalarios() {
        service.calcularSalarios();
        listarSalarios();
    }

    public List<PessoaSalarioConsolidado> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaSalarioConsolidado> pessoas) {
        this.pessoas = pessoas;
    }
}
