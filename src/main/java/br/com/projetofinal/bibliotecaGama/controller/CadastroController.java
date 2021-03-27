package br.com.projetofinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;
import br.com.projetofinal.bibliotecaGama.repository.JpaRepository;
import br.com.projetofinal.bibliotecaGama.service.EnderecoService;
import br.com.projetofinal.bibliotecaGama.util.Validadores;

public class CadastroController {
	private Scanner scanner;
	
	public void run() {

		JpaRepository jpaRepository;
		
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
				System.out.print("Digite o login: ");
				String login 	= scanner.nextLine();
				System.out.print("Digite a senha: ");
				String senha 	= scanner.nextLine();
				System.out.println("Para finalizar.. vamos cadastrar seu endereco!");
				System.out.print("Digite seu cep: ");
				String cep = scanner.nextLine();
				
				Gson gson = new Gson();

				Endereco endereco = null;
				
				try {
					endereco = gson.fromJson(EnderecoService.viaCep(cep), Endereco.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(endereco);

				Cadastro cadastro = new Cadastro(cpf, nome, email, telefone, login, senha,endereco);

				jpaRepository = new JpaRepository();
				jpaRepository.insert(endereco);
				jpaRepository.insert(cadastro);
				jpaRepository.fechar();
				
				System.out.println("Cadastro efetuado com sucesso!\n");
				break;
			case 2:
				System.out.println("imprimir todas as pessoas cadastradas\n");

				jpaRepository = new JpaRepository();
				List<Cadastro> results = jpaRepository.selectAll();
				
				for (Cadastro result : results) {
				      System.out.println(result.getCpf());
				}
				
				jpaRepository.fechar();
				break;
			case 3:
				System.out.println("Informe o id: ");
				int idSearch = Integer.parseInt(scanner.nextLine());
				
				jpaRepository = new JpaRepository();
				Cadastro auxCadastro = jpaRepository.select(idSearch);
				System.out.println(auxCadastro.getNome());
				
				jpaRepository.fechar();
				
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while(option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}
}
