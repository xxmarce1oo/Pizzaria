package br.ufpr.pizzaria.model;

// Define a classe Triangulo que estende a classe Forma.
public class Triangulo extends Forma {
    // Atributo privado que armazena o lado do triângulo.
    private int lado;

    // Construtor da classe Triangulo que inicializa o atributo lado.
    public Triangulo(int lado) {
        // Verifica se o lado está dentro do intervalo permitido (entre 20 e 60 cm).
        if (lado < 20 || lado > 60) {
            // Se o lado não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O lado do triângulo deve ter entre 20 e 60 cm.");
        }
        // Atribui o valor do lado ao atributo da classe.
        this.lado = lado;
    }

    // Método sobrescrito da classe Forma que calcula a área do triângulo.
    @Override
    public double calcularArea() {
        // Retorna a área do triângulo equilátero usando a fórmula (sqrt(3) / 4) * lado^2.
        return (Math.sqrt(3) / 4) * lado * lado;
    }
}