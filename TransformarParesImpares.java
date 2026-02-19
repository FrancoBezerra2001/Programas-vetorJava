import java.util.Scanner;

public class TransformarParesImpares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos números deseja inserir? ");
        int n = sc.nextInt();
        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Posição [" + i + "]: ");
            vetor[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            if (vetor[i] % 2 == 0) {
                vetor[i] = vetor[i] * 2; 
            } else {
                vetor[i] = (int) Math.pow(vetor[i], 2); 
            }
        }

    
        System.out.print("Resultado: [");
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + (i == n - 1 ? "" : ", "));
        }
        System.out.println("]");
        sc.close();
    }
}