package br.ufpr.pizzaria.model;

public class Triangulo extends Forma {
    private int lado;

    public Triangulo(int lado) {
        if (lado < 20 || lado > 60) {
            throw new IllegalArgumentException("O lado do triângulo deve ter entre 20 e 60 cm.");
        }
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return (Math.sqrt(3) / 4) * lado * lado;
    }
}
