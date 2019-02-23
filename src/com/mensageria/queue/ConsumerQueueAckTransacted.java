package com.mensageria.queue;

import com.mensageria.Consumer;

import javax.jms.Session;
import java.util.Scanner;

public class ConsumerQueueAckTransacted {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("financeiro")
                .clientId("serviceQueuefinanceiro1")
                .isTransacted(true)
                .acknowledgeType(Session.SESSION_TRANSACTED)
                .build();

        consumer.messageListenerWithTransacted();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
