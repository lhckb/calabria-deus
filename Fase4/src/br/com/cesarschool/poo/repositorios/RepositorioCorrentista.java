package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Correntista;

/**
 * @author An�nimo
 *
 * Implementa��o prim�ria para efeito did�tico.
 * Ser� melhorada!!!
 */
public class RepositorioCorrentista extends RepositorioGenericoIdentificavel {

    private static RepositorioCorrentista instancia;


    private RepositorioCorrentista() {
    }

    public static RepositorioCorrentista getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioCorrentista();
        }
        return instancia;
    }

    public boolean incluir(Correntista correntista) {
        return super.incluir(correntista);
    }
    public boolean alterar(Correntista correntista) {
        return super.alterar(correntista);
    }

    public Correntista buscar(String cpfCnpj) {
        return (Correntista)super.buscar(cpfCnpj);
    }

    public boolean excluir(String cpfCnpj) {
        return super.excluir(cpfCnpj);
    }

    public Correntista[] buscarTodos() {
        return (Correntista[])super.buscarTodos();
    }

    @Override
    public int getTamanhoMaximoRepositorio() {
        return 1000;
    }

}
