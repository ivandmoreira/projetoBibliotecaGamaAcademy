package br.com.projetoFinal.bibliotecaGama.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.controller.CadastroController;
import br.com.projetoFinal.bibliotecaGama.controller.LivroController;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoItem;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoStatusEnum;
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
		Locacao locacao = jpaLocacaoRepository.select(id);
		jpaLocacaoRepository.fechar();
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

	public Locacao agendarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de agendar locacao\n");

//		######## CASO 1 ########
		// MANIPULACAO DE LIVRO
		LivroController livroController = new LivroController();
		Livro livro = livroController.getBook(132);

		// MANIPULACAO DE LOCACAO ITEM
		LocacaoItem locacaoItem = new LocacaoItem();
		locacaoItem.setLivro(livro);
		locacaoItem.setLocacao(locacao);
//		livro.setLocacaoItem(locacaoItem);
		
		locacaoItem.setDiarias(1);
		
//		// MANIPULACAO DE LOCACAO
//		// define a data de agendamento como hoje
//		LocalDate dataPrevisaoEntrega = LocalDate.now().plusDays(2);
//		locacao.setDataRetirada(dataPrevisaoEntrega);
//
//		locacaoItem.setDataPrevisaoEntrega(dataPrevisaoEntrega);
		
		List<LocacaoItem> list = new ArrayList<LocacaoItem>();
		list.add(locacaoItem);
//		#######################
		
//		
////		######## CASO 2 ########
//		// MANIPULACAO DE LIVRO
//		livroController = new LivroController();
//		livro = livroController.getBook(112);
//		
//		// MANIPULACAO DE LOCACAO ITEM
//		locacaoItem = new LocacaoItem();
//		locacaoItem.setLivro(livro);
//		locacaoItem.setLocacao(locacao);
//		livro.setLocacaoItem(locacaoItem);
//		
//		list.add(locacaoItem);
////		#######################
		
		
		locacao.setLocacaoItem(list);
		
		// MANIPULACAO DE LOCACAO
		// define a data de agendamento como hoje
		LocalDate dataRetirada = LocalDate.now();
		locacao.setDataRetirada(dataRetirada);

		// MANIPULACAO DE LOCACAO
		// define a data de agendamento como hoje
		LocalDate dataAgendamento = LocalDate.now();
		locacao.setDataAgendamento(dataAgendamento);

		// define a data de finalizacao como daqui a 30 dias, ou seja, se nao pegar em
		// 30 dias, solicitacao finalizada
		LocalDate dataFinalizacao = LocalDate.now().plusDays(15);
		locacao.setDataFinalizacao(dataFinalizacao);

		locacao.setStatus(LocacaoStatusEnum.RESERVADA);
		locacao.setValorTotal(0.0);

		CadastroController cadastroController = new CadastroController();
		locacao.setCadastro(cadastroController.getUser(12));

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");

		return locacao;
	}

	public Locacao retirarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de retirar locacao\n");

		LocalDate dataRetirada = LocalDate.now();
		locacao.setDataRetirada(dataRetirada);

		// alterar status para efetivada
		locacao.setStatus(LocacaoStatusEnum.EFETIVADA);

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Retirada efetuada com sucesso!\n");

		return locacao;
	}

	public Locacao devolverLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de entregar locacao\n");

		// List<Livro> listLivro = locacao.getLocacaoItem().getLivros();
		// listLivro.forEach(System.out::println);
		// locacao.getLocacaoItem().getLivros().get(0);
		//// incrementa 1 do exemplar
		// livro.setExemplares(livro.getExemplares() - 1);
		//
		//// decrementa 1 do reservado
		// livro.setReservados(livro.getReservados() + 1);

		// locacao.setStatus(LocacaoStatusEnum.FINALIZADA);

		// jpaLocacaoRepository = new JpaLocacaoRepository();
		// jpaLocacaoRepository.insert(locacao);
		// jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");

		return locacao;
	}

}
