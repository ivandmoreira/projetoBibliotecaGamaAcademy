package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Endereco;
import br.com.projetoFinal.bibliotecaGama.model.Telefone;
import br.com.projetoFinal.bibliotecaGama.model.TelefoneTipo;
import br.com.projetoFinal.bibliotecaGama.repository.JpaCadastroRepository;
import br.com.projetoFinal.bibliotecaGama.service.CadastroService;
import br.com.projetoFinal.bibliotecaGama.service.EnderecoService;

public class CadastroController {
	private Scanner scanner;
		
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
				cadastrar();
				break;
			case 2:
				printAll();
				break;
			case 3:
				buscarLogin();
				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private void printAll() {
		System.out.println("imprimir todas as pessoas cadastradas\n");

		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		List<Cadastro> results = jpaCadastroRepository.selectAll();

		for (Cadastro result : results) {
			System.out.println(result.getCpf());
		}

		jpaCadastroRepository.fechar();
	}
	
	private void buscarLogin() {
//		System.out.println("Informe o id: ");
//		int idSearch = Integer.parseInt(scanner.nextLine());
		System.out.print("Informe o login: ");
		String cpfSearch = scanner.nextLine();

		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		Cadastro auxCadastro = jpaCadastroRepository.selectLogin(cpfSearch);
		System.out.println("retorno: "+auxCadastro.getNome());

		jpaCadastroRepository.fechar();
	}
	
	private void cadastrar() {
		System.out.println("tela de cadastrar pessoas\n");
		int option = 0;

		String cpf = null;
		do {
			if (cpf != null)
				System.out.println("Cpf inválido, por favor digite novamente..");
			System.out.print("Digite seu cpf: ");
			cpf = scanner.nextLine();
		} while (!CadastroService.isCpfValid(cpf));

		Cadastro cadastro = new Cadastro();
		cadastro.setCpf(cpf);

		System.out.print("Digite seu nome: ");
		String nome = scanner.nextLine();
		cadastro.setNome(nome);

		boolean bolEmail = false;

		while (!bolEmail) {
			bolEmail = true;

			String email = null;
			do {
				if (email != null)
					System.out.println("E-mail inválido, por favor digite novamente..");
				System.out.print("Digite seu e-mail: ");
				email = scanner.nextLine();
			} while (!CadastroService.isEmailValid(email));
			cadastro.addEmail(email);

			System.out.print("Você deseja adicionar outro e-mail? (S ou N): ");
			String aux = scanner.nextLine();
			if (aux != null && aux.equalsIgnoreCase("s") || aux.equalsIgnoreCase("sim")) {
				bolEmail = false;
			}

		}

		boolean bolTelefone = false;

		while (!bolTelefone) {
			bolTelefone = true;

			String telefone = null;
			do {
				if (telefone != null)
					System.out.println("Telefone inválido, por favor digite novamente..");
				System.out.print("Digite seu telefone: ");
				telefone = scanner.nextLine();
			} while (!CadastroService.isTelefoneValid(telefone));
			cadastro.addTelefone(new Telefone(TelefoneTipo.CELULAR, Long.parseLong(telefone)));

			System.out.print("Você deseja adicionar outro numero? (S ou N): ");
			String aux = scanner.nextLine();
			if (aux != null && aux.equalsIgnoreCase("s") || aux.equalsIgnoreCase("sim")) {
				bolTelefone = false;
			}

		}

		System.out.println("\nAgora... vamos cadastrar seu endereco!");
		System.out.print("Digite seu cep: ");
		String cep = scanner.nextLine();

		Gson gson = new Gson();
		Endereco endereco = null;
		try {
			endereco = gson.fromJson(EnderecoService.viaCep(cep), Endereco.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cadastro.setEndereco(endereco);

		System.out.println("\nPara finalizar... seu login poderá ser feito de 3 formas:");
		do {
			System.out.println("1 - com Cpf");
			System.out.println("2 - com Telefone");
			System.out.println("3 - com Apelido de até 20 caracteres");
			System.out.println("_______________________");
			System.out.print("Digite sua melhor forma de logar: ");
			option = Integer.parseInt(scanner.nextLine());
		} while (!(option == 1 | option == 2 | option == 3));

		String login = null;
		if (option == 1) {
			System.out.println("\nForma selecionada: cpf");
			login = cadastro.getCpf();
		} else if (option == 2) {
			System.out.println("\nForma selecionada: telefone");
			System.out.println("Telefones disponiveis:");

			int index = 0;
			for (Telefone tel : cadastro.getTelefones()) {
				System.out.println((++index) + " - " + tel.getNumero());
			}

			do {
				System.out.print("Digite a opção correspondente: ");
				option = Integer.parseInt(scanner.nextLine());
			} while (!(option >= 1 | option <= index));

			login = cadastro.getTelefones().get(--option).getNumero().toString();
		} else {
			System.out.println("\nForma selecionada: apelido");

			do {
				if(login != null) {
					System.out.println("Apelido com mais de 20 caracteres ou menos que 3.");
				}
				System.out.print("Digite seu apelido com no máximo 20 caracteres: ");
				login = scanner.nextLine();
			} while (!CadastroService.isLoginApelidoValid(login));
		}

		cadastro.setLogin(login);

		System.out.print("Digite sua senha: ");
		String senha = scanner.nextLine();
		cadastro.setSenha(senha);

		JpaCadastroRepository jpaCadastroRepository = new JpaCadastroRepository();
		jpaCadastroRepository.insert(cadastro);
		jpaCadastroRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
	}
}
