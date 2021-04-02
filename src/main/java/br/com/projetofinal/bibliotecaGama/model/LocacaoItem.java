package br.com.projetofinal.bibliotecaGama.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = LocacaoItem.SEQUENCE_NAME, sequenceName = LocacaoItem.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class LocacaoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "SEQUENCE_LOCACAOITEM";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Integer id;
	private LocalDate dataPrevisaoEntrega;
	private LocalDate dataEntrega;
	private Long diarias;
	private Double valorDiaria;
	private Double valorLocacao;

	@OneToOne
	private Livro livro;

	@ManyToOne
	private Locacao locacao;

	public LocacaoItem() {
	}

	public LocacaoItem(Integer id, LocalDate dataPrevisaoEntrega, LocalDate dataEntrega, Long diarias,
			Double valorDiaria, Double valorLocacao, Livro livro, Locacao locacao) {
		super();
		this.id = id;
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
		this.dataEntrega = dataEntrega;
		this.diarias = diarias;
		this.valorDiaria = valorDiaria;
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

	public LocalDate getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getDiarias() {
		return diarias;
	}

	public void setDiarias(Long diarias) {
		this.diarias = diarias;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
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
