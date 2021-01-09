package br.com.alura.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProducerSender {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageProducer producer = session.createProducer((Destination) context.lookup("financeiro"));
		
		for (int i = 1; i <= 10; i++) {
			TextMessage message = session.createTextMessage("<cart><id>" + i + "</id></cart>");
			producer.send(message);
		}
		/*
		 * To define priorities for the messages you can use this method. 
		 * 
		 * producer.send(message, deliveryMode, priority, timeToLive);
		 * message - Message to be sent
		 * deliveryMode - If the message will be retrieved after activeMq restarts
		 * priority - 0 will be delivered after all other priorities, 9 will be delivered first
		 * timeToLive - Time the message will wait in the queue/topic.
		 * 
		 */
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
