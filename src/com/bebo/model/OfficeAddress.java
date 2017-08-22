/**
 * 
 */
package com.bebo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anthakur
 *
 */
@Entity
@Table(name="officeaddress")
public class OfficeAddress {

	@Id
	@GeneratedValue
	@Column(name = "plotNo")
	private int plotNo;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;

	public int getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(int plotNo) {
		this.plotNo = plotNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
