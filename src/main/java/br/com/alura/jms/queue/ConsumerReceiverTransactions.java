package br.com.alura.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerReceiverTransactions {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		//Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		
		MessageConsumer consumer = session.createConsumer((Destination) context.lookup("financeiro"));
		consumer.setMessageListener(message -> {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println(textMessage.getText());
				//message.acknowledge();
				session.commit();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		});
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
