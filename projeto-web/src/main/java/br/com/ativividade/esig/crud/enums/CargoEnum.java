package br.com.ativividade.esig.crud.enums;

public enum CargoEnum {
    ESTAGIARIO(1, "Estagiário"),
    TECNICO(2, "Tecnico"),
    ANALISTA(3, "Analista"),
    COORDENADOR(4, "Coordenador"),
    GERENTE(5, "Gerente");

    private Integer codigo;
    private String nome;

    CargoEnum(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
