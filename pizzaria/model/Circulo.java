package br.ufpr.pizzaria.model;

public class Circulo extends Forma {
    private int raio;

    public Circulo(int raio) {
        if (raio < 7 || raio > 23) {
            throw new IllegalArgumentException("O raio do círculo deve ter entre 7 e 23 cm.");
        }
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}
