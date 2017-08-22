
package com.bebo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author anthakur
 *
 */

@Entity  
@DiscriminatorValue("contractemployee") 
public class ContractEmployee extends Employee {
	
	@Column(name="payPerHour")
	private Integer payPerHour;
	
	@Column(name="contractDuration")
	private String contractDuration;
	
	public Integer getPayPerHour() {
		return payPerHour;
	}
	public void setPayPerHour(Integer payPerHour) {
		this.payPerHour = payPerHour;
	}
	public String getContractDuration() {
		return contractDuration;
	}
	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}			
}
