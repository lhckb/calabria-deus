package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;

import static br.com.cesarschool.poo.utils.Ordenador.ordenar;

public class CorrentistaMediator {

    private RepositorioCorrentista repositorioCorrentista = RepositorioCorrentista.getInstancia();

    public boolean incluir(Correntista correntista) {
        return repositorioCorrentista.incluir(correntista);
    }
    public boolean alterar(Correntista correntista) {
        return repositorioCorrentista.alterar(correntista);
    }
    public Correntista buscar(String cpfCnpj) {
        return repositorioCorrentista.buscar(cpfCnpj);
    }
    public boolean excluir(String cpfCnpj) {
        return repositorioCorrentista.excluir(cpfCnpj);
    }

    public Correntista[] consultarTodosOrdenadoPorNome() {
        Correntista[] todos = repositorioCorrentista.buscarTodos();
        if (todos != null && todos.length > 0) {
            ordenar(todos);
        }
        return todos;
    }
}
