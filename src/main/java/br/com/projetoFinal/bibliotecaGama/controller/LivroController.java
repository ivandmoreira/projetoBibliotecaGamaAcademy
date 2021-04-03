package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.service.LivroService;

public class LivroController {
	private Scanner scanner;
	private LivroService livroService;

	public void run() {
		System.out.println("\n## Abriu tela livros ##\n");
		Livro livro;
		scanner = new Scanner(System.in);

		String option;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar um Livro");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = scanner.nextLine();

			switch (option) {
			case "0":
				break;
			case "1":
				livro = new Livro();
				livro = cadastrar(livro);
				if (livro != null) {
					System.out.println("Cadastro realizado com sucesso");
				}
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (!option.equals("0"));

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Livro cadastrar(Livro livro) {
		livroService = new LivroService();
		return livroService.cadastrar(livro);
	}

	public Livro getById(Integer id) {
		livroService = new LivroService();
		return livroService.getById(id);
	}

	public boolean hasExemplares(Integer id) {
		livroService = new LivroService();
		return livroService.hasExemplares(id);
	}

}
