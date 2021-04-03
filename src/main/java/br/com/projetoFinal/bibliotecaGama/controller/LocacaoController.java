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
import br.com.projetoFinal.bibliotecaGama.util.Validadores;

public class LocacaoController {
	private Scanner scanner;
	private LocacaoService locacaoService;

	public void run() {
		System.out.println("\n## Abriu tela locacao ##\n");

		Locacao locacao;
		int id;

		scanner = new Scanner(System.in);

		String option;

		do {

			System.out.println("## Escolha uma das opcoes abaixo ##");
			System.out.println("1 - Agendar uma locacao");
			System.out.println("2 - Retirar uma locacao");
			System.out.println("3 - Devolver uma locacao");
			System.out.println("4 - Buscar locacao por id");
			System.out.println("5 - Buscar por Data de Agendamento ");
			System.out.println("6 - Buscar por Data de Retirada ");
			System.out.println("7 - Buscar por Status de locacao ");
			System.out.println("0 - Voltar tela");
			System.out.println("_______________________");
			System.out.print("Digite sua opcao: ");

			option = scanner.nextLine();

			switch (option) {
			case "0":
				break;
			case "1":
				locacao = new Locacao();

				System.out.print("id do usuario: ");
				id = Integer.parseInt(scanner.nextLine());

				CadastroController cadastroController = new CadastroController();
				Cadastro cadastro = cadastroController.getById(id);
				if (cadastro == null) {
					System.out.println("Cadastro nao encontrado");
					break;
				}

				List<Livro> listLivro = new ArrayList<>();
				LivroController livroController = new LivroController();

				boolean verificador = false;
				while (!verificador) {
					verificador = true;

					System.out.print("id do livro: ");
					id = Integer.parseInt(scanner.nextLine());

					if (!livroController.hasExemplares(id)) {
						System.out.println("Esse livro nao existe ou esta sem exemplares disponiveis.");
					} else {
						Livro livro = livroController.getById(id);
						listLivro.add(livro);
					}

					System.out.print("Você deseja adicionar outro livro? (S ou N): ");
					String aux = scanner.nextLine();
					if (aux != null && aux.equalsIgnoreCase("s") || aux.equalsIgnoreCase("sim")) {
						verificador = false;
					}

				}

				if (listLivro != null) {
					locacao = agendar(locacao, cadastro, listLivro);

					if (locacao != null) {
						System.out.println("Agendamento realizado com sucesso");
						System.out.println(locacao.toString());
					}
				} else {
					System.err.println("Nenhum livro foi selecionado, agendamento cancelado.");
				}

				break;
			case "2":
				System.out.println("id da locacao: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getById(id);

				if (locacao.getStatus() == LocacaoStatusEnum.EFETIVADA) {
					System.out.println("Livros ja retirados.");
					break;
				}

				if (locacao != null) {
					locacao = retirar(locacao);
					System.out.println(locacao.toString());
				}

				break;
			case "3":
				System.out.println("Informe o id: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getById(id);

				if (locacao != null) {
					locacao = devolver(locacao);
				}

				break;
			case "4":
				System.out.print("Informe o id: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getById(id);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			case "5":
				System.out.print("Formato aaaa-MM-dd\nInforme a data: ");

				String data = scanner.nextLine();
				if (!Validadores.data(data)) {
					System.err.println("Data invalida ou escrita no formato incorreto");
					break;
				}
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dataAgendamento = LocalDate.parse(data, format);

				locacao = getByDateLocacao(dataAgendamento);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			case "6":
				System.out.print("Formato aaaa-MM-dd\nInforme a data: ");

				String dataRetorno = scanner.nextLine();
				if (!Validadores.data(dataRetorno)) {
					System.err.println("Data invalida ou escrita no formato incorreto");
					break;
				}
				DateTimeFormatter formatReturn = DateTimeFormatter.ofPattern("dd-mm-yyyy");
				LocalDate dataReturn = LocalDate.parse(dataRetorno, formatReturn);

				locacao = getByDateRetirada(dataReturn);

				if (locacao != null) {
					System.out.println(locacao.getId());
				}

				break;
			case "7":
				System.out.println("Status disponiveis:");

				System.out.println("1 - " + LocacaoStatusEnum.RESERVADA);
				System.out.println("2 - " + LocacaoStatusEnum.EFETIVADA);
				System.out.println("3 - " + LocacaoStatusEnum.FINALIZADA);

				System.out.print("Informe o digito correspondente: ");
				option = scanner.nextLine();

				List<Locacao> listLocacao = null;
				switch (option) {
				case "1":
					listLocacao = getByStatus(LocacaoStatusEnum.RESERVADA);
					break;
				case "2":
					listLocacao = getByStatus(LocacaoStatusEnum.EFETIVADA);
					break;
				case "3":
					listLocacao = getByStatus(LocacaoStatusEnum.FINALIZADA);
					break;
				default:
					System.out.println("Opcao nao disponivel\n");
					break;
				}

				if (listLocacao != null) {
					for (Locacao locacao2 : listLocacao) {
						System.out.println(locacao2.toString());
					}
				}

				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (!option.equals("0"));

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Locacao agendar(Locacao locacao, Cadastro cadastro, List<Livro> listLivro) {
		locacaoService = new LocacaoService();
		return locacaoService.agendar(locacao, cadastro, listLivro);
	}

	private Locacao retirar(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.retirar(locacao);
	}

	private Locacao devolver(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.devolver(locacao);
	}

	public Locacao getById(Integer id) {
		locacaoService = new LocacaoService();
		return locacaoService.getById(id);
	}

	public Locacao getByDateLocacao(LocalDate data) {
		locacaoService = new LocacaoService();
		return locacaoService.getByDataLocacao(data);
	}

	public Locacao getByDateRetirada(LocalDate data) {
		locacaoService = new LocacaoService();
		return locacaoService.getByDataRetirada(data);
	}

	public List<Locacao> getByStatus(LocacaoStatusEnum status) {
		locacaoService = new LocacaoService();
		return locacaoService.getByStatus(status);
	}
}
