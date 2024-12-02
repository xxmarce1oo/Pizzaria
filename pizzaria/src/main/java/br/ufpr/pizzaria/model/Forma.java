package br.ufpr.pizzaria.model;

// Define uma classe abstrata chamada Forma.
public abstract class Forma {
    private double[] medida;

    // Construtor parametrizado que inicia array de medidas
    public Forma(int numMedidas) {
        medida = new double[numMedidas];
    }

    // Obtém uma medida da forma pelo índice
    public double getMedida(int i) {
        if (i >= 0 && i < medida.length) {
            return medida[i];
        } else {
            return -1;
        }
    }

    // Define uma medida da forma pelo índice
    protected void setMedida(int i, double m) {
        if (i >= 0 && i < medida.length && m >= 0) {
            medida[i] = m;
        }
    }

    // Devolve uma string que representa a forma
    @Override
    public String toString() {
        String formaS = getClass().getSimpleName();
        formaS = formaS + "[medidas:";
        for (int i = 0; i < medida.length - 1; i++) {
            formaS = formaS + medida[i] + ",";
        }
        formaS = formaS + medida[medida.length - 1] + "]";
        return formaS;
    }

    // Retorna número de medidas da Forma
    public int getNumMedidas() {
        return medida.length;
    }

    // Método abstrato que determinará a área
    public abstract double calcularArea();
}