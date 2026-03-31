import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Produto implements Comparable<Produto> {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    @Override
    public int compareTo(Produto outro) {
        
        return Double.compare(this.preco, outro.getPreco());
    }

    @Override
    public String toString() {
        return String.format("Produto: %-15s | Preço: R$ %8.2f", nome, preco);
    }
}

class ListaUtil {
     
    
    public static <T extends Comparable<? super T>> void ordenarExibir(List<T> lista) {
       
        Collections.sort(lista);
        
        System.out.println("--- Lista Ordenada ---");
        for (T item : lista) {
            System.out.println(item);
        }
    }
}

public class ClassCollections {
    public static void main(String[] args) {
        
        List<Produto> listaProdutos = new ArrayList<>();
        
        listaProdutos.add(new Produto("Monitor G-Sync", 1250.00));
        listaProdutos.add(new Produto("Mouse Gamer", 250.50));
        listaProdutos.add(new Produto("Teclado Mecânico", 450.90));
        listaProdutos.add(new Produto("Headset 7.1", 320.00));

        
        ListaUtil.ordenarExibir(listaProdutos);
    }
}