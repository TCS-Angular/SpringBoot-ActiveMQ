package com.send;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.send.model.Product;

@SpringBootApplication
@EnableJms
public class MessageSenderApplication {
	
	private static final String MESSAGE_QUEUE = "message_queue";

	public static void main(String[] args) {
		// launch the application
		ConfigurableApplicationContext context = SpringApplication.run(MessageSenderApplication.class, args);
		
		// to get the jms template bean reference 
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
		for(int i = 1; i<= 10; i++) {
			Product product = new Product();
			product.setProductId(i);
			product.setName("Washing Machine");
			product.setQuantity(i);
			
			// send a message 
			System.out.println("Sending a product " + i);
			jmsTemplate.convertAndSend(MESSAGE_QUEUE, product);
			
		}
		
		
	}

}
