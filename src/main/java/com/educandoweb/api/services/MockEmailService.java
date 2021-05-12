package com.educandoweb.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulando envio de email....");
		LOG.info(message.toString());
		LOG.info("Email enviado!");
	}

}
