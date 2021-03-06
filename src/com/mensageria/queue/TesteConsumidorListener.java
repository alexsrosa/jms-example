package com.mensageria.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

import static java.lang.System.out;

public class TesteConsumidorListener {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila );

        consumer.setMessageListener(message -> {

            TextMessage textMessage = (TextMessage)message;

            try {
                out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}
