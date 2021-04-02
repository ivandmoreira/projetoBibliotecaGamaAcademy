package br.com.projetofinal.bibliotecaGama.model.enums;

public enum LocacaoStatus {
	
	RESERVADA(1, "Reservada"),
	EFETIVADA(2, "Eferivada"),
	FINALIZADA(3, "Finalizada");

	private int cod;
	private String descricao;

	private LocacaoStatus(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static LocacaoStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for(LocacaoStatus x : LocacaoStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
