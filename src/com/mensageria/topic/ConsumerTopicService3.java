package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService3 {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer.Builder()
                .look("loja")
                .clientId("service3")
                .subsName("assinatura3-selector")
                .selector("admin=true")
                .build();

        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
