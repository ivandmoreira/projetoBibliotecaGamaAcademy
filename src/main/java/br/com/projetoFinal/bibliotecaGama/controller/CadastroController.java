package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Endereco;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;
import br.com.projetoFinal.bibliotecaGama.service.EnderecoService;
import br.com.projetoFinal.bibliotecaGama.util.Validadores;

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

				String cpf = null;
				do {
					if(cpf != null)
						System.out.println("Cpf inválido, por favor digite novamente..");
					System.out.print("Digite seu cpf: ");
					cpf = scanner.nextLine();
				} while (!Validadores.isCpf(cpf));
				
				
				System.out.print("Digite seu nome: ");
				String nome 	= scanner.nextLine();
				System.out.print("Digite seu email: ");
				String email 	= scanner.nextLine();
				System.out.print("Digite o telefone: ");
				String telefone = scanner.nextLine();
				System.out.println("\nAgora... vamos cadastrar seu endereco!");
				System.out.print("Digite seu cep: ");
				String cep = scanner.nextLine();
				
				System.out.println("\nPara finalizar... seu login poderá ser feito de 3 formas:");
				do {
					System.out.println("1 - com Cpf");
					System.out.println("2 - com Telefone");
					System.out.println("3 - com Apelido de até 20 caracteres");
					System.out.println("_______________________");
					System.out.print("Digite sua melhor forma de logar: ");
					option = Integer.parseInt(scanner.nextLine());
				} while (!(option == 1 | option == 2 | option == 3));
				
				String login;
				if(option == 1) {
					System.out.println("Forma selecionada: cpf");
					login = cpf;
				} else if(option == 2) {
					System.out.println("Forma selecionada: telefone");
					login = telefone;
				}
				else {
					System.out.println("Forma selecionada: apelido");
					System.out.print("Digite seu apelido: ");
					login 	= scanner.nextLine();
				}
				
				System.out.print("Digite sua senha: ");
				String senha 	= scanner.nextLine();
				
				Gson gson = new Gson();
				Endereco endereco = null;				
				try {
					endereco = gson.fromJson(EnderecoService.viaCep(cep), Endereco.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Cadastro cadastro = new Cadastro(cpf, nome, email, telefone, login, senha, endereco);

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
