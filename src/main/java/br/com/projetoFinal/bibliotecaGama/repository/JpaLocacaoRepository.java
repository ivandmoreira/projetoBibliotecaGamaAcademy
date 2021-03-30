package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.projetoFinal.bibliotecaGama.model.Locacao;

public class JpaLocacaoRepository implements Repository<Locacao> {

	private EntityManager entityManager;

	public JpaLocacaoRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}
	
	@Override
	public void insert(Locacao e) {
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void update(Locacao e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public Locacao select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Locacao> selectAll() {
		Query query = entityManager.createQuery("SELECT l FROM Locacao l"); //JPQL
		return query.getResultList();
	}

}