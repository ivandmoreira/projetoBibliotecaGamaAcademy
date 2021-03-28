package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.projetoFinal.bibliotecaGama.model.Cadastro;

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
	
	public Cadastro selectCpf(String cpf) {
		String consulta = "SELECT e FROM Cadastro e WHERE e.cpf = :cpf";
		TypedQuery<Cadastro> query = entityManager.createQuery(consulta, Cadastro.class);
		query.setParameter("cpf",cpf);
		Cadastro cadastro = null;
		try {
			cadastro = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Cpf disponível para cadastro");
		}
		return cadastro;
	}

	public <T> Cadastro selectLogin(T login) {
		String consulta = "SELECT e FROM Cadastro e WHERE e.login = :login";
		TypedQuery<Cadastro> query = entityManager.createQuery(consulta, Cadastro.class);
		query.setParameter("login",login);
		Cadastro cadastro = null;
		try {
			cadastro = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login disponível para cadastro");
		}
		return cadastro;
	}
////	RETORNO DE UMA LISTA DE OBJETO
//	public List<Cadastro> selectCpf(String cpf) {
//		String consulta = "SELECT e FROM Cadastro e WHERE e.cpf = :cpf";
//		TypedQuery<Cadastro> query = entityManager.createQuery(consulta, Cadastro.class);
//		query.setParameter("cpf",cpf);
//		List<cadastro> listaCadastro = query.getResultList();
//		return listaCadastro;
//	}

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
