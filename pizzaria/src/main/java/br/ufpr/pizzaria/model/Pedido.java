package br.ufpr.pizzaria.model;

import br.ufpr.pizzaria.util.IdGenerator;
import java.util.ArrayList;

// Define a classe Pedido.
public class Pedido {
    // Atributo privado que armazena o ID do pedido.
    private int id;
    // Atributo privado que armazena o cliente associado ao pedido.
    private Cliente cliente;
    // Atributo privado que armazena a lista de pizzas do pedido.
    private ArrayList<Pizza> pizzas;
    // Atributo privado que armazena o estado do pedido.
    private String estado;

    // Construtor da classe Pedido que inicializa os atributos.
    public Pedido(Cliente cliente) {
        // Gera um ID único para o pedido usando o IdGenerator.
        this.id = IdGenerator.nextId();
        // Inicializa o cliente associado ao pedido.
        this.cliente = cliente;
        // Inicializa a lista de pizzas do pedido como uma nova ArrayList.
        this.pizzas = new ArrayList<>();
        // Define o estado inicial do pedido como "Aberto".
        this.estado = "Aberto";
    }

    // Construtor alternativo da classe Pedido (não implementado).
    public Pedido(int i, Cliente cliente, ArrayList<Pizza> listaPizzas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método público que retorna o ID do pedido.
    public int getId() {
        return id;
    }

    // Método público que retorna o cliente associado ao pedido.
    public Cliente getCliente() {
        return cliente;
    }

    // Método público que retorna a lista de pizzas do pedido.
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    // Método público que adiciona uma pizza à lista de pizzas do pedido.
    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    // Método público que retorna o estado do pedido.
    public String getEstado() {
        return estado;
    }

    // Método público que define o estado do pedido.
    public void setStatus(String novoStatus) {
        // Verifica se o novo estado é válido.
        if (!"Aberto".equals(novoStatus) && !"A Caminho".equals(novoStatus) && !"Entregue".equals(novoStatus)) {
            // Se o estado for inválido, lança uma exceção.
            throw new IllegalArgumentException("Status inválido! Os status permitidos são: Aberto, A Caminho, ou Entregue.");
        }
        // Atribui o novo estado ao atributo da classe.
        this.estado = novoStatus;
    }

    // Método público que calcula o preço total do pedido.
    public double calcularPrecoTotal() {
        // Calcula a soma dos preços de todas as pizzas no pedido.
        return pizzas.stream().mapToDouble(Pizza::calcularPreco).sum();
    }

    // Método público que lança uma exceção ao tentar obter o valor total do pedido (não implementado).
    public Object getValorTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método público que lança uma exceção ao tentar obter o status do pedido (não implementado).
    public Object getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}