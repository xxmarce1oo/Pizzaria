package br.ufpr.pizzaria.util;

import br.ufpr.pizzaria.model.Cliente;
import java.io.*;
import java.util.ArrayList;

/**
 * Classe utilitária para persistência de dados de clientes.
 */
public class Persistencia {

    // Nome do arquivo onde os dados dos clientes serão salvos.
    private static final String ARQUIVO_CLIENTES = "clientes.dat";

    /**
     * Salva a lista de clientes em um arquivo.
     *
     * @param clientes Lista de clientes a ser salva.
     */
    public static void salvarClientes(ArrayList<Cliente> clientes) {
        // Usa um ObjectOutputStream para salvar a lista de clientes no arquivo.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CLIENTES))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            // Imprime uma mensagem de erro se ocorrer uma exceção de E/S.
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    /**
     * Carrega a lista de clientes de um arquivo.
     *
     * @return Lista de clientes carregada, ou uma lista vazia em caso de erro.
     */
    public static ArrayList<Cliente> carregarClientes() {
        // Cria um objeto File para representar o arquivo de clientes.
        File arquivo = new File(ARQUIVO_CLIENTES);
        // Verifica se o arquivo existe.
        if (!arquivo.exists()) {
            // Se o arquivo não existir, imprime uma mensagem e retorna uma lista vazia.
            System.out.println("Arquivo de clientes não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        // Usa um ObjectInputStream para ler a lista de clientes do arquivo.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();

            // Verifica se o objeto lido é do tipo esperado antes de fazer o casting.
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;
                ArrayList<Cliente> clientes = new ArrayList<>();

                // Itera sobre a lista temporária e adiciona apenas objetos do tipo Cliente à lista final.
                for (Object item : tempList) {
                    if (item instanceof Cliente) {
                        clientes.add((Cliente) item);
                    } else {
                        // Imprime uma mensagem de erro se um objeto inválido for encontrado.
                        System.err.println("Objeto inválido encontrado no arquivo. Ignorando.");
                    }
                }
                return clientes;
            } else {
                // Imprime uma mensagem de erro se o formato do arquivo for inválido.
                System.err.println("Formato de arquivo inválido.");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Imprime uma mensagem de erro se ocorrer uma exceção de E/S ou de classe não encontrada.
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }

        // Retorna uma lista vazia em caso de erro.
        return new ArrayList<>();
    }
}