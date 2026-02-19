import java.util.Scanner;

public class InversaoNome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um nome para inverter: ");
        String nome = sc.nextLine();
        
        char[] vetorOriginal = nome.toCharArray();
        char[] vetorInvertido = new char[vetorOriginal.length];

        // Percorrendo e invertendo
        int j = 0;
        for (int i = vetorOriginal.length - 1; i >= 0; i--) {
            vetorInvertido[j] = vetorOriginal[i];
            j++;
        }

        // Exibindo o resultado como String
        String resultado = new String(vetorInvertido);
        System.out.println("Resultado: \"" + resultado + "\"");
        
        sc.close();
    }
}
