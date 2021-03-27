package br.com.projetofinal.bibliotecaGama.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
@Repository
public interface CadastroRepository extends CrudRepository<Cadastro, Integer>{

}
