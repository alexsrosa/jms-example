package com.mensageria.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TesteConsumidorReceive {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection conexao = cf.createConnection();

        conexao.start();

        Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila);

        Message message = consumer.receive();
        System.out.println("Recebendo msg: " + message);

        session.close();

        new Scanner(System.in).nextLine();
        conexao.close();
        context.close();
    }
}
