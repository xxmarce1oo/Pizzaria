package br.ufpr.pizzaria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAtualizarPrecoPizza extends JDialog {
    private JTextField txtPrecoSimples, txtPrecoEspecial, txtPrecoPremium;
    private JButton btnAtualizar;
    private double precoSimples, precoEspecial, precoPremium;

    public TelaAtualizarPrecoPizza(double precoSimples, double precoEspecial, double precoPremium) {
        this.precoSimples = precoSimples;
        this.precoEspecial = precoEspecial;
        this.precoPremium = precoPremium;

        setTitle("Atualizar Preço das Pizzas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Preço Simples (por cm²):"));
        txtPrecoSimples = new JTextField(String.valueOf(precoSimples));
        add(txtPrecoSimples);

        add(new JLabel("Preço Especial (por cm²):"));
        txtPrecoEspecial = new JTextField(String.valueOf(precoEspecial));
        add(txtPrecoEspecial);

        add(new JLabel("Preço Premium (por cm²):"));
        txtPrecoPremium = new JTextField(String.valueOf(precoPremium));
        add(txtPrecoPremium);

        btnAtualizar = new JButton("Atualizar Preços");
        add(btnAtualizar);

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPrecos();
            }
        });
    }

    private void atualizarPrecos() {
        try {
            precoSimples = Double.parseDouble(txtPrecoSimples.getText());
            precoEspecial = Double.parseDouble(txtPrecoEspecial.getText());
            precoPremium = Double.parseDouble(txtPrecoPremium.getText());
            JOptionPane.showMessageDialog(this, "Preços atualizados com sucesso!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.");
        }
    }

    public double getPrecoSimples() {
        return precoSimples;
    }

    public double getPrecoEspecial() {
        return precoEspecial;
    }

    public double getPrecoPremium() {
        return precoPremium;
    }
}