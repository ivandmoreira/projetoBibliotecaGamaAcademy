package br.com.projetofinal.bibliotecaGama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Integer id;

	@Column(name="cep", length=9, nullable = false)
	private String cep;

	@Column(name="logradouro", length=40, nullable = true)
	private String logradouro;
	
	@Column(name="bairro", length=40, nullable = true)
	private String bairro;

	@Column(name="localidade", length=40, nullable = true)
	private String localidade;

	@Column(name="uf", length=2, nullable = true)
	private String uf;
	
	@Column(name="ibge", length=40, nullable = true)
	private Integer ibge;
	
	public Endereco() {}
	
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
