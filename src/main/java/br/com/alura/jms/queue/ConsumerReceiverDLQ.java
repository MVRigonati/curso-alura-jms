package br.com.alura.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerReceiverDLQ {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageConsumer consumer = session.createConsumer((Destination) context.lookup("dlq"));
		consumer.setMessageListener(message -> {
			System.out.println(message);
		});
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
