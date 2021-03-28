package br.com.projetoFinal.bibliotecaGama.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_cadastro")
public class Cadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Integer id;
	
	@Column(name="cpf", length=14, nullable = false, unique = true)
	private String cpf;
	
	@Column(name="nome", length=100)
	private String nome;
		
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(length=100)
	private List<String> emails = new ArrayList<String>();

	//EntityGraph
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cadastro")
	private List<Telefone> telefones  = new ArrayList<Telefone>();

	@Column(name="login", length=20, unique = true)
	private String login;

	@Column(name="senha", length=150)
	private String senha;

	@Embedded
	private Endereco endereco;
	
	public Cadastro(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void addEmail(String email) {
		emails.add(email);
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void addTelefone(Telefone telefone) {
		telefone.setCadastro(this);
		telefones.add(telefone);
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
