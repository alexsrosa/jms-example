package com.mensageria;

import com.mensageria.modelo.Pedido;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.Serializable;

public class Consumer {

    private JmsConnection connection;
    private MessageConsumer messageConsumer;
    private static final Logger logger = LogManager.getLogger(Consumer.class);

    public Consumer(String look, String clientId) throws NamingException, JMSException {
        this.connection = new JmsConnection(clientId);
        initMessageConsumer(look);
        logger.info("Consumer inicializado!");
    }

    public Consumer(String look, String clientId, String subsName, String selector) throws NamingException, JMSException {
        this.connection = new JmsConnection(clientId);
        initMessageConsumer(look, subsName, selector);
        logger.info("Consumer inicializado!");
    }

    private void initMessageConsumer(String look) throws JMSException, NamingException {
        this.messageConsumer = connection.getSession().createDurableSubscriber(
                (Topic) connection.getContext().lookup(look), "assinatura");
    }

    private void initMessageConsumer(String look, String subsName, String selector) throws JMSException, NamingException {
        this.messageConsumer = connection.getSession().createDurableSubscriber(
                (Topic) connection.getContext().lookup(look), subsName, selector, false);
    }

    public void messageListener() throws JMSException {

        this.messageConsumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage)message;

            try {
                logger.info("Mensagem recebida com sucesso!");
                logger.info(textMessage.getText());
            } catch (JMSException e) {
                logger.error(e.getMessage());
            }
        });
    }

    public void messageListenerObject() throws JMSException {

        this.messageConsumer.setMessageListener(object -> {
            logger.info("Mensagem recebida com sucesso!");
            logger.info(object.toString());
        });
    }

    public void close() throws JMSException, NamingException {
        connection.close();
    }
}
