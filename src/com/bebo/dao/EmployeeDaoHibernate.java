package com.bebo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bebo.model.Employee;

/*
 * @author Anil.Thakur
 */

@Repository
public class EmployeeDaoHibernate {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void register(Employee employee) {
		hibernateTemplate.save(employee);
	}

	@SuppressWarnings("unchecked")
	public boolean employeeExist(Employee employee) {
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.getNamedQuery("findEmployeeByUserNameAndPassword");
		query.setString("username", employee.getUsername());
		query.setString("password", employee.getPassword());
		List<Employee> employees = query.list();
		if (!employees.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<Employee> findAllEmployee() {
		return hibernateTemplate.loadAll(Employee.class);
	}

/*	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeeUsingCriteia() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class)
				.setProjection(Projections.projectionList().add(Projections.property("name"), "name")
						.add(Projections.property("email"), "email").add(Projections.property("age"), "age"))
				.setResultTransformer(Transformers.aliasToBean(Employee.class));
		criteria.add(Restrictions.gt("age", 26));
		criteria.addOrder(Order.asc("age"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);
		return criteria.list();

	}*/
	
	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeeUsingCriteia() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		DetachedCriteria  detachedCriteria =DetachedCriteria.forClass(Employee.class);
		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("name"), "name")
						.add(Projections.property("email"), "email").add(Projections.property("age"), "age"))
				.setResultTransformer(Transformers.aliasToBean(Employee.class));
		
		detachedCriteria.add(Restrictions.gt("age", 26));
		detachedCriteria.addOrder(Order.asc("age"));

		return detachedCriteria.getExecutableCriteria(session).list();

	}
	
	public void deleteEmployee(int employeeId ) {
		Employee employee =hibernateTemplate.load(Employee.class, employeeId);
		hibernateTemplate.delete(employee);
	}
	
}
