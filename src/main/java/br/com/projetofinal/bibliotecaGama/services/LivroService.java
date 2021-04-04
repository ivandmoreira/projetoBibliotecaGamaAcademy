package br.com.projetofinal.bibliotecaGama.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.bibliotecaGama.model.Livro;
import br.com.projetofinal.bibliotecaGama.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro salva(Livro livro) {
		
		return livroRepository.save(livro);
	}

	public Iterable<Livro> buscarLivros() {
		return livroRepository.findAll();
	}

	public Optional<Livro> buscarPorId(int id) {
		return livroRepository.findById(id);
	}
	
	public Livro incrementarExemplares(Livro livro) {
		
		int qtdExemplares = livro.getExemplares();
		qtdExemplares=qtdExemplares+1;
		livro.setExemplares(qtdExemplares);;
		
		return livro;
	}
	public Livro decrementarExemplares(Livro livro) {
		
		int qtdExemplares = livro.getExemplares();
		qtdExemplares=qtdExemplares-1;
		livro.setExemplares(qtdExemplares);;
		
		return livro;
	}
	public Livro incrementarReservados(Livro livro) {
		
		int qtdReservados = livro.getReservados();
		qtdReservados=qtdReservados+1;
		livro.setReservados(qtdReservados);;
		
		return livro;
	}
	public Livro decrementarReservados(Livro livro) {
		
		int qtdReservados = livro.getReservados();
		qtdReservados=qtdReservados-1;
		livro.setReservados(qtdReservados);;
		
		return livro;
	}
}
