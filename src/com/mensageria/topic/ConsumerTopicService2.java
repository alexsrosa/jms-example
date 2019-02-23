package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService2 {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("loja")
                .clientId("service2")
                .subsName("assinatura2")
                .build();
        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
