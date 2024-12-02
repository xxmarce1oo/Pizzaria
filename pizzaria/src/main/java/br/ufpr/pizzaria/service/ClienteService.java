package br.ufpr.pizzaria.service;

import br.ufpr.pizzaria.model.Cliente;
import java.util.ArrayList;

// Define a classe ClienteService.
public class ClienteService {
    // Atributo privado que armazena a lista de clientes.
    private final ArrayList<Cliente> clientes;

    // Construtor da classe ClienteService que inicializa a lista de clientes.
    public ClienteService(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Método público que adiciona um novo cliente à lista.
    public void adicionarCliente(String nome, String sobrenome, String telefone) {
        // Cria um novo cliente com um ID baseado no tamanho da lista de clientes.
        Cliente cliente = new Cliente(clientes.size() + 1, nome, sobrenome, telefone);
        // Adiciona o cliente à lista.
        clientes.add(cliente);
    }

    // Método público que atualiza as informações de um cliente existente.
    public void atualizarCliente(int id, String nome, String sobrenome, String telefone) {
        // Percorre a lista de clientes.
        for (Cliente cliente : clientes) {
            // Verifica se o ID do cliente corresponde ao ID fornecido.
            if (cliente.getId() == id) {
                // Atualiza os atributos do cliente.
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setTelefone(telefone);
                return;
            }
        }
        // Lança uma exceção se o cliente não for encontrado.
        throw new IllegalArgumentException("Cliente não encontrado.");
    }

    // Método público que exclui um cliente da lista com base no ID.
    public void excluirCliente(int id) {
        // Remove o cliente da lista se o ID corresponder.
        clientes.removeIf(cliente -> cliente.getId() == id);
    }

    // Método público que filtra clientes com base em um filtro de texto.
    public ArrayList<Cliente> filtrarClientes(String filtro) {
        // Cria uma lista para armazenar os resultados do filtro.
        ArrayList<Cliente> resultado = new ArrayList<>();
        // Percorre a lista de clientes.
        for (Cliente cliente : clientes) {
            // Verifica se o nome, sobrenome ou telefone do cliente contém o filtro.
            if (cliente.getNome().contains(filtro) || cliente.getSobrenome().contains(filtro) || cliente.getTelefone().contains(filtro)) {
                // Adiciona o cliente à lista de resultados.
                resultado.add(cliente);
            }
        }
        // Retorna a lista de clientes que correspondem ao filtro.
        return resultado;
    }
}