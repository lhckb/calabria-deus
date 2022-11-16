package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.ContaPoupanca;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.repositorios.RepositorioConta;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;
import br.com.cesarschool.poo.utils.conta.ComparadorContaDataDeAbertura;
import br.com.cesarschool.poo.utils.conta.ComparadorContaSaldo;

import static br.com.cesarschool.poo.utils.Ordenador.ordenar;

public class ContaMediator {
    public static final int NOME_NAO_INFORMADO = 1;
    public static final int NOME_MUITO_CURTO = 2;
    public static final int TAMANHO_MINIMO_NOME = 3;

    static final int ATIVA = 1;
    static final int ENCERRADA = 2;
    static final int BLOQUEADA = 3;

    static final int SUCESSO = 0;
    static final int FRACASSO = -1;

    private static final RepositorioConta repositorioConta = RepositorioConta.getInstancia();

    public boolean incluir(Conta conta) {
        return repositorioConta.incluir(conta);
    }
    public boolean incluir(ContaPoupanca conta) {
        return repositorioConta.incluir(conta);
    }

    public boolean alterar(Conta conta) {
        return repositorioConta.alterar(conta);
    }
    public Conta buscar(String codigo) {
        return repositorioConta.buscar(codigo);
    }
    public boolean excluir(String codigo) {
        return repositorioConta.excluir(codigo);
    }

    public static int creditar(Conta conta, double valor) {
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

    public static int debitar(Conta conta, double valor) {
        if (conta instanceof ContaPoupanca) {
            if (((ContaPoupanca) conta).status == BLOQUEADA || valor < 0) { return FRACASSO; }
            else {
                ((ContaPoupanca) conta).saldo = ((ContaPoupanca) conta).saldo - valor;
                return SUCESSO;
            }
        }
        else {
            if (conta.status == BLOQUEADA || valor < 0) { return FRACASSO; }
            else {
                conta.saldo -= valor;
                return SUCESSO;
            }
        }
    }

    public static boolean codigoValido(Conta conta) {
        return conta.numero > 0;
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

    public static Conta[] getOrdenadoPorSaldo() {
        Conta[] contas = repositorioConta.buscarTodos();
        ordenar(contas, new ComparadorContaSaldo());
        return contas;
    }

    public static Conta[] getOrdenadoPorDataDeAbertura(Conta[] contas) {
        ordenar(contas, new ComparadorContaDataDeAbertura());
        return contas;
    }
}
