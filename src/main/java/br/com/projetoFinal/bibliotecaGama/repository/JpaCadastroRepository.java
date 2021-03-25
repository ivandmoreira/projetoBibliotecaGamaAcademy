package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
/**
 * @author Ivan D. Moreira
 */
public class JpaCadastroRepository implements Repository<Cadastro> {
	private EntityManager entityManager;

	public JpaCadastroRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insert(Cadastro e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Cadastro e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cadastro select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cadastro> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
