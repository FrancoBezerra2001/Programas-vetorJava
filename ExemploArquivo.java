import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 1. Classe de Modelo
class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Método para transformar o objeto em uma tag XML simples
    public String toXML() {
        return "  <produto>\n" +
               "    <id>" + id + "</id>\n" +
               "    <nome>" + nome + "</nome>\n" +
               "    <preco>" + preco + "</preco>\n" +
               "  </produto>";
    }
}

// 2. Classe Principal
public class ExemploArquivo {
    public static void main(String[] args) {
        String caminho = "produtos_db.txt";

        // ESCRITA: Criando o arquivo com conteúdo sistemático (CSV-like)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write("1;Notebook;3500.00");
            writer.newLine();
            writer.write("2;Mouse Gamer;150.50");
            writer.newLine();
            writer.write("3;Teclado Mecanico;420.00");
            
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro na escrita: " + e.getMessage());
        }

        // LEITURA E TRANSFORMAÇÃO
        System.out.println("\n--- Convertendo dados para XML ---");
        System.out.println("<estoque>");

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Regra de separação: Split pelo caractere ';'
                String[] dados = linha.split(";");
                
                if (dados.length == 3) {
                    // Transforma dados lidos em Objeto Java
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    double preco = Double.parseDouble(dados[2]);
                    
                    Produto p = new Produto(id, nome, preco);
                    
                    // Exibe o objeto em formato XML
                    System.out.println(p.toXML());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na leitura: " + e.getMessage());
        }

        System.out.println("</estoque>");
    }
}