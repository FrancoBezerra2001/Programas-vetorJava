public class Reserva {
    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        setNumeroDias(numeroDias); 
        setValorDiaria(valorDiaria); 
    }

    public Reserva(String nomeHospede, String tipoQuarto) {
        this(nomeHospede, tipoQuarto, 1, 100.0); 
    }

    public String getNomeHospede() { return nomeHospede; }
    
    public int getNumeroDias() { return numeroDias; }
    public void setNumeroDias(int numeroDias) {
        this.numeroDias = Math.max(numeroDias, 1); 
    }

    public double getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(double valorDiaria) {
        if (valorDiaria > 0) {
            this.valorDiaria = valorDiaria;
        } else {
            this.valorDiaria = 50.0; 
        }
    }

    public double calcularValorTotal() {
        return numeroDias * valorDiaria;
    }

    @Override
    public String toString() {
        return String.format("Hóspede: %-15s | Quarto: %-12s | Dias: %2d | Total: R$ %.2f",  
                nomeHospede, tipoQuarto, numeroDias, calcularValorTotal());
    }
    
    public static void main(String[] args) {
       
        Reserva reserva1 = new Reserva("João Silva", "Standard", 3, 150.0);
        Reserva reserva2 = new Reserva("Maria Santos", "Luxo");
        
        System.out.println("Reservas de Hotel:");
        System.out.println("==================");
        System.out.println(reserva1);
        System.out.println(reserva2);
     
        reserva2.setNumeroDias(5);
        reserva2.setValorDiaria(200.0);
        System.out.println("\nApós atualização:");
        System.out.println(reserva2);
    }
}
