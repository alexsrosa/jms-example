package com.mensageria;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Map;

public class Producer {

    private JmsConnection connection;
    private MessageProducer messageProducer;
    private static final Logger logger = LogManager.getLogger(Producer.class);

    public Producer(String look) throws NamingException, JMSException {
        this.connection = new JmsConnection("");
        initMessageProducer(look);
        logger.info("Producer inicializado!");
    }

    private void initMessageProducer(String look) throws JMSException, NamingException {
        this.messageProducer = connection.getSession().createProducer(
                (Destination) connection.getContext().lookup(look));
    }

    public void sendMessage(Serializable message) throws JMSException {
        messageProducer.send(connection.getSession().createObjectMessage(message));
        logger.info("Mensagem enviada com sucesso!");
        logger.info(message);
    }

    public void sendMessage(String message) throws JMSException {
        messageProducer.send(connection.getSession().createTextMessage(message));
        logger.info("Mensagem enviada com sucesso!");
        logger.info(message);
    }

    public void sendMessage(String message, Map<String, Boolean> propriedade) throws JMSException {
        TextMessage textMessage = connection.getSession().createTextMessage(message);

        propriedade.forEach((key, value) -> {
            try {
                textMessage.setBooleanProperty(key, value);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        messageProducer.send(textMessage);
        logger.info("Mensagem enviada com sucesso!");
        logger.info(message);
    }

    public void close() throws JMSException, NamingException {
        connection.close();
    }
}
