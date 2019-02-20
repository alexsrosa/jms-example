package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService2 {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer("loja", "service2","assinatura2");
        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
