package br.com.projetofinal.bibliotecaGama.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.projetofinal.bibliotecaGama.model.LocacaoItem;



public class LocacaoDto {
	
	private LocalDate dataAgendamento;

	private LocalDate dataFinalizacao;

	private int usuario_id;
	
	private List<LocacaoItemDto> locacaoItem;

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}


	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public List<LocacaoItemDto> getLocacaoItem() {
		return locacaoItem;
	}

	public void setLocacaoItem(List<LocacaoItemDto> locacaoItem) {
		this.locacaoItem = locacaoItem;
	}

}
