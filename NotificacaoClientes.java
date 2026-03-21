interface Notificador {
    void enviarMensagem(String mensagem);
}

class EmailNotificador implements Notificador {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Enviando e-mail: " + mensagem);
    }
}

class SmsNotificador implements Notificador {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Enviando SMS: " + mensagem);
    }
}

class WhatsappNotificador implements Notificador {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Enviando WhatsApp: " + mensagem);
    }
}

class UsuarioService {
    private Notificador notificador;
    
   
    public UsuarioService(Notificador notificador) {
        this.notificador = notificador;
    }
    
    public void registrar(String nome) {
        System.out.println("Registrando usuário: " + nome);
        notificador.enviarMensagem("Bem-vindo, " + nome + "! Seu registro foi realizado com sucesso.");
    }
}

public class NotificacaoClientes {
    public static void main(String[] args) {
        
        System.out.println("=== Teste com Email Notificador ===");
        new UsuarioService(new EmailNotificador()).registrar("João");
        
        System.out.println("\n=== Teste com WhatsApp Notificador ===");
        new UsuarioService(new WhatsappNotificador()).registrar("Maria");
        
        System.out.println("\n=== Teste com SMS Notificador ===");
        new UsuarioService(new SmsNotificador()).registrar("Pedro");
        
        System.out.println("\n=== Teste com outro Email Notificador ===");
        new UsuarioService(new EmailNotificador()).registrar("Ana");
    }
}