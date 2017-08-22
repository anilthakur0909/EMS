package com.bebo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author Anil.Thakur
 */

@Aspect
public class EmployeeAspect {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeAspect.class);

	@Before("execution(* com.bebo.dao.EmployeeDao.*(..))")
	public void logBeforeEmployeeDaoMethods(JoinPoint joinPoint) {
		logger.debug("*****************logBeforeEmployeeDaoMethods:********************"+joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.bebo.dao.EmployeeDao.*(..))")
	public void logAfterEmployeeDaoMethods(JoinPoint joinPoint) {
		logger.debug("*****************logAfterEmployeeDaoMethods:********************"+joinPoint.getSignature().getName());
	}

	@Before("execution(* com.bebo.controller.LoginController.*(..))")
	public void logBeforeLoginControllerMethods(JoinPoint joinPoint) {
		logger.debug("*****************logBeforeLoginControllerMethods:********************"+joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.bebo.controller.LoginController.*(..))")
	public void logAfterLoginControllerMethods(JoinPoint joinPoint) {
		logger.debug("*****************logAfterLoginControllerMethods:********************"+joinPoint.getSignature().getName());
	}

	
	@Before("execution(* com.bebo.controller.LoginController.getEmployeeUsingCriteriaUsingRestFul*(..))")
	public void logBeforeEmployeeUsingCriteriaUsingRestFul(JoinPoint joinPoint) {
		logger.debug("*****************getEmployeeUsingCriteriaUsingRestFul:********************"+joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.bebo.controller.LoginController.getEmployeeUsingCriteriaUsingRestFul*(..))")
	public void logAfterEmployeeUsingCriteriaUsingRestFul(JoinPoint joinPoint) {
		logger.debug("*****************getEmployeeUsingCriteriaUsingRestFul:********************"+joinPoint.getSignature().getName());
	}
}
