package br.com.ativividade.esig.crud.model;


import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private Integer id;
    private String nome;
    private String cidade;
    private String email;
    private String cep;
    private String endereco;
    private String pais;
    private String usuario;
    private String telefone;
    private LocalDate dataNascimento;
    private Cargo cargo;


    public Pessoa() {
        this.cargo = new Cargo();
    }

    public Pessoa(Integer id, String nome, String cidade, String email, String cep, String endereco, String pais, String usuario, String telefone, LocalDate dataNascimento, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.pais = pais;
        this.usuario = usuario;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(cidade, pessoa.cidade) && Objects.equals(email, pessoa.email) && Objects.equals(cep, pessoa.cep) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(pais, pessoa.pais) && Objects.equals(usuario, pessoa.usuario) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(dataNascimento, pessoa.dataNascimento) && Objects.equals(cargo, pessoa.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cidade, email, cep, endereco, pais, usuario, telefone, dataNascimento, cargo);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", endereco='" + endereco + '\'' +
                ", pais='" + pais + '\'' +
                ", usuario='" + usuario + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cargo=" + cargo +
                '}';
    }
}
