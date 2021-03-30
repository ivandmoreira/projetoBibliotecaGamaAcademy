package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;
import br.com.projetoFinal.bibliotecaGama.repository.JpaLivroRepository;
import br.com.projetoFinal.bibliotecaGama.service.CadastroService;
import br.com.projetoFinal.bibliotecaGama.service.LivroService;

public class LivroController {
	private Scanner scanner;
	private LivroService livroService;
	
	public void run() {
		System.out.println("\n## Abriu tela usuarios ##\n");
		Livro livro;
		scanner = new Scanner(System.in);

		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar um Livro");
			System.out.println("2 - Buscar livros cadastrados");
			System.out.println("3 - Buscar livro por id");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				livro = new Livro();
				livro = createBook(livro);
				if(livro != null) {
					System.out.println("Cadastro realizado com sucesso");
				}
				break;
			case 2:
				List<Livro> results = getAllBooks();

				for (Livro result : results) {
					System.out.println(result.getTitulo());
				}

				break;
			case 3:
				System.out.println("Informe o id: ");
				int id = Integer.parseInt(scanner.nextLine());
				
				livro = getBook(id);
				
				if(livro != null) {
					System.out.println(livro.getTitulo());
				}
				
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}
	
	private Livro createBook(Livro livro) {
		livroService = new LivroService();
		return livroService.cadastrarLivro(livro);
	}
	
	public List<Livro> getAllBooks() {
		livroService = new LivroService();
		return livroService.buscarTodos();
	}
	
	public Livro getBook(Integer id) {
		livroService = new LivroService();
		return livroService.buscarPorId(id);
	}
	
}
