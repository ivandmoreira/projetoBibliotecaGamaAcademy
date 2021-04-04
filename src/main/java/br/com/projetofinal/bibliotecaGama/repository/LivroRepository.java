package br.com.projetofinal.bibliotecaGama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Livro;
@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {

	@Query("SELECT l FROM Livro l where l.id = :idParam and l.exemplares >= 1")
	public Optional<Livro> VerificaDisponibilidade(Integer idParam);
}
