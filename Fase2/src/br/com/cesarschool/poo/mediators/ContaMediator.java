package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Conta;

import java.time.LocalDate;

public class ContaMediator {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;

    static final int ATIVA = 1;
    static final int ENCERRADA = 2;
    static final int BLOQUEADA = 3;

    static final int SUCESSO = 0;
    static final int FRACASSO = -1;

    public static int creditar(Conta conta, double valor) {
        if (conta.status == ENCERRADA || valor < 0) { return FRACASSO; }
        else {
            conta.saldo += valor;
            return SUCESSO;
        }
    }

    public static int debitar(Conta conta, double valor) {
        if (conta.status == BLOQUEADA || valor < 0) { return FRACASSO; }
        else {
            conta.saldo -= valor;
            return SUCESSO;
        }
    }

    public static boolean codigoValido(Conta conta) {
        if (conta.numero <= 0) {
            return false;
        }
        return true;
    }
    public static int validarNome(Conta conta) {
        if (conta.nome == null || conta.nome.trim().equals("")) {
            return NOME_NAO_INFORMADO;
        } else if (conta.nome.trim().length() < TAMANHO_MINIMO_NOME) {
            return NOME_MUITO_CURTO;
        }
        return SUCESSO;
    }

    // Pode ser Erro
    public static boolean tipoPreechido(Conta conta) {
        return conta.status != 0;
    }
}
