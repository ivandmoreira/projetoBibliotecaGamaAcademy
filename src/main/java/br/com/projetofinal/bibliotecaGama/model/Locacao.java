package br.com.projetofinal.bibliotecaGama.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_locacao")
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name = "dt_agenda")	
	private Date dataAgendamento;
	
	@JoinColumn(name = "dt_Retir")
	private Date dataRetirada;
	
	@JoinColumn(name = "dt_finaliz")
	private Date dataFinalizacao;
	
	@JoinColumn(name = "vl_total")
	private Double valorTotal;

	@JoinColumn(name= "cad_id")
	private Cadastro cadastro;

	private LocacaoStatusEnum status;
	
	@JoinColumn(name = "locItem_Id")
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
