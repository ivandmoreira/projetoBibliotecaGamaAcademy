package br.com.projetoFinal.bibliotecaGama.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = LocacaoItem.SEQUENCE_NAME, sequenceName = LocacaoItem.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class LocacaoItem implements Serializable {
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

	@OneToMany
	private List<Livro> Livros;

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

}