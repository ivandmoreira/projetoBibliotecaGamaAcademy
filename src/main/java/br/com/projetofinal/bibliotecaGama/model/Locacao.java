package br.com.projetofinal.bibliotecaGama.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date dataAgendamento;
	
	private Date dataRetirada;
	
	private Date dataFinalizacao;
	
	private Double valorTotal;

	@OneToOne
	private Cadastro cadastro;
	@Enumerated(EnumType.ORDINAL)

	private LocacaoStatusEnum status;
	@OneToOne
	private LocacaoItem locacaoItem;

	public Locacao(Integer id, Date dataAgendamento, Date dataRetirada, Date dataFinalizacao, Double valorTotal,
			Cadastro cadastro, LocacaoStatusEnum status, LocacaoItem locacaoItem) {
		super();
		this.id = id;
		this.dataAgendamento = dataAgendamento;
		this.dataRetirada = dataRetirada;
		this.dataFinalizacao = dataFinalizacao;
		this.valorTotal = valorTotal;
		this.cadastro = cadastro;
		this.status = status;
		this.locacaoItem = locacaoItem;
	}

	
	public Integer getId() {
		return id;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public LocacaoStatusEnum getStatus() {
		return status;
	}

	public void setStatus(LocacaoStatusEnum status) {
		this.status = status;
	}

	public LocacaoItem getLocacaoItem() {
		return locacaoItem;
	}

	public void setLocacaoItem(LocacaoItem locacaoItem) {
		this.locacaoItem = locacaoItem;
	}
	
}
