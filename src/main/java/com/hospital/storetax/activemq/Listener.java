package com.hospital.storetax.activemq;



import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class Listener {
	
	@Autowired 
	private Consumer consumer;
	
	@JmsListener(destination = "inbound")
	public void receiveMessage(final String jsonMessage) throws JMSException {
		System.out.println("Received message " + jsonMessage);
		consumer.sendMessage(jsonMessage);
	}
	
}
