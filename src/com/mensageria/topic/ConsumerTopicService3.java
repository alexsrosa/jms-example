package com.mensageria.topic;

import com.mensageria.Consumer;

import java.util.Scanner;

public class ConsumerTopicService3 {
    public static void main(String[] args) throws Exception {

        Consumer consumer =
                new Consumer("loja", "service3", "assinatura3-selector", "admin=true");
        consumer.messageListener();

        // Mantém conexão
        new Scanner(System.in).nextLine();

        consumer.close();
    }
}
