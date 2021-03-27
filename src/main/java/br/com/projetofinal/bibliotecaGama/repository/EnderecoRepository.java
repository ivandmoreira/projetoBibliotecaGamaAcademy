package br.com.projetofinal.bibliotecaGama.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Endereco;
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {

}
