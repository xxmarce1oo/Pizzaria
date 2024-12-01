package br.ufpr.pizzaria.model;
import br.ufpr.pizzaria.util.IdGenerator;
import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private ArrayList<Pedido> pedidos;

    public Cliente(int par, String nome, String sobrenome, String telefone) {
        this.id = IdGenerator.nextId();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.pedidos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        if (sobrenome == null || sobrenome.trim().isEmpty()) {
            throw new IllegalArgumentException("O sobrenome não pode estar vazio.");
        }
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || !telefone.matches("\\d+")) {
            throw new IllegalArgumentException("O telefone deve conter apenas números.");
        }
        this.telefone = telefone;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}