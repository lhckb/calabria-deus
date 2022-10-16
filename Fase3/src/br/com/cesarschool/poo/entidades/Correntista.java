package br.com.cesarschool.poo.entidades;

import br.com.cesarschool.poo.ValidaCPF;

public class Correntista {
    public String cpf;
    public String nome;

    public Correntista(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    boolean validaCpf() {
        if (ValidaCPF.isCPF(this.cpf)) {
            return true;
        }
        return false;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }
}
