package com.hospital.storetax.activemq;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String message) {
//		System.out.println("Sending message " + message);
//		Gson gson=new Gson();
//		String json=gson.toJson(newProduct);
		jmsTemplate.convertAndSend("Outbound",message);
//		System.out.println(requestType);
	}	
}