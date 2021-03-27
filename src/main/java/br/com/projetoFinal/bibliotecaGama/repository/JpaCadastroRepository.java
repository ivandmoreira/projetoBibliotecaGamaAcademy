package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;
import br.com.projetoFinal.bibliotecaGama.model.Endereco;
import br.com.projetoFinal.bibliotecaGama.model.Livro;
import br.com.projetoFinal.bibliotecaGama.model.Locacao;
import br.com.projetoFinal.bibliotecaGama.model.LocacaoItem;

public class JpaCadastroRepository implements Repository<Cadastro> {
	private EntityManager entityManager;

	public JpaCadastroRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insert(Cadastro e) {
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Cadastro e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public Cadastro select(Integer id) {
		return entityManager.find(Cadastro.class,id);
	}

	@Override
	public List<Cadastro> selectAll() {
		Query query = entityManager.createQuery("SELECT e FROM Cadastro e"); //JPQL
		return query.getResultList();
	}
	
	public void fechar() {
		entityManager.clear();
		entityManager.close();
	}	
}
