package br.com.ativividade.esig.crud.controller;


import br.com.ativividade.esig.crud.model.Cargo;
import br.com.ativividade.esig.crud.model.Pessoa;
import br.com.ativividade.esig.crud.service.CargoService;
import br.com.ativividade.esig.crud.service.PessoaService;
import br.com.ativividade.esig.crud.util.DataUtil;
import br.com.ativividade.esig.crud.util.MensagemUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.MENSAGEM_ERRO_ALTERAR_PESSOA;
import static br.com.ativividade.esig.crud.shared.Constantes.Constantes.mensagems.MENSAGEM_ERRO_CADASTRAR_PESSOA;
import static br.com.ativividade.esig.crud.util.VerificadorUtil.naoEstaNulo;

@ManagedBean(name = "pessoaController")
@ViewScoped
public class PessoaController implements Serializable {
    private List<Pessoa> pessoas;
    private Pessoa pessoa;
    private List<Cargo> listaCargos;
    private CargoService cargoService;
    private String senhaGerada;


    private PessoaService pessoaService;

    public PessoaController() {
        this.pessoas = new ArrayList<>();
        this.pessoaService = new PessoaService();
        this.pessoa = new Pessoa();
        this.listaCargos = new ArrayList<>();
        this.cargoService = new CargoService();
    }

    public void carregarCargos() {
        this.listaCargos = this.cargoService.obterCargos();
        System.out.println("Lista de cargos: " + this.listaCargos.size());
    }

    public String cadastrar() throws SQLException {
        if (naoEstaNulo(this.pessoa)) {
            this.senhaGerada = pessoaService.cadastrar(pessoa);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
            MensagemUtil.sucesso("Cadastro realizado com sucesso!");
            System.out.println("Senha gerada" + senhaGerada);
            this.pessoa = new Pessoa();
        } else {
            MensagemUtil.erro(MENSAGEM_ERRO_CADASTRAR_PESSOA);
        }
        System.out.println("Senha gerada" + senhaGerada);
        return null;
    }

    public String alterar(Pessoa pessoa) throws SQLException {

        if (this.pessoa != null) {
            this.senhaGerada = pessoaService.alterar(pessoa);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atualização realizada com sucesso!"));
            MensagemUtil.sucesso("Cadastro realizado com sucesso!");
            System.out.println("Pessoa alterada: " + pessoa);
        } else {
            MensagemUtil.erro(MENSAGEM_ERRO_ALTERAR_PESSOA);
        }
        return null;
    }

    public String getSenhaGerada() {
        return senhaGerada;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Date getDataMaxima() {
        return DataUtil.getDataMaxima();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public List<Cargo> getListaCargos() {
        return listaCargos;
    }
}
