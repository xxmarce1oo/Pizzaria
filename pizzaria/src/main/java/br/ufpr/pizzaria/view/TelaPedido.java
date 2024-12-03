package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.model.Pedido;
import br.ufpr.pizzaria.model.Pizza;
import br.ufpr.pizzaria.model.Sabor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelaPedido extends JDialog {
     // Componentes da interface gráfica.
     private JComboBox<Cliente> comboClientes; // ComboBox para selecionar clientes.
     private JComboBox<String> comboFormaPizza; // ComboBox para selecionar a forma da pizza.
     private JTextField txtDimensao; // Campo de texto para inserir a dimensão da pizza.
     private JTable tabelaPizzas; // Tabela para exibir as pizzas adicionadas ao pedido.
     private DefaultTableModel modeloTabela; // Modelo da tabela para gerenciar os dados da tabela.
     private JLabel lblPrecoTotal; // Label para exibir o preço total do pedido.
 
     // Listas de pedidos e clientes.
     private ArrayList<Pedido> listaPedidos; // Lista de pedidos.
     private TelaControlePedidos telaControlePedidos; // Referência à tela de controle de pedidos.
     private JComboBox<Sabor> comboSaboresPizza; // ComboBox para selecionar o primeiro sabor da pizza.
     private JComboBox<Sabor> comboSegundoSaborPizza; // ComboBox para selecionar o segundo sabor da pizza.
 
     // Preços por cm² para cada tipo de pizza.
     private double precoSimples; // Preço por cm² para pizza simples.
     private double precoEspecial; // Preço por cm² para pizza especial.
     private double precoPremium; // Preço por cm² para pizza premium.
 

    // Construtor da classe TelaPedido que inicializa os componentes.
    public TelaPedido(ArrayList<Cliente> clientes, ArrayList<Pedido> pedidos, ArrayList<Sabor> sabores, TelaControlePedidos telaControlePedidos, double precoSimples, double precoEspecial, double precoPremium) {
        this.listaPedidos = pedidos;
        this.telaControlePedidos = telaControlePedidos;
        this.precoSimples = precoSimples;
        this.precoEspecial = precoEspecial;
        this.precoPremium = precoPremium;

        setTitle("Realização de Pedidos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        modeloTabela = new DefaultTableModel(new String[]{"Forma", "Dimensão", "Sabores", "Preço"}, 0);
        tabelaPizzas = new JTable(modeloTabela);

        comboClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        comboFormaPizza = new JComboBox<>(new String[]{"Círculo", "Quadrado", "Triângulo"});
        txtDimensao = new JTextField();
        lblPrecoTotal = new JLabel("Preço Total: R$0,00");

        // Adicionando os novos campos para seleção de sabores da pizza
        comboSaboresPizza = new JComboBox<>(sabores.toArray(new Sabor[0]));
        comboSegundoSaborPizza = new JComboBox<>(sabores.toArray(new Sabor[0])); // Inicialização da variável

        JButton btnAdicionarPizza = new JButton("Adicionar Pizza");
        btnAdicionarPizza.addActionListener(e -> adicionarPizza());

        JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
        btnFinalizarPedido.addActionListener(e -> finalizarPedido());

        JPanel panelForm = new JPanel(new GridLayout(6, 2)); // Alterado para 6 linhas
        panelForm.add(new JLabel("Cliente:"));
        panelForm.add(comboClientes);
        panelForm.add(new JLabel("Forma da Pizza:"));
        panelForm.add(comboFormaPizza);
        panelForm.add(new JLabel("Dimensão:"));
        panelForm.add(txtDimensao);
        panelForm.add(new JLabel("Sabor da Pizza:")); // Novo campo
        panelForm.add(comboSaboresPizza); // Novo campo
        panelForm.add(new JLabel("Segundo Sabor da Pizza:")); // Novo campo
        panelForm.add(comboSegundoSaborPizza); // Novo campo

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnAdicionarPizza, BorderLayout.WEST);
        panelInferior.add(btnFinalizarPedido, BorderLayout.EAST);
        panelInferior.add(lblPrecoTotal, BorderLayout.SOUTH);

        // Adicionando o botão para remover uma pizza
        JButton btnRemoverPizza = new JButton("Remover Pizza");
        btnRemoverPizza.addActionListener(e -> removerPizza());

        panelInferior.add(btnAdicionarPizza, BorderLayout.WEST);
        panelInferior.add(btnRemoverPizza, BorderLayout.CENTER); // Adicionando o botão de remover pizza
        panelInferior.add(btnFinalizarPedido, BorderLayout.EAST);
        panelInferior.add(lblPrecoTotal, BorderLayout.SOUTH);

        panelPrincipal.add(panelForm, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(tabelaPizzas), BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        comboClientes.addActionListener(e -> carregarPedidoExistente());

        // Carregar pedido existente se houver
        carregarPedidoExistente();
    }

    public TelaPedido(ArrayList<Cliente> clientes, ArrayList<Pedido> pedidos,
            TelaControlePedidos telaControlePedidos2) {
    }

    private void carregarPedidoExistente() {
        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        if (cliente == null) {
            return;
        }

        Pedido pedidoExistente = buscarPedidoPorCliente(cliente);
        modeloTabela.setRowCount(0);
        if (pedidoExistente != null) {
            for (Pizza pizza : pedidoExistente.getPizzas()) {
                modeloTabela.addRow(new Object[]{pizza.getForma(), pizza.getDimensao(), pizza.getSabor(), String.format("R$%.2f", pizza.getPreco())});
            }
            lblPrecoTotal.setText(String.format("Preço Total: R$%.2f", calcularPrecoTotal()));
        } else {
            lblPrecoTotal.setText("Preço Total: R$0,00");
        }
    }

    private Pedido buscarPedidoPorCliente(Cliente cliente) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCliente().getId() == cliente.getId() && pedido.getEstado().equals("Aberto")) {
                return pedido;
            }
        }
        return null;
    }

    // Método para adicionar uma pizza ao pedido.
    private void adicionarPizza() {
        try {
            String forma = (String) comboFormaPizza.getSelectedItem();
            double dimensao = Double.parseDouble(txtDimensao.getText());
            List<Sabor> sabores = new ArrayList<>();
            sabores.add((Sabor) comboSaboresPizza.getSelectedItem());
            sabores.add((Sabor) comboSegundoSaborPizza.getSelectedItem());

            // Validações de dimensão da pizza.
            if (forma.equals("Círculo") && (dimensao < 7 || dimensao > 23)) {
                throw new IllegalArgumentException("O raio do círculo deve ser entre 7 e 23 cm!");
            } else if (forma.equals("Quadrado") && (dimensao < 10 || dimensao > 40)) {
                throw new IllegalArgumentException("O lado do quadrado deve ser entre 10 e 40 cm!");
            } else if (forma.equals("Triângulo") && (dimensao < 20 || dimensao > 60)) {
                throw new IllegalArgumentException("O lado do triângulo deve ser entre 20 e 60 cm!");
            }

            double preco = calcularPreco(forma, sabores, dimensao);

            modeloTabela.addRow(new Object[]{forma, dimensao, sabores.stream().map(Sabor::getNome).collect(Collectors.joining(", ")), String.format("R$%.2f", preco)});
            lblPrecoTotal.setText(String.format("Preço Total: R$%.2f", calcularPrecoTotal()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico válido para a dimensão.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Método para calcular o preço da pizza.
    private double calcularPreco(String forma, List<Sabor> sabores, double dimensao) {
        double totalPrecoPorCm2 = 0;
        for (Sabor sabor : sabores) {
            switch (sabor.getTipo()) {
                case "Simples":
                    totalPrecoPorCm2 += precoSimples;
                    break;
                case "Especial":
                    totalPrecoPorCm2 += precoEspecial;
                    break;
                case "Premium":
                    totalPrecoPorCm2 += precoPremium;
                    break;
                default:
                    throw new IllegalArgumentException("Sabor inválido.");
            }
        }
        double mediaPrecoPorCm2 = totalPrecoPorCm2 / sabores.size();
        double area = calcularArea(forma, dimensao);
        return area * mediaPrecoPorCm2;
    }

    // Método para calcular a área da pizza.
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

    // Método para calcular o preço total do pedido.
    private double calcularPrecoTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            String precoStr = (String) modeloTabela.getValueAt(i, 3);
            total += Double.parseDouble(precoStr.replace("R$", "").replace(",", "."));
        }
        return total;
    }

    // Método para finalizar o pedido.
    private void finalizarPedido() {
        if (modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos uma pizza ao pedido!");
            return;
        }

        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente!");
            return;
        }

        Pedido pedidoExistente = buscarPedidoPorCliente(cliente);
        if (pedidoExistente != null) {
            pedidoExistente.getPizzas().clear();
            for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                String forma = (String) modeloTabela.getValueAt(i, 0);
                double dimensao = Double.parseDouble(modeloTabela.getValueAt(i, 1).toString());
                String sabor = (String) modeloTabela.getValueAt(i, 2);
                double preco = Double.parseDouble(modeloTabela.getValueAt(i, 3).toString().replace("R$", "").replace(",", "."));
                Pizza pizza = new Pizza(forma, dimensao, sabor, preco);
                pedidoExistente.adicionarPizza(pizza);
            }
            JOptionPane.showMessageDialog(this, "Pedido atualizado para o cliente " + cliente.getNome() + "!");
        } else {
            Pedido novoPedido = new Pedido(cliente);
            for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                String forma = (String) modeloTabela.getValueAt(i, 0);
                double dimensao = Double.parseDouble(modeloTabela.getValueAt(i, 1).toString());
                String sabor = (String) modeloTabela.getValueAt(i, 2);
                double preco = Double.parseDouble(modeloTabela.getValueAt(i, 3).toString().replace("R$", "").replace(",", "."));
                Pizza pizza = new Pizza(forma, dimensao, sabor, preco);
                novoPedido.adicionarPizza(pizza);
            }
            listaPedidos.add(novoPedido);
            JOptionPane.showMessageDialog(this, "Pedido finalizado para o cliente " + cliente.getNome() + "!");
        }

        telaControlePedidos.atualizarTabelaExterna();
        dispose();
    }

    private void removerPizza() {
        int selectedRow = tabelaPizzas.getSelectedRow();
        if (selectedRow != -1) {
            modeloTabela.removeRow(selectedRow);
            lblPrecoTotal.setText(String.format("Preço Total: R$%.2f", calcularPrecoTotal()));
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma pizza para remover.");
        }
    }
}