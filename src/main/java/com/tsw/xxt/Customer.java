package com.tsw.xxt;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.56.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String queueName = "queue001";
        channel.queueDeclare(queueName, true, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel);
        channel.basicConsume(queueName, true, consumer);



        channel.close();
        connection.close();

    }


}
