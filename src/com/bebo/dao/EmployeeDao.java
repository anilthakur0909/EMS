package com.bebo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bebo.model.Employee;

/*
 * @author Anil.Thakur
 */

@Repository
public class EmployeeDao {

	@PersistenceContext
	EntityManager entityManager;

	public void register(Employee employee) {
		try {
			entityManager.persist(employee);
		} catch (PersistenceException eee) {
			// Never gets here
			System.out.println("MaintCategory.doNewCateogry(): caught: " + eee);
		} catch (DatabaseException dbe) {
			// Never gets here neither
			System.out.println("MaintCategory.doNewCateogry(): caught: " + dbe);
		}
	}

	public boolean employeeExist(Employee employee) {
		try {
			entityManager.createQuery("SELECT e FROM  Employee e where e.username = :username and e.password=:password")
					.setParameter("username", employee.getUsername()).setParameter("password", employee.getPassword())
					.getFirstResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	public List<Employee> findAllEmployee() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public List<Employee> findEmployeeUsingCriteia() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public void deleteEmployee(int employeeId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		entityManager.remove(employee);
	}

	public Employee findEmployee(Integer employeeId) {
		return (Employee) entityManager.find(Employee.class, employeeId);
	}

	public boolean isUserNameExist(String username) {

		try {
			entityManager.createQuery("SELECT e FROM  Employee e where e.username = :username")
					.setParameter("username", username).getFirstResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;

	}
}
