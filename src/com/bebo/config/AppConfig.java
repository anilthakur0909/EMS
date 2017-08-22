package com.bebo.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bebo.aop.EmployeeAspect;
import com.bebo.cache.CacheMonitor;
import com.bebo.component.EmployeeBeanPostProcessor;
import com.bebo.component.EmployeeEmail;
import com.bebo.model.OfficeAddress;

/*
 * @author Anil.Thakur
 */

@Import({ RepositoryConfig.class })
@Configuration
@EnableAspectJAutoProxy
@EnableAsync
@ComponentScan(basePackages = "com.bebo.*")
@EnableElasticsearchRepositories(basePackages = "com.bebo.elastic")
public class AppConfig extends WebMvcConfigurerAdapter {

	@PersistenceUnit    
	private EntityManagerFactory  entityManagerFactory;
	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(new ClassPathResource("application.properties"));
		propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertyPlaceholderConfigurer;
	}

	@Bean
	public EmployeeAspect employeeAspect() {
		return new EmployeeAspect();
	}

/*	@Bean
	public CacheMonitor cacheMonitor() {
		return new CacheMonitor();
	}*/

	@Bean
	public EmployeeEmail employeeEmail() {
		return new EmployeeEmail();
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("anil.thakur0909@gmail.com");
		mailSender.setPassword("moorhumtum090946246");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		javaMailProperties.put("mail.smtp.socketFactory.port", "465");
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
		javaMailProperties.put("mail.smtp.protocol", "smtp");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

/*	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExecuteTimeInterceptor());
	}*/

/*	@Bean
	public EmployeeBeanPostProcessor employeeBeanPostProcessor() {
		return new EmployeeBeanPostProcessor();
	}*/

	@Bean
	public Client client() throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "tc_cluster").build();
		@SuppressWarnings("resource")
		TransportClient transportClient = new TransportClient(settings);
		transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		return (Client) transportClient;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}

	@Bean
	public OfficeAddress contractEmployeeAddress() {
		OfficeAddress address = new OfficeAddress();
		address.setCity("Sector 34 chandigarh");
		address.setCountry("India");
		return address;
	}

	@Bean
	public OfficeAddress regularEmployeeAddress() {
		OfficeAddress address = new OfficeAddress();
		address.setCity("IT Park chandigarh");
		address.setCountry("India");
		return address;
	}
}
