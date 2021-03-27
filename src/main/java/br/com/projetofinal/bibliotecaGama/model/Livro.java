package br.com.projetofinal.bibliotecaGama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ivan D. Moreira
 */
@Entity
public class Livro {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 13)
	private String isbn;
	@Column(length = 50)
	private String titulo;
	@Column(precision = 4 , scale = 2)
	private Double valorDiaria;
	private Integer exemplares;
	private Integer reservados;

	public Integer getId() {
		return id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public Integer getExemplares() {
		return exemplares;
	}
	public void setExemplares(Integer exemplares) {
		this.exemplares = exemplares;
	}
	public Integer getReservados() {
		return reservados;
	}
	public void setReservados(Integer reservados) {
		this.reservados = reservados;
	}
	
	
}
