import br.com.alura.conversorMonetario.Conexao.ApiConection;
import br.com.alura.conversorMonetario.Conexao.ExchangesRates;
import br.com.alura.conversorMonetario.Modelagem.Moedas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        Moedas.mostrarMoedas();
        String moedasDisponiveis = """
                No momento temos essas moedas Disponíveis, adicionaremos mais no brevemente:
                ARS - Peso argentino
                EUR - Euro
                BRL - Real brasileiro
                GBP - Libra
                USD - Dólar americano
                """;
        System.out.println(moedasDisponiveis);

        while (loop)
        {

            System.out.println("Escolha a sua moeda: (ARS, USD, EUR, GBP, BRL)");
            String moedaInicial = sc.nextLine().toUpperCase();

            if (!Moedas.isMoedaValida(moedaInicial)){
                System.out.println("Moeda inválida! Tente novamente");
                continue;
            }

            ApiConection apiConection = new ApiConection();
            ExchangesRates exchangesRates = apiConection.buscaMoedas(moedaInicial);

            if (exchangesRates != null)
            {

                System.out.println("Base: "
                        + exchangesRates.getBase_code());

                System.out.print("Para qual moeda você quer converter: (ARS, USD, EUR, GBP, BRL) ");
                String moedaConversao = sc.nextLine().toUpperCase();

                if (!Moedas.isMoedaValida(moedaConversao)) {
                    System.out.println("Moeda de conversão inválida!");
                    continue;
                }

                if (exchangesRates.getConversion_rates().containsKey(moedaConversao))
                {

                    double rate = exchangesRates.getConversion_rates().get(moedaConversao);
                    System.out.println("Taxa de conversão de "
                            + moedaInicial +
                            " para "
                            + moedaConversao +
                            ": " +
                            rate);

                    System.out.print("Digite o valor que deseja converter: ");
                    if (sc.hasNextDouble())
                    {

                        double valor = sc.nextDouble();
                        sc.nextLine();

                        double valorConvertido = valor * rate;
                        System.out.printf("%.2f %s em %s é: %.2f %n",
                                valor,
                                moedaInicial,
                                moedaConversao,
                                valorConvertido);

                    }else {
                        System.out.println("Entrada inválida! Certifique-se de digitar um número.");
                        sc.nextLine();
                    }


                } else {
                    System.out.println("Moeda de conversão inválida!");
                }

            }else {
                System.out.println("Erro ao obter taxas de câmbio. Verifique a moeda original.");
            }

            System.out.print("Deseja realizar outra conversão? (s/n): ");
            String condicaoSaida = sc.nextLine().toLowerCase();
            loop = condicaoSaida.equals("s");
        }

        System.out.println("Programa finalizado!");
        sc.close();
    }
}