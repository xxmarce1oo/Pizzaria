/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpr.pizzaria.model;

public class Sabor {
    private String nome;
    private String tipo; // Simples, Especial, Premium
    private double precoPorCm2;

    public Sabor(String nome, String tipo, double precoPorCm2) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoPorCm2 = precoPorCm2;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecoPorCm2() {
        return precoPorCm2;
    }
}