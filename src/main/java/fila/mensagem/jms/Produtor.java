package fila.mensagem.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import fila.mensagem.model.Veiculo;

@Component
public class Produtor {

	// Definindo informações para conexão com ActiveMQ
	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String subject = "FILA";

	public void enviarParaFila(Veiculo veiculo) {
		try {
			// Obtem uma conexão com o servidor JMS

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Criando sessão para envio de mensagens.
			// Destination é a fila para onde as mensagens serão enviadas.
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);

			MessageProducer producer = session.createProducer(destination);

			// instancia um XStream
			XStream xstream = new XStream(new StaxDriver());

			TextMessage message = session.createTextMessage(xstream.toXML(veiculo));
			producer.send(message);
			System.out.println("[Lista de Classificados] - Veículo cadastrado (" + veiculo.getDataPublicacao() + ")");
			connection.close();

		} catch (JMSException e) {
			System.out.println("[Lista de Classificados] - Não foi possível conectar com o servidor JMS");
		}

	}
}
