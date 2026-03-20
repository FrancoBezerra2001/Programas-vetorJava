import java.util.ArrayList;
import java.util.Scanner;

abstract class Produto {
    protected final String nome;
    protected final double preco;
    
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    public String getNome() {
        return nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}

class Televisao extends Produto {
    public Televisao() {
        super("Televisão", 2500.00);
    }
}

class Radio extends Produto {
    public Radio() {
        super("Rádio", 150.00);
    }
}

class Videogame extends Produto {
    public Videogame() {
        super("Videogame", 3500.00);
    }
}

class Tablet extends Produto {
    public Tablet() {
        super("Tablet", 1200.00);
    }
}

class Celular extends Produto {
    public Celular() {
        super("Celular", 2000.00);
    }
}

class ItemCarrinho {
    private Produto produto;
    private int quantidade;
    
    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
    
    @Override
    public String toString() {
        return produto.getNome() + " | Quantidade: " + quantidade + " | Subtotal: R$ " + String.format("%.2f", getSubtotal());
    }
}

public class ProdutosEletrodomesticos {
    private static ArrayList<Produto> produtosDisponiveis = new ArrayList<>();
    private static ArrayList<ItemCarrinho> carrinho = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        inicializarProdutos();
        
        System.out.println("=====================================");
        System.out.println("  Bem-vindo à Loja de Eletrodomésticos!");
        System.out.println("=====================================");
        
        boolean continuarComprando = true;
        
        while (continuarComprando) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    adicionarProdutoAoCarrinho();
                    break;
                case 3:
                    exibirCarrinho();
                    break;
                case 4:
                    finalizarCompra();
                    continuarComprando = false;
                    break;
                case 5:
                    System.out.println("Obrigado por visitar nossa loja!");
                    continuarComprando = false;
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        }
        
        scanner.close();
    }
    
    private static void inicializarProdutos() {
        produtosDisponiveis.add(new Televisao());
        produtosDisponiveis.add(new Radio());
        produtosDisponiveis.add(new Videogame());
        produtosDisponiveis.add(new Tablet());
        produtosDisponiveis.add(new Celular());
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Listar produtos disponíveis");
        System.out.println("2. Adicionar produto ao carrinho");
        System.out.println("3. Exibir carrinho");
        System.out.println("4. Finalizar compra");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }
    
    private static void listarProdutos() {
        System.out.println("\n=== PRODUTOS DISPONÍVEIS ===");
        for (int i = 0; i < produtosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + produtosDisponiveis.get(i));
        }
    }
    
    private static void adicionarProdutoAoCarrinho() {
        listarProdutos();
        System.out.print("\nDigite o número do produto que deseja comprar: ");
        
        try {
            int indiceProduto = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indiceProduto >= 0 && indiceProduto < produtosDisponiveis.size()) {
                Produto produtoSelecionado = produtosDisponiveis.get(indiceProduto);
                
                System.out.print("Digite a quantidade desejada: ");
                int quantidade = Integer.parseInt(scanner.nextLine());
                
                if (quantidade > 0) {
                    carrinho.add(new ItemCarrinho(produtoSelecionado, quantidade));
                    System.out.println("Produto adicionado ao carrinho com sucesso!");
                } else {
                    System.out.println("Quantidade inválida! Deve ser maior que zero.");
                }
            } else {
                System.out.println("Produto inválido! Por favor, escolha um número da lista.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite números válidos.");
        }
    }
    
    private static void exibirCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("\nSeu carrinho está vazio.");
            return;
        }
        
        System.out.println("\n=== SEU CARRINHO ===");
        double total = 0;
        
        for (int i = 0; i < carrinho.size(); i++) {
            ItemCarrinho item = carrinho.get(i);
            System.out.println((i + 1) + ". " + item);
            total += item.getSubtotal();
        }
        
        System.out.println("TOTAL: R$ " + String.format("%.2f", total));
    }
    
    private static void finalizarCompra() {
        if (carrinho.isEmpty()) {
            System.out.println("\nSeu carrinho está vazio. Não há compras para finalizar.");
            return;
        }
        
        System.out.println("\n=== RESUMO DA COMPRA ===");
        double total = 0;
        
        for (ItemCarrinho item : carrinho) {
            System.out.println(item);
            total += item.getSubtotal();
        }
        
        System.out.println("\nTOTAL DA COMPRA: R$ " + String.format("%.2f", total));
        System.out.println("\nObrigado por comprar conosco! Volte sempre!");
    }
}