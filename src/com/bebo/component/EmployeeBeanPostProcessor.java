package com.bebo.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author anthakur
 *
 */
public class EmployeeBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//System.out.println("****************************************Inside post process before initialization: " + beanName);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//System.out.println("***************************************Inside post process after initialization: " + beanName);
		return bean;
	}

}
