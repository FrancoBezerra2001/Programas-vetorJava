import java.util.Scanner;
import java.util.Random;

public class ContagemVogais {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        System.out.print("Digite o tamanho do vetor (n): ");
        int n = sc.nextInt();
        int[] vetor = new int[n];

    
        for (int i = 0; i < n; i++) {
            vetor[i] = rd.nextInt(101);
        }


        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }

        System.out.println("Vetor ordenado com sucesso.");

    
        System.out.print("Agora, digite um nome: ");
        sc.nextLine(); 
        String nome = sc.nextLine().toLowerCase();
        int contVogais = 0;

        for (char c : nome.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contVogais++;
            }
        }

        System.out.println("O nome '" + nome + "' possui " + contVogais + " vogais.");
        sc.close();
    }
}