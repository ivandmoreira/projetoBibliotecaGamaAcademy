package br.com.projetoFinal.bibliotecaGama.model;

import java.util.Date;

public class Locacao {

	private Integer id;
	private Date dataAgendamento;
	private Date dataRetirada;
	private Date dataFinalizacao;
	private Double valorTotal;
	private Cadastro cadastro;
<<<<<<< HEAD
	private LocacaoStatusEnum status;
=======
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

>>>>>>> parent of 65eafb6 (ajustes controller cadastro, validações e remoção de entidades)
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
