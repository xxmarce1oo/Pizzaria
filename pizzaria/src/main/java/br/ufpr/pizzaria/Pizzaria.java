package br.ufpr.pizzaria;

import br.ufpr.pizzaria.view.TelaCadastroCliente;
import br.ufpr.pizzaria.view.TelaControlePedidos;
import br.ufpr.pizzaria.view.TelaPedido;
import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;

import javax.swing.*;
import java.util.ArrayList;

public class Pizzaria {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        // Exibe as opções do menu
        String[] opcoes = {"Cadastro de Clientes", "Controle de Pedidos", "Realizar Pedido", "Sair"};
        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Escolha uma opção:",
                "Pizzaria",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        // Se o usuário escolher 'Sair' ou cancelar, o programa fecha
        if (escolha == null || escolha.equals("Sair")) {
            System.out.println("Encerrando o programa...");
            System.exit(0);  // Encerra o programa
        }

        // Exibe a tela correspondente com base na escolha do usuário
        switch (escolha) {
            case "Cadastro de Clientes":
                TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(clientes);
                telaCadastroCliente.setVisible(true);
                break;

            case "Controle de Pedidos":
                TelaControlePedidos telaControlePedidos = new TelaControlePedidos(pedidos);
                telaControlePedidos.setVisible(true);
                break;

            case "Realizar Pedido":
                TelaPedido telaPedido = new TelaPedido(clientes, pedidos);
                telaPedido.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
        }
    }
}
