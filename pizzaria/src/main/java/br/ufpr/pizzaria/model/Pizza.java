package br.ufpr.pizzaria.model;

import java.util.ArrayList;

public class Pizza {
    private Forma forma;
    private ArrayList<Sabor> sabores;

    public Pizza(Forma forma, ArrayList<Sabor> sabores) {
        this.forma = forma;
        this.sabores = sabores;
    }

    public Pizza(String forma, double dimensao, String sabor, double quantidade, double precoPizza) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double calcularPreco() {
        if (sabores == null || sabores.isEmpty()) {
            throw new IllegalStateException("A lista de sabores está vazia!");
        }

        double area = forma.calcularArea();
        double precoMedio = 0;

        for (Sabor sabor : sabores) {
            precoMedio += sabor.getPrecoPorCm2();
        }
        precoMedio /= sabores.size();

        return area * precoMedio;
    }
}
