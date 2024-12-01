package br.ufpr.pizzaria.service;

import br.ufpr.pizzaria.model.Cliente;
import java.util.ArrayList;

public class ClienteService {
    private final ArrayList<Cliente> clientes;

    public ClienteService(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void adicionarCliente(String nome, String sobrenome, String telefone) {
        Cliente cliente = new Cliente(listaClientes.size() + 1, nome, sobrenome, telefone);
        clientes.add(cliente);
    }

    public void atualizarCliente(int id, String nome, String sobrenome, String telefone) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setTelefone(telefone);
                return;
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado.");
    }

    public void excluirCliente(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }

    public ArrayList<Cliente> filtrarClientes(String filtro) {
        ArrayList<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getNome().contains(filtro) || cliente.getSobrenome().contains(filtro) || cliente.getTelefone().contains(filtro)) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }
}
