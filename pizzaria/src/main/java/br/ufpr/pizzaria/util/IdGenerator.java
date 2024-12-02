package br.ufpr.pizzaria.util;

// Define a classe IdGenerator.
public class IdGenerator {
    // Atributo estático privado que armazena o ID atual.
    private static int currentId = 0;

    // Método estático sincronizado que gera o próximo ID.
    public static synchronized int nextId() {
        // Incrementa o ID atual e retorna o novo valor.
        return ++currentId;
    }
}