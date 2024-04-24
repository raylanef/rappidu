package br.com.rappidu.domian.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Cliente {
    private Long id;

    private String name;

    private Cpf cpf;

    private List<Endereco> enderecos = new ArrayList<>();

    public void setCpf(String documentNumber) {
        this.cpf = new Cpf(documentNumber);
    }

    public String getCpf() {
        return this.cpf.get();
    }
}