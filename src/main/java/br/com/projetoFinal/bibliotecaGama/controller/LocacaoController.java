package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.service.LocacaoService;

public class LocacaoController {
	private Scanner scanner;
	private LocacaoService locacaoService;

	public void run() {
		System.out.println("\n## Abriu tela locacao ##\n");
		Locacao locacao;
		scanner = new Scanner(System.in);

		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Cadastrar uma locacao");
			System.out.println("2 - Exibir todos as locacoes cadastradas");
			System.out.println("3 - Buscar locacao por id");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				locacao = new Locacao();
				locacao = createRent(locacao);
				if (locacao != null) {
					System.out.println("Cadastro realizado com sucesso");
				}
				break;
			case 2:
				List<Locacao> results = getAllRents();

				for (Locacao result : results) {
					System.out.println(result.getStatus());
				}

				break;
			case 3:
				System.out.println("Informe o id: ");
				int id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(id);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Locacao createRent(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.cadastrarLocacao(locacao);
	}

	public List<Locacao> getAllRents() {
		locacaoService = new LocacaoService();
		return locacaoService.buscarTodos();
	}

	public Locacao getRent(Integer id) {
		locacaoService = new LocacaoService();
		return locacaoService.buscarPorId(id);
	}
}
