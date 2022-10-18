package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.ContaPoupanca;

import java.time.LocalDate;

public class RepositorioConta {
    private static final int TAMANHO_MAX_PRODUTOS = 1000;

    private Conta[] cadastroConta = new Conta[TAMANHO_MAX_PRODUTOS];
    private ContaPoupanca[] cadastroContaPoupanca = new ContaPoupanca[TAMANHO_MAX_PRODUTOS];

    private int tamanhoAtual = 0;
    private int tamanhoAtualPoupanca = 0;

    private static RepositorioConta instancia = null;

    public static RepositorioConta getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioConta();
        }
        return instancia;
    }

    public boolean incluir(Conta conta) {
        if (buscarIndice(conta.getCodigo(), 1) != -1) {
            return false;
        } else if (tamanhoAtual == TAMANHO_MAX_PRODUTOS - 1) {
            return false;
        } else {
            for (int i = 0; i < cadastroConta.length; i++) {
                if (cadastroConta[i] == null) {
                    cadastroConta[i] = conta;
                    break;
                }
            }
            tamanhoAtual++;
            return true;
        }
    }

    public boolean incluirPoupanca(ContaPoupanca conta) {
        if (buscarIndice(conta.getCodigo(), 2) != -1) {
            return false;
        } else if (tamanhoAtualPoupanca == TAMANHO_MAX_PRODUTOS - 1) {
            return false;
        } else {
            for (int i = 0; i < cadastroContaPoupanca.length; i++) {
                if (cadastroContaPoupanca[i] == null) {
                    cadastroContaPoupanca[i] = conta;
                    break;
                }
            }
            tamanhoAtualPoupanca++;
            return true;
        }
    }

    public boolean alterar(long numero, LocalDate novaDataAbertura, int tipo) {
        int indice = buscarIndice(numero, tipo);
        if (indice < 0) {
            return false;
        }
        cadastroConta[indice].setDataAbertura(novaDataAbertura);
        return true;
    }

    public boolean alterarPoupanca(long numero, LocalDate novaDataAbertura, int tipo) {
        int indice = buscarIndice(numero, tipo);
        if (indice < 0) {
            return false;
        }
        cadastroConta[indice].setDataAbertura(novaDataAbertura);
        return true;
    }

    public Conta buscar(long codigo) {
        int indice = buscarIndice(codigo, 1);
        if (indice == -1) {
            return null;
        } else {
            return cadastroConta[indice];
        }
    }

    public ContaPoupanca buscarPoupanca(long codigo) {
        int indice = buscarIndice(codigo, 2);
        if (indice == -1) {
            return null;
        } else {
            return cadastroContaPoupanca[indice];
        }
    }

    public boolean excluir(long codigo, int tipo) {
        int indice = buscarIndice(codigo, tipo);
        if (indice == -1) {
            return false;
        } else if (tipo == 1) {
            cadastroConta[indice] = null;
            tamanhoAtual--;
            return true;
        } else if (tipo == 2) {
            cadastroContaPoupanca[indice] = null;
            tamanhoAtualPoupanca--;
            return true;
        }
        return false;
    }

    private int buscarIndice(long codigo, int tipo) {
        if (tipo == 1) {
            for (int i = 0; i < cadastroConta.length; i++) {
                Conta conta = cadastroConta[i];
                if (conta != null && conta.getCodigo() == codigo) {
                    return i;
                }
            }
        } else if (tipo == 2) {
            for (int i = 0; i < cadastroContaPoupanca.length; i++) {
                ContaPoupanca conta = cadastroContaPoupanca[i];
                if (conta != null && conta.getCodigo() == codigo) {
                    return i;
                }
            }
        }

        return -1;
    }
}
