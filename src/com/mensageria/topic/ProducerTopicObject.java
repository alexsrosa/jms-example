package com.mensageria.topic;

import com.mensageria.Producer;
import com.mensageria.modelo.Pedido;
import com.mensageria.modelo.PedidoFactory;

public class ProducerTopicObject {

    public static void main(String[] args) throws Exception {

        Producer producer = new Producer("loja");

        for(int i = 0; i <= 10; i++){
            Pedido pedido = new PedidoFactory().geraPedidoComValores();
            producer.sendMessage(pedido);
        }

        producer.close();
    }
}
