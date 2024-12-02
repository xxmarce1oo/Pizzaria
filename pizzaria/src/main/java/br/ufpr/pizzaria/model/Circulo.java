package br.ufpr.pizzaria.model;

// A classe Circulo estende a classe Forma, indicando que Circulo é uma subclasse de Forma.
public class Circulo extends Forma {
    // Construtor da classe Circulo que recebe o raio como parâmetro.
    public Circulo(int raio) {
        // Chama o construtor da superclasse Forma com 1 medida.
        super(1);
        // Verifica se o raio está dentro do intervalo permitido (entre 7 e 23 cm).
        if (raio < 7 || raio > 23) {
            // Se o raio não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O raio do círculo deve ter entre 7 e 23 cm.");
        }
        // Define a medida do raio.
        setMedida(0, raio);
    }

    // Método sobrescrito da classe Forma que calcula a área do círculo.
    @Override
    public double calcularArea() {
        // Obtém o raio da medida.
        double raio = getMedida(0);
        // Retorna a área do círculo usando a fórmula π * raio^2.
        return Math.PI * raio * raio;
    }
}