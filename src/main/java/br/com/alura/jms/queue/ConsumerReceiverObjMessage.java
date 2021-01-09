package br.com.alura.jms.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.alura.jms.model.Cart;

public class ConsumerReceiverObjMessage {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ActiveMQConnectionFactory factory = (ActiveMQConnectionFactory) context.lookup("ConnectionFactory");
		factory.setTrustAllPackages(true);
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageConsumer consumer = session.createConsumer((Destination) context.lookup("financeiro"));
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				ObjectMessage objMessage = (ObjectMessage) message;
				try {
					Cart cart = (Cart) objMessage.getObject();
					System.out.println(cart.toString());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
