package br.com.cesarschool.poo.geral;

import java.time.LocalDate;

public class Conta {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;
    double saldo = 0;
    final int ATIVA = 1;
    final int ENCERRADA = 2;
    final int BLOQUEADA = 3;

    int status = 1;

    final int SUCESSO = 0;
    final int FRACASSO = -1;

    private long numero;
    private String nome;
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

    public int creditar(double valor) {
        if (this.status == ENCERRADA || valor < 0) { return FRACASSO; }
        else {
            this.saldo += valor;
            return SUCESSO;
        }
    }

    public int debitar(double valor) {
        if (this.status == BLOQUEADA || valor < 0 || this.saldo < 0) { return FRACASSO; }
        else {
            this.saldo -= valor;
            return SUCESSO;
        }
    }

    int calcularEscore() {
        if (status == BLOQUEADA || status == ENCERRADA) {
            return 0;
        }

        long diff_days = (LocalDate.now().toEpochDay()) - (LocalDate.now().toEpochDay());
        double F = (saldo * 3) + (diff_days) * 2;

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
