package br.com.projetoFinal.bibliotecaGama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
>>>>>>> parent of 65eafb6 (ajustes controller cadastro, validações e remoção de entidades)

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String cep;
	private String logradouro;
<<<<<<< HEAD
	private String Bairro;
	private String localidade;
=======

	@Column(length = 5, nullable = false)
	private String numero;

	@Column(length = 40, nullable = true)
	private String bairro;

	@Column(length = 40, nullable = true)
	private String localidade;

	@Column(length = 2, nullable = true)
>>>>>>> parent of 65eafb6 (ajustes controller cadastro, validações e remoção de entidades)
	private String uf;
	private Integer ibge;
<<<<<<< HEAD
=======

	public Endereco() {

	}

	public Endereco(String cep, String logradouro, String numero, String bairro, String localidade, String uf,
			int ibge) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
	}

>>>>>>> parent of 65eafb6 (ajustes controller cadastro, validações e remoção de entidades)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
<<<<<<< HEAD
=======

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

>>>>>>> parent of 65eafb6 (ajustes controller cadastro, validações e remoção de entidades)
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Integer getIbge() {
		return ibge;
	}
	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}
	
	
	
}
