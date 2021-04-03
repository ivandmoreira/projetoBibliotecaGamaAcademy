package br.com.projetoFinal.bibliotecaGama.service;

import java.util.List;
import java.util.Scanner;

import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.repository.JpaLivroRepository;

public class LivroService {
	private JpaLivroRepository jpaLivroRepository;
	private Scanner scanner;

	public List<Livro> getAll() {
		jpaLivroRepository = new JpaLivroRepository();
		List<Livro> livro = jpaLivroRepository.selectAll();
		jpaLivroRepository.fechar();
		return livro;
	}

	public boolean hasExemplares(Integer id) {
		jpaLivroRepository = new JpaLivroRepository();
		boolean temExemplares = jpaLivroRepository.disponivel(id);
		jpaLivroRepository.fechar();
		return temExemplares;
	}

	public Livro getById(Integer id) {
		jpaLivroRepository = new JpaLivroRepository();
		Livro livro = jpaLivroRepository.select(id);
		jpaLivroRepository.fechar();
		return livro;
	}

	public Livro cadastrar(Livro livro) {
		scanner = new Scanner(System.in);

		System.out.println("tela de cadastrar livros\n");

		System.out.print("Digite o isbn: ");
		String isbn = scanner.nextLine();
		livro.setIsbn(isbn);

		System.out.print("Digite o titulo: ");
		String titulo = scanner.nextLine();
		livro.setTitulo(titulo);

		System.out.print("Digite o valor da diaria: ");
		double valorDiaria = Double.parseDouble(scanner.nextLine());
		livro.setValorDiaria(valorDiaria);

		// N�mero de exemplares do livro maior ou igual a 1
		System.out.print("Digite a quantidade de exemplares: ");
		int exemplares = Integer.parseInt(scanner.nextLine());
		livro.setExemplares(exemplares);

		livro.setReservados(0);

		jpaLivroRepository = new JpaLivroRepository();
		jpaLivroRepository.insert(livro);
		jpaLivroRepository.fechar();

		System.out.println("Cadastro efetuado com sucesso!\n");

		return livro;
	}
}
