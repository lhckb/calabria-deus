package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;

/**
 * @author An�nimo
 *
 * Implementa��o prim�ria para efeito did�tico.
 * Ser� melhorada!!!
 */
public class RepositorioConta extends RepositorioGenericoIdentificavel {

    private static RepositorioConta instancia;


    public RepositorioConta() {
    }

    public static RepositorioConta getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioConta();
        }
        return instancia;
    }

    public boolean incluir(Conta correntista) {
        return super.incluir(correntista);
    }
    public boolean alterar(Conta correntista) {
        return super.alterar(correntista);
    }

    public Conta buscar(String codigo) {
        return (Conta)super.buscar(codigo);
    }

    public boolean excluir(String codigo) {
        return super.excluir(codigo);
    }

    public Conta[] buscarTodos() {
        return (Conta[])super.buscarTodos();
    }

    @Override
    public int getTamanhoMaximoRepositorio() {
        return 1000;
    }

}
