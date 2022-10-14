package br.com.cesarschool.poo.geral;

public class Conta {
    long numero;
    double saldo = 0;
    int status;
    long data_de_criacao = System.currentTimeMillis();

    final int ATIVA = 1;
    final int ENCERRADA = 2;
    final int BLOQUEADA = 3;

    final int SUCESSO = 1;
    final int FRACASSO = 0;

    public long getCodigo() {
        return numero;
    }
    public void setCodigo(long codigo) {
        this.numero = codigo;
    }

    int creditar(double valor) {
        if (this.status == ENCERRADA || valor < 0) { return FRACASSO; }
        else {
            this.saldo += valor;
            return SUCESSO;
        }
    }

    int debitar(double valor) {
        if (this.status == BLOQUEADA || valor < 0) { return FRACASSO; }
        else {
            this.saldo -= valor;
            return SUCESSO;
        }
    }

    int calcularEscore() {
        if (status == BLOQUEADA || status == ENCERRADA) {
            return 0;
        }
        int days_since_epoch = (int) ((System.currentTimeMillis() / (1000*60*60*24)) % 7);
        int days_since_epoch_from_created_time = (int) ((data_de_criacao / (1000*60*60*24)) % 7);
        double F = (saldo * 3) + (days_since_epoch - days_since_epoch_from_created_time) * 2;

        if (F < 5800) {
            return 1;
        }
        else if (F < 13000){
            return 2;
        }
        else if (F < 39000){
            return 3;
        }
        else {
            return 4;
        }
    }
}
