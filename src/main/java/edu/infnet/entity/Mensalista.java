package edu.infnet.entity;

public class Mensalista {
    private String nome;
    private int matricula;

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return this.matricula;
    }

    @Override
    public String toString() {
        return "Mensalista{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
