package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.service.CadastroService;

public class CadastroController {
	private Scanner scanner;
	private CadastroService cadastroService;

	public void run() {
		System.out.println("\n## Abriu tela usuarios ##\n");

		scanner = new Scanner(System.in);

		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar usuario");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				Cadastro cadastro = new Cadastro();
				cadastro = cadastrar(cadastro);
				if (cadastro != null) {
					System.out.println("Cadastro realizado com sucesso");
				}
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Cadastro cadastrar(Cadastro cadastro) {
		cadastroService = new CadastroService();
		return cadastroService.cadastrar(cadastro);
	}

	public Cadastro getById(Integer id) {
		cadastroService = new CadastroService();
		return cadastroService.buscarPorId(id);
	}

}
