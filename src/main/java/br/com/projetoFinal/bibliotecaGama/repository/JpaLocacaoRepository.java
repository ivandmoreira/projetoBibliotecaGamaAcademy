package br.com.projetoFinal.bibliotecaGama.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		return entityManager.find(Locacao.class, id);
	}

	@Override
	public List<Locacao> selectAll() {
		Query query = entityManager.createQuery("SELECT l FROM Locacao l"); // JPQL
		return query.getResultList();
	}

	public Locacao selectDataLocacaoRetirada(LocalDate dataAgendamento, LocalDate dataRetirada) {
		String consulta = "SELECT l FROM Locacao l WHERE l.dataAgendamento = :dataagendamento OR l.dataRetirada = :dataretirada";
		TypedQuery<Locacao> query = entityManager.createQuery(consulta, Locacao.class);
		query.setParameter("dataagendamento", dataAgendamento);
		query.setParameter("dataretirada", dataRetirada);
		Locacao locacao = null;
		try {
			locacao = query.getSingleResult();
		} catch (Exception e) {

		}
		return locacao;
	}

	public void fechar() {
		entityManager.clear();
		entityManager.close();
	}

}
