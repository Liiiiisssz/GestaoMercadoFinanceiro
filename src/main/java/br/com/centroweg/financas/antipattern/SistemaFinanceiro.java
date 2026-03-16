package br.com.centroweg.financas.antipattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaFinanceiro {

    // "Entidade" misturada na mesma classe
    static class Investimento {

        String ativo;
        String tipoInvestimento;
        double valor;
        String tipoInvestidor;

        Investimento(String ativo, String tipoInvestimento, double valor, String tipoInvestidor) {
            this.ativo = ativo;
            this.tipoInvestimento = tipoInvestimento;
            this.valor = valor;
            this.tipoInvestidor = tipoInvestidor;
        }
    }

    static List<Investimento> bancoMemoria = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SISTEMA FINANCEIRO =====");
            System.out.println("1 - Investir");
            System.out.println("2 - Listar investimentos");
            System.out.println("3 - Calcular impostos");
            System.out.println("4 - Enviar notificação");
            System.out.println("5 - Gerar relatório");
            System.out.println("6 - Sair");

            int opcao = sc.nextInt();

            if (opcao == 1) {

                System.out.println("Digite o ativo (PETR4, BTC, etc):");
                String ativo = sc.next();

                System.out.println("Tipo investimento (ACAO, CRIPTO, FII, TESOURO, ETF, STARTUP):");
                String tipoInvestimento = sc.next();

                System.out.println("Digite o valor:");
                double valor = sc.nextDouble();

                System.out.println("Tipo investidor (PF, PJ, DAYTRADER, APOSENTADO, EMPRESA_TECH, GLOBAL):");
                String tipoInvestidor = sc.next();

                Investimento inv = new Investimento(ativo, tipoInvestimento, valor, tipoInvestidor);

                bancoMemoria.add(inv);

                salvarNoBanco(inv);

                enviarEmail("Novo investimento realizado em " + ativo);

            }

            else if (opcao == 2) {
                System.out.println("\n===== LISTA DE INVESTIMENTOS =====");
                for (Investimento inv : bancoMemoria) {
                    System.out.println(
                            inv.ativo + " | "
                                    + inv.tipoInvestimento + " | "
                                    + inv.valor + " | "
                                    + inv.tipoInvestidor
                    );
                }
            }

            else if (opcao == 3) {

                for (Investimento inv : bancoMemoria) {

                    double imposto = 0;

                    if (inv.tipoInvestidor.equals("PF")) {

                        if (inv.tipoInvestimento.equals("ACAO")) {
                            imposto = inv.valor * 0.15;
                        }

                        else if (inv.tipoInvestimento.equals("CRIPTO")) {
                            imposto = inv.valor * 0.20;
                        }

                        else if (inv.tipoInvestimento.equals("FII")) {
                            imposto = inv.valor * 0.18;
                        }

                        else {
                            imposto = inv.valor * 0.10;
                        }

                    }

                    else if (inv.tipoInvestidor.equals("PJ")) {

                        if (inv.tipoInvestimento.equals("ACAO")) {
                            imposto = inv.valor * 0.18;
                        }

                        else if (inv.tipoInvestimento.equals("CRIPTO")) {
                            imposto = inv.valor * 0.25;
                        }

                        else {
                            imposto = inv.valor * 0.15;
                        }

                    }

                    else if (inv.tipoInvestidor.equals("DAYTRADER")) {

                        imposto = inv.valor * 0.30;

                    }

                    else if (inv.tipoInvestidor.equals("APOSENTADO")) {

                        imposto = inv.valor * 0.08;

                    }

                    else if (inv.tipoInvestidor.equals("EMPRESA_TECH")) {

                        if (inv.tipoInvestimento.equals("STARTUP")) {
                            imposto = inv.valor * 0.12;
                        }

                        else {
                            imposto = inv.valor * 0.20;
                        }

                    }

                    else if (inv.tipoInvestidor.equals("GLOBAL")) {

                        imposto = inv.valor * 0.22;

                    }

                    System.out.println(
                            "Imposto para "
                                    + inv.ativo
                                    + " (" + inv.tipoInvestimento + ") = "
                                    + imposto
                    );

                }

            }

            else if (opcao == 4) {

                System.out.println("Digite a mensagem:");
                String msg = sc.next();

                enviarEmail(msg);
                enviarSMS(msg);
                enviarWebhook(msg);

            }

            else if (opcao == 5) {

                gerarRelatorioCompleto();

            }

            else if (opcao == 6) {

                break;

            }

        }

    }

    // conexão hardcoded
    public static void salvarNoBanco(Investimento inv) {

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fintech",
                    "root",
                    "123456"
            );

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                    "INSERT INTO investimentos VALUES('"
                            + inv.ativo + "','"
                            + inv.tipoInvestimento + "',"
                            + inv.valor + ",'"
                            + inv.tipoInvestidor + "')"
            );

            conn.close();

        }

        catch (Exception e) {

            System.out.println("Erro ao salvar no banco.");

        }

    }

    public static void enviarEmail(String msg) {

        System.out.println("EMAIL enviado: " + msg);

    }

    public static void enviarSMS(String msg) {

        System.out.println("SMS enviado: " + msg);

    }

    public static void enviarWebhook(String msg) {

        System.out.println("Webhook enviado: " + msg);

    }

    public static void gerarRelatorioCompleto() {

        double total = 0;

        int quantidade = bancoMemoria.size();

        for (Investimento inv : bancoMemoria) {

            total += inv.valor;

        }

        System.out.println("\n===== RELATÓRIO =====");

        System.out.println("Quantidade investimentos: " + quantidade);

        System.out.println("Total investido: " + total);

        if (total > 50000) {

            System.out.println("Perfil: INVESTIDOR ALTO PATRIMÔNIO");

        }

        else if (total  > 10000) {

            System.out.println("Perfil: INVESTIDOR MÉDIO");

        }

        else {

            System.out.println("Perfil: INVESTIDOR INICIANTE");

        }

    }

}

