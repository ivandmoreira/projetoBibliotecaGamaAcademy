package br.com.projetofinal.bibliotecaGama.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LocacaoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataPrevisaoEntrega;
	private Date dataEntrega;
	private Integer diarias;
	private Double valoDiaria;
	private Double valorLocacao;
	@OneToMany
	private List<Livro> Livro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(Date dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getDiarias() {
		return diarias;
	}

	public void setDiarias(Integer diarias) {
		this.diarias = diarias;
	}

	public Double getValoDiaria() {
		return valoDiaria;
	}

	public void setValoDiaria(Double valoDiaria) {
		this.valoDiaria = valoDiaria;
	}

	public Double getValorLocacao() {
		return valorLocacao;
	}

	public void setValorLocacao(Double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

	public List<Livro> getLivro() {
		return Livro;
	}

	public void setLivro(List<Livro> livro) {
		Livro = livro;
	}
}
