package com.bebo.component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bebo.model.Employee;

/*
 * @author Anil.Thakur
 */
@Component
public class EmployeeEmail {

	@Autowired
	JavaMailSender mailSender;

	@Async
	public void sendEmail(Employee employee) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		MimeMessagePreparator preparator = getMessagePreparator(employee);

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final Employee employee) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom("anil.thakur0909@gmail.com");
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmail()));
				mimeMessage.setText("Dear " + employee.getName() + ", Welcome to EMS <br/> Your username= "
						+ employee.getUsername() + "and Password is=" + employee.getPassword());
				mimeMessage.setSubject("Welcome to EMS");
			}
		};
		return preparator;
	}

	@PostConstruct
	public void init() {
		System.out.println("******************************Inside init method of EmployeeEmail*******************************");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("*******************************Inside destroy method of EmployeeEmail****************************");
	}

}
