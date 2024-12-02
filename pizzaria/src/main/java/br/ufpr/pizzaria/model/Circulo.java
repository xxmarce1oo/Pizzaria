package br.ufpr.pizzaria.model;

// A classe Circulo estende a classe Forma, indicando que Circulo é uma subclasse de Forma.
public class Circulo extends Forma {
    // Atributo privado que armazena o raio do círculo.
    private int raio;

    // Construtor da classe Circulo que recebe o raio como parâmetro.
    public Circulo(int raio) {
        // Verifica se o raio está dentro do intervalo permitido (entre 7 e 23 cm).
        if (raio < 7 || raio > 23) {
            // Se o raio não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O raio do círculo deve ter entre 7 e 23 cm.");
        }
        // Atribui o valor do raio ao atributo da classe.
        this.raio = raio;
    }

    // Método sobrescrito da classe Forma que calcula a área do círculo.
    @Override
    public double calcularArea() {
        // Retorna a área do círculo usando a fórmula π * raio^2.
        return Math.PI * raio * raio;
    }
}