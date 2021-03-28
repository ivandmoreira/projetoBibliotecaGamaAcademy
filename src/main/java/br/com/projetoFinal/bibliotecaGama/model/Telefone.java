package br.com.projetoFinal.bibliotecaGama.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_telefone")
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=14)
	private Long numero;
	
	@Enumerated(EnumType.STRING)
	private TelefoneTipo tipo;
	
	@OneToOne
	@JsonBackReference
//	@JsonIgnoreProperties("cadastro")
	private Cadastro cadastro;
	
	public Cadastro getCadastro() {
		return cadastro;
	}
	void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}
	
	public Telefone() {
		
	}
	
	public Telefone(TelefoneTipo tipo, Long numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public TelefoneTipo getTipo() {
		return tipo;
	}
	public void setTipo(TelefoneTipo tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return numero+"\n";
	}	
	
}
