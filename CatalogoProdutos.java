import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Produto {
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format("Produto: %s | Preço: R$ %.2f", nome, preco);
    }
}

public class CatalogoProdutos {
    public static void main(String[] args) {
        
        List<Produto> produtos = Arrays.asList(
            new Produto("Smartphone", 1200.00),
            new Produto("Fone de Ouvido", 80.00),
            new Produto("Monitor", 950.00),
            new Produto("Teclado Mecânico", 150.00),
            new Produto("Mouse Pad", 45.00)
        );

        List<Produto> caros = produtos.stream()
            .filter(p -> p.getPreco() > 100)
            .collect(Collectors.toList());

        System.out.println("--- Produtos acima de R$ 100 ---");
        caros.forEach(System.out::println);

        List<Produto> ordenados = produtos.stream()
            .sorted(Comparator.comparing(Produto::getNome))
            .collect(Collectors.toList());

        System.out.println("\n--- Catálogo Ordenado ---");
        ordenados.forEach(System.out::println);

        System.out.println("\n--- Resultado da Busca ---");
        buscarProduto(produtos, "Monitor");
        buscarProduto(produtos, "Notebook");
    }

    public static void buscarProduto(List<Produto> lista, String nomeBusca) {
        Optional<Produto> produtoEncontrado = lista.stream()
            .filter(p -> p.getNome().equalsIgnoreCase(nomeBusca))
            .findFirst();

        produtoEncontrado.ifPresentOrElse(
            p -> System.out.println("Encontrado: " + p),
            () -> System.out.println("Produto não encontrado.")
        );
    }
}