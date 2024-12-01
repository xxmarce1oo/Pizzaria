package br.ufpr.pizzaria.model;

import br.ufpr.pizzaria.util.IdGenerator;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;
    private ArrayList<Pizza> pizzas;
    private String estado;

    public Pedido(Cliente cliente) {
        this.id = IdGenerator.nextId();
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        this.estado = "Aberto"; // Estado padrão ao criar um pedido.
    }

    public Pedido(int i, Cliente cliente, ArrayList<Pizza> listaPizzas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public String getEstado() {
        return estado;
    }

    public void setStatus(String novoStatus) {
        if (!"Aberto".equals(novoStatus) && !"A Caminho".equals(novoStatus) && !"Entregue".equals(novoStatus)) {
            throw new IllegalArgumentException("Status inválido! Os status permitidos são: Aberto, A Caminho, ou Entregue.");
        }
        this.estado = novoStatus;
    }

    public double calcularPrecoTotal() {
        return pizzas.stream().mapToDouble(Pizza::calcularPreco).sum();
    }

    public Object getValorTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}