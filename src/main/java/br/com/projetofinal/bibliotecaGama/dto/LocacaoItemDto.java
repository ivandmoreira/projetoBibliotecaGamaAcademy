package br.com.projetofinal.bibliotecaGama.dto;

import java.time.LocalDate;

public class LocacaoItemDto {

	private LocalDate dataPrevisaoEntrega;
	private Double valorDiaria;
	private int livro_id;
	
	
	public LocalDate getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}
	public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}
	public Double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public int getLivro_id() {
		return livro_id;
	}
	public void setLivro_id(int livro_id) {
		this.livro_id = livro_id;
	}
	
	
}
