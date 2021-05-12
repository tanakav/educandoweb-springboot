package com.educandoweb.api.services;

import org.springframework.mail.SimpleMailMessage;

import com.educandoweb.api.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage message);
}
