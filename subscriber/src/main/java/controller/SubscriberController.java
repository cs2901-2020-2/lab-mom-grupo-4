package controller;

import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/subscribe")
@CrossOrigin(origins = "*")
public class SubscriberController {
    Channel channel;
    Connection connection;
    List<String> response = new ArrayList<String>();

    private void startConnectionWithRabbitMQ(String channelName) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();

        channel.queueDeclare(channelName, false, false, false, null);
    }

    @GetMapping("/{channelName}")
    public List<String> receiveMessage(@PathVariable String channelName) throws IOException, TimeoutException {
        startConnectionWithRabbitMQ(channelName);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            response.add(message);
        };
        channel.basicConsume(channelName, true, deliverCallback, consumerTag -> { });

        return response;
    }
}
