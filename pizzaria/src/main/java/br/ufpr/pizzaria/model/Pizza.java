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
        double area = forma.calcularArea();
        double precoMedio = sabores.stream().mapToDouble(Sabor::getPrecoPorCm2).average().orElse(0);
        return area * precoMedio;
    }
}
