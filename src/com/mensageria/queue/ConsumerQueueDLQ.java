package com.mensageria.queue;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerQueueDLQ {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer("DLQ", "serviceQueue1");
        consumer.messageListenerDLQ();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
