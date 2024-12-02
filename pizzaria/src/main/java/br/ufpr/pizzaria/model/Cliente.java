package br.ufpr.pizzaria.model;

import br.ufpr.pizzaria.util.IdGenerator;
import java.util.ArrayList;

// Define a classe Cliente.
public class Cliente {
    private int id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private ArrayList<Pedido> pedidos;

    // Construtor da classe Cliente que inicializa os atributos.
    public Cliente(int par, String nome, String sobrenome, String telefone) {
        this.id = IdGenerator.nextId();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.pedidos = new ArrayList<>();
    }

    // Método público que retorna o ID do cliente.
    public int getId() {
        return id;
    }

    // Método público que retorna o nome do cliente.
    public String getNome() {
        return nome;
    }

    // Método público que define o nome do cliente.
    public void setNome(String nome) {
        // Verifica se o nome é nulo ou vazio.
        if (nome == null || nome.trim().isEmpty()) {
            // Se o nome for inválido, lança uma exceção.
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        // Atribui o valor do nome ao atributo da classe.
        this.nome = nome;
    }

    // Método público que retorna o sobrenome do cliente.
    public String getSobrenome() {
        return sobrenome;
    }

    // Método público que define o sobrenome do cliente.
    public void setSobrenome(String sobrenome) {
        // Verifica se o sobrenome é nulo ou vazio.
        if (sobrenome == null || sobrenome.trim().isEmpty()) {
            // Se o sobrenome for inválido, lança uma exceção.
            throw new IllegalArgumentException("O sobrenome não pode estar vazio.");
        }
        // Atribui o valor do sobrenome ao atributo da classe.
        this.sobrenome = sobrenome;
    }

    // Método público que retorna o telefone do cliente.
    public String getTelefone() {
        return telefone;
    }

    // Método público que define o telefone do cliente.
    public void setTelefone(String telefone) {
        // Verifica se o telefone é nulo ou não contém apenas números.
        if (telefone == null || !telefone.matches("\\d+")) {
            // Se o telefone for inválido, lança uma exceção.
            throw new IllegalArgumentException("O telefone deve conter apenas números.");
        }
        // Atribui o valor do telefone ao atributo da classe.
        this.telefone = telefone;
    }

    // Método público que retorna a lista de pedidos do cliente.
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    // Método público que adiciona um pedido à lista de pedidos do cliente.
    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    // Método público que lança uma exceção ao tentar definir o ID do cliente.
    public void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método sobrescrito que retorna uma representação em string do cliente.
    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}