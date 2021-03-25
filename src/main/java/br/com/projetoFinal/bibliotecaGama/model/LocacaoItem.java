package br.com.projetoFinal.bibliotecaGama.model;

import java.util.Date;
/**
 * @author Ivan D. Moreira
 */
public class LocacaoItem {

	private Integer id;
	private Date dataPrevisaoEntrega;
	private Date dataEntrega;
	private Integer diarias;
	private Double valoDiaria;
	private Double valorLocacao;
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
