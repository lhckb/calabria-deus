package br.com.cesarschool.poo.geral;

import java.time.LocalDate;
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
            if (opcao == 1) { // INCLUIR
                processaInclusao();
            }else if (opcao == 2) { // ALTERAR
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaAlteracao(codigo);
                }
            } else if (opcao == 3) { // BLOQUEAR
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    Conta conta = repositorioConta.buscar(codigo);
                    bloquear(conta);
                }
            } else if (opcao == 4) { // ENCERRAR
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    Conta conta = repositorioConta.buscar(codigo);
                    encerrar(conta);
                }
            }
            else if (opcao == 5) { // Desbloquear
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    Conta conta = repositorioConta.buscar(codigo);
                    desbloquear(conta);
                }
            } else if (opcao == 6) { // CREDITAR
                codigo = processaBusca();
                if(codigo != CODIGO_DESCONHECIDO) {
                    System.out.print("Digite o valor a ser creditado: ");
                    double valor = ENTRADA.nextDouble();
                    Conta conta = repositorioConta.buscar(codigo);
                    int resultado = conta.creditar(valor);

                    if(resultado != 0) System.out.println("Valor não aceito!");
                    else System.out.println("Valor creditado!");
                }
            } else if (opcao == 7) { // DEBITAR
                codigo = processaBusca();
                if(codigo != CODIGO_DESCONHECIDO) {
                    System.out.print("Digite o valor a ser debitado: ");
                    double valor = ENTRADA.nextDouble();
                    Conta conta = repositorioConta.buscar(codigo);
                    int resultado = conta.debitar(valor);

                    if(resultado != 0) System.out.println("Valor não aceito!");
                    else System.out.println("Valor debitado!");
                }
            } else if (opcao == 8) { // EXCLUIR
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaExclusao(codigo);
                }
            } else if (opcao == 9) { // BUSCAR
                processaBusca();
            } else if (opcao == 10) { // SAIR
                System.out.println("Saindo do cadastro de Contas");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir"); // Já tem
        System.out.println("2- Alterar"); // Já tem
        System.out.println("3- Bloquear"); // Já tem
        System.out.println("4- Encerrar"); // Já tem
        System.out.println("5- Desbloquear"); // Já tem
        System.out.println("6- Creditar"); // Já tem
        System.out.println("7- Debitar"); // Já tem
        System.out.println("8- Excluir"); // Ja tem
        System.out.println("9- Buscar"); // Ja tem
        System.out.println("10- Sair"); // Ja tem
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
        Conta conta = repositorioConta.buscar(codigo);
        String retornoValidacao = validar(conta);
        if (retornoValidacao == null) {
            boolean retornoRepositorio = repositorioConta.alterar(conta.getCodigo(), novaData(conta));
            if (retornoRepositorio) {
                System.out.println("Conta alterado com sucesso!");
            } else {
                System.out.println("Erro na alteração de conta!");
            }
        } else {
            System.out.println(retornoValidacao);
        }
    }

    private void bloquear(Conta conta) {
        if(conta.status == conta.ENCERRADA) {
            System.out.println("Conta já Encerrada!");
        }
        else if(conta.status == conta.BLOQUEADA) System.out.println("Conta já Bloqueada!");
        else conta.status = conta.BLOQUEADA;
    }
    private void encerrar(Conta conta){
        if(conta.status == conta.ENCERRADA) {
            System.out.println("Conta já Encerrada!");
        }
        else conta.status = conta.ENCERRADA;
    }

    private void desbloquear(Conta conta){
        if(conta.status == conta.ENCERRADA) {
            System.out.println("Conta já Encerrada!");
        }
        else if(conta.status == conta.ATIVA) System.out.println("Conta já Ativa!");
        else conta.status = conta.ATIVA;
    }

    private long processaBusca() {
        System.out.print("Digite o código: ");
        long codigo = ENTRADA.nextLong();
        Conta conta = repositorioConta.buscar(codigo);
        if (conta == null) {
            System.out.println("Conta não encontrado!");
            return CODIGO_DESCONHECIDO;
        } else {
            System.out.println("Código: " + conta.getCodigo());
            System.out.println("Nome: " + conta.getNome());
            System.out.println("Status: " + conta.status);
            System.out.println("Data de Abertura: " + conta.getDataAbertura());
            System.out.println("Saldo: " + conta.saldo);
            int conta_score = conta.calcularEscore();
            switch (conta_score) {
                case 1:
                    System.out.println("Escore Conta: Bronze");
                    break;
                case 2:
                    System.out.println("Escore Conta: Prata");
                    break;
                case 3:
                    System.out.println("Escore Conta: Ouro");
                    break;
                case 4:
                    System.out.println("Escore Conta: Diamante");
                    break;
            }
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
            System.out.print("Digite o código: ");
            codigo = ENTRADA.nextLong();
        } else {
            codigo = codigoDaAlteracao;
        }
        System.out.print("Digite o nome: ");
        String nome = ENTRADA.next();
        System.out.print("Digite o tipo de conta (1- Corrente): ");
        // Pode ser um erro
        int codigoTipo = ENTRADA.nextInt();
        LocalDate dataAbertura = LocalDate.now();
        return new Conta(codigo, nome, codigoTipo);
    }

    public static LocalDate novaData(Conta conta) {
        LocalDate novaData = null;
        LocalDate dataLimite = conta.getDataAbertura().minusDays(1).minusMonths(1);
        int dia, mes, ano;
        boolean validarData = true;

        while (validarData) {
            System.out.print("Dia: ");
            dia = ENTRADA.nextInt();
            System.out.print("Mes: ");
            mes = ENTRADA.nextInt();
            System.out.print("Ano: ");
            ano = ENTRADA.nextInt();
            novaData = LocalDate.of(ano, mes, dia);

            if (novaData.equals(conta.getDataAbertura())) {
                validarData = false;

            } else if (novaData.isAfter(dataLimite) && novaData.isBefore(conta.getDataAbertura())) {
                validarData = false;

            } else {
                System.out.println("Data não é válida!");
            }
        }
        return novaData;
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
