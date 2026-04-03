import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Empresa {
    private String nome;
    private List<Funcionario> funcionarios;

    public Empresa(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }
}

class Funcionario {
    private String nome;
    private LocalDate dataNascimento;
    private double salario;
    private String tipoContrato; // "CLT" ou "PJ"

    public Funcionario(String nome, LocalDate dataNascimento, double salario, String tipoContrato) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public double getSalario() {
        return salario;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", idade=" + getIdade() +
                ", salario=" + salario +
                ", tipoContrato='" + tipoContrato + '\'' +
                '}';
    }
}

public class ListaEmpresas {

    public static List<Funcionario> filtrarFuncionarios(List<Empresa> empresas) {
        return empresas.stream()
                .flatMap(empresa -> empresa.getFuncionarios().stream())
                .filter(funcionario -> {
                    int anoNascimento = funcionario.getDataNascimento().getYear();
                    return anoNascimento >= 1975 && anoNascimento <= 1985;
                })
                .filter(funcionario -> funcionario.getSalario() <= 3000.0)
                .collect(Collectors.toList());
    }

   
    public static int somarNumerosPares(List<Integer> numeros) {
        return numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<Funcionario> getTrintaMaioresSalariosCLT(List<Empresa> empresas) {
        return empresas.stream()
                .flatMap(empresa -> empresa.getFuncionarios().stream())
                .filter(funcionario -> "CLT".equals(funcionario.getTipoContrato()))
                .filter(funcionario -> funcionario.getIdade() < 25)
                .sorted((f1, f2) -> Double.compare(f2.getSalario(), f1.getSalario())) // Ordena por salário decrescente
                .limit(30)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        
        Empresa empresa1 = new Empresa("Empresa A");
        Empresa empresa2 = new Empresa("Empresa B");

       
        empresa1.adicionarFuncionario(new Funcionario("João", LocalDate.of(1980, 5, 15), 2500.0, "CLT"));
        empresa1.adicionarFuncionario(new Funcionario("Maria", LocalDate.of(1978, 8, 22), 3500.0, "CLT"));
        empresa1.adicionarFuncionario(new Funcionario("Pedro", LocalDate.of(1982, 12, 3), 2800.0, "PJ"));
        empresa1.adicionarFuncionario(new Funcionario("Ana", LocalDate.of(1976, 3, 10), 3000.0, "CLT"));

        empresa2.adicionarFuncionario(new Funcionario("Carlos", LocalDate.of(1983, 7, 19), 4000.0, "CLT"));
        empresa2.adicionarFuncionario(new Funcionario("Julia", LocalDate.of(1979, 11, 30), 2200.0, "CLT"));
        empresa2.adicionarFuncionario(new Funcionario("Lucas", LocalDate.of(1984, 1, 25), 3200.0, "PJ"));

        List<Empresa> empresas = Arrays.asList(empresa1, empresa2);

        
        System.out.println("Funcionários nascidos entre 1975 e 1985 com salário <= 3000:");
        List<Funcionario> filtrados = filtrarFuncionarios(empresas);
        filtrados.forEach(System.out::println);

        System.out.println("\n---");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int somaPares = somarNumerosPares(numeros);
        System.out.println("Soma dos números pares: " + somaPares);

        System.out.println("\n---");

        System.out.println("Funcionários CLT com menos de 25 anos:");
        List<Funcionario> maioresSalarios = getTrintaMaioresSalariosCLT(empresas);
        maioresSalarios.forEach(System.out::println);
    }
}