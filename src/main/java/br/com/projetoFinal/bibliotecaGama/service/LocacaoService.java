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
		List<Locacao> locacao =jpaLocacaoRepository.selectAll();
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
		
		System.out.println("tela de cadastrar locacao\n");
		
		LivroController livroController = new LivroController();

		Livro livro = livroController.getBook(192);
		List<Livro> list = new ArrayList<Livro>();
		list.add(livro);
		
//		decrementa 1 do exemplar
		livro.setExemplares(livro.getExemplares()-1);
		
//		incrementa 1 do reservado
		livro.setReservados(livro.getReservados()+1);
		
		LocacaoItem locacaoItem = new LocacaoItem();
		locacao.setLocacaoItem(locacaoItem);
		locacaoItem.setLivros(list);
		locacaoItem.setDiarias(2);
		locacaoItem.setValorDiaria(5.0);
		
//		define a data de agendamento como hoje
		LocalDate dataAgendamento = LocalDate.now();
		locacao.setDataAgendamento(dataAgendamento);

//		define a data de finalização como daqui a 30 dias, ou seja, se não pegar em 30 dias, solicitação finalizada
		LocalDate dataFinalizacao = LocalDate.now().plusMonths(1);
		locacao.setDataFinalizacao(dataFinalizacao);
		
		locacao.setStatus(LocacaoStatusEnum.RESERVADA);
		locacao.setValorTotal(0.0);
		

		locacaoItem.setValorLocacao(locacaoItem.getValorDiaria() * locacaoItem.getDiarias());
		
		CadastroController cadastroController = new CadastroController();
		locacao.setCadastro(cadastroController.getUser(22));

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
		
		return locacao;
	}
	
	public Locacao retirarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);
		
		System.out.println("tela de retirar locacao\n");
		
		LivroController livroController = new LivroController();

		Livro livro = livroController.getBook(192);
		List<Livro> list = new ArrayList<Livro>();
		list.add(livro);
		
//		decrementa 1 do exemplar
		livro.setExemplares(livro.getExemplares()-1);
		
//		incrementa 1 do reservado
		livro.setReservados(livro.getReservados()+1);
		
		LocacaoItem locacaoItem = new LocacaoItem();
		locacao.setLocacaoItem(locacaoItem);
		locacaoItem.setLivros(list);
		locacaoItem.setDiarias(2);
		locacaoItem.setValorDiaria(5.0);
		
		LocalDate dataAgendamento = LocalDate.now();
		locacao.setDataAgendamento(dataAgendamento);
		

		LocalDate dataRetirada = LocalDate.now().plusDays(2);
//		LocalDate dataRetirada = LocalDate.now().plusDays(2);
		locacao.setStatus(LocacaoStatusEnum.RESERVADA);
		locacao.setValorTotal(0.0);
		

		locacaoItem.setValorLocacao(locacaoItem.getValorDiaria() * locacaoItem.getDiarias());
		
		CadastroController cadastroController = new CadastroController();
		locacao.setCadastro(cadastroController.getUser(22));

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
		
		return locacao;
	}
	
	
}
