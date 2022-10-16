package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.ContaPoupanca;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.repositorios.RepositorioConta;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;

public class ContaMediator {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;

    static final int ATIVA = 1;
    static final int ENCERRADA = 2;
    static final int BLOQUEADA = 3;

    static final int SUCESSO = 1;
    static final int FRACASSO = 0;

    private RepositorioConta repositorioConta = RepositorioConta.getInstancia();

    public boolean incluir(Conta conta) {
        return repositorioConta.incluir(conta);
    }
    public boolean alterar(Conta conta) {
        return repositorioConta.alterar(conta);
    }
    public Conta buscar(int codigo) {
        return repositorioConta.buscar(codigo);
    }
    public boolean excluir(int codigo) {
        return repositorioConta.excluir(codigo);
    }

    static int creditar(Conta conta, double valor) {
        if (conta instanceof ContaPoupanca) {
            conta.saldo = conta.saldo + (1 + ((ContaPoupanca) conta).taxa_juros / 100) * valor;
            ((ContaPoupanca) conta).total_depositos++;
            return SUCESSO;
        }
        else {
            if (conta.status == ENCERRADA || valor < 0) { return FRACASSO; }
            else {
                conta.saldo += valor;
                return SUCESSO;
            }
        }
    }

    static int debitar(Conta conta, double valor) {
        if (conta.status == BLOQUEADA || valor < 0) { return FRACASSO; }
        else {
            conta.saldo -= valor;
            return SUCESSO;
        }
    }

    static int calcularEscore(Conta conta) {
        if (conta.status == BLOQUEADA || conta.status == ENCERRADA) {
            return 0;
        }
        int days_since_epoch = (int) ((System.currentTimeMillis() / (1000*60*60*24)) % 7);
        int days_since_epoch_from_created_time = (int) ((conta.data_de_criacao / (1000*60*60*24)) % 7);
        double F = (conta.saldo * 3) + (days_since_epoch - days_since_epoch_from_created_time) * 2;

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
