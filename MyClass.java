import java.util.HashSet;
import java.util.Set;

class Pessoa {
    private String nome;
    private int idade;
    
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
  
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
  
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
       
        Pessoa outraPessoa = (Pessoa) obj;
        return idade == outraPessoa.idade && 
               nome != null && 
               nome.equals(outraPessoa.nome);
    }
    
    @Override
    public int hashCode() {
      
        return nome != null ? nome.hashCode() + idade : idade;
    }
    
    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', idade=" + idade + "}";
    }
}

public class MyClass {
    public static void main(String args[]) {
        
        Set<Pessoa> conjuntoPessoas = new HashSet<>();
        
        conjuntoPessoas.add(new Pessoa("João", 25));
        conjuntoPessoas.add(new Pessoa("Maria", 30));
        conjuntoPessoas.add(new Pessoa("Pedro", 22));
        conjuntoPessoas.add(new Pessoa("João", 25));  
        conjuntoPessoas.add(new Pessoa("Ana", 28));
        conjuntoPessoas.add(new Pessoa("Maria", 30)); 
        conjuntoPessoas.add(new Pessoa("Carlos", 35));
        conjuntoPessoas.add(new Pessoa("Pedro", 22)); 
        
        
        System.out.println("Número de pessoas no conjunto: " + conjuntoPessoas.size());
        System.out.println();
        
        System.out.println("Pessoas cadastradas:");
        for (Pessoa pessoa : conjuntoPessoas) {
            System.out.println(pessoa);
        }
        System.out.println();
        
        System.out.println("EXPLICAÇÃO:");
        System.out.println("O HashSet é uma estrutura de dados que não permite elementos duplicados.");
        System.out.println("Quando implementamos os métodos equals() e hashCode() na classe Pessoa,");
        System.out.println("definimos que duas pessoas são iguais se tiverem o mesmo nome e idade.");
        System.out.println("Por isso, quando tentamos adicionar pessoas com os mesmos nome e idade,");
        System.out.println("o HashSet as considera duplicatas e não as armazena, mantendo apenas uma instância.");
        System.out.println();
        System.out.println("Inicialmente tentamos adicionar 8 pessoas, mas apenas " + conjuntoPessoas.size() + 
                          " foram armazenadas pois 3 eram duplicatas (João-25, Maria-30, Pedro-22).");
    }
}
