package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Pedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaControlePedidos extends JDialog {
    // Componentes da interface gráfica.
    private JTable tabelaPedidos;
    private DefaultTableModel modeloTabela;
    private JComboBox<String> comboStatus;
    private JButton btnAlterarStatus, btnAtualizarTabela;

    // Lista de pedidos.
    private ArrayList<Pedido> listaPedidos;

    // Construtor da classe TelaControlePedidos que inicializa os componentes.
    public TelaControlePedidos(ArrayList<Pedido> pedidos) {
        this.listaPedidos = pedidos;
        setTitle("Controle de Pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Painel superior com filtro de status e botão de atualização.
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboStatus = new JComboBox<>(new String[]{"Todos", "Aberto", "A Caminho", "Entregue"});
        btnAtualizarTabela = new JButton("Atualizar Tabela");

        painelSuperior.add(new JLabel("Filtrar por Status:"));
        painelSuperior.add(comboStatus);
        painelSuperior.add(btnAtualizarTabela);

        add(painelSuperior, BorderLayout.NORTH);

        // Configuração da tabela de pedidos.
        String[] colunas = {"ID Pedido", "Cliente", "Valor Total", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPedidos = new JTable(modeloTabela);

        add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);

        // Painel inferior com botão para alterar o status do pedido.
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAlterarStatus = new JButton("Alterar Status");
        painelInferior.add(btnAlterarStatus);

        add(painelInferior, BorderLayout.SOUTH);

        // Atualiza a tabela com os dados iniciais.
        atualizarTabela();

        // Adiciona listeners aos botões.
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

    // Método público para atualizar a tabela externamente.
    public void atualizarTabelaExterna() {
        atualizarTabela();
    }

    // Método privado para atualizar a tabela com os dados dos pedidos.
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        String statusFiltro = (String) comboStatus.getSelectedItem();
        for (Pedido pedido : listaPedidos) {
            if (statusFiltro.equals("Todos") || statusFiltro.equalsIgnoreCase(pedido.getEstado())) {
                modeloTabela.addRow(new Object[]{
                        pedido.getId(),
                        pedido.getCliente().getNome(),
                        pedido.calcularPrecoTotal(),
                        pedido.getEstado()
                });
            }
        }
    }

    // Método privado para alterar o status de um pedido.
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
}