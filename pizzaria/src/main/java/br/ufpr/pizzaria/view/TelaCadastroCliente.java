package br.ufpr.pizzaria.view;

import br.ufpr.pizzaria.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaCadastroCliente extends JDialog {
    // Componentes da interface gráfica.
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;
    private JTextField txtNome, txtSobrenome, txtTelefone;
    private JButton btnAdicionar, btnAtualizar, btnExcluir, btnFiltrar;
    private ArrayList<Cliente> listaClientes;

    // Construtor da classe TelaCadastroCliente que inicializa os componentes.
    public TelaCadastroCliente(ArrayList<Cliente> clientes) {
        this.listaClientes = clientes;
        setTitle("Cadastro de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Painel de cadastro de clientes.
        JPanel painelCadastro = new JPanel(new GridLayout(4, 2));
        painelCadastro.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCadastro.add(txtNome);

        painelCadastro.add(new JLabel("Sobrenome:"));
        txtSobrenome = new JTextField();
        painelCadastro.add(txtSobrenome);

        painelCadastro.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelCadastro.add(txtTelefone);

        btnAdicionar = new JButton("Adicionar Cliente");
        painelCadastro.add(btnAdicionar);

        btnFiltrar = new JButton("Filtrar");
        painelCadastro.add(btnFiltrar);

        add(painelCadastro, BorderLayout.NORTH);

        // Configuração da tabela de clientes.
        String[] colunas = {"ID", "Nome", "Sobrenome", "Telefone"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(modeloTabela);

        add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);

        // Painel inferior com botões de atualização e exclusão.
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAtualizar = new JButton("Atualizar Cliente");
        btnExcluir = new JButton("Excluir Cliente");
        painelInferior.add(btnAtualizar);
        painelInferior.add(btnExcluir);

        add(painelInferior, BorderLayout.SOUTH);

        // Atualiza a tabela com os dados iniciais.
        atualizarTabela();

        // Adiciona listeners aos botões.
        btnAdicionar.addActionListener(e -> adicionarCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnFiltrar.addActionListener(e -> filtrarClientes());
    }

    // Método para adicionar um novo cliente.
    private void adicionarCliente() {
        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String telefone = txtTelefone.getText();

        // Validação dos campos.
        if (nome.isEmpty() || sobrenome.isEmpty() || telefone.isEmpty() || !telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios e o telefone deve ser numérico!");
            return;
        }

        // Cria um novo cliente e adiciona à lista.
        Cliente novoCliente = new Cliente(listaClientes.size() + 1, nome, sobrenome, telefone);
        listaClientes.add(novoCliente);
        JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
        atualizarTabela();
        limparCampos();
    }

    // Método para atualizar um cliente existente.
    private void atualizarCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
            return;
        }

        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String telefone = txtTelefone.getText();

        // Validação dos campos.
        if (nome.isEmpty() || sobrenome.isEmpty() || telefone.isEmpty() || !telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios e o telefone deve ser numérico!");
            return;
        }

        // Atualiza os dados do cliente selecionado.
        Cliente cliente = listaClientes.get(linhaSelecionada);
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setTelefone(telefone);

        JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        atualizarTabela();
        limparCampos();
    }

    // Método para excluir um cliente.
    private void excluirCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }

        // Remove o cliente da lista e atualiza os IDs.
        listaClientes.remove(linhaSelecionada);
        for (int i = 0; i < listaClientes.size(); i++) {
            listaClientes.get(i).setId(i + 1);
        }

        JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
        atualizarTabela();
    }

    // Método para atualizar a tabela com os dados dos clientes.
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            modeloTabela.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getSobrenome(),
                    cliente.getTelefone()
            });
        }
    }

    // Método para limpar os campos de entrada.
    private void limparCampos() {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtTelefone.setText("");
    }

    // Método para filtrar clientes com base em um critério.
    private void filtrarClientes() {
        String filtro = JOptionPane.showInputDialog(this, "Digite o nome, sobrenome ou telefone para filtrar:");
        if (filtro == null || filtro.isEmpty()) {
            atualizarTabela();
            return;
        }

        modeloTabela.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().contains(filtro) || cliente.getSobrenome().contains(filtro) || cliente.getTelefone().contains(filtro)) {
                modeloTabela.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getSobrenome(),
                        cliente.getTelefone()
                });
            }
        }
    }

    // Método principal para testar a interface.
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "João", "Silva", "123456789"));
        clientes.add(new Cliente(2, "Maria", "Oliveira", "987654321"));
        TelaCadastroCliente tela = new TelaCadastroCliente(clientes);
        tela.setVisible(true);
    }
}