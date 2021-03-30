package br.com.projetoFinal.bibliotecaGama.model;

import java.util.Date;

public class Locacao {

	private Integer id;
	private Date dataAgendamento;
	private Date dataRetirada;
	private Date dataFinalizacao;
	private Double valorTotal;
	private Cadastro cadastro;
	private LocacaoStatusEnum status;
	
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
