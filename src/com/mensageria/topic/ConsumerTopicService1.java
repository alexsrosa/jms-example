package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService1 {
    public static void main(String[] args) throws Exception {

        Consumer consumerTopic = new Consumer("loja", "service1", "assinatura1");
        consumerTopic.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumerTopic.close();
    }
}
