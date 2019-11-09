package com.tsw.xxt;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.56.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        Map<String,Object> heads = new HashMap<>();
        heads.put("name","tanshiwei");
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .expiration("10000")
                .contentEncoding("UTF-8")
                .headers(heads)
                .build();


        for (int i=0;i<5;i++){
            String msg = "Hello,world ! this is the "+i+" message !";
            channel.basicPublish("", "routing-key-01", properties, msg.getBytes());
            System.out.println("message "+i+" generated");
        }

        channel.close();
        connection.close();

    }


}
