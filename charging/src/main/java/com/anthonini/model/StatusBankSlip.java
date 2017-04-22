package com.anthonini.model;

public enum StatusBankSlip {
	PENDING("Pending"),
	RECEIVED("Received");
	
	private String description;
	
	private StatusBankSlip(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}	
}
