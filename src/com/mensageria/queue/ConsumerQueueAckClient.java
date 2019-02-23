package com.mensageria.queue;

import com.mensageria.Consumer;

import javax.jms.Session;
import java.util.Scanner;

public class ConsumerQueueAckClient {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("financeiro")
                .clientId("serviceQueuefinanceiro1")
                .acknowledgeType(Session.CLIENT_ACKNOWLEDGE)
                .build();

        consumer.messageListenerWithAcknowledge();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
