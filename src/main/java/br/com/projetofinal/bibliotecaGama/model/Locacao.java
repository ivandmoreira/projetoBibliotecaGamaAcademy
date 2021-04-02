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
import javax.persistence.SequenceGenerator;

import br.com.projetofinal.bibliotecaGama.model.enums.LocacaoStatus;
@Entity
@SequenceGenerator(name = Locacao.SEQUENCE_NAME, sequenceName = Locacao.SEQUENCE_NAME, initialValue = 1, allocationSize = 10)
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "SEQUENCE_LOCACAO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Integer id;
	
	private Date dataAgendamento;
	
	private Date dataRetirada;
	
	private Date dataFinalizacao;
	
	private Double valorTotal;

	@OneToOne
	private Cadastro cadastro;
	@Enumerated(EnumType.ORDINAL)

	private LocacaoStatus status;
	@OneToOne
	private LocacaoItem locacaoItem;

	public Locacao(Integer id, Date dataAgendamento, Date dataRetirada, Date dataFinalizacao, Double valorTotal,
			Cadastro cadastro, LocacaoStatus status, LocacaoItem locacaoItem) {
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

	public LocacaoStatus getStatus() {
		return status;
	}

	public void setStatus(LocacaoStatus status) {
		this.status = status;
	}

	public LocacaoItem getLocacaoItem() {
		return locacaoItem;
	}

	public void setLocacaoItem(LocacaoItem locacaoItem) {
		this.locacaoItem = locacaoItem;
	}
	
}
