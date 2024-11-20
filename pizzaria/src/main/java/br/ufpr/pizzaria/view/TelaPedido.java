package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.model.Pizza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPedido extends JFrame {
    private JComboBox<Cliente> comboClientes;
    private JComboBox<String> comboFormaPizza, comboSabores;
    private JTextField txtDimensao, txtQuantidade;
    private JTable tabelaPizzas;
    private DefaultTableModel modeloTabela;
    private JButton btnAdicionarPizza, btnFinalizarPedido;
    private JLabel lblPrecoTotal;

    private ArrayList<Pedido> listaPedidos;
    private ArrayList<Pizza> listaPizzas = new ArrayList<>();
    private ArrayList<Cliente> listaClientes;

    public TelaPedido(ArrayList<Cliente> clientes, ArrayList<Pedido> pedidos) {
        this.listaClientes = clientes;
        this.listaPedidos = pedidos;

        setTitle("Realização de Pedidos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Painel superior: Seleção do cliente
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboClientes = new JComboBox<>();
        for (Cliente cliente : listaClientes) {
            comboClientes.addItem(cliente);
        }
        painelSuperior.add(new JLabel("Cliente:"));
        painelSuperior.add(comboClientes);

        add(painelSuperior, BorderLayout.NORTH);

        // Painel central: Configuração do pedido
        JPanel painelCentral = new JPanel(new GridLayout(5, 2));
        painelCentral.add(new JLabel("Forma da Pizza:"));
        comboFormaPizza = new JComboBox<>(new String[]{"Quadrado", "Círculo", "Triângulo"});
        painelCentral.add(comboFormaPizza);

        painelCentral.add(new JLabel("Dimensão (cm):"));
        txtDimensao = new JTextField();
        painelCentral.add(txtDimensao);

        painelCentral.add(new JLabel("Sabores (até 2):"));
        comboSabores = new JComboBox<>(new String[]{"Simples", "Especial", "Premium"});
        painelCentral.add(comboSabores);

        painelCentral.add(new JLabel("Quantidade (cm²):"));
        txtQuantidade = new JTextField();
        painelCentral.add(txtQuantidade);

        btnAdicionarPizza = new JButton("Adicionar Pizza");
        painelCentral.add(btnAdicionarPizza);

        lblPrecoTotal = new JLabel("Preço Total: R$ 0,00");
        painelCentral.add(lblPrecoTotal);

        add(painelCentral, BorderLayout.CENTER);

        // Painel inferior: Tabela de pizzas e ações
        JPanel painelInferior = new JPanel(new BorderLayout());
        String[] colunas = {"Forma", "Dimensão", "Sabores", "Quantidade", "Preço"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPizzas = new JTable(modeloTabela);

        painelInferior.add(new JScrollPane(tabelaPizzas), BorderLayout.CENTER);

        btnFinalizarPedido = new JButton("Finalizar Pedido");
        painelInferior.add(btnFinalizarPedido, BorderLayout.SOUTH);

        add(painelInferior, BorderLayout.SOUTH);

        // Listeners
        btnAdicionarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPizza();
            }
        });

        btnFinalizarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPedido();
            }
        });
    }

    private void adicionarPizza() {
        try {
            String forma = (String) comboFormaPizza.getSelectedItem();
            double dimensao = Double.parseDouble(txtDimensao.getText());
            String sabor = (String) comboSabores.getSelectedItem();
            double quantidade = Double.parseDouble(txtQuantidade.getText());

            // Validação das dimensões
            if (forma.equals("Quadrado") && (dimensao < 10 || dimensao > 40)) {
                JOptionPane.showMessageDialog(this, "O lado do quadrado deve ser entre 10 e 40 cm!");
                return;
            } else if (forma.equals("Círculo") && (dimensao < 7 || dimensao > 23)) {
                JOptionPane.showMessageDialog(this, "O raio do círculo deve ser entre 7 e 23 cm!");
                return;
            } else if (forma.equals("Triângulo") && (dimensao < 20 || dimensao > 60)) {
                JOptionPane.showMessageDialog(this, "O lado do triângulo deve ser entre 20 e 60 cm!");
                return;
            }

            if (quantidade < 100 || quantidade > 1600) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser entre 100 e 1600 cm²!");
                return;
            }

            // Calcula o preço
            double precoPizza = calcularPreco(forma, sabor, quantidade);

            // Adiciona à lista de pizzas
            Pizza pizza = new Pizza(forma, dimensao, sabor, quantidade, precoPizza);
            listaPizzas.add(pizza);

            // Atualiza a tabela
            modeloTabela.addRow(new Object[]{forma, dimensao, sabor, quantidade, precoPizza});

            atualizarPrecoTotal();
            limparCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!");
        }
    }

    private double calcularPreco(String forma, String sabor, double quantidade) {
        double precoBase;
        switch (sabor) {
            case "Simples":
                precoBase = 1.0;
                break;
            case "Especial":
                precoBase = 1.5;
                break;
            case "Premium":
                precoBase = 2.0;
                break;
            default:
                precoBase = 0;
        }
        return quantidade * precoBase;
    }

    private void atualizarPrecoTotal() {
        double total = listaPizzas.stream().mapToDouble(Pizza::getPreco).sum();
        lblPrecoTotal.setText(String.format("Preço Total: R$ %.2f", total));
    }

    private void limparCampos() {
        txtDimensao.setText("");
        txtQuantidade.setText("");
    }

    private void finalizarPedido() {
        if (listaPizzas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos uma pizza ao pedido!");
            return;
        }

        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        Pedido pedido = new Pedido(listaPedidos.size() + 1, cliente, listaPizzas);

        listaPedidos.add(pedido);

        JOptionPane.showMessageDialog(this, "Pedido finalizado com sucesso!");
        dispose();
    }

    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        TelaPedido tela = new TelaPedido(clientes, pedidos);
        tela.setVisible(true);
    }
}
