package br.com.cesarschool.poo.entidades;

import br.com.cesarschool.poo.ValidaCPF;
import br.com.cesarschool.poo.utils.Comparavel;
import br.com.cesarschool.poo.utils.Identificavel;

import java.time.LocalDate;

public class Conta extends Identificavel {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;
    public double saldo = 0;
    public int status = 0;
    public long data_de_criacao = System.currentTimeMillis();

    public final int ATIVA = 1;
    public final int ENCERRADA = 2;
    public final int BLOQUEADA = 3;

    final int SUCESSO = 0;
    final int FRACASSO = -1;

    public long numero;
    public String nome;
    private LocalDate dataAbertura;

    public Correntista correntista;

    public Conta(long codigo, String nome, int status, Correntista correntista) {
        this.numero = codigo;
        this.nome = nome;
        // Pode ser um erro
        this.status = status;
        this.correntista = correntista;
        this.dataAbertura = LocalDate.now();
    }

    public long getCodigo() {
        return numero;
    }
    public void setCodigo(long codigo) {
        this.numero = codigo;
    }

    public double getSaldo() { return this.saldo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }
    public int calcularEscore(Conta conta) {
        if (status == BLOQUEADA || status == ENCERRADA) {
            return 0;
        }
        long diff_days = (LocalDate.now().toEpochDay()) - (LocalDate.now().toEpochDay());
        double F = (this.saldo * 3) + (diff_days) * 2;

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

    @Override
    public String getIdentificadorUnico() {
        return Long.toString(getCodigo());
    }
}
