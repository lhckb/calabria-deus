package br.com.cesarschool.poo.geral;

import java.time.LocalDate;

public class Conta {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;
    double saldo = 0;
    long data_de_criacao = System.currentTimeMillis();

    final int ATIVA = 1;
    final int ENCERRADA = 2;
    final int BLOQUEADA = 3;

    int status = 1;

    final int SUCESSO = 0;
    final int FRACASSO = -1;

    private long numero;
    private String nome;
    private LocalDate dataAbertura;

    public Conta(long codigo, String nome, int status, LocalDate dataAbertura) {
        this.numero = codigo;
        this.nome = nome;
        // Pode ser um erro
        this.status = status;
        this.dataAbertura = dataAbertura;
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
    boolean codigoValido() {
        if (this.numero <= 0) {
            return false;
        }
        return true;
    }
    int validarNome() {
        if (nome == null || nome.trim().equals("")) {
            return NOME_NAO_INFORMADO;
        } else if (nome.trim().length() < TAMANHO_MINIMO_NOME) {
            return NOME_MUITO_CURTO;
        }
        return SUCESSO;
    }

    // Pode ser Erro
    boolean tipoPreechido() {
        return status != 0;
    }
}
