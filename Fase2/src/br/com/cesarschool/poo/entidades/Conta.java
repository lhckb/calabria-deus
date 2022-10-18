package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

public class Conta {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;
    public double saldo = 0;
    public int status = 0;

    public static final int ATIVA = 1;
    public static final int ENCERRADA = 2;
    public static final int BLOQUEADA = 3;

    final int SUCESSO = 0;
    final int FRACASSO = -1;

    public long numero;
    public String nome;
    private LocalDate dataAbertura;

    public Conta(long codigo, String nome, int status) {
        this.numero = codigo;
        this.nome = nome;
        // Pode ser um erro
        this.status = status;
        this.dataAbertura = LocalDate.now();
    }

    public long getCodigo() {
        return numero;
    }
    public void setCodigo(long codigo) {
        this.numero = codigo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }
    public int calcularEscore() {
        if (this.status == BLOQUEADA || this.status == ENCERRADA) {
            return 0;
        }

        long diff_days = (LocalDate.now().toEpochDay()) - (LocalDate.now().toEpochDay());
        double F = (this.saldo * 3) + (diff_days) * 2;

        if (F < 5800) {
            return 1; // Bronze
        }
        else if (F < 13000){
            return 2; // Prata
        }
        else if (F < 39000){
            return 3; // Ouro
        }
        else {
            return 4; // Diamante
        }
    }

}
