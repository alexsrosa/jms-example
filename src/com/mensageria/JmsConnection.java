package com.mensageria;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Objects;

public class JmsConnection {

    private Connection connection;
    private Session session;
    private InitialContext context = new InitialContext();
    private static final Logger logger = LogManager.getLogger(JmsConnection.class);
    private String clientId;
    private boolean isTransacted;
    private Integer acknowledgeType;

    public static class Builder{

        private String clientId;
        private boolean isTransacted;
        private Integer acknowledgeType;

        public Builder clientId(String clientId){
            this.clientId = clientId;
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

        public JmsConnection build() throws JMSException, NamingException {
            return new JmsConnection(this);
        }
    }

    private JmsConnection(Builder builder) throws NamingException, JMSException {
        init(builder);
        initConnection();
        initSession();
        logger.info("Jms Connection inicializado!");
    }

    private void init(Builder builder) {
        this.clientId = builder.clientId;
        this.isTransacted = Objects.nonNull(builder.isTransacted) && builder.isTransacted;
        this.acknowledgeType = Objects.nonNull(builder.acknowledgeType)
                ? builder.acknowledgeType : Session.AUTO_ACKNOWLEDGE;
    }

    private void initConnection() throws JMSException, NamingException {
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        this.connection = factory.createConnection();
        this.connection.setClientID(this.clientId);
        connection.start();
    }

    private void initSession() throws JMSException {
        this.session = connection.createSession(this.isTransacted, this.acknowledgeType);
    }

    public Context getContext() {
        return context;
    }

    public Session getSession() {
        return this.session;
    }

    public void close() throws JMSException, NamingException {
        session.close();
        connection.close();
        context.close();
        logger.info("Jms Connection fechado!");
    }
}
