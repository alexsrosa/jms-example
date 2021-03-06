package com.mensageria.queue;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerQueueDLQ {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("DLQ")
                .clientId("serviceQueue1")
                .build();

        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
