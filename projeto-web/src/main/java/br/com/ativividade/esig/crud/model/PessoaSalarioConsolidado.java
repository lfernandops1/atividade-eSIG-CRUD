package br.com.ativividade.esig.crud.model;

import java.util.Objects;

public class PessoaSalarioConsolidado {

    private Integer id;
    private Pessoa pessoa;
    private Cargo cargo;
    private Double salario;

    public PessoaSalarioConsolidado() {
        this.cargo = new Cargo();
        this.pessoa = new Pessoa();
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaSalarioConsolidado that = (PessoaSalarioConsolidado) o;
        return Objects.equals(id, that.id) && Objects.equals(pessoa, that.pessoa) && Objects.equals(cargo, that.cargo) && Objects.equals(salario, that.salario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pessoa, cargo, salario);
    }

    @Override
    public String toString() {
        return "PessoaSalarioConsolidado{" +
                "id=" + id +
                ", pessoa=" + pessoa +
                ", cargo=" + cargo +
                ", salario=" + salario +
                '}';
    }
}
