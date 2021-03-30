package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
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
			System.out.println("2 - Imprime pessoas cadastradas");
			System.out.println("3 - Buscar pessoa por id");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				Cadastro cadastro = new Cadastro();
				cadastro = createUser(cadastro);
				if(cadastro != null) {
					System.out.println("Cadastro realizado com sucesso");
				}
				break;
			case 2:
				List<Cadastro> results = getTodosCadastros();

				for (Cadastro result : results) {
					System.out.println(result.getCpf());
				}

				break;
			case 3:
				System.out.println("Informe o id: ");
				int id = Integer.parseInt(scanner.nextLine());
				
				cadastro = getUsuario(id);
				
				if(cadastro != null) {
					System.out.println(cadastro.getCpf());
				}
				
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}
	
	private Cadastro createUser(Cadastro cadastro) {
		cadastroService = new CadastroService();
		return cadastroService.cadastrarUsuario(cadastro);
	}
	
	public List<Cadastro> getTodosCadastros() {
		cadastroService = new CadastroService();
		return cadastroService.buscarTodos();
	}
	
	public Cadastro getUsuario(Integer id) {
		cadastroService = new CadastroService();
		return cadastroService.buscarPorId(id);
	}
	
}
