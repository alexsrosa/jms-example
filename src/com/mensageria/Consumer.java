package com.mensageria;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Objects;

public class Consumer {

    private JmsConnection connection;
    private MessageConsumer messageConsumer;
    private static final Logger logger = LogManager.getLogger(Consumer.class);
    private String look;
    private String clientId;
    private String subsName;
    private String selector;
    private boolean isTransacted;
    private Integer acknowledgeType;

    public static class Builder{

        private String look;
        private String clientId;
        private String subsName;
        private String selector;
        private boolean isTransacted;
        private Integer acknowledgeType;

        public Builder look(String look){
            this.look = look;
            return this;
        }

        public Builder clientId(String clientId){
            this.clientId = clientId;
            return this;
        }

        public Builder subsName(String subsName){
            this.subsName = subsName;
            return this;
        }

        public Builder selector(String selector){
            this.selector = selector;
            return this;
        }

        public Builder isTransacted(boolean isTransacted){
            this.isTransacted = isTransacted;
            return this;
        }

        public Builder acknowledgeType(Integer acknowledgeType){
            this.acknowledgeType = acknowledgeType;
            return this;
        }

        public Consumer build() throws JMSException, NamingException {
            return new Consumer(this);
        }
    }

    private Consumer(Builder builder) throws NamingException, JMSException {
        init(builder);
        initConnection();
        initMessageConsumer();
    }

    private void init(Builder builder) {
        this.look = builder.look;
        this.clientId = builder.clientId;
        this.subsName = builder.subsName;
        this.selector = builder.selector;
        this.isTransacted = builder.isTransacted;
        this.acknowledgeType = builder.acknowledgeType;
    }

    private void initConnection() throws JMSException, NamingException {
        this.connection = new JmsConnection.Builder()
                .clientId(this.clientId)
                .isTransacted(this.isTransacted)
                .acknowledgeType(this.acknowledgeType)
                .build();
    }

    private void initMessageConsumer() throws NamingException, JMSException {
        if(Objects.isNull(this.subsName)){
            this.messageConsumer = connection.getSession().createConsumer(
                    (Destination) connection.getContext().lookup(this.look));
            logger.info("Consumer de Queue inicializado!");
        }else{
            this.messageConsumer = connection.getSession().createDurableSubscriber(
                    (Topic) connection.getContext().lookup(this.look), this.subsName, this.selector, false);
            logger.info("Consumer topic inicializado!");
        }
    }

    public void textMessageListener() throws JMSException {

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

    public void messageListener() throws JMSException {
        this.messageConsumer.setMessageListener(message -> {
            logger.info("Mensagem recebida com sucesso!");
            logger.info(message);
        });
    }

    public void messageListenerWithAcknowledge() throws JMSException {
        this.messageConsumer.setMessageListener(message -> {
            logger.info("Mensagem recebida com sucesso!");
            logger.info(message);

            try {
                message.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }


    public void messageListenerWithTransacted() throws JMSException {
        this.messageConsumer.setMessageListener(message -> {
            logger.info("Mensagem recebida com sucesso!");
            logger.info(message);

            try {
                this.connection.getSession().commit();
            } catch (JMSException e) {
                try {
                    this.connection.getSession().rollback();
                } catch (JMSException e1) {
                    logger.info(e1.getMessage());
                }
                logger.info(e.getMessage());
            }
        });
    }

    public void close() throws JMSException, NamingException {
        connection.close();
    }
}
