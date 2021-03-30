package br.com.projetoFinal.bibliotecaGama.service;

import java.sql.Date;
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

	public Locacao agendarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);

		System.out.println("tela de agendar locacao\n");

//		MANIPULAÇÃO DE LIVRO
		LivroController livroController = new LivroController();
		Livro livro = livroController.getBook(192);
		
		if(livro.getExemplares() >= 1) {
			
		}
		
//		decrementa 1 do exemplar
		livro.setExemplares(livro.getExemplares() - 1);

//		incrementa 1 do reservado
		livro.setReservados(livro.getReservados() + 1);
		
		
		List<Livro> list = new ArrayList<Livro>();
		list.add(livro);
		
		double valorDiaria = 0.0;
		valorDiaria = valorDiaria+livro.getValorDiaria();

		
		
		
//		MANIPULACAO DE LOCACAO ITEM		
		LocacaoItem locacaoItem = new LocacaoItem();
		locacaoItem.setLivros(list);
		locacaoItem.setValorDiaria(valorDiaria);
		

		LocalDate dataPrevisaoEntrega = LocalDate.now().plusDays(15);
		locacaoItem.setDataPrevisaoEntrega(dataPrevisaoEntrega);
		System.out.println("Previsão de entrega: "+ dataPrevisaoEntrega);
		
		
//		locacaoItem.setDiarias(2);
//		SET VALOR LOCACAO
//		locacaoItem.setValorLocacao(locacaoItem.getValorDiaria() * locacaoItem.getDiarias());

		
		
		

		locacao.setLocacaoItem(locacaoItem);
		
//		MANIPULACAO DE LOCACAO
//		define a data de agendamento como hoje
		LocalDate dataAgendamento = LocalDate.now();
		locacao.setDataAgendamento(dataAgendamento);

//		define a data de finalização como daqui a 30 dias, ou seja, se não pegar em 30 dias, solicitação finalizada
		LocalDate dataFinalizacao = LocalDate.now().plusMonths(1);
		locacao.setDataFinalizacao(dataFinalizacao);

		locacao.setStatus(LocacaoStatusEnum.RESERVADA);
		locacao.setValorTotal(0.0);

		CadastroController cadastroController = new CadastroController();
		locacao.setCadastro(cadastroController.getUser(32));

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
		
//		alterar status para efetivada
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
		
		List<Livro> listLivro = locacao.getLocacaoItem().getLivros();
		listLivro.forEach(System.out::println);
//		locacao.getLocacaoItem().getLivros().get(0);
////		incrementa 1 do exemplar
//		livro.setExemplares(livro.getExemplares() - 1);
//
////		decrementa 1 do reservado
//		livro.setReservados(livro.getReservados() + 1);

//		locacao.setStatus(LocacaoStatusEnum.FINALIZADA);

//		jpaLocacaoRepository = new JpaLocacaoRepository();
//		jpaLocacaoRepository.insert(locacao);
//		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");

		return locacao;
	}

}
