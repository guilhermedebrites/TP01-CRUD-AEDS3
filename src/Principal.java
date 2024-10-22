import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControleLivros controleLivros = new ControleLivros(scanner);

        while (true) {
            System.out.println("PUCBOOK 1.0");
            System.out.println("-----------");
            System.out.println("> Início");
            System.out.println("1) Categorias");
            System.out.println("2) Livros");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Chamar controle de categorias
                    break;
                case 2:
                    controleLivros.menu();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}