package br.com.projetoFinal.bibliotecaGama.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@SequenceGenerator(name = Cadastro.SEQUENCE_NAME, sequenceName = Cadastro.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class LocacaoItem {
	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "SEQUENCE_LOCACAOITEM";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Integer id;
	private Date dataPrevisaoEntrega;
	private Date dataEntrega;
	private Integer diarias;
	private Double valoDiaria;
	private Double valorLocacao;

	@OneToOne
	private Livro livro;

	@ManyToOne
	private Locacao locacao;

	public LocacaoItem() {
	}

	public LocacaoItem(Integer id, LocalDate dataPrevisaoEntrega, LocalDate dataEntrega, Integer diarias,
			Double valorDiaria, Double valorLocacao, Livro livro, Locacao locacao) {
		super();
		this.id = id;
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
		this.dataEntrega = dataEntrega;
		this.diarias = diarias;
		this.valoDiaria = valoDiaria;
		this.valorLocacao = valorLocacao;
		this.livro = livro;
		this.locacao = locacao;
	}

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

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

}
