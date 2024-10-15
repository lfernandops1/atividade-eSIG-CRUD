package br.com.ativividade.esig.crud.model;

import java.io.Serializable;
import java.util.Objects;

public class Cargo implements Serializable {

    private Integer id;
    private String nome;

    public Cargo() {
    }

    public Cargo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cargo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(id, cargo.id) && Objects.equals(nome, cargo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
