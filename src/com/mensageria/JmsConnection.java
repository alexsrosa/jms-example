package com.mensageria;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsConnection {

    private Connection connection;
    private Session session;
    private InitialContext context = new InitialContext();
    private static final Logger logger = LogManager.getLogger(JmsConnection.class);

    public JmsConnection(String clientId) throws NamingException, JMSException {
        initConnection(clientId);
        initSession();
        logger.info("Jms Connection inicializado!");
    }

    private void initConnection(String clientId) throws JMSException, NamingException {
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        this.connection = factory.createConnection();
        this.connection.setClientID(clientId);
        connection.start();
    }

    private void initSession() throws JMSException {
        this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
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
