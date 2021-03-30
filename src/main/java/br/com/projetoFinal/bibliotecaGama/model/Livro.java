package br.com.projetoFinal.bibliotecaGama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jorge Ferraz
 */
@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private Integer id;
	
	@Column(name="isbn", length=50, nullable = false)
	private String isbn;
	
	@Column(name="titulo", length=150, nullable = false)
	private String titulo;
	
	@Column(name="valordiaria", length=50)
	private Double valorDiaria;
	
	@Column(name="exemplares", length=50)
	private Integer exemplares;
	
	@Column(name="reservados", length=50)
	private Integer reservados;

	
	public Livro() {
	}

	public Livro(String isbn, String titulo, Double vd, Integer ex, int reservados) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.valorDiaria = vd;
		this.exemplares = ex;
		this.reservados = reservados;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
