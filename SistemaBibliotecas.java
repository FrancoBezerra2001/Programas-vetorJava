import java.util.*;


class BibliotecaException extends Exception {
    public BibliotecaException(String mensagem) {
        super(mensagem);
    }
}

class Usuario {
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getEmail() { return email; }
    public String getNome() { return nome; }
    @Override
    public String toString() { return "Nome: " + nome + " | Email: " + email; }
}

class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private boolean emprestado;

    public Livro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.emprestado = false;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }
    public boolean isEmprestado() { return emprestado; }
    public void setEmprestado(boolean emprestado) { this.emprestado = emprestado; }

    @Override
    public String toString() {
        return String.format("Título: %s | Autor: %s | Ano: %d | Status: %s", 
                titulo, autor, ano, (emprestado ? "Emprestado" : "Disponível"));
    }
}
public class SistemaBibliotecas {
    private static List<Livro> livros = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;
        do {
            try {
                System.out.println("\n--- MENU BIBLIOTECA ---");
                System.out.println("1. Cadastrar Livro\n2. Cadastrar Usuário\n3. Listar Livros\n4. Listar Usuários");
                System.out.println("5. Buscar por Autor\n6. Ordenar por Ano\n7. Mostrar Emprestados");
                System.out.println("8. Emprestar Livro\n9. Devolver Livro\n0. Sair");
                System.out.print("Escolha: ");
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarLivro();
                    case 2 -> cadastrarUsuario();
                    case 3 -> listarLivros();
                    case 4 -> listarUsuarios();
                    case 5 -> buscarPorAutor();
                    case 6 -> ordenarPorAno();
                    case 7 -> mostrarEmprestados();
                    case 8 -> gerenciarEmprestimo(true);
                    case 9 -> gerenciarEmprestimo(false);
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.err.println("ERRO: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void cadastrarUsuario() throws BibliotecaException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new BibliotecaException("Este email já está cadastrado no sistema!");
            }
        }
        usuarios.add(new Usuario(nome, email));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        
        livros.add(new Livro(titulo, autor, ano));
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        if (livros.isEmpty()) System.out.println("Nenhum livro cadastrado.");
        livros.forEach(System.out::println);
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) System.out.println("Nenhum usuário cadastrado.");
        usuarios.forEach(System.out::println);
    }

    private static void buscarPorAutor() {
        System.out.print("Nome do autor: ");
        String autor = scanner.nextLine();
        livros.stream()
              .filter(l -> l.getAutor().equalsIgnoreCase(autor))
              .forEach(System.out::println);
    }

    private static void ordenarPorAno() {
        livros.sort(Comparator.comparingInt(Livro::getAno));
        System.out.println("Livros ordenados por ano.");
        listarLivros();
    }

    private static void mostrarEmprestados() {
        livros.stream().filter(Livro::isEmprestado).forEach(System.out::println);
    }

    private static void gerenciarEmprestimo(boolean emprestar) throws BibliotecaException {
        System.out.print("Digite o título exato do livro: ");
        String titulo = scanner.nextLine();
        
        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow(() -> new BibliotecaException("Livro não encontrado!"));

        if (emprestar) {
            if (livro.isEmprestado()) throw new BibliotecaException("Livro já está emprestado!");
            livro.setEmprestado(true);
            System.out.println("Empréstimo realizado!");
        } else {
            if (!livro.isEmprestado()) throw new BibliotecaException("O livro já está na biblioteca!");
            livro.setEmprestado(false);
            System.out.println("Devolução realizada!");
        }
    }
}