package br.com.rappidu.domain.entities;


public class Cpf {

    private final String cpf;

    public Cpf(String cpfNumber) {
        // TODO Fazer validações
        this.cpf = cpfNumber;
    }

    public String get() {
        return this.cpf;
    }
}
