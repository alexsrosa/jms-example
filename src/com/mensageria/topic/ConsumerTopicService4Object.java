package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService4Object {
    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer("loja", "service4","assinatura4");
        consumer.messageListenerObject();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
