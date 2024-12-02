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
        // Cria listas para armazenar clientes e pedidos.
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        // Cria a tela de controle de pedidos.
        TelaControlePedidos telaControlePedidos = new TelaControlePedidos(pedidos);

        // Loop principal para exibir o menu de opções.
        while (true) {
            // Define as opções do menu.
            String[] opcoes = {"Cadastro de Clientes", "Controle de Pedidos", "Realizar Pedido"};
            
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
                    JDialog telaPedido = new TelaPedido(clientes, pedidos, telaControlePedidos);
                    telaPedido.setModal(true);
                    telaPedido.setVisible(true);
                    break;
                default:
                    // Exibe uma mensagem de erro se a opção for inválida.
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}