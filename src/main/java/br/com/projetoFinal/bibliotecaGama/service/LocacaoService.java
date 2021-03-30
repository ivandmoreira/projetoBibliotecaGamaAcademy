package br.com.projetoFinal.bibliotecaGama.service;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Locacao;
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
		
//		System.out.print("Digite o isbn: ");
//		String isbn = scanner.nextLine();
//		livro.setIsbn(isbn);
//		
//		System.out.print("Digite o titulo: ");
//		String titulo = scanner.nextLine();
//		livro.setTitulo(titulo);
//		
//		System.out.print("Digite o valor da diaria: ");
//		double valorDiaria = Double.parseDouble(scanner.nextLine());
//		livro.setValorDiaria(valorDiaria);
//		
//		System.out.print("Digite a quantidade de exemplares: ");
//		int exemplares = Integer.parseInt(scanner.nextLine());
//		livro.setExemplares(exemplares);
//		
//		livro.setReservados(0);

		jpaLocacaoRepository = new JpaLocacaoRepository();
		jpaLocacaoRepository.insert(locacao);
		jpaLocacaoRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");
		
		return locacao;
	}
}
