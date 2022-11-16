package br.com.cesarschool.poo.entidades;

import br.com.cesarschool.poo.ValidaCPF;
import br.com.cesarschool.poo.utils.Comparavel;
import br.com.cesarschool.poo.utils.Identificavel;

import java.lang.String;

public class Correntista extends Identificavel implements Comparavel {
    public String cpf;
    public String nome;

    public Correntista(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

     public boolean validaCpf(String cpf) { //Não Funciona Ainda!
        if (ValidaCPF.validarCPF(cpf)) {
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

    @Override
    public int comparar(Object objeto) {
        Correntista incoming = (Correntista) objeto;
        return incoming.nome.compareTo(this.getNome());
    }

    @Override
    public String getIdentificadorUnico() {
        return getCpf();
    }
}
