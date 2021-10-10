package com.classifier.enums;

public enum TypeDocument {

	IDENTIDADE(1, "Documento de identificação"),
	CPF(2, "Certificado de pessoa física"),
	COMPROVANTE_RESIDENCIA(3, "Comprovante de residência");

	private Integer cod;
	private String description;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private TypeDocument(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public static TypeDocument toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TypeDocument x : TypeDocument.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
