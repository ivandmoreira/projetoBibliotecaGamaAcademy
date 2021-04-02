package br.com.projetoFinal.bibliotecaGama.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = Endereco.SEQUENCE_NAME, sequenceName = Endereco.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "SEQUENCE_ENDERECO";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Integer id;

	@Column(length = 9, nullable = false)
	private String cep;

	@Column(length = 40, nullable = true)
	private String logradouro;

	@Column(length = 40, nullable = true)
	private String bairro;

	@Column(length = 40, nullable = true)
	private String localidade;

	@Column(length = 2, nullable = true)
	private String uf;

	@Column(length = 40, nullable = true)
	private Integer ibge;

	public Endereco() {

	}

	public Endereco(String cep, String logradouro, String bairro, String localidade, String uf, int ibge) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
	}

	public Integer getId() {
		return id;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	@Override
	public String toString() {
		return "Endereco {id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + "}\n";
	}

}