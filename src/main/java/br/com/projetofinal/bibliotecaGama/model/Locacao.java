package br.com.projetofinal.bibliotecaGama.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	private LocalDate dataAgendamento;

	private LocalDate dataRetirada;

	private LocalDate dataFinalizacao;

	private Double valorTotal;

	@OneToOne
	private Cadastro cadastro;
	@Enumerated(EnumType.STRING)
	private LocacaoStatus status;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "locacao", fetch = FetchType.EAGER)
	private List<LocacaoItem> locacaoItem;

	public Locacao() {
	}

	public Locacao(Integer id, LocalDate dataAgendamento, LocalDate dataRetirada, LocalDate dataFinalizacao,
			Double valorTotal, Cadastro cadastro, LocacaoStatus status, List<LocacaoItem> locacaoItem) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
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

	public List<LocacaoItem> getLocacaoItem() {
		return locacaoItem;
	}

	public void setLocacaoItem(List<LocacaoItem> locacaoItem) {
		this.locacaoItem = locacaoItem;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataAgendamento=" + dataAgendamento + ", dataRetirada=" + dataRetirada
				+ ", dataFinalizacao=" + dataFinalizacao + ", valorTotal=" + valorTotal + ", cadastro=" + cadastro
				+ ", status=" + status + ", locacaoItem=" + locacaoItem + "]";
	}

}
