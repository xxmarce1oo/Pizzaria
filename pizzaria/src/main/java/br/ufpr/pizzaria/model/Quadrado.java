package br.ufpr.pizzaria.model;

// Define a classe Quadrado que estende a classe Forma.
public class Quadrado extends Forma {
    // Atributo privado que armazena o lado do quadrado.
    private int lado;

    // Construtor da classe Quadrado que inicializa o atributo lado.
    public Quadrado(int lado) {
        // Verifica se o lado está dentro do intervalo permitido (entre 10 e 40 cm).
        if (lado < 10 || lado > 40) {
            // Se o lado não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O lado do quadrado deve ter entre 10 e 40 cm.");
        }
        // Atribui o valor do lado ao atributo da classe.
        this.lado = lado;
    }

    // Método sobrescrito da classe Forma que calcula a área do quadrado.
    @Override
    public double calcularArea() {
        // Retorna a área do quadrado usando a fórmula lado * lado.
        return lado * lado;
    }
}