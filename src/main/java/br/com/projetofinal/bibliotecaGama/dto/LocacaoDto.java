package br.com.projetofinal.bibliotecaGama.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LocacaoDto {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataAgendamento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataFinalizacao;

	private int usuario_id;
	
	private String usuario_name;
	
	private String Status;
	
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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getUsuario_name() {
		return usuario_name;
	}

	public void setUsuario_name(String usuario_name) {
		this.usuario_name = usuario_name;
	}
	
	
	
	

}
