package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService4Object {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("loja")
                .clientId("service4")
                .subsName("assinatura4")
                .build();

        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
