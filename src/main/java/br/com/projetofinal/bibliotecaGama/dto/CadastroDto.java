package br.com.projetofinal.bibliotecaGama.dto;


import br.com.projetofinal.bibliotecaGama.model.Cadastro;
import br.com.projetofinal.bibliotecaGama.model.Endereco;
import br.com.projetofinal.bibliotecaGama.model.Login;

public class CadastroDto {
	
	
	private String cpf;

	private String nome;

	private String email;

	private String telefone;

	private Login login;

	private Endereco endereco;

	public CadastroDto(Cadastro cadDto) {
		super();
		this.cpf = cadDto.getCpf();
		this.nome = cadDto.getNome();
		this.email = cadDto.getEmail();
		this.telefone = cadDto.getTelefone();
		this.login = cadDto.getLogin();
		this.endereco = cadDto.getEndereco();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	

}
