import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

class TestReflection {
    private String nome;

    public TestReflection(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Utensílio de Cozinha: [ " + nome + " ]";
    }
}

public class TesteReflectionCozinha {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando operações via Reflection...\n");

            Class<?> classeLista = Class.forName("java.util.ArrayList");
            Constructor<?> construtorLista = classeLista.getDeclaredConstructor();
            List<Object> listaRefletida = (List<Object>) construtorLista.newInstance();

            
            Method metodoAdd = classeLista.getMethod("add", Object.class);

            String[] nomesUtensilios = {"Faca", "Garfo", "Colher", "Espátula", "Batedor"};
            
            for (String nome : nomesUtensilios) {
                TestReflection novoUtensilio = new TestReflection(nome);
               
                metodoAdd.invoke(listaRefletida, novoUtensilio);
            }

            Method metodoSize = classeLista.getMethod("size");
            Method metodoGet = classeLista.getMethod("get", int.class);

            int totalItens = (int) metodoSize.invoke(listaRefletida);

            System.out.println("--- Lista de Utensílios (Iteração via Reflection) ---");
            for (int i = 0; i < totalItens; i++) {
                
                Object item = metodoGet.invoke(listaRefletida, i);
                System.out.println(item);
            }

            System.out.println("\nOperação finalizada com sucesso!");

        } catch (Exception e) {
            
            System.err.println("Erro ao processar Reflection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}