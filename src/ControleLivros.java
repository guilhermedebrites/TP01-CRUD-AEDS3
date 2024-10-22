import java.util.Scanner;

public class ControleLivros {
    private Scanner scanner;
    private VisaoLivros visaoLivros;
    private ArquivoLivro arquivoLivro;

    public ControleLivros(Scanner scanner) {
        this.scanner = scanner;
        this.visaoLivros = new VisaoLivros(scanner);
        try {
            this.arquivoLivro = new ArquivoLivro("livros.db", Livro.class.getConstructor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        while (true) {
            System.out.println("PUCBOOK 1.0");
            System.out.println("-----------");
            System.out.println("> Início > Livros");
            System.out.println("1) Incluir");
            System.out.println("2) Buscar");
            System.out.println("3) Alterar");
            System.out.println("4) Excluir");
            System.out.println("0) Retornar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    excluir();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void incluir() {
        Livro livro = visaoLivros.leLivro();
        try {
            int id = arquivoLivro.create(livro);
            System.out.println("Livro incluído com ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscar() {
        System.out.print("Digite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Livro livro = arquivoLivro.read(id);
            if (livro != null) {
                visaoLivros.mostraLivro(livro);
            } else {
                System.out.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alterar() {
        System.out.print("Digite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Livro livro = arquivoLivro.read(id);
            if (livro != null) {
                Livro novoLivro = visaoLivros.leLivro();
                novoLivro.setId(id);
                if (arquivoLivro.update(novoLivro)) {
                    System.out.println("Livro alterado com sucesso!");
                } else {
                    System.out.println("Erro ao alterar o livro!");
                }
            } else {
                System.out.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluir() {
        System.out.print("Digite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            if (arquivoLivro.delete(id)) {
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir o livro!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}