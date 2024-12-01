package br.ufpr.pizzaria;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.view.TelaCadastroCliente;
import br.ufpr.pizzaria.view.TelaControlePedidos;
import br.ufpr.pizzaria.view.TelaPedido;

import javax.swing.*;
import java.util.ArrayList;

public class Pizzaria {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        TelaControlePedidos telaControlePedidos = new TelaControlePedidos(pedidos);

        while (true) {
            String[] opcoes = {"Cadastro de Clientes", "Controle de Pedidos", "Realizar Pedido"};
            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Pizzaria",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == null) {
                break;
            }

            switch (escolha) {
                case "Cadastro de Clientes":
                    JDialog telaCadastroCliente = new TelaCadastroCliente(clientes);
                    telaCadastroCliente.setModal(true);
                    telaCadastroCliente.setVisible(true);
                    break;
                case "Controle de Pedidos":
                    telaControlePedidos.setModal(true);
                    telaControlePedidos.setVisible(true);
                    break;
                case "Realizar Pedido":
                    JDialog telaPedido = new TelaPedido(clientes, pedidos, telaControlePedidos);
                    telaPedido.setModal(true);
                    telaPedido.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}