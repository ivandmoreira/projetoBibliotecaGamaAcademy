package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.projetoFinal.bibliotecaGama.model.Livro;

public class JpaLivroRepository implements Repository<Livro> {
	private EntityManager entityManager;

	public JpaLivroRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insert(Livro e) {
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Livro e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public Livro select(Integer id) {
		return entityManager.find(Livro.class, id);
	}

	@Override
	public List<Livro> selectAll() {
		Query query = entityManager.createQuery("SELECT e FROM Livro e"); // JPQL
		return query.getResultList();
	}

	public boolean disponivel(Integer id) {
		String consulta = "SELECT l FROM Livro l where l.id = :idParam and l.exemplares >= 1";
		TypedQuery<Livro> query = entityManager.createQuery(consulta, Livro.class); // JPQL
		query.setParameter("idParam", id);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}

	}

	public void fechar() {
		entityManager.clear();
		entityManager.close();
	}

}
