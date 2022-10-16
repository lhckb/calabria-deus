package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Correntista;

public class RepositorioCorrentista {
    private static final int TAMANHO_MAX_CORRENTISTA = 1000;
    private static RepositorioCorrentista instancia;

    private Correntista[] cadastroCorrentista = new Correntista[TAMANHO_MAX_CORRENTISTA];
    private int tamanhoAtual = 0;

    private RepositorioCorrentista() {

    }

    public static RepositorioCorrentista getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioCorrentista();
        }
        return instancia;
    }

    public boolean incluir(Correntista correntista) {
        if (buscarIndice(correntista.getCpf()) != -1) {
            return false;
        } else if (tamanhoAtual == TAMANHO_MAX_CORRENTISTA - 1) {
            return false;
        } else {
            for (int i = 0; i < cadastroCorrentista.length; i++) {
                if (cadastroCorrentista[i] == null) {
                    cadastroCorrentista[i] = correntista;
                    break;
                }
            }
            tamanhoAtual++;
            return true;
        }
    }
    public boolean alterar(Correntista correntista) {
        int indice = buscarIndice(correntista.getCpf());
        if (indice == -1) {
            return false;
        } else {
            cadastroCorrentista[indice] = correntista;
            return true;
        }
    }

    public Correntista buscar(String cpfCnpj) {
        int indice = buscarIndice(cpfCnpj);
        if (indice == -1) {
            return null;
        } else {
            return cadastroCorrentista[indice];
        }
    }

    public boolean excluir(String cpfCnpj) {
        int indice = buscarIndice(cpfCnpj);
        if (indice == -1) {
            return false;
        } else {
            cadastroCorrentista[indice] = null;
            tamanhoAtual--;
            return true;
        }
    }

    public Correntista[] buscarTodos() {
        Correntista[] resultado = new Correntista[tamanhoAtual];
        int indice = 0;
        for (Correntista fornecedor : cadastroCorrentista) {
            if (fornecedor != null) {
                resultado[indice++] = fornecedor;
            }
        }
        return resultado;
    }

    private int buscarIndice(String cpfCnpj) {
        for (int i = 0; i < cadastroCorrentista.length; i++) {
            Correntista correntista = cadastroCorrentista[i];
            if (correntista != null && correntista.getCpf().equals(cpfCnpj)) {
                return i;
            }
        }
        return -1;
    }
}
