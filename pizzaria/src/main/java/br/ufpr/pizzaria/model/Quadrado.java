package br.ufpr.pizzaria.model;

// Define a classe Quadrado que estende a classe Forma.
public class Quadrado extends Forma {
    // Construtor da classe Quadrado que recebe o lado como parâmetro.
    public Quadrado(int lado) {
        // Chama o construtor da superclasse Forma com 1 medida.
        super(1);
        // Verifica se o lado está dentro do intervalo permitido (entre 10 e 40 cm).
        if (lado < 10 || lado > 40) {
            // Se o lado não estiver no intervalo permitido, lança uma exceção.
            throw new IllegalArgumentException("O lado do quadrado deve ter entre 10 e 40 cm.");
        }
        // Define a medida do lado.
        setMedida(0, lado);
    }

    // Método sobrescrito da classe Forma que calcula a área do quadrado.
    @Override
    public double calcularArea() {
        // Obtém o lado da medida.
        double lado = getMedida(0);
        // Retorna a área do quadrado usando a fórmula lado * lado.
        return lado * lado;
    }
}