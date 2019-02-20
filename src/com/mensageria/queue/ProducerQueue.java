package com.mensageria.queue;

import com.mensageria.Producer;

public class ProducerQueue {

    public static void main(String[] args) throws Exception {

        Producer producer = new Producer("financeiro");

        for(int i = 0; i < 10; i++){
            producer.sendMessage("<pedido><id>".concat(String.valueOf(i)).concat("</id></pedido>"));
        }

        producer.close();
    }
}
