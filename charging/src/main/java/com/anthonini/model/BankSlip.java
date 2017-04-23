package com.anthonini.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class BankSlip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_bank_slip")
	private Long id;
	
	@NotEmpty(message="Description is mandatory")
	@Size(max=60, message="Description cannot exceed 60 characters")
	private String description;
	
	@NotNull(message="Date is mandatory")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@NotNull(message="Value is mandatory")
	@DecimalMin(value="0.01", message="Value cannot be smaller than 0,01")
	@DecimalMax(value="9999999.99", message="Value cannot be bigger than 9.999.999,99")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	private StatusBankSlip statusBankSlip;
	
	public boolean isPending(){
		return this.statusBankSlip.equals(StatusBankSlip.PENDING);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public StatusBankSlip getStatusBankSlip() {
		return statusBankSlip;
	}
	public void setStatusBankSlip(StatusBankSlip statusBankSlip) {
		this.statusBankSlip = statusBankSlip;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankSlip other = (BankSlip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
