package com.hospital.storetax.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String jsonMessage) {
		System.out.println("Sending message " + jsonMessage);
		jmsTemplate.convertAndSend("Outbound", jsonMessage);
	}
	
}
