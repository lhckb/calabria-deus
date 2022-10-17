package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.ContaPoupanca;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.mediators.ContaMediator;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

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
                System.out.println("Digite o código da conta: ");
                codigo = ENTRADA.nextLong();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaAlteracao(codigo);
                }
            } else if (opcao == 3) {
                System.out.println("Digite o código da conta: ");
                codigo = ENTRADA.nextLong();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaExclusao(codigo);
                }
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de Contas");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!!");
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
        System.out.println("Escolha o tipo de Conta que deseja adicionar: 1- Conta Corrente | 2- Conta Poupança");
        int escolha = ENTRADA.nextInt();

        if(escolha == 1) {
            Conta conta = capturaConta(CODIGO_DESCONHECIDO);
            String retornoValidacao = validar(conta);

            Correntista correntistaCorrente = conta.correntista;
            if(correntistaCorrente.validaCpf(correntistaCorrente.cpf) == false) {
                System.out.println("Cpf Inválido!");
            }
            else {
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
        }
        else {
            ContaPoupanca contaPoupanca = capturaContaPoupanca(CODIGO_DESCONHECIDO);
            Correntista correntistaPoupanca = contaPoupanca.correntista;
            String retornoValidacao = validar(contaPoupanca);

            if(correntistaPoupanca.validaCpf(correntistaPoupanca.cpf) == false) {
                System.out.println("Cpf Inválido!");
            }
            else {
                System.out.println("Deseja alterar o juros? Sim- Colocar valor desejado | Não- Colocar igual 0");
                double taxa_juros = ENTRADA.nextDouble();
                contaPoupanca.alterar_juros(taxa_juros);

                if (retornoValidacao == null) {
                    boolean retornoRepositorio = repositorioConta.incluir(contaPoupanca);
                    if (retornoRepositorio) {
                        System.out.println("Conta incluído com sucesso!");
                    } else {
                        System.out.println("Erro na inclusão de conta!");
                    }
                } else {
                    System.out.println(retornoValidacao);
                }
            }
        }
    }

    private void processaAlteracao(long codigo) {
        System.out.println("Escolha o tipo de Conta que deseja alterar: 1- Conta Corrente | 2- Conta Poupança");
        int escolha = ENTRADA.nextInt();

        if(escolha == 1) {
            Conta conta = capturaConta(codigo);
            String retornoValidacao = validar(conta);

            Correntista correntistaCorrente = conta.correntista;
            if(correntistaCorrente.validaCpf(correntistaCorrente.cpf) == false) {
                System.out.println("Correntista não Encontrado!");
            }
            else {
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
        }
        else {
            ContaPoupanca contaPoupanca = capturaContaPoupanca(codigo);
            String retornoValidacao = validar(contaPoupanca);

            Correntista correntistaPoupanca = contaPoupanca.correntista;

            if(correntistaPoupanca.validaCpf(correntistaPoupanca.cpf) == false) {
                System.out.println("Correntista não Encontrado!");
            }
            else {
                System.out.println("Deseja alterar o juros? Sim- Colocar valor desejado | Não- Colocar igual 0");
                double taxa_juros = ENTRADA.nextDouble();
                contaPoupanca.alterar_juros(taxa_juros);

                if (retornoValidacao == null) {
                    boolean retornoRepositorio = repositorioConta.alterar(contaPoupanca);
                    if (retornoRepositorio) {
                        System.out.println("Conta Poupança alterado com sucesso!");
                    } else {
                        System.out.println("Erro na alteração de conta poupança!");
                    }
                } else {
                    System.out.println(retornoValidacao);
                }
            }
        }
    }

    private long processaBusca() {
        System.out.println("Deseja buscar que tipo de Conta: 1- Corrente | 2- Poupança");
        int escolha = ENTRADA.nextInt();

        if (escolha == 1) {
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
        else {
            System.out.print("Digite o c�digo: ");
            long codigo = ENTRADA.nextLong();
            ContaPoupanca contaPoupanca = repositorioConta.buscarPoupanca(codigo);
            if (contaPoupanca == null) {
                System.out.println("Conta não encontrado!");
                return CODIGO_DESCONHECIDO;
            } else {
                System.out.println("Código: " + contaPoupanca.getCodigo());
                System.out.println("Nome: " + contaPoupanca.getNome());

                System.out.println("Nome Correntista: " + contaPoupanca.getNomeCorrentista());
                System.out.println("Cpf Correntista: " + contaPoupanca.getCpfCorrentista());
                System.out.println("Taxa de Juros: " + contaPoupanca.getTaxaJuros());

                return codigo;
            }
        }
    }

    private void processaExclusao(long codigo) {
        System.out.println("Deseja excluir que tipo de Conta: 1- Corrente | 2- Poupança");
        int escolha = ENTRADA.nextInt();
        if (escolha == 1) {
            boolean retornoRepositorio = repositorioConta.excluir(codigo, 1);
            if (retornoRepositorio) {
                System.out.println("Conta excluído com sucesso!");
            } else {
                System.out.println("Erro na exclusão de conta!");
            }
        } else if (escolha == 2) {
            boolean retornoRepositorio = repositorioConta.excluir(codigo, 2);
            if (retornoRepositorio) {
                System.out.println("Conta excluído com sucesso!");
            } else {
                System.out.println("Erro na exclusão de conta!");
            }
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
            int codigoTipo = 1;

            System.out.print("Digite o nome do correntista: ");
            String nome_correntista = ENTRADA.next();
            System.out.print("Digite o cpf do correntista: ");
            String cpf_correntista = ENTRADA.next();

            Correntista correntista = new Correntista(nome_correntista, cpf_correntista);

            return new Conta(codigo, nome, codigoTipo, correntista);
    }

    private ContaPoupanca capturaContaPoupanca(long codigoDaAlteracao) {
        long codigo;
        if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
            System.out.print("Digite o c�digo: ");
            codigo = ENTRADA.nextLong();
        } else {
            codigo = codigoDaAlteracao;
        }
        System.out.print("Digite o nome do Cliente: ");
        String nome = ENTRADA.next();
        int codigoTipo = 2;

        System.out.print("Digite o nome do correntista: ");
        String nome_correntista = ENTRADA.next();
        System.out.print("Digite o cpf do correntista: ");
        String cpf_correntista = ENTRADA.next();

        Correntista correntista = new Correntista(nome_correntista, cpf_correntista);

        return new ContaPoupanca(codigo, nome, codigoTipo, correntista);
    }

    private String validar(Conta conta) {
        int validacaoNome = ContaMediator.validarNome(conta);
        if (!ContaMediator.codigoValido(conta)) {
            return "Código inválido!";
        } else if (validacaoNome == Conta.NOME_NAO_INFORMADO) {
            return "Nome não informado!";
        } else if (validacaoNome == Conta.NOME_MUITO_CURTO) {
            return "Nome muito curto!";
        } else if (!ContaMediator.tipoPreechido(conta)) {
            return "Tipo não preenchido!";
        } else {
            return null;
        }
    }
}
