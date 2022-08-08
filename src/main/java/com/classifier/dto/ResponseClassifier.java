package com.classifier.dto;

public class ResponseClassifier {

	private String img;
	private double probality;
	private boolean isDocument;
	private String msg;
	private Integer type;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isDocument() {
		return isDocument;
	}

	public void setDocument(boolean isDocument) {
		this.isDocument = isDocument;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getType() {
		return type;
	}
	
	

	public double getProbality() {
		return probality;
	}

	public void setProbality(double probality) {
		this.probality = probality;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
