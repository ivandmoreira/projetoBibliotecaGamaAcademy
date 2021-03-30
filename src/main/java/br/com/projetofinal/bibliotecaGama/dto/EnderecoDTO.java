package br.com.projetofinal.bibliotecaGama.dto;

import java.util.Optional;

import br.com.projetofinal.bibliotecaGama.model.Cadastro;

public class EnderecoDTO {
	
	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String uf;

	private Integer ibge;

	public EnderecoDTO(String cep, String logradouro, String bairro, String localidade, String uf, Integer ibge) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
	}

	public EnderecoDTO() {
		// TODO Auto-generated constructor stub
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

	public Optional<Cadastro> stream() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
