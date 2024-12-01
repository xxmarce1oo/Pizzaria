package br.ufpr.pizzaria.util;

import br.ufpr.pizzaria.model.Cliente;
import java.io.*;
import java.util.ArrayList;

/**
 * Classe utilitária para persistência de dados de clientes.
 */
public class Persistencia {

    private static final String ARQUIVO_CLIENTES = "clientes.dat";

    /**
     * Salva a lista de clientes em um arquivo.
     *
     * @param clientes Lista de clientes a ser salva.
     */
    public static void salvarClientes(ArrayList<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CLIENTES))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    /**
     * Carrega a lista de clientes de um arquivo.
     *
     * @return Lista de clientes carregada, ou uma lista vazia em caso de erro.
     */
    public static ArrayList<Cliente> carregarClientes() {
        File arquivo = new File(ARQUIVO_CLIENTES);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de clientes não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();

            // Verifica se o objeto é do tipo esperado antes do casting.
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;
                ArrayList<Cliente> clientes = new ArrayList<>();

                for (Object item : tempList) {
                    if (item instanceof Cliente) {
                        clientes.add((Cliente) item);
                    } else {
                        System.err.println("Objeto inválido encontrado no arquivo. Ignorando.");
                    }
                }
                return clientes;
            } else {
                System.err.println("Formato de arquivo inválido.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
