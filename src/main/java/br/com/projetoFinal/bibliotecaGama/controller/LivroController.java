package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;
import br.com.projetoFinal.bibliotecaGama.repository.JpaLivroRepository;

public class LivroController {
	private Scanner scanner;

	public void run() {

		JpaLivroRepository jpaLivroRepository;

		System.out.println("\n## Abriu tela de Livros ##\n");

		scanner = new Scanner(System.in);

		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar um Livro");
			System.out.println("2 - Buscar livros cadastrados");
			System.out.println("3 - Buscar livro por id");
			System.out.println("0 - Sair do programa");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				System.out.println("tela de cadastrar livros\n");
				System.out.print("Digite o isbn: ");
				String isbn = scanner.nextLine();
				System.out.print("Digite o titulo: ");
				String titulo = scanner.nextLine();
				System.out.print("Digite o valor da diaria: ");
				String valorDiaria = scanner.nextLine();
				System.out.print("Digite a quantidade de exemplares: ");
				String exemplares = scanner.nextLine();

				Double vd = Double.valueOf(valorDiaria);
				Integer ex = Integer.valueOf(exemplares);

				Livro livro = new Livro(isbn, titulo, vd, ex, 0);

				jpaLivroRepository = new JpaLivroRepository();
				jpaLivroRepository.insert(livro);
				jpaLivroRepository.fechar();

				System.out.println("Cadastro efetuado com sucesso!\n");
				break;
			case 2:
				System.out.println("imprimir todos os Livros cadastrados\n");

				jpaLivroRepository = new JpaLivroRepository();
				List<Livro> results = jpaLivroRepository.selectAll();

				for (Livro result : results) {
					System.out.println(result.getIsbn());
				}

				jpaLivroRepository.fechar();
				break;
			case 3:
				System.out.println("Informe o id: ");
				int idSearch = Integer.parseInt(scanner.nextLine());

				jpaLivroRepository = new JpaLivroRepository();
				Livro auxLivro = jpaLivroRepository.select(idSearch);
				System.out.println(auxLivro.getTitulo());

				jpaLivroRepository.fechar();

				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela de livros ##\n");
	}
}