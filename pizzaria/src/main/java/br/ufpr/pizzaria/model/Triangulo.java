package br.ufpr.pizzaria.model;

/**
 * A classe Triangulo estende a classe Forma, indicando que Triangulo é uma subclasse de Forma.
 */
public class Triangulo extends Forma {
    // Construtor da classe Triangulo que recebe o lado como parâmetro.
    public Triangulo(int lado) {
        // Chama o construtor da superclasse Forma com 1 medida.
        super(1);
        // Verifica se o lado está dentro do intervalo permitido (entre 20 e 60 cm).
        if (lado < 20 || lado > 60) {
            // Se o lado não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O lado do triângulo deve ter entre 20 e 60 cm.");
        }
        // Define a medida do lado.
        setMedida(0, lado);
    }

    // Método sobrescrito da classe Forma que calcula a área do triângulo.
    @Override
    public double calcularArea() {
        // Obtém o lado da medida.
        double lado = getMedida(0);
        // Retorna a área do triângulo equilátero usando a fórmula (sqrt(3) / 4) * lado^2.
        return (Math.sqrt(3) / 4) * lado * lado;
    }
}