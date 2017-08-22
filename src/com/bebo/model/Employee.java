package com.bebo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;*/

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * @author Anil.Thakur
 */

@NamedQueries({
		@NamedQuery(name = "findEmployeeByUserNameAndPassword", query = "from Employee e where e.username = :username and e.password=:password") })

@Entity
@Table(name = "employee")

/*@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "employee")*/

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeId", unique = true, nullable = false)
	private int employeeId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "age")
	private int age;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	@JsonIgnore
	private List<Role> roles;

	@OneToOne(cascade = CascadeType.ALL)
	private OfficeAddress address;

	public OfficeAddress getAddress() {
		return address;
	}

	public void setAddress(OfficeAddress address) {
		this.address = address;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
/*
 * Table Per Hierarchy
 * 
 * @Entity
 * 
 * @Table(name = "employee101")
 * 
 * @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
 * 
 * @DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
 * 
 * @DiscriminatorValue(value="employee") public class Employee {
 * 
 * subclasses
 * 
 * @Entity
 * 
 * @DiscriminatorValue("regularemployee") public class RegularEmployee extends
 * Employee{
 * 
 * @Entity
 * 
 * @DiscriminatorValue("contractemployee") public class Contract_Employee
 * extends Employee{
 ******************************************************************
 * 
 * Table Per Concrete class
 * 
 * @Entity
 * 
 * @Table(name = "employee102")
 * 
 * @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 * 
 * public class Employee {
 * 
 * 
 * @Entity
 * 
 * @Table(name="regularemployee102")
 * 
 * @AttributeOverrides({
 * 
 * @AttributeOverride(name="id", column=@Column(name="id")),
 * 
 * @AttributeOverride(name="name", column=@Column(name="name")) }) public class
 * RegularEmployee extends Employee{
 * 
 * @Entity
 * 
 * @Table(name="contractemployee102")
 * 
 * @AttributeOverrides({
 * 
 * @AttributeOverride(name="id", column=@Column(name="id")),
 * 
 * @AttributeOverride(name="name", column=@Column(name="name")) }) public class
 * Contract_Employee extends Employee{
 ******************************************************************
 * 
 * Table Per Table Per Subclass
 * 
 * @Entity
 * 
 * @Table(name = "employee103")
 * 
 * @Inheritance(strategy=InheritanceType.JOINED)
 * 
 * public class Employee {
 * 
 * @Entity
 * 
 * @Table(name="regularemployee103")
 * 
 * @PrimaryKeyJoinColumn(name="ID") public class RegularEmployee extends
 * Employee{
 * 
 * @Entity
 * 
 * @Table(name="contractemployee103")
 * 
 * @PrimaryKeyJoinColumn(name="ID") public class ContractEmployee extends
 * Employee{
 * 
 */
