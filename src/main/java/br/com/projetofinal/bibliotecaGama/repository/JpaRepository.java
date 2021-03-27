package br.com.projetofinal.bibliotecaGama.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;
import br.com.projetofinal.bibliotecaGama.model.Livro;
import br.com.projetofinal.bibliotecaGama.model.Locacao;
import br.com.projetofinal.bibliotecaGama.model.LocacaoItem;

public class JpaRepository implements Repository<Cadastro> {
	private EntityManager entityManager;

	public JpaRepository() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca_PU");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insert(Object e) {
		if(e instanceof Cadastro) {
			e = (Cadastro) e;
		}else if (e instanceof Endereco){
			e = (Endereco) e;
		}else if (e instanceof Livro){
			e = (Livro) e;
		}else if (e instanceof Locacao){
			e = (Locacao) e;
		}else if (e instanceof LocacaoItem){
			e = (LocacaoItem) e;
		}
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Object e) {
		if(e instanceof Cadastro) {
			e = (Cadastro) e;
		}else if (e instanceof Endereco){
			e = (Endereco) e;
		}else if (e instanceof Livro){
			e = (Livro) e;
		}else if (e instanceof Locacao){
			e = (Locacao) e;
		}else if (e instanceof LocacaoItem){
			e = (LocacaoItem) e;
		}
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
