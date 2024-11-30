package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Pedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaControlePedidos extends JFrame {
    private JTable tabelaPedidos;
    private DefaultTableModel modeloTabela;
    private JComboBox<String> comboStatus;
    private JButton btnAlterarStatus, btnAtualizarTabela;

    private ArrayList<Pedido> listaPedidos;

    public TelaControlePedidos(ArrayList<Pedido> pedidos) {
        this.listaPedidos = pedidos;
        setTitle("Controle de Pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel superior com filtros
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboStatus = new JComboBox<>(new String[]{"Todos", "Aberto", "A Caminho", "Entregue"});
        btnAtualizarTabela = new JButton("Atualizar Tabela");

        painelSuperior.add(new JLabel("Filtrar por Status:"));
        painelSuperior.add(comboStatus);
        painelSuperior.add(btnAtualizarTabela);

        add(painelSuperior, BorderLayout.NORTH);

        // Tabela para exibir os pedidos
        String[] colunas = {"ID Pedido", "Cliente", "Valor Total", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPedidos = new JTable(modeloTabela);

        add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);

        // Painel inferior com ações
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAlterarStatus = new JButton("Alterar Status");
        painelInferior.add(btnAlterarStatus);

        add(painelInferior, BorderLayout.SOUTH);

        // Carregar dados iniciais na tabela
        atualizarTabela();

        // Listeners
        btnAtualizarTabela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });

        btnAlterarStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarStatusPedido();
            }
        });
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpar tabela

        String statusFiltro = (String) comboStatus.getSelectedItem();
        for (Pedido pedido : listaPedidos) {
            if (statusFiltro.equals("Todos") || statusFiltro.equalsIgnoreCase((String) pedido.getStatus())) {
                modeloTabela.addRow(new Object[]{
                        pedido.getId(),
                        pedido.getCliente().getNome(),
                        pedido.getValorTotal(),
                        pedido.getStatus()
                });
            }
        }
    }

    private void alterarStatusPedido() {
        int linhaSelecionada = tabelaPedidos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para alterar o status.");
            return;
        }

        String novoStatus = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o novo status:",
                "Alterar Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Aberto", "A Caminho", "Entregue"},
                "Aberto"
        );

        if (novoStatus != null) {
            int pedidoId = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            for (Pedido pedido : listaPedidos) {
                if (Integer.valueOf(pedidoId).equals(pedido.getId())) {
                    pedido.setStatus(novoStatus);
                    JOptionPane.showMessageDialog(this, "Status alterado com sucesso!");
                    atualizarTabela();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Teste da interface
        ArrayList<Pedido> pedidos = new ArrayList<>();
        // Adicionar pedidos de exemplo para teste
        TelaControlePedidos tela = new TelaControlePedidos(pedidos);
        tela.setVisible(true);
    }
}
