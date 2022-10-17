package br.com.cesarschool.poo.geral;

import java.util.Scanner;

// incluir conta, alterar, encerrar, bloquear, desbloquear, excluir, buscar, creditar, debitar

public class TelaConta {

    private static final int CODIGO_DESCONHECIDO = -1;
    private static final Scanner ENTRADA = new Scanner(System.in);
    private RepositorioConta repositorioConta = new RepositorioConta();

    public void executaTela() {
        while(true) {
            long codigo = CODIGO_DESCONHECIDO;
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            if (opcao == 1) {
                processaInclusao();
            } else if (opcao == 2) {
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaAlteracao(codigo);
                }
            } else if (opcao == 3) {
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaExclusao(codigo);
                }
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de Contas");
                System.exit(0);
            } else {
                System.out.println("Opção inv�lida!!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void processaInclusao() {
        Conta conta = capturaConta(CODIGO_DESCONHECIDO);
        String retornoValidacao = validar(conta);
        if (retornoValidacao == null) {
            boolean retornoRepositorio = repositorioConta.incluir(conta);
            if (retornoRepositorio) {
                System.out.println("Conta incluído com sucesso!");
            } else {
                System.out.println("Erro na inclusão de conta!");
            }
        } else {
            System.out.println(retornoValidacao);
        }
    }

    private void processaAlteracao(long codigo) {
        Conta conta = capturaConta(codigo);
        String retornoValidacao = validar(conta);
        if (retornoValidacao == null) {
            boolean retornoRepositorio = repositorioConta.alterar(conta);
            if (retornoRepositorio) {
                System.out.println("Conta alterado com sucesso!");
            } else {
                System.out.println("Erro na alteração de conta!");
            }
        } else {
            System.out.println(retornoValidacao);
        }
    }

    private long processaBusca() {
        System.out.print("Digite o c�digo: ");
        long codigo = ENTRADA.nextLong();
        Conta conta = repositorioConta.buscar(codigo);
        if (conta == null) {
            System.out.println("Conta não encontrado!");
            return CODIGO_DESCONHECIDO;
        } else {
            System.out.println("Código: " + conta.getCodigo());
            System.out.println("Nome: " + conta.getNome());
            return codigo;
        }
    }

    private void processaExclusao(long codigo) {
        boolean retornoRepositorio = repositorioConta.excluir(codigo);
        if (retornoRepositorio) {
            System.out.println("Conta excluído com sucesso!");
        } else {
            System.out.println("Erro na exclusão de conta!");
        }
    }

    private Conta capturaConta(long codigoDaAlteracao) {
        long codigo;
        if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
            System.out.print("Digite o c�digo: ");
            codigo = ENTRADA.nextLong();
        } else {
            codigo = codigoDaAlteracao;
        }
        System.out.print("Digite o nome: ");
        String nome = ENTRADA.next();
        System.out.print("Digite o tipo de conta (1- Corrente): ");
        // Pode ser um erro
        int codigoTipo = ENTRADA.nextInt();
        return new Conta(codigo, nome, codigoTipo);
    }

    private String validar(Conta conta) {
        int validacaoNome = conta.validarNome();
        if (!conta.codigoValido()) {
            return "Código inválido!";
        } else if (validacaoNome == Conta.NOME_NAO_INFORMADO) {
            return "Nome não informado!";
        } else if (validacaoNome == Conta.NOME_MUITO_CURTO) {
            return "Nome muito curto!";
        } else if (!conta.tipoPreechido()) {
            return "Tipo não preenchido!";
        } else {
            return null;
        }
    }
}
