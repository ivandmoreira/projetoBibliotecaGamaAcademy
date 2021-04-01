package br.com.projetoFinal.bibliotecaGama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = Livro.SEQUENCE_NAME, sequenceName = Livro.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class Livro {

	public static final String SEQUENCE_NAME = "SEQUENCE_LIVRO";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Integer id;
	
	@Column(length = 13)
	private String isbn;
	
	@Column(length = 50)
	private String titulo;
	
	@Column(precision = 4 , scale = 2)
	private Double valorDiaria;
	
	@Column(length = 5, nullable = false)
	private Integer exemplares;
	
	@Column(length = 5, nullable = false)
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
