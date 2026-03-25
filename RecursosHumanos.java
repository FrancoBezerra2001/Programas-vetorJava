import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

class RegistroHoras {
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;
    private int horasTrabalhadas;
    private int horasExtras;

    public RegistroHoras(LocalDate data, LocalTime entrada, LocalTime saida) {
        this.data = data;
        this.entrada = entrada;
        this.saida = saida;
        calcularHoras();
    }

    private void calcularHoras() {
       
        int minutosTotais = (int) java.time.Duration.between(entrada, saida).toMinutes() - 60;
        this.horasTrabalhadas = minutosTotais / 60;
        this.horasExtras = Math.max(0, horasTrabalhadas - 8); 
    }

    public LocalDate getData() { return data; }
    public LocalTime getEntrada() { return entrada; }
    public LocalTime getSaida() { return saida; }
    public int getHorasTrabalhadas() { return horasTrabalhadas; }
    public int getHorasExtras() { return horasExtras; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("Data: %s | Entrada: %s | Sada: %s | Horas Trabalhadas: %d | Horas Extras: %d",
                data, entrada.format(formatter), saida.format(formatter), horasTrabalhadas, horasExtras);
    }
}

abstract class Funcionario {
    protected String nome;
    protected int id;
    protected List<RegistroHoras> registros;

    public Funcionario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.registros = new ArrayList<>();
    }

  
    public abstract boolean batePonto();

    public abstract int getLimiteHorasExtras();

    public abstract String getTipo();

    public boolean adicionarRegistro(LocalDate data, LocalTime entrada, LocalTime saida) {
        
        if (saida.isBefore(entrada) || saida.equals(entrada)) {
            System.out.println("Erro: Horrio de sada deve ser posterior ao horrio de entrada.");
            return false;
        }

        if (entrada.isBefore(LocalTime.of(6, 0))) {
            System.out.println("Erro: Horrio de entrada no pode ser antes das 06:00.");
            return false;
        }

        if (saida.isAfter(LocalTime.of(22, 0))) {
            System.out.println("Erro: Horrio de sada no pode ser aps as 22:00.");
            return false;
        }

        RegistroHoras registro = new RegistroHoras(data, entrada, saida);
        
        
        if (registro.getHorasExtras() > getLimiteHorasExtras()) {
            System.out.printf("Erro: Limite de horas extras excedido. Mximo permitido para %s: %d horas.%n",
                    getTipo(), getLimiteHorasExtras());
            return false;
        }

        registros.add(registro);
        return true;
    }

    public String getNome() { return nome; }
    public int getId() { return id; }
    public List<RegistroHoras> getRegistros() { return registros; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Tipo: %s", id, nome, getTipo());
    }
}

class Gerente extends Funcionario {
    public Gerente(String nome, int id) {
        super(nome, id);
    }

    @Override
    public boolean batePonto() {
        return false; 
    }

    @Override
    public int getLimiteHorasExtras() {
        return 0; 
    }

    @Override
    public String getTipo() {
        return "Gerente";
    }
}

class Coordenador extends Funcionario {
    public Coordenador(String nome, int id) {
        super(nome, id);
    }

    @Override
    public boolean batePonto() {
        return true;
    }

    @Override
    public int getLimiteHorasExtras() {
        return 5;
    }

    @Override
    public String getTipo() {
        return "Coordenador";
    }
}

class Analista extends Funcionario {
    public Analista(String nome, int id) {
        super(nome, id);
    }

    @Override
    public boolean batePonto() {
        return true;
    }

    @Override
    public int getLimiteHorasExtras() {
        return 3;
    }

    @Override
    public String getTipo() {
        return "Analista";
    }
}

class Assistente extends Funcionario {
    public Assistente(String nome, int id) {
        super(nome, id);
    }

    @Override
    public boolean batePonto() {
        return true;
    }

    @Override
    public int getLimiteHorasExtras() {
        return 3;
    }

    @Override
    public String getTipo() {
        return "Assistente";
    }
}

class Estagiario extends Funcionario {
    public Estagiario(String nome, int id) {
        super(nome, id);
    }

    @Override
    public boolean batePonto() {
        return false; 
    }

    @Override
    public int getLimiteHorasExtras() {
        return 0; 
    }

    @Override
    public String getTipo() {
        return "Estagiário";
    }
}

public class RecursosHumanos {
    private static Map<Integer, Funcionario> funcionarios = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Bem-vindo ao Portal de Recursos Humanos ===");
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    criarFuncionario();
                    break;
                case 2:
                    registrarHoras();
                    break;
                case 3:
                    listarFuncionarios();
                    break;
                case 4:
                    buscarFuncionario();
                    break;
                case 5:
                    removerFuncionario();
                    break;
                case 6:
                    listarRegistrosFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opo invlida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU DE OPÇÕES ===");
        System.out.println("1. Criar Funcionário");
        System.out.println("2. Registrar Horas de Trabalho");
        System.out.println("3. Listar Todos os Funcionários");
        System.out.println("4. Buscar Funcionário por ID");
        System.out.println("5. Remover Funcionário");
        System.out.println("6. Listar Registros de Horas de um Funcionário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opoção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void criarFuncionario() {
        System.out.println("\n=== CRIAR FUNCIONÁRIO ===");
        System.out.println("Tipos disponveis:");
        System.out.println("1. Gerente");
        System.out.println("2. Coordenador");
        System.out.println("3. Analista");
        System.out.println("4. Assistente");
        System.out.println("5. Estagiário");
        System.out.print("Escolha o tipo de funcionário (1-5): ");
        
        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Digite o ID do funcionário: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        if (funcionarios.containsKey(id)) {
            System.out.println("J existe um funcionário com este ID.");
            return;
        }

        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();

        Funcionario funcionario = null;
        switch (tipo) {
            case 1:
                funcionario = new Gerente(nome, id);
                break;
            case 2:
                funcionario = new Coordenador(nome, id);
                break;
            case 3:
                funcionario = new Analista(nome, id);
                break;
            case 4:
                funcionario = new Assistente(nome, id);
                break;
            case 5:
                funcionario = new Estagiario(nome, id);
                break;
            default:
                System.out.println("Tipo de funcionário inválido.");
                return;
        }

        funcionarios.put(id, funcionario);
        System.out.println("Funcionário criado com sucesso!");
        System.out.println(funcionario);
    }

    private static void registrarHoras() {
        System.out.println("\n=== REGISTRAR HORAS DE TRABALHO ===");
        System.out.print("Digite o ID do funcionário: ");
        
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Funcionario funcionario = funcionarios.get(id);
        if (funcionario == null) {
            System.out.println("Funcionário no encontrado.");
            return;
        }

        if (!funcionario.batePonto()) {
            System.out.println("Erro: " + funcionario.getTipo() + " no bate ponto.");
            return;
        }

        System.out.print("Digite a data (AAAA-MM-DD): ");
        LocalDate data;
        try {
            data = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida.");
            return;
        }

        System.out.print("Digite o horrio de entrada (HH:MM): ");
        LocalTime entrada;
        try {
            entrada = LocalTime.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Horrio de entrada inválido.");
            return;
        }

        System.out.print("Digite o horrio de sada (HH:MM): ");
        LocalTime saida;
        try {
            saida = LocalTime.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Horrio de sada inválido.");
            return;
        }

        if (funcionario.adicionarRegistro(data, entrada, saida)) {
            System.out.println("Registro de horas adicionado com sucesso!");
        }
    }

    private static void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario f : funcionarios.values()) {
            System.out.println(f);
        }
    }

    private static void buscarFuncionario() {
        System.out.println("\n=== BUSCAR FUNCIONÁRIO ===");
        System.out.print("Digite o ID do funcionário: ");
        
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Funcionario funcionario = funcionarios.get(id);
        if (funcionario == null) {
            System.out.println("Funcionário no encontrado.");
        } else {
            System.out.println("Funcionário encontrado:");
            System.out.println(funcionario);
        }
    }

    private static void removerFuncionario() {
        System.out.println("\n=== REMOVER FUNCIONÁRIO ===");
        System.out.print("Digite o ID do funcionário: ");
        
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Funcionario funcionario = funcionarios.remove(id);
        if (funcionario == null) {
            System.out.println("Funcionário no encontrado.");
        } else {
            System.out.println("Funcionário removido com sucesso:");
            System.out.println(funcionario);
        }
    }

    private static void listarRegistrosFuncionario() {
        System.out.println("\n=== LISTAR REGISTROS DE HORAS ===");
        System.out.print("Digite o ID do funcionário: ");
        
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Funcionario funcionario = funcionarios.get(id);
        if (funcionario == null) {
            System.out.println("Funcionário no encontrado.");
            return;
        }

        if (!funcionario.batePonto()) {
            System.out.println(funcionario.getTipo() + " no registra horas de trabalho.");
            return;
        }

        System.out.println("Registros de horas para " + funcionario.getNome() + ":");
        if (funcionario.getRegistros().isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (RegistroHoras registro : funcionario.getRegistros()) {
                System.out.println(registro);
            }
        }
    }
}