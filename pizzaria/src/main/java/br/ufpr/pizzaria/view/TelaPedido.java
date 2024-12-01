package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.model.Pizza;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaPedido extends JFrame {
    private JComboBox<Cliente> comboClientes;
    private JComboBox<String> comboFormaPizza, comboSabores;
    private JTextField txtDimensao;
    private JTable tabelaPizzas;
    private DefaultTableModel modeloTabela;
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

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        modeloTabela = new DefaultTableModel(new String[]{"Forma", "Dimensão", "Sabores", "Preço"}, 0);
        tabelaPizzas = new JTable(modeloTabela);

        comboClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        comboFormaPizza = new JComboBox<>(new String[]{"Círculo", "Quadrado", "Triângulo"});
        comboSabores = new JComboBox<>(new String[]{"Simples", "Especial", "Premium"});
        txtDimensao = new JTextField();
        lblPrecoTotal = new JLabel("Preço Total: R$0,00");

        JButton btnAdicionarPizza = new JButton("Adicionar Pizza");
        btnAdicionarPizza.addActionListener(e -> adicionarPizza());

        JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
        btnFinalizarPedido.addActionListener(e -> finalizarPedido());

        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        panelForm.add(new JLabel("Cliente:"));
        panelForm.add(comboClientes);
        panelForm.add(new JLabel("Forma da Pizza:"));
        panelForm.add(comboFormaPizza);
        panelForm.add(new JLabel("Dimensão:"));
        panelForm.add(txtDimensao);
        panelForm.add(new JLabel("Sabor:"));
        panelForm.add(comboSabores);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnAdicionarPizza, BorderLayout.WEST);
        panelInferior.add(btnFinalizarPedido, BorderLayout.EAST);
        panelInferior.add(lblPrecoTotal, BorderLayout.SOUTH);

        panelPrincipal.add(panelForm, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(tabelaPizzas), BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void adicionarPizza() {
        try {
            String forma = (String) comboFormaPizza.getSelectedItem();
            double dimensao = Double.parseDouble(txtDimensao.getText());
            String sabor = (String) comboSabores.getSelectedItem();

            // Validação das dimensões
            if (forma.equals("Círculo") && (dimensao < 7 || dimensao > 23)) {
                throw new IllegalArgumentException("O raio do círculo deve ser entre 7 e 23 cm!");
            } else if (forma.equals("Quadrado") && (dimensao < 10 || dimensao > 40)) {
                throw new IllegalArgumentException("O lado do quadrado deve ser entre 10 e 40 cm!");
            } else if (forma.equals("Triângulo") && (dimensao < 20 || dimensao > 60)) {
                throw new IllegalArgumentException("O lado do triângulo deve ser entre 20 e 60 cm!");
            }

            // Cálculo do preço
            double preco = calcularPreco(forma, sabor, dimensao);

            // Adiciona a pizza na lista
            modeloTabela.addRow(new Object[]{forma, dimensao, sabor, String.format("R$%.2f", preco)});
            lblPrecoTotal.setText(String.format("Preço Total: R$%.2f", calcularPrecoTotal()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico válido para a dimensão.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private double calcularPreco(String forma, String sabor, double dimensao) {
        double precoPorCm2;
        switch (sabor) {
            case "Simples":
                precoPorCm2 = 1.0;
                break;
            case "Especial":
                precoPorCm2 = 1.5;
                break;
            case "Premium":
                precoPorCm2 = 2.0;
                break;
            default:
                throw new IllegalArgumentException("Sabor inválido.");
        }
        double area = calcularArea(forma, dimensao);
        return area * precoPorCm2;
    }

    private double calcularArea(String forma, double dimensao) {
        switch (forma) {
            case "Círculo":
                return Math.PI * Math.pow(dimensao, 2);
            case "Quadrado":
                return Math.pow(dimensao, 2);
            case "Triângulo":
                return (Math.sqrt(3) / 4) * Math.pow(dimensao, 2);
            default:
                throw new IllegalArgumentException("Forma inválida.");
        }
    }

    private double calcularPrecoTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            String precoStr = (String) modeloTabela.getValueAt(i, 3);
            total += Double.parseDouble(precoStr.replace("R$", "").replace(",", "."));
        }
        return total;
    }

    private void finalizarPedido() {
        if (modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos uma pizza ao pedido!");
            return;
        }

        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Pedido finalizado para o cliente " + cliente.getNome() + "!");
        dispose();
    }
}
