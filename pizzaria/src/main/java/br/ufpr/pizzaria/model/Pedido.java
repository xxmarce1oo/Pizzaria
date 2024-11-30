/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpr.pizzaria.model;

import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<Pizza> pizzas;
    private String estado; // Aberto, A caminho, Entregue

    public Pedido(int par, Cliente cliente, ArrayList<Pizza> listaPizzas) {
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        this.estado = "Aberto";
    }

    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double calcularPrecoTotal() {
        double precoTotal = 0;
        for (Pizza pizza : pizzas) {
            precoTotal += pizza.calcularPreco();
        }
        return precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Object getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getValorTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setStatus(String novoStatus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}