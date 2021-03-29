package br.com.projetoFinal.bibliotecaGama.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


	@Entity
	@SequenceGenerator(name = Cadastro.SEQUENCE_NAME, sequenceName = Cadastro.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
	public class Cadastro implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public static final String SEQUENCE_NAME = "SEQUENCE_CADASTRO";

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
		private Integer id;	
		
	  @Column(name="cpf", length=14, nullable = false, unique = true)
		private String cpf;

		@Column(length = 50, nullable = false)
		private String nome;

		@Column(length = 50)
		private String email;

		@Column(length = 50)
		private String telefone;

		@Column(name="login", length=20 , unique = true)
		private String login;

		@Column(length = 50)
		private String senha;

		@OneToOne( fetch = FetchType.EAGER, orphanRemoval = true)
		private Endereco endereco;

		public Cadastro() {
		}

		public Cadastro(String cpf, String nome, String email, String telefone, String login, String senha,
				Endereco endereco) {
			this.cpf = cpf;
			this.nome = nome;
			this.email = email;
			this.telefone = telefone;
			this.login = login;
			this.senha = senha;
			this.endereco = endereco;
		}

		public Integer getId() {
			return id;
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