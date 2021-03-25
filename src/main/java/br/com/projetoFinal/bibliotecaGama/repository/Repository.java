package br.com.projetoFinal.bibliotecaGama.repository;

import java.util.List;
/**
 * @author Ivan D. Moreira
 */
public interface Repository<E> {
	public void insert(E e);
	public void update(E e);
	public E select(Integer id);
	public List<E> selectAll(); 

}
