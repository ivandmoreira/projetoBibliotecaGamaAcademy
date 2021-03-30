package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;

public class CadastroController {
	private Scanner scanner;
	
	public void run() {

		JpaCadastroRepository jpaCadastroRepository;
		
		System.out.println("\n## Abriu tela usuarios ##\n");

		scanner = new Scanner(System.in);
		
		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar usuario");
			System.out.println("2 - Imprime pessoas cadastradas");
			System.out.println("3 - Buscar pessoa por id");
			System.out.println("0 - Sair do programa");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");
			
			option = Integer.parseInt(scanner.nextLine());

			switch(option) {
			case 0: break;
			case 1:
				System.out.println("tela de cadastrar pessoas\n");
				System.out.print("Digite seu cpf: ");
				String cpf 		= scanner.nextLine();
				System.out.print("Digite seu nome: ");
				String nome 	= scanner.nextLine();
				System.out.print("Digite seu email: ");
				String email 	= scanner.nextLine();
				System.out.print("Digite o telefone: ");
				String telefone = scanner.nextLine();
				System.out.print("Digite o login: ");
				String login 	= scanner.nextLine();
				System.out.print("Digite a senha: ");
				String senha 	= scanner.nextLine();

				Cadastro cadastro = new Cadastro(cpf, nome, email, telefone, login, senha);

				jpaCadastroRepository = new JpaCadastroRepository();
				jpaCadastroRepository.insert(cadastro);
				jpaCadastroRepository.fechar();
				
				System.out.println("Cadastro efetuado com sucesso!\n");
				break;
			case 2:
				System.out.println("imprimir todas as pessoas cadastradas\n");

				jpaCadastroRepository = new JpaCadastroRepository();
				List<Cadastro> results = jpaCadastroRepository.selectAll();
				
				for (Cadastro result : results) {
				      System.out.println(result.getCpf());
				}
				
				jpaCadastroRepository.fechar();
				break;
			case 3:
				System.out.println("Informe o id: ");
				int idSearch = Integer.parseInt(scanner.nextLine());
				
				jpaCadastroRepository = new JpaCadastroRepository();
				Cadastro auxCadastro = jpaCadastroRepository.select(idSearch);
				System.out.println(auxCadastro.getNome());
				
				jpaCadastroRepository.fechar();
				
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while(option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}
}
