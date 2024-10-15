package br.com.ativividade.esig.crud.model;

import java.io.Serializable;
import java.util.Objects;


public class Vencimento implements Serializable {

    private Integer id;
    private Pessoa pessoaId;
    private double valor;
    private String tipo;

    public Vencimento() {
    }

    public Vencimento(Pessoa pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Pessoa pessoaId) {
        this.pessoaId = pessoaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vencimento that = (Vencimento) o;
        return Double.compare(valor, that.valor) == 0 && Objects.equals(id, that.id) && Objects.equals(pessoaId, that.pessoaId) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pessoaId, valor, tipo);
    }
}
