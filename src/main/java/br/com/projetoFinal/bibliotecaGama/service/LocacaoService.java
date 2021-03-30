package br.com.projetoFinal.bibliotecaGama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.controller.CadastroController;
import br.com.projetoFinal.bibliotecaGama.controller.LivroController;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoItem;
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
	
	public Locacao cadastrarLocacao(Locacao locacao) {
		scanner = new Scanner(System.in);
		
		System.out.println("tela de cadastrar locacao\n");
		
		LivroController livroController = new LivroController();

		Livro livro = livroController.getBook(112);
		List<Livro> list = new ArrayList<Livro>();
		list.add(livro);
		
		LocacaoItem locacaoItem = new LocacaoItem();
		locacao.setLocacaoItem(locacaoItem);
		locacaoItem.setLivros(list);
		locacaoItem.setDiarias(2);
		
		CadastroController cadastroController = new CadastroController();
		locacao.setCadastro(cadastroController.getUser(22));

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
		
		return locacao;
	}
}
