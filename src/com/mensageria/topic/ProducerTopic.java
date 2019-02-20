package com.mensageria.topic;

import com.mensageria.Producer;

public class ProducerTopic {

    public static void main(String[] args) throws Exception {

        Producer producer = new Producer("loja");

        for(int i = 0; i <= 10; i++){
            producer.sendMessage("<pedido><id>"+i+"</id></pedido>");
        }

        producer.close();
    }
}
