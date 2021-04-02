package br.com.projetoFinal.bibliotecaGama.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoItem;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoStatusEnum;
import br.com.projetoFinal.bibliotecaGama.repository.JpaLivroRepository;
import br.com.projetoFinal.bibliotecaGama.repository.JpaLocacaoRepository;

public class LocacaoService {
	private JpaLocacaoRepository jpaLocacaoRepository;
	private Scanner scanner;

	public List<Locacao> buscarTodos() {
		jpaLocacaoRepository = new JpaLocacaoRepository();
		List<Locacao> locacao = jpaLocacaoRepository.selectAll();
		jpaLocacaoRepository.fechar();
		return locacao;
	}

	public Locacao buscarPorId(Integer id) {
		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.select(id);
		Locacao locacao = jpaLocacaoRepository.select(id);
		jpaLocacaoRepository.fechar();

		LocalDate dataHoje = LocalDate.now();

		double valorTotal = 0.0;
		boolean alterou = false;

		for (LocacaoItem locacaoItem : locacao.getLocacaoItem()) {
			if (locacaoItem.getDataEntrega() == null) {
				alterou = true;
				locacaoItem.setDiarias(calcDiarias(locacao, dataHoje));
				locacaoItem.setValorLocacao(calcValorLocacao(locacaoItem));
				valorTotal += locacaoItem.getValorLocacao();
			}
		}

		if (alterou) {
			// set valor total
			locacao.setValorTotal(valorTotal);
			jpaLocacaoRepository = new JpaLocacaoRepository();
			jpaLocacaoRepository.update(locacao);
			jpaLocacaoRepository.fechar();
		}

		return locacao;
	}

	public Locacao buscarPorDataLocacao(LocalDate data) {
		jpaLocacaoRepository = new JpaLocacaoRepository();
		Locacao locacao = jpaLocacaoRepository.selectDataLocacaoRetirada(data, null);
		jpaLocacaoRepository.fechar();
		return locacao;
	}

	public Locacao buscarPorDataRetirada(LocalDate data) {
		jpaLocacaoRepository = new JpaLocacaoRepository();
		Locacao locacao = jpaLocacaoRepository.selectDataLocacaoRetirada(data, null);
		jpaLocacaoRepository.fechar();
		return locacao;
	}

	public Locacao agendarLocacao(Locacao locacao, Cadastro cadastro, List<Livro> listLivro) {
		scanner = new Scanner(System.in);

		System.out.println("tela de agendar locacao\n");

		List<LocacaoItem> listLocacaoItem = new ArrayList<>();

		for (Livro livro : listLivro) {

			LocacaoItem locacaoItem = new LocacaoItem();
			locacaoItem.setLivro(livro);
			locacaoItem.setLocacao(locacao);

			locacaoItem.setDiarias(0L);
			locacaoItem.setValorDiaria(livro.getValorDiaria());

			livro.setExemplares(decrementarExemplares(livro));
			livro.setReservados(incrementarReservados(livro));

			locacaoItem.setValorLocacao(calcValorLocacao(locacaoItem));

			listLocacaoItem.add(locacaoItem);

			JpaLivroRepository jpaLivroRepository = new JpaLivroRepository();
			jpaLivroRepository.update(livro);
			jpaLivroRepository.fechar();

		}

		locacao.setLocacaoItem(listLocacaoItem);

		// #### MANIPULACAO DE LOCACAO ####
		// define a data de agendamento como hoje
		LocalDate dataAgendamento = LocalDate.now();
		locacao.setDataAgendamento(dataAgendamento);

		// define a data de finalizacao como daqui a 3 dias, ou seja
		// Se o livro nao for retirado em 3 dias, a locacao sera finalizada
		LocalDate dataFinalizacao = LocalDate.now().plusDays(3);
		locacao.setDataFinalizacao(dataFinalizacao);

		locacao.setStatus(LocacaoStatusEnum.RESERVADA);
		locacao.setValorTotal(0.0);

		locacao.setCadastro(cadastro);

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");

		return locacao;
	}

	private double calcValorLocacao(LocacaoItem locacaoItem) {
		return locacaoItem.getDiarias() * locacaoItem.getValorDiaria();
	}

	public Locacao retirarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de retirar locacao\n");

		LocalDate dataHoje = LocalDate.now();
		locacao.setDataRetirada(dataHoje);

		// setar previsão de entrega
		LocalDate dataPrevisaoEntrega = LocalDate.now().plusDays(5);

		double valorTotal = 0.0;

		for (LocacaoItem locacaoItem : locacao.getLocacaoItem()) {
			locacaoItem.setDataPrevisaoEntrega(dataPrevisaoEntrega);
			locacaoItem.setDiarias(calcDiarias(locacao, dataHoje));
			locacaoItem.setValorLocacao(calcValorLocacao(locacaoItem));
			valorTotal += locacaoItem.getValorLocacao();
		}

		// seta uma previsao de data de finalizacao para o dia de previsao de entrega.
		locacao.setDataFinalizacao(dataPrevisaoEntrega);

		// alterar status para efetivada
		locacao.setStatus(LocacaoStatusEnum.EFETIVADA);

		// set valor total
		locacao.setValorTotal(valorTotal);

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.update(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Retirada efetuada com sucesso!\n");

		return locacao;
	}

	public Locacao devolverLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de entregar locacao\n");

		LocalDate dataHoje = LocalDate.now().plusDays(15);

		double valorTotal = 0.0;

		for (LocacaoItem locacaoItem : locacao.getLocacaoItem()) {

			locacaoItem.setDataEntrega(dataHoje);
			locacaoItem.setDiarias(calcDiarias(locacao, dataHoje));
			locacaoItem.setValorLocacao(calcValorLocacao(locacaoItem));

			Livro livro = locacaoItem.getLivro();

			livro.setExemplares(incrementarExemplares(livro));
			livro.setReservados(decrementarReservados(livro));

			valorTotal += locacaoItem.getValorLocacao();

			JpaLivroRepository jpaLivroRepository = new JpaLivroRepository();
			jpaLivroRepository.update(livro);
			jpaLivroRepository.fechar();
		}

		locacao.setStatus(LocacaoStatusEnum.FINALIZADA);
		locacao.setValorTotal(valorTotal);
		locacao.setDataFinalizacao(dataHoje);

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.update(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Locacao finalizada com sucesso!\n");

		return locacao;
	}

	private int decrementarExemplares(Livro livro) {
		return livro.getExemplares() - 1;
	}

	private int incrementarReservados(Livro livro) {
		return livro.getReservados() + 1;
	}

	private int decrementarReservados(Livro livro) {
		return livro.getReservados() - 1;
	}

	private int incrementarExemplares(Livro livro) {
		return livro.getExemplares() + 1;
	}

	private long calcDiarias(Locacao locacao, LocalDate data) {
		return ChronoUnit.DAYS.between(locacao.getDataRetirada(), data);
	}

}
