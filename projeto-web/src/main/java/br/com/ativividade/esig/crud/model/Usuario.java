package br.com.ativividade.esig.crud.model;

import java.util.Objects;

public class Usuario {
    private Integer id;
    private String usuario;
    private String senha;
    private Pessoa pessoa;

    public Usuario() {
        this.pessoa = new Pessoa();
    }

    public Usuario(Integer id, String usuario, String senha, Pessoa pessoa) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(usuario, usuario1.usuario) && Objects.equals(senha, usuario1.senha) && Objects.equals(pessoa, usuario1.pessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, senha, pessoa);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", pessoa=" + pessoa +
                '}';
    }
}
