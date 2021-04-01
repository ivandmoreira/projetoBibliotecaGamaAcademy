package br.com.projetoFinal.bibliotecaGama.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoStatusEnum;
import br.com.projetoFinal.bibliotecaGama.service.LocacaoService;

public class LocacaoController {
	private Scanner scanner;
	private LocacaoService locacaoService;

	public void run() {
		System.out.println("\n## Abriu tela locacao ##\n");

		Locacao locacao;
		int id;

		scanner = new Scanner(System.in);

		int option = 0;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Agendar uma locacao");
			System.out.println("2 - Retirar uma locacao");
			System.out.println("3 - Devolver uma locacao");
			System.out.println("4 - Exibir todos as locacoes cadastradas");
			System.out.println("5 - Buscar locacao por id");
			System.out.println("6 - Buscar por Data de Agendamento ");
			System.out.println("7 - Buscar por Data de Retirada ");
			System.out.println("8 - Buscar por Status de locacao ");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 0:
				break;
			case 1:
				locacao = new Locacao();

				CadastroController cadastroController = new CadastroController();
				Cadastro cadastro = cadastroController.getUser(32);

				LivroController livroController = new LivroController();
				Livro livro = livroController.getBook(222);

				List<Livro> listLivro = new ArrayList<>();
				listLivro.add(livro);

				livro = livroController.getBook(232);
				listLivro.add(livro);

				locacao = scheduleRental(locacao, cadastro, listLivro);

				if (locacao != null) {
					System.out.println("Agendamento realizado com sucesso");
				}
				break;
			case 2:
				System.out.println("Informe o id: ");
				// id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(442);

				if (locacao != null) {
					System.out.println(locacao.getId());
					System.out.println(locacao.getLocacaoItem().toArray());
					locacao = takeRental(locacao);
				}

				break;
			case 3:
				System.out.println("Informe o id: ");
				// id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(442);

				if (locacao != null) {
					System.out.println(locacao.getId());
					locacao = returnRental(locacao);
				}

				break;
			case 4:
				List<Locacao> results = getAllRents();

				for (Locacao result : results) {
					System.out.println(result.getStatus());
				}

				break;
			case 5:
				System.out.print("Informe o id: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(id);

				if (locacao != null) {
					System.out.println(locacao.getLocacaoItem());
				}

				break;
			case 6:
				System.out.print("Informe a data no formato aaaa-mm-dd: ");

				String data = scanner.nextLine();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dataAgendamento = LocalDate.parse(data, format);

				locacao = getDateRent(dataAgendamento);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			case 7:
				System.out.print("Informe a data no formato aaaa-mm-dd: ");

				String dataRetorno = scanner.nextLine();
				DateTimeFormatter formatReturn = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dataReturn = LocalDate.parse(dataRetorno, formatReturn);

				locacao = getDateReturn(dataReturn);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			case 8:
				System.out.println("Status disponiveis:");

				System.out.println("1 - " + LocacaoStatusEnum.EFETIVADA);
				System.out.println("2 - " + LocacaoStatusEnum.FINALIZADA);
				System.out.println("3 - " + LocacaoStatusEnum.RESERVADA);

				System.out.print("Informe o digito correspondente: ");
				id = Integer.parseInt(scanner.nextLine());

				switch (id) {
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				default:
					break;
				}

				// locacao = getRent(-id);
				//
				// if (locacao != null) {
				// System.out.println(locacao.getLocacaoItem().getId());
				// }

				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Locacao scheduleRental(Locacao locacao, Cadastro cadastro, List<Livro> listLivro) {
		locacaoService = new LocacaoService();
		return locacaoService.agendarLocacao(locacao, cadastro, listLivro);
	}

	private Locacao takeRental(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.retirarLocacao(locacao);
	}

	private Locacao returnRental(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.devolverLocacao(locacao);
	}

	public List<Locacao> getAllRents() {
		locacaoService = new LocacaoService();
		return locacaoService.buscarTodos();
	}

	public Locacao getRent(Integer id) {
		locacaoService = new LocacaoService();
		return locacaoService.buscarPorId(id);
	}

	public Locacao getDateRent(LocalDate data) {
		locacaoService = new LocacaoService();
		return locacaoService.buscarPorDataLocacao(data);
	}

	public Locacao getDateReturn(LocalDate data) {
		locacaoService = new LocacaoService();
		return locacaoService.buscarPorDataRetirada(data);
	}
}
