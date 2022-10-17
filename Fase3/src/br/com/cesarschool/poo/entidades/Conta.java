package br.com.cesarschool.poo.entidades;

import br.com.cesarschool.poo.ValidaCPF;

public class Conta {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;
    public double saldo = 0;
    public int status = 0;
    public long data_de_criacao = System.currentTimeMillis();

    final int ATIVA = 1;
    final int ENCERRADA = 2;
    final int BLOQUEADA = 3;

    final int SUCESSO = 0;
    final int FRACASSO = -1;

    public long numero;
    public String nome;
    private double preco;

    public Correntista correntista;

    public Conta(long codigo, String nome, int status, Correntista correntista) {
        this.numero = codigo;
        this.nome = nome;
        // Pode ser um erro
        this.status = status;
        this.correntista = correntista;
    }

    public long getCodigo() {
        return numero;
    }
    public void setCodigo(long codigo) {
        this.numero = codigo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

}
