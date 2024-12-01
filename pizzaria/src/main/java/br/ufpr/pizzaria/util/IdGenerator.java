package br.ufpr.pizzaria.util;

public class IdGenerator {
    private static int currentId = 0;

    public static synchronized int nextId() {
        return ++currentId;
    }
}
