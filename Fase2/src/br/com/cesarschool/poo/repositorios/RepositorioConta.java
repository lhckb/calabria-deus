package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;

public class RepositorioConta {
    private static final int TAMANHO_MAX_PRODUTOS = 1000;

    private Conta[] cadastroConta = new Conta[TAMANHO_MAX_PRODUTOS];
    private int tamanhoAtual = 0;

    public boolean incluir(Conta conta) {
        if (buscarIndice(conta.getCodigo()) != -1) {
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
    public boolean alterar(Conta conta) {
        int indice = buscarIndice(conta.getCodigo());
        if (indice == -1) {
            return false;
        } else {
            cadastroConta[indice] = conta;
            return true;
        }
    }

    public Conta buscar(long codigo) {
        int indice = buscarIndice(codigo);
        if (indice == -1) {
            return null;
        } else {
            return cadastroConta[indice];
        }
    }

    public boolean excluir(long codigo) {
        int indice = buscarIndice(codigo);
        if (indice == -1) {
            return false;
        } else {
            cadastroConta[indice] = null;
            tamanhoAtual--;
            return true;
        }
    }

    private int buscarIndice(long codigo) {
        for (int i = 0; i < cadastroConta.length; i++) {
            Conta conta = cadastroConta[i];
            if (conta != null && conta.getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }
}
