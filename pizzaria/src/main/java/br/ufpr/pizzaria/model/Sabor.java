package br.ufpr.pizzaria.model;

// Define a classe Sabor.
public class Sabor {
    // Atributo privado que armazena o nome do sabor.
    private String nome;
    // Atributo privado que armazena o tipo do sabor (Simples, Especial, Premium).
    private String tipo;

    // Construtor da classe Sabor que inicializa os atributos.
    public Sabor(String nome, String tipo) {
        // Inicializa o nome do sabor.
        this.nome = nome;
        // Inicializa o tipo do sabor.
        this.tipo = tipo;
    }

    // Método público que retorna o nome do sabor.
    public String getNome() {
        return nome;
    }

    // Método público que retorna o tipo do sabor.
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}