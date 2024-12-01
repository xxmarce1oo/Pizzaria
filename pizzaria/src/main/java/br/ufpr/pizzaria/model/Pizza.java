package br.ufpr.pizzaria.model;

public class Pizza {
    private String forma;
    private double dimensao;
    private String sabor;
    private double preco;

    public Pizza(String forma, double dimensao, String sabor, double preco) {
        this.forma = forma;
        this.dimensao = dimensao;
        this.sabor = sabor;
        this.preco = preco;
    }

    public String getForma() {
        return forma;
    }

    public double getDimensao() {
        return dimensao;
    }

    public String getSabor() {
        return sabor;
    }

    public double getPreco() {
        return preco;
    }

    public double calcularPreco() {
        return preco;
    }
}