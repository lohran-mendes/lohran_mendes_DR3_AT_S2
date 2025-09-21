package edu.infnet.entity;

public class Mensalista {
    private final String nome;
    private final String matricula;

    public Mensalista(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    @Override
    public String toString() {
        return "Mensalista{" + "nome='" + nome + '\'' + ", matricula='" + matricula + '\'' + '}';
    }
}
