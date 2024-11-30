/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpr.pizzaria;

/**
 *
 * @author marce
 */

import br.ufpr.pizzaria.model.Cliente;
import br.ufpr.pizzaria.util.Persistencia;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = Persistencia.carregarClientes();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> Persistencia.salvarClientes(clientes)));

        TelaCadastroClientes telaCadastroClientes = new TelaCadastroClientes(clientes);
    }
}