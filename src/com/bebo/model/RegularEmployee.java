package com.bebo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author anthakur
 *
 */
@Entity
@DiscriminatorValue("regularemployee")
public class RegularEmployee extends Employee {
	@Column(name = "salary")
	private Integer salary;

	@Column(name = "bonus")
	private Integer bonus;

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

}
