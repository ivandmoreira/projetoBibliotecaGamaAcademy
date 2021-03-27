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
@Table(name = "tbl_locItem")
public class LocacaoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name="dt_prev_ent")
	private Date dataPrevisaoEntrega;
	
	@JoinColumn(name="dt_ent")
	private Date dataEntrega;
	
	
	private Integer diarias;
	
	@JoinColumn(name="vr_diat")
	private Double valoDiaria;
	
	@JoinColumn(name="vr_loc")
	private Double valorLocacao;
	
	@JoinColumn(name="livro_id")
	private Livro Livro;
	
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
		return Livro;
	}
	public void setLivro(Livro livro) {
		Livro = livro;
	}
}
