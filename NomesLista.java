import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface FiltroNome {
    boolean testar(String nome);
}

public class NomesLista {
    public static void main(String[] args) {
        
        List<String> nomes = Arrays.asList(
            "Ana", "Bruno", "Carlos", "Amanda", "Beatriz", 
            "Arthur", "Aline", "Andreia", "Augusto", "Alice"
        );

        FiltroNome filtroA = (nome) -> nome.startsWith("A");

        System.out.println("=== Nomes que começam com 'A' ===");

        
        long total = nomes.stream()
            .filter(n -> filtroA.testar(n))
            .peek(System.out::println) 
            .count(); 

        
        System.out.println("---------------------------------");
        System.out.println("Total de ocorrências encontradas: " + total);
    }
}