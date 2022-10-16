package br.com.cesarschool.poo.entidades;

public class ContaPoupanca extends Conta{

    public int total_depositos = 0;
    public double taxa_juros;

    public ContaPoupanca(long codigo, String nome, int status, Correntista correntista) {
        super(codigo, nome, status, correntista);
    }

    boolean validarTaxaDeJuros() {
        if (taxa_juros >= 0) { return true; }
        return false;
    }


}
