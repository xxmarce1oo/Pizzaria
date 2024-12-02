package br.ufpr.pizzaria.model;

// Define a classe Sabor.
public class Sabor {
    // Atributo privado que armazena o nome do sabor.
    private String nome;
    // Atributo privado que armazena o tipo do sabor (Simples, Especial, Premium).
    private String tipo;
    // Atributo privado que armazena o preço por cm² do sabor.
    private double precoPorCm2;

    // Construtor da classe Sabor que inicializa os atributos.
    public Sabor(String nome, String tipo, double precoPorCm2) {
        // Inicializa o nome do sabor.
        this.nome = nome;
        // Inicializa o tipo do sabor.
        this.tipo = tipo;
        // Inicializa o preço por cm² do sabor.
        this.precoPorCm2 = precoPorCm2;
    }

    // Método público que retorna o nome do sabor.
    public String getNome() {
        return nome;
    }

    // Método público que retorna o tipo do sabor.
    public String getTipo() {
        return tipo;
    }

    // Método público que retorna o preço por cm² do sabor.
    public double getPrecoPorCm2() {
        return precoPorCm2;
    }
}