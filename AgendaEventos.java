import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

enum DiaDaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
}

class Evento {
    private String nome;
    private LocalDateTime dataHora;
    private DiaDaSemana diaDaSemana;
  
    public Evento(String nome, LocalDateTime dataHora, DiaDaSemana diaDaSemana) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.diaDaSemana = diaDaSemana;
    }
   
    public void exibirEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("=== Detalhes do Evento ===");
        System.out.println("Nome: " + this.nome);
        System.out.println("Data e Hora: " + this.dataHora.format(formatter));
        System.out.println("Dia da Semana: " + this.diaDaSemana);
        System.out.println("==========================");
    }
  
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

public class AgendaEventos {
    public static void main(String[] args) {
        
        LocalDateTime dataAtual = LocalDateTime.now();
        Evento evento = new Evento("Reunião de Trabalho", dataAtual, DiaDaSemana.QUARTA);
       
        System.out.println("Evento original:");
        evento.exibirEvento();
        
        LocalDateTime novaData = evento.getDataHora().plusDays(5);
        evento.setDataHora(novaData);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Nova data do evento (com +5 dias): " + evento.getDataHora().format(formatter));
        
        
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime saoPauloTime = evento.getDataHora().atZone(saoPauloZone);
        
        ZoneId gmtZone = ZoneId.of("GMT");
        ZonedDateTime gmtTime = evento.getDataHora().atZone(gmtZone);
        
        System.out.println("\n=== Conversão de Fusos Horários ===");
        System.out.println("Horário em São Paulo (BRT): " + saoPauloTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
        System.out.println("Horário em GMT: " + gmtTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
        System.out.println("===================================");
    }
}