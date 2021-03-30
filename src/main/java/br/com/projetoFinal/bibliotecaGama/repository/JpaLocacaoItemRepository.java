package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoItem;

public class JpaLocacaoItemRepository implements Repository <LocacaoItem> {

	private EntityManager entityManager;

	public JpaLocacaoItemRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insert(LocacaoItem e) {
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(LocacaoItem e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();

	}

	@Override
	public LocacaoItem select(Integer id) {
		return entityManager.find(LocacaoItem.class,id);
	}

	@Override
	public List selectAll() {
		Query query = entityManager.createQuery("SELECT e FROM LocacaoItem e"); // JPQL
		return query.getResultList();
	}

	public void fechar() {
		entityManager.clear();
		entityManager.close();
	}

}
