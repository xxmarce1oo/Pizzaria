/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpr.pizzaria.model;


import java.util.ArrayList;

public class Pizza {
    private Forma forma;
    private ArrayList<Sabor> sabores;

    public Pizza(Forma forma, ArrayList<Sabor> sabores) {
        this.forma = forma;
        this.sabores = sabores;
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
