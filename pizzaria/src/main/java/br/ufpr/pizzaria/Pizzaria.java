package br.ufpr.pizzaria;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.model.Sabor;
import br.ufpr.pizzaria.view.TelaCadastroCliente;
import br.ufpr.pizzaria.view.TelaCadastroSabores;
import br.ufpr.pizzaria.view.TelaControlePedidos;
import br.ufpr.pizzaria.view.TelaPedido;

import javax.swing.*;
import java.util.ArrayList;

public class Pizzaria {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Sabor> sabores = new ArrayList<>(); // Definindo e inicializando a variável sabores

        TelaControlePedidos telaControlePedidos = new TelaControlePedidos(pedidos);

        while (true) {
            // Define as opções do menu.
            String[] opcoes = {"Cadastro de Clientes", "Controle de Pedidos", "Realizar Pedido", "Cadastro de Sabores"};

            // Exibe um diálogo para o usuário escolher uma opção.
            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha uma opção:",
                    "Pizzaria",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            // Se o usuário cancelar o diálogo, sai do loop.
            if (escolha == null) {
                break;
            }

            // Executa a ação correspondente à escolha do usuário.
            switch (escolha) {
                case "Cadastro de Clientes":
                    // Abre a tela de cadastro de clientes.
                    JDialog telaCadastroCliente = new TelaCadastroCliente(clientes, telaControlePedidos);
                    telaCadastroCliente.setModal(true);
                    telaCadastroCliente.setVisible(true);
                    break;
                case "Controle de Pedidos":
                    // Abre a tela de controle de pedidos.
                    telaControlePedidos.setModal(true);
                    telaControlePedidos.setVisible(true);
                    break;
                case "Realizar Pedido":
                    // Abre a tela de realização de pedidos.
                    JDialog telaPedido = new TelaPedido(clientes, pedidos, sabores, telaControlePedidos);
                    telaPedido.setModal(true);
                    telaPedido.setVisible(true);
                    break;
                case "Cadastro de Sabores":
                    // Abre a tela de cadastro de sabores.
                    JDialog telaCadastroSabores = new TelaCadastroSabores(sabores);
                    telaCadastroSabores.setModal(true);
                    telaCadastroSabores.setVisible(true);
                    break;
                default:
                    // Exibe uma mensagem de erro se a opção for inválida.
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}