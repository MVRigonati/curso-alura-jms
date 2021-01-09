package br.com.alura.jms.queue;

import java.math.BigDecimal;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.alura.jms.model.Cart;

public class ProducerSenderObjMessage {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ActiveMQConnectionFactory factory = (ActiveMQConnectionFactory) context.lookup("ConnectionFactory");
		factory.setTrustAllPackages(true);
		
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageProducer producer = session.createProducer((Destination) context.lookup("financeiro"));
		
		for (int i = 1; i <= 10; i++) {
			Cart cart = new Cart(Integer.toUnsignedLong(i), (i%2 == 0), new BigDecimal(i*100));
			ObjectMessage message = session.createObjectMessage(cart);
			producer.send(message);
		}
		
		session.close();
		connection.close();
		context.close();
		
	}
	
}
