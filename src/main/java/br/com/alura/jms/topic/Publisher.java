package br.com.alura.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageProducer publisher = session.createProducer((Destination) context.lookup("loja"));
		
		TextMessage message = session.createTextMessage("[{cart:{id: 1}}]");
		publisher.send(message);
		TextMessage messageNotEbook = session.createTextMessage("[{cart:{id: 2}, ebook: false}]");
		messageNotEbook.setBooleanProperty("ebook", false);
		publisher.send(messageNotEbook);
		TextMessage messageEbook = session.createTextMessage("[{cart:{id: 3}, ebook: true}]");
		messageEbook.setBooleanProperty("ebook", true);
		publisher.send(messageEbook);
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
