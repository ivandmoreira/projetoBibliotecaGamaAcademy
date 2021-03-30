package br.com.projetoFinal.bibliotecaGama.controller;

import java.util.List;
import java.util.Scanner;

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
				locacao = scheduleRental(locacao);
				if (locacao != null) {
					System.out.println("Agendamento realizado com sucesso");
				}
				break;
			case 2:
				System.out.println("Informe o id: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(id);

				if (locacao != null) {
					System.out.println(locacao.getId());
					locacao = withdrawRental(locacao);
				}

				break;
			case 3:
				System.out.println("Informe o id: ");
				id = Integer.parseInt(scanner.nextLine());

				locacao = getRent(id);

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
					System.out.println(locacao.getLocacaoItem().getId());
				}

				break;
			case 6:
				System.out.print("Informe a data no formato aaaa-mm-dd: ");
				id = Integer.parseInt(scanner.nextLine());

//				locacao = getRent(id);
//
//				if (locacao != null) {
//					System.out.println(locacao.getLocacaoItem().getId());
//				}

				break;
			case 7:
				System.out.print("Informe a data no formato aaaa-mm-dd: ");
				id = Integer.parseInt(scanner.nextLine());

//				locacao = getRent(id);
//
//				if (locacao != null) {
//					System.out.println(locacao.getLocacaoItem().getId());
//				}

				break;
			case 8:
				System.out.println("Status disponíveis:");

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
				
//				locacao = getRent(-id);
//
//				if (locacao != null) {
//					System.out.println(locacao.getLocacaoItem().getId());
//				}

				break;
			default:
				System.out.println("Opcao nao disponivel\n");
				break;
			}

		} while (option != 0);

		System.out.println("\n## Fechou tela usuarios ##\n");
	}

	private Locacao scheduleRental(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.agendarLocacao(locacao);
	}

	private Locacao withdrawRental(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.retirarLocacao(locacao);
	}

	private Locacao returnRental(Locacao locacao) {
		locacaoService = new LocacaoService();
		return locacaoService.retirarLocacao(locacao);
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
