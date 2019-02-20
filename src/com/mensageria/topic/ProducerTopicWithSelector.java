package com.mensageria.topic;

import com.mensageria.Producer;

import java.util.HashMap;
import java.util.Map;

public class ProducerTopicWithSelector {

    public static void main(String[] args) throws Exception {

        Producer producer = new Producer("loja");

        Map<String, Boolean> params = new HashMap<>();
        params.put("admin", true);

        for(int i = 0; i <= 1; i++){
            producer.sendMessage("<pedido><id>"+i+"</id></pedido>", params);
        }

        producer.close();
    }
}
