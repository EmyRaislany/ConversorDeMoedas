package br.com.alura.conversorMonetario.Modelagem;

import java.util.Set;

public class Moedas{
        private static  final Set<String> MOEDAS_SUPORTADAS =Set.of(
                "ARS", // Peso argentino
                "EUR", // Euro
                "BRL", // Real brasileiro
                "GBP", // Libra
                "USD"  // Dólar americano

        );

        public static  boolean isMoedaValida(String moeda) {

            return MOEDAS_SUPORTADAS.contains(moeda);
        }

        public static  void mostrarMoedas() {

            System.out.println("Moedas suportadas: " + MOEDAS_SUPORTADAS);
        }


}
