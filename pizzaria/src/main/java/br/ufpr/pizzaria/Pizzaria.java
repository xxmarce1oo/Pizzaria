package br.ufpr.pizzaria;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.model.Sabor;
import br.ufpr.pizzaria.view.TelaCadastroCliente;
import br.ufpr.pizzaria.view.TelaControlePedidos;
import br.ufpr.pizzaria.view.TelaPedido;
import br.ufpr.pizzaria.view.TelaAtualizarPrecoPizza;
import br.ufpr.pizzaria.view.TelaCadastroSabores;

import javax.swing.*;
import java.util.ArrayList;

public class Pizzaria {
    private static double precoSimples = 1.0;
    private static double precoEspecial = 1.5;
    private static double precoPremium = 2.0;

    // Armazena Clientes, Pedidos e Sabores
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Sabor> sabores = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializa as listas
        inicializarListas();

        // Cria a tela de controle de pedidos
        TelaControlePedidos telaControlePedidos = new TelaControlePedidos(pedidos);

        while (true) {
            // opções do menu.
            String[] opcoes = {"Cadastro de Clientes", "Controle de Pedidos", "Realizar Pedido", "Atualizar Preço das Pizzas", "Cadastrar Sabor"};

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
                    JDialog telaPedido = new TelaPedido(clientes, pedidos, sabores, telaControlePedidos, precoSimples, precoEspecial, precoPremium);
                    telaPedido.setModal(true);
                    telaPedido.setVisible(true);
                    break;
                case "Atualizar Preço das Pizzas":
                    // Abre a tela para atualizar o preço das pizzas.
                    TelaAtualizarPrecoPizza telaAtualizarPrecoPizza = new TelaAtualizarPrecoPizza(precoSimples, precoEspecial, precoPremium);
                    telaAtualizarPrecoPizza.setModal(true);
                    telaAtualizarPrecoPizza.setVisible(true);
                    // Atualiza os preços após fechar a tela.
                    precoSimples = telaAtualizarPrecoPizza.getPrecoSimples();
                    precoEspecial = telaAtualizarPrecoPizza.getPrecoEspecial();
                    precoPremium = telaAtualizarPrecoPizza.getPrecoPremium();
                    break;
                case "Cadastrar Sabor":
                    // Abre a tela para cadastrar um novo sabor.
                    TelaCadastroSabores telaCadastroSabor = new TelaCadastroSabores(sabores);
                    telaCadastroSabor.setModal(true);
                    telaCadastroSabor.setVisible(true);
                    break;
                default:
                    // Exibe uma mensagem de erro se a opção for inválida.
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    // Método para inicializar as listas
    private static void inicializarListas() {
        // Adiciona alguns sabores de exemplo
        sabores.add(new Sabor("Mussarela", "Simples"));
        sabores.add(new Sabor("Calabresa", "Especial"));
        sabores.add(new Sabor("Frango com Catupiry", "Premium"));
    }
}