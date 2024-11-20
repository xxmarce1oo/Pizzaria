package br.ufpr.pizzaria.model;

public class Quadrado extends Forma {
    private int lado;

    public Quadrado(int lado) {
        if (lado < 10 || lado > 40) {
            throw new IllegalArgumentException("O lado do quadrado deve ter entre 10 e 40 cm.");
        }
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}
