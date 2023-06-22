package fila.mensagem.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import fila.mensagem.model.Veiculo;
import fila.mensagem.repository.VeiculosRepositorio;

@Component
public class Consumidor {

	@Autowired
	private VeiculosRepositorio veiculosRepositorio;

	// Definindo informações para conexão com ActiveMQ
	private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String subject = "FILA";

	public void consumir() {
		try {
			// Cria a fabrica de conexao e inicia a conexao
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Cria sessão para consumir a mensagem
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Cria a fila destino (Queue)
			Destination destination = session.createQueue(subject);

			// Cria uma MessageConsumer da Session do Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive(500);

			TextMessage textMessage = (TextMessage) message;
			if (textMessage != null && textMessage.getText().toLowerCase().contains("xml")) {
				recuperaMensagemObjeto(textMessage);
			} else {
				System.out.println("[Lista de Classificados] - Sem resultados");
			}

			consumer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			System.out.println("[Lista de Classificados] - Não foi possível conectar com o servidor JMS");
		}
	}

	private void recuperaMensagemObjeto(TextMessage textMessage) {
		try {
			TextMessage msg = (TextMessage) textMessage;

			// Instancia um XStream
			XStream xstream = new XStream(new StaxDriver());
			xstream.addPermission(AnyTypePermission.ANY);

			Veiculo veiculo = (Veiculo) xstream.fromXML(msg.getText());

			veiculosRepositorio.save(veiculo);

		} catch (JMSException e) {
			System.out.println("[Lista de Classificados] - Não foi possível recuperar resultados");
		}
	}

}
