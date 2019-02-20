package com.mensageria.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class QueueReceiverTest {

    public static void main(String[] args) throws Exception {
        InitialContext ctx = new InitialContext();
        QueueConnectionFactory cf = (QueueConnectionFactory)ctx.lookup("ConnectionFactory");
        QueueConnection conexao = cf.createQueueConnection();
        conexao.start();

        QueueSession sessao = conexao.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue fila = (Queue) ctx.lookup("financeiro");
        QueueReceiver receiver = sessao.createReceiver(fila );

        Message message = receiver.receive();
        System.out.println(message);

        new Scanner(System.in).nextLine();

        sessao.close();
        conexao.close();
        ctx.close();
    }
}
