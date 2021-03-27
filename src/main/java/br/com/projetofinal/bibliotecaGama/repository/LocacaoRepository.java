package br.com.projetofinal.bibliotecaGama.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Locacao;
@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {

}
