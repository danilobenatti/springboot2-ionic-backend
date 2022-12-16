package br.com.ecosensor.cursospringmc.domain.enums;

public enum PerfilUsuario {
	
	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT"), GUEST(3, "ROLE_GUEST");
	
	private int code;
	private String description;
	
	private PerfilUsuario(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PerfilUsuario toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (PerfilUsuario profile : PerfilUsuario.values()) {
			if (code.equals(profile.getCode())) {
				return profile;
			}
		}
		throw new IllegalArgumentException("Invalid code for profile: " + code);
	}
	
}
