import java.util.Scanner;


class Aluno {
  
    private String nome;
    private int matricula;
    private double[] notas;
   
    public Aluno(String nome, int matricula, int quantidadeNotas) {
        this.nome = nome;
        this.matricula = matricula;
        this.notas = new double[quantidadeNotas];
    }
    
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getMatricula() {
        return matricula;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public double[] getNotas() {
        return notas;
    }
    
    public void setNota(int indice, double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota inválida! A nota deve estar entre 0 e 10.");
        }
        if (indice < 0 || indice >= notas.length) {
            throw new IllegalArgumentException("Índice de nota inválido!");
        }
        this.notas[indice] = nota;
    }
  
    public double calcularMedia() {
        if (notas.length == 0) {
            return 0;
        }
        
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.length;
    }
   
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.print("Notas: ");
        for (int i = 0; i < notas.length; i++) {
            System.out.print(notas[i]);
            if (i < notas.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("Média: " + String.format("%.2f", calcularMedia()));
        System.out.println("------------------------");
    }
}

public class MinhaClasse {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Digite a quantidade de alunos: ");
            int quantidadeAlunos = scanner.nextInt();
           
            System.out.print("Digite a quantidade de provas realizadas no ano: ");
            int quantidadeProvas = scanner.nextInt();
         
            Aluno[] alunos = new Aluno[quantidadeAlunos];
           
            for (int i = 0; i < quantidadeAlunos; i++) {
                System.out.println("\n--- Cadastro do Aluno " + (i + 1) + " ---");
                
                System.out.print("Nome do aluno: ");
                scanner.nextLine(); 
                String nome = scanner.nextLine();
                
                System.out.print("Matrícula do aluno: ");
                int matricula = scanner.nextInt();
              
                alunos[i] = new Aluno(nome, matricula, quantidadeProvas);
        
                for (int j = 0; j < quantidadeProvas; j++) {
                    boolean notaValida = false;
                    while (!notaValida) {
                        try {
                            System.out.print("Digite a nota da prova " + (j + 1) + ": ");
                            double nota = scanner.nextDouble();
                            alunos[i].setNota(j, nota);
                            notaValida = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Por favor, digite novamente a nota.");
                        }
                    }
                }
            }
          
            System.out.println("\n=== RELATÓRIO FINAL ===");
            for (int i = 0; i < quantidadeAlunos; i++) {
                alunos[i].exibirInformacoes();
            }
            
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
