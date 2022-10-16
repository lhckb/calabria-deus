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

    public double alterar_juros(double taxa){
        if(taxa >= 0) {
            taxa_juros = taxa;
        }
        return taxa_juros;
    }

    public String getNomeCorrentista() { return correntista.nome; }

    public String getCpfCorrentista() { return correntista.cpf; }

    public Double getTaxaJuros() { return taxa_juros; }

}
