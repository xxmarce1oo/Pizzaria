package br.ufpr.pizzaria.model;

// Define a classe Pizza.
public class Pizza {
    // Atributo privado que armazena a forma da pizza.
    private String forma;
    // Atributo privado que armazena a dimensão da pizza.
    private double dimensao;
    // Atributo privado que armazena o sabor da pizza.
    private String sabor;
    // Atributo privado que armazena o preço da pizza.
    private double preco;

    // Construtor da classe Pizza que inicializa os atributos.
    public Pizza(String forma, double dimensao, String sabor, double preco) {
        // Inicializa a forma da pizza.
        this.forma = forma;
        // Inicializa a dimensão da pizza.
        this.dimensao = dimensao;
        // Inicializa o sabor da pizza.
        this.sabor = sabor;
        // Inicializa o preço da pizza.
        this.preco = preco;
    }

    // Método público que retorna a forma da pizza.
    public String getForma() {
        return forma;
    }

    // Método público que retorna a dimensão da pizza.
    public double getDimensao() {
        return dimensao;
    }

    // Método público que retorna o sabor da pizza.
    public String getSabor() {
        return sabor;
    }

    // Método público que retorna o preço da pizza.
    public double getPreco() {
        return preco;
    }

    // Método público que calcula o preço da pizza.
    public double calcularPreco() {
        return preco;
    }
}