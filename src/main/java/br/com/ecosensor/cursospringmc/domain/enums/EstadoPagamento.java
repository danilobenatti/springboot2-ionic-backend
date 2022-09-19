package br.com.ecosensor.cursospringmc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private EstadoPagamento(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static EstadoPagamento toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (EstadoPagamento status : EstadoPagamento.values()) {
			if (code.equals(status.getCode())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid code for status: " + code);
	}
	
}
