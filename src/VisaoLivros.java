import java.util.Scanner;

public class VisaoLivros {
    private Scanner scanner;

    public VisaoLivros(Scanner scanner) {
        this.scanner = scanner;
    }

    public Livro leLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        System.out.print("Digite a categoria do livro: ");
        int categoriaId = scanner.nextInt();
        scanner.nextLine();

        return new Livro(0, titulo, Integer.parseInt(isbn), categoriaId);
    }

    public void mostraLivro(Livro livro) {
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("ISBN: " + livro.getISBN());
        System.out.println("Categoria: " + livro.getIdCategoria());
    }
}