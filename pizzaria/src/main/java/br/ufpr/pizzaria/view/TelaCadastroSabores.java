package br.ufpr.pizzaria.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.ufpr.pizzaria.model.Sabor;

import java.awt.*;
import java.util.ArrayList;

public class TelaCadastroSabores extends JDialog{
    private JTextField txtSabor;
    private JComboBox<String> comboTipo;
    private JTable tabelaSabores;
    private DefaultTableModel modeloTabela;
    private ArrayList<Sabor> listaSabores;
    
     public TelaCadastroSabores(ArrayList<Sabor> sabores) {
        this.listaSabores = sabores;
        setTitle("Cadastro de Sabores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Painel de cadastro de sabores.
        JPanel painelCadastro = new JPanel(new GridLayout(3, 2));
        painelCadastro.add(new JLabel("Sabor:"));
        txtSabor = new JTextField();
        painelCadastro.add(txtSabor);

        painelCadastro.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Simples", "Especial", "Premium"});
        painelCadastro.add(comboTipo);

        JButton btnAdicionar = new JButton("Adicionar Sabor");
        btnAdicionar.addActionListener(e -> adicionarSabor());
        painelCadastro.add(btnAdicionar);

        JButton btnRemover = new JButton("Remover Sabor");
        btnRemover.addActionListener(e -> removerSabor());
        painelCadastro.add(btnRemover);

        add(painelCadastro, BorderLayout.NORTH);

        // Configuração da tabela de sabores.
        String[] colunas = {"Sabor", "Tipo"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaSabores = new JTable(modeloTabela);

        add(new JScrollPane(tabelaSabores), BorderLayout.CENTER);

        // Atualiza a tabela com os dados iniciais.
        atualizarTabela();
    }
    private void adicionarSabor() {
        String sabor = txtSabor.getText();
        String tipo = (String) comboTipo.getSelectedItem();

        if (sabor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Sabor é obrigatório!");
            return;
        }

        Sabor novoSabor = new Sabor(sabor, tipo);
        listaSabores.add(novoSabor);
        JOptionPane.showMessageDialog(this, "Sabor adicionado com sucesso!");
        atualizarTabela();
        limparCampos();
    }
    private void removerSabor() {
        int linhaSelecionada = tabelaSabores.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um sabor para remover.");
            return;
        }

        listaSabores.remove(linhaSelecionada);
        JOptionPane.showMessageDialog(this, "Sabor removido com sucesso!");
        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Sabor sabor : listaSabores) {
            modeloTabela.addRow(new Object[]{sabor.getNome(), sabor.getTipo()});
        }
    }

    private void limparCampos() {
        txtSabor.setText("");
        comboTipo.setSelectedIndex(0);
    }
}

